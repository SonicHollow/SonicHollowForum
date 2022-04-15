package com.sonichollow.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.mapper.PostMapper;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.service.IPostService;
import com.sonichollow.forum.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * post的业务层
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> {
    @Autowired
    private PostMapper postMapper;
//    @Autowired
//    private JedisPool jedisPool;
//
//    @Autowired
//    private TaskExecutor taskExecutor;


    @Autowired
    private UserMapper userMapper;

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

    public void updatePost(Post post){

        UpdateWrapper<Post> wrapper = new UpdateWrapper<Post>();
        wrapper.eq("text",post.getText());
        post.setModifiedUser(post.getUsername());
        post.setModifiedTime(new Date());
        postMapper.update(post,wrapper);

    }

    //未测试
//    public String clickLike(int pid, String username) {
//        Jedis jedis = jedisPool.getResource();
//        //被用户点赞
//        jedis.sadd(pid+":like", username);
//        //增加用户获赞数
//        jedis.hincrBy("vote",username+"",1);
//        String result = String.valueOf(jedis.scard(pid+":like"));
//
//        if(jedis!=null){
//            jedisPool.returnResource(jedis);
//        }
//        return result;
//    }



}
