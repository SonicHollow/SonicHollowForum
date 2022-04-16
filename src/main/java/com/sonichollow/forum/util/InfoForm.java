package com.sonichollow.forum.util;

public class InfoForm {
    private String user;
    private String content;

    public InfoForm(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "InfoForm{" +
                "user='" + user + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
