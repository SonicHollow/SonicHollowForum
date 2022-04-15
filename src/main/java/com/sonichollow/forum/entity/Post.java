package com.sonichollow.forum.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    //    @TableId(value ="pid",type = IdType.AUTO)
    @TableId
    private Integer pid;                //'帖子id'
    private String username;            //'用户名'
    private String postName;            //'帖子名称'
    private String text;                //'帖子内容'
    private Integer likes;              //'点赞'
    private Integer hates;              //'踩'
    private String accessPermission;    //'访问权限: only-me/public/private'
    private Integer accessTimes;        //'访问次数'
    @TableLogic
    private Integer isDelete;           //'是否删除：0-未删除，1-已删除'
    private Integer isSelect;           //'是否精选：0-未精选，1-精选'
    private Integer isAbove;            //'是否精选：0-不置顶，1-置顶'
    // 标签max=8
    private String tag1;
    private String tag2;
    private String tag3;
    private String tag4;
    private String tag5;
    private String tag6;
    private String tag7;
    private String tag8;
    //备用字段
    private String feature1;
    private String feature2;
    private String feature3;
    private String feature4;
    private String feature5;
    private String feature6;
    private String feature7;
    private String feature8;
    //日志
    private String createdUser;        //'日志-创建人'
    private Date createdTime;          //'日志-创建时间'
    private String modifiedUser;       //'日志-最后修改执行人'
    private Date modifiedTime;         //'日志-最后修改时间'

}
