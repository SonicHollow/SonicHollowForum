package com.sonichollow.forum.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 贴子的实体类
 */
public class Post extends BaseEntity implements Serializable {

    private Integer pid;// `pid` INT NULL AUTO_INCREMENT COMMENT '帖子编号',
    private String content;// `content` VARCHAR(300) NOT NULL COMMENT '内容',
    private Date replyTime;//`reply_time` DATE NOT NULL COMMENT '回复时间',
    private Integer replyCount;//`reply_count` INT NULL DEFAULT 0 COMMENT '回复数',

    //外键
//    private User user;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Post post = (Post) o;
        return Objects.equals(pid, post.pid) && Objects.equals(content, post.content) && Objects.equals(replyTime, post.replyTime) && Objects.equals(replyCount, post.replyCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pid, content, replyTime, replyCount);
    }

    @Override
    public String toString() {
        return "Post{" +
                "pid=" + pid +
                ", content='" + content + '\'' +
                ", replyTime=" + replyTime +
                ", replyCount=" + replyCount +
                '}';
    }
}
