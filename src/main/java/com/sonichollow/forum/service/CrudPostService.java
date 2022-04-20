package com.sonichollow.forum.service;

import com.sonichollow.forum.entity.Post;


import java.util.List;

public interface CrudPostService {
    //post id
    Post getAccp(int pid);

    //查看所有post
    List<Post> listAllAccp();

    //根据id删除post
    void deletepostById(int pid);

}
