package com.sonichollow.forum.util;


import com.sonichollow.forum.dto.UserDTO;

public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static UserDTO getUser(){
        return tl.get();
    }

}
