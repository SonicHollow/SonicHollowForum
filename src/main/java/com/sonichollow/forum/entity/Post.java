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
     * 帖子id
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 帖子名称
     */
    private String postName;

    /**
     * 帖子内容
     */
    private String text;

    /**
     * 点赞
     */
    private Integer likes;

    /**
     * 踩
     */
    private Integer hates;

    /**
     * 访问权限: only-me/public/private
     */
    private String accessPermission;

    /**
     * 访问次数
     */
    private Integer accessTimes;

    /**
     * 是否删除：0-未删除，1-已删除
     */
    private Integer isDelete;

    /**
     * 是否精选：0-未精选，1-精选
     */
    private Integer isSelect;

    /**
     * 是否精选：0-不置顶，1-置顶
     */
    private Integer isAbove;

    /**
     *
     */
    private Integer viewCount;

    /**
     * 0-未点赞，1-已点赞
     */
    private Boolean isLike;

    /**
     *
     */
    private String tag3;

    /**
     *
     */
    private String tag4;

    /**
     *
     */
    private String tag5;

    /**
     *
     */
    private String tag6;

    /**
     *
     */
    private String tag7;

    /**
     *
     */
    private String tag8;

    /**
     *
     */
    private String feature1;

    /**
     *
     */
    private String feature2;

    /**
     *
     */
    private String feature3;

    /**
     *
     */
    private String feature4;

    /**
     *
     */
    private String feature5;

    /**
     *
     */
    private String feature6;

    /**
     *
     */
    private String feature7;

    /**
     *
     */
    private String feature8;

    /**
     * 日志-创建人
     */
    private String createdUser;

    /**
     * 日志-创建时间
     */
    private Date createdTime;

    /**
     * 日志-最后修改执行人
     */
    private String modifiedUser;

    /**
     * 日志-最后修改时间
     */
    private Date modifiedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
