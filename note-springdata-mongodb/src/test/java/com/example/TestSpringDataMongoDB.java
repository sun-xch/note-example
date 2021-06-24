package com.example;

import com.example.mongo.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NoteSpringdataMongodbApplication.class})
public class TestSpringDataMongoDB {

    /**
     * Spring Data MongoDB提供了通用客户端类型MongoTemplate
     * 可以快速实现数据读写操作
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testInsert(){
        UserInfo userInfo = new UserInfo();
        //id:字符串类型或ObjectId类型可以自动生成，其他数据类型不可自动生成
        userInfo.setLoginName("test06240001");
        userInfo.setNickName("test06240001");
        userInfo.setRealName("测试用户06240001");
        userInfo.setPhone("13006240001");
        userInfo.setMail("06240001@123.com");
        userInfo.setSex("1");
        userInfo.setBirthday(LocalDate.now());
        userInfo.setAge(10);
        userInfo.setAvatar("www.123123serfsfdswesf443fs4353fd06240001.com");
        userInfo.setAddress("测试用户06240001的地址信息");
        userInfo.setWork("Java");
        userInfo.setNativePlace("山东");
        userInfo.setCreditRating("10");
        userInfo.setRemarks("测试用户06240001的备注信息");
        userInfo.setFlag("T");
        userInfo.setCourses(Arrays.asList("java","vue"));
        UserInfo insert = mongoTemplate.insert(userInfo);
        System.out.println(insert.toString());
    }
}
