package com.diao.mongodb_s_m2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.diao.mongodb_s_m2.dao.MongoDBBaseDao;
import com.diao.mongodb_s_m2.model.User;


public class BaseTestDao extends BaseTest {
    @Resource(name = "mongoDBBaseDao")
    MongoDBBaseDao mongoDBBaseDao;

    /**
     * 插入单条数据，id自定义
     */
    @Test
    public void testAdd() {
        User user = new User();
        Date creatTime = new Date();
        user.setCreateTime(creatTime);
        user.setAge(10);
        user.setUsername("福東江a");
        this.mongoDBBaseDao.add(user);
    }

    /**
     * 插入1000条数据，id自定义
     */
    @Test
    public void testAddCollection() {
        List<User> userList = new ArrayList<User>();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 100; i++) {
                User user = new User();
                Date creatTime = new Date();
                user.setCreateTime(creatTime);
                user.setAge(10);
                user.setUsername("冊南");
                userList.add(user);
            }
        }
        this.mongoDBBaseDao.addCollection(User.class, userList);
    }

    @Test
    public void testFindAll() {
        //spring-data mongoDB给实体类属性赋值时并没有调用set()方法,hard fuck
        List<User> userList = this.mongoDBBaseDao.findAll(User.class);
        for (User user : userList) {
            System.out.println("id:" + user.getId() + " username:" + user.getUsername() + "   age:"
                    + user.getAge() + " createTime:" + user.getCreateTime());
        }
        System.out.println("获取全部的数据----------------------");
    }

    @Test
    public void testFindById() {
        User user = this.mongoDBBaseDao.findById(User.class, "5729a3e8ed9cbeb5580f2191");
        if(user!=null){
            System.out.println(user.getUsername());
        }else{
            System.out.println("no such record!");
        }
        System.out.println("获取单个对象----------------------");
    }

    @Test
    public void testUpdate() {
        User user = this.mongoDBBaseDao.findById(User.class, "5729a3e8ed9cbeb5580f2191");
        user.setUsername("反倒是淮");
        this.mongoDBBaseDao.saveOrUpdate(user);
        User newUser = this.mongoDBBaseDao.findById(User.class, "5729a3e8ed9cbeb5580f2191");
        System.out.println(newUser.getUsername().equals("反倒是淮"));
        System.out.println("修改数据成功");
        this.mongoDBBaseDao.saveOrUpdate(user);
    }

    @Test
    public void testRemove() {
        User user = this.mongoDBBaseDao.findById(User.class, "5729a3e8ed9cbeb5580f2191");
        this.mongoDBBaseDao.remove(user);
        User oldUser = this.mongoDBBaseDao.findById(User.class, "5729a3e8ed9cbeb5580f2191");
        if (oldUser == null) {
            System.out.println(oldUser == null);
            System.out.println("删除对象成功");
        }
        this.mongoDBBaseDao.add(user);
    }

    @Test
    public void testCount() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("username").is("冊南");
        query.addCriteria(criteria);
        long total = this.mongoDBBaseDao.count(User.class, query);
        System.out.println("用户总数:" + total);
    }
}
