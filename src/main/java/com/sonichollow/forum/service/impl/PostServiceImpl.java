package com.sonichollow.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.service.PostService;
import com.sonichollow.forum.mapper.PostMapper;
import com.sonichollow.forum.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

import static com.sonichollow.forum.util.RedisUtil.POST_LIKED_KEY;
/**
 * @author admin
 * @description  service implementation of post module
 * @createDate 2022-04-18 16:21:26
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService{
    @Resource
    private PostMapper postMapper;

    @Resource
    private UserMapper userMapper;


    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    /**
     *  publish post
     * @param post
     * @return post id
     */
    @Override
    public int PublishPost(Post post){
        //construct the posts
        post.setPostName(post.getPostName());
        post.setUsername(post.getUsername());
        post.setModifiedUser(post.getUsername());
        post.setText(post.getText());
        post.setCreatedTime(new Date());
        post.setLikes(0);


        //insert the posts
        postMapper.insert(post);
        System.out.println(post.getPid());



        return post.getPid();
    }

    /**
     * edit the content of the posts
     * @param post
     */
    @Override
    public void updatePost(Post post){
        UpdateWrapper<Post> wrapper = new UpdateWrapper<Post>();
        wrapper.eq("text",post.getText());
        post.setModifiedUser(post.getUsername());
        post.setModifiedTime(new Date());
        postMapper.update(post,wrapper);
    }

    /**
     *  like the posts
     * @param pid
     * @return
     */
    @Override
    public Result clickLikes(int pid, int uid) {
        // check whether the log-in user has like the posts
        String key = POST_LIKED_KEY + uid;
        Double score = stringRedisTemplate.opsForZSet().score(key, String.valueOf(uid));
        // if not - user could like the posts
        if (score == null) {
            boolean isSuccess = update().setSql("likes = likes + 1").eq("pid", pid).update();
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().add(key, String.valueOf(uid), System.currentTimeMillis());
            }
        } else {
            // cancel likes
            boolean isSuccess = update().setSql("likes = likes - 1").eq("pid", pid).update();
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().remove(key, String.valueOf(uid));
            }
        }
        return Result.ok();
    }

    /**
     * Get the content of a post by its post id
     * @param pid
     * @return the content of a post
     * @throws NullPointerException
     */
    @Override
    public Post getPostByPid(int pid) throws NullPointerException{
        //update the number of views
        postMapper.updateViewCount(pid);
        Post post = postMapper.getAllByPid(pid);
        // get the number of likes
        String key = pid + POST_LIKED_KEY;
        int likes = stringRedisTemplate.opsForZSet().zCard(key).intValue();
        post.setLikes(likes);
        return post;
    }

    /**
     * Determine whether a user likes a post or not
     * @param post
     * @return true: Users have liked the post
     *         false: Users do not like posts
     */
    @Override
    public boolean getLikeStatus(Post post,String username) {
        int uid = userMapper.getUser(username).getUid();

        // check if the user likes the posts or not
        String key = "post:like"+post.getPid();
        Double score = stringRedisTemplate.opsForZSet().score(key, String.valueOf(uid));
        post.setIsLike(score != null);
        if(score != null){
            post.setIsLike(true);
            return true;
        }else
        {
            return false;
        }
    }
}