package com.sonichollow.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sonichollow.forum.dto.UserDTO;
import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.PostService;
import com.sonichollow.forum.mapper.PostMapper;
import com.sonichollow.forum.dto.Result;
import com.sonichollow.forum.util.UserHolder;
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
 * @description 针对表【post】的数据库操作Service实现
 * @createDate 2022-04-18 16:21:26
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService{
    @Resource
    private PostMapper postMapper;


    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int PublishPost(Post post){
        //构造帖子
        post.setPostName(post.getPostName());
        post.setUsername(post.getUsername());
        post.setModifiedUser(post.getUsername());
        post.setText(post.getText());
        post.setCreatedTime(new Date());
        post.setLikes(0);
        post.setHates(0);
        post.setIsDelete(0);

        //插入帖子
        postMapper.insert(post);
        System.out.println(post.getPid());

        //更新用户发帖--关联到User


        return post.getPid();
    }

    @Override
    public void updatePost(Post post){
        UpdateWrapper<Post> wrapper = new UpdateWrapper<Post>();
        wrapper.eq("text",post.getText());
        post.setModifiedUser(post.getUsername());
        post.setModifiedTime(new Date());
        postMapper.update(post,wrapper);

    }


    @Override
    public Result clickLikes(int pid) {
        //获取登录用户的uid
        int uid = 1;
        //有可以识别的uid的时候可以替换上一行
//        int uid = UserHolder.getUser().getId().intValue();
        // 判断当前登录用户是否已经点赞
        String key = POST_LIKED_KEY + uid;
        Double score = stringRedisTemplate.opsForZSet().score(key, String.valueOf(uid));
        //如果未点赞，可以点赞----pid被uid点赞
        //将帖子中的用户点赞信息存入数据库
        if (score == null) {
            // 未点赞
            // 数据库点赞数 + 1
            boolean isSuccess = update().setSql("likes = likes + 1").eq("pid", pid).update();
            // 保存用户到Redis的set集合  zadd key value score
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().add(key, String.valueOf(uid), System.currentTimeMillis());
            }
        } else {
            // 如果已点赞，取消点赞
            // 数据库点赞数 -1
            boolean isSuccess = update().setSql("likes = likes - 1").eq("pid", pid).update();
            // 把用户从Redis的set集合移除
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().remove(key, String.valueOf(uid));
            }
        }

        return Result.ok();
    }


    @Override
    public Post getPostByPid(int pid) throws NullPointerException{
        //更新浏览数
        postMapper.updateViewCount(pid);
        Post post = postMapper.getAllByPid(pid);
        //获取点赞数
        int uid = 1;
        //有可以识别的uid的时候可以替换上一行
//        int uid = UserHolder.getUser().getId().intValue();
        //设置点赞数
        String key = POST_LIKED_KEY + uid;
        int likes = stringRedisTemplate.opsForZSet().zCard(key).intValue();
        post.setLikes(likes);
        return post;
    }

    //某用户是否赞过某帖子
    @Override
    public boolean getLikeStatus(Post post) {
        // 获取登录用户---若能查询可以添加下方注释内容
//        UserDTO user = UserHolder.getUser();
//        if (user == null) {
//            // 用户未登录，无需查询
//            return;
//        }
        //若能查询到用户id,可以添加下一行
        int uid = 1;
//        int uid = user.getId().intValue();
        //判断当前用户是否点赞情况
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