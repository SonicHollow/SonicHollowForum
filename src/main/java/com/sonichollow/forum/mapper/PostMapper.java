package com.sonichollow.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sonichollow.forum.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PostMapper extends BaseMapper<Post> {
    int updateViewCount(@Param("pid") Integer viewCount);
    Post getAllByPid(@Param("pid") Integer pid);

    Post selectByPid(int pid);

    void deletePost(int pid);

    List<Post> selectAllPost();
}