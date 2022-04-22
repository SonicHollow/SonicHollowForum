package com.sonichollow.forum.service;

import com.sonichollow.forum.entity.Post;


import java.util.List;

public interface CrudPostService {
    // post id
    Post getPid(int pid);

    //查看所有post
    List<Post> listAllPost();

    //根据id删除post
    void deletePostById(int pid);

}
