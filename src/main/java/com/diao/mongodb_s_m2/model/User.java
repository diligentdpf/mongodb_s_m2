package com.diao.mongodb_s_m2.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
    private String id;
    private String username;
    private int age;
    private Date createTime;
    private String date_str;

    public User() {
        
    }
    
    public String getDate_str() {
        this.date_str=format.format(this.createTime);
        return date_str;
    }

    public void setDate_str(String date_str) {
        this.date_str = date_str;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        this.date_str=format.format(createTime);
    }

    @Override
    public String toString() {
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "User [id=" + id + ", username=" + username + ", age=" + age + ", createTime="
                + createTime + "]";
    }
}
