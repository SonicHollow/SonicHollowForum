package com.sonichollow.forum.mapper;

import com.sonichollow.forum.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 帖子模块的持久层接口
 */
public interface PostMapper {
    /**
     * 插入帖子的数据
     * @param post
     * @return
     */
    Integer insertPost(Post post);

    void updateReplyTime(int pid);
    void updateReplyCount(int pid);

    Post getPostByPid(int pid);
    Integer EditPost (Post post) ;

}
