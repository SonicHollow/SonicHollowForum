package com.sonichollow.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sonichollow.forum.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PostMapper extends BaseMapper<Post> {
}
