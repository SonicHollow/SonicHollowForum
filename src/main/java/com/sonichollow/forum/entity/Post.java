package com.sonichollow.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @TableName post
 */
@TableName(value ="post")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Post implements Serializable {
    /**
     * post ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     *
     */
    private String username;

    /**
     * the content of post
     */
    private String text;

    /**
     * the number of likes
     */
    private Integer likes;

    /**
     * the number of views
     */
    private Integer viewCount;

    /**
     * 0- unlike the posts，1- like the posts
     */
    private Boolean isLike;

    /**
     * log - created time
     */
    private Date createdTime;

    /**
     * log - Last modifier
     */
    private String modifiedUser;

    /**
     * log - Last Modified time
     */
    private Date modifiedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}