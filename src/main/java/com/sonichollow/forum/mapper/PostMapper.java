package com.sonichollow.forum.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sonichollow.forum.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author admin
 * @description 针对表【post】的数据库操作Mapper
 * @createDate 2022-04-18 16:21:26
 * @Entity com.sonichollow.forum.entity.Post
 */
public interface PostMapper extends BaseMapper<Post> {
    int updateViewCount(@Param("pid") Integer viewCount);
    Post getAllByPid(@Param("pid") Integer pid);
}
