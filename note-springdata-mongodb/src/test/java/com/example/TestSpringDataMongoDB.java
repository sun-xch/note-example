package com.example;

import com.example.mongo.entity.UserInfo;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NoteSpringdataMongodbApplication.class})
public class TestSpringDataMongoDB {

    /**
     * Spring Data MongoDB提供了通用客户端类型MongoTemplate
     * 可以快速实现数据读写操作
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 聚合查询
     */
    @Test
    public void aggTest(){

        TypedAggregation<UserInfo> aggregation = TypedAggregation.newAggregation(UserInfo.class,Aggregation.group().count().as("count"));

        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, Map.class);

        Map map = results.getUniqueMappedResult();

        System.out.println(map);

    }


    /**
     * 分页
     */
    @Test
    public void pageableTest(){
        Query query = new Query();
        query.with(
                PageRequest.of(0,10)    //页码从0开始
        );
        List<UserInfo> userInfos = mongoTemplate.find(query, UserInfo.class);
        for (UserInfo userInfo : userInfos){
            System.out.println(userInfo);
        }

    }

    /**
     * 排序
     */
    @Test
    public void sortTest(){
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC,"_id"));
        List<UserInfo> userInfos = mongoTemplate.find(query, UserInfo.class);
        for (UserInfo userInfo : userInfos){
            System.out.println(userInfo);
        }
    }

    /**
     * 复合条件查询
     */
    @Test
    public void findTest(){
        Query query = new Query();
        Criteria criteria = new Criteria();
        // 查询login_name是zhangsan 并且phone是13006240001
//        criteria.andOperator(
//                Criteria.where("login_name").is("zhangsan"),
//                Criteria.where("phone").is("13006240001")
//        );
        // 查询login_name是zhangsan 或 phone是13006240001
//        criteria.orOperator(
//                Criteria.where("login_name").is("zhangsan"),
//                Criteria.where("phone").is("13006240001")
//        );

        // 查询login_name是zhangsan 并且phone是13006240001 或 login_name是lisi
        criteria.orOperator(
            new Criteria().andOperator(
                Criteria.where("login_name").is("zhangsan"),
                Criteria.where("phone").is("13006240001")
            ),
            new Criteria().andOperator(
                Criteria.where("login_name").is("lisi")
            )
        );


        query.addCriteria(criteria);
        List<UserInfo> userInfos = mongoTemplate.find(query, UserInfo.class);
        for (UserInfo userInfo : userInfos){
            System.out.println(userInfo);
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void findByQuery(){
        Query query = new Query();
        query.addCriteria(
            //Criteria.where("login_name").is("zhangsan") //等值判断
            //Criteria.where("age").lt(12).gt(9) //范围判断
            //Criteria.where("name").exists(true) //属性是否存在
            Criteria.where("login_name").regex("l") //正则查询，包含"l" 不需要鞋正则的起始结束标记 /.../  直接写正则就行
        );

        List<UserInfo> userInfos = mongoTemplate.find(query, UserInfo.class);
        for (UserInfo userInfo : userInfos){
            System.out.println(userInfo);
        }

    }

    /**
     * 查询单一数据
     */
    @Test
    public void findOne(){
        // 根据主键查询数据 传递的类对象，用来描述访问的集合是什么。
        UserInfo byId = mongoTemplate.findById("60e30355c618b70a10a4ac37", UserInfo.class);
        System.out.println("byId:" + byId);

        // 根据查询条件，查询第一条数据。相当于mongodb中的 db.findOne({})

        Query query = new Query();
        query.addCriteria(Criteria.where("login_name").is("zhangsan"));

        UserInfo one = mongoTemplate.findOne(query, UserInfo.class);
        System.out.println("findOne:" + one);
    }

    /**
     * 查询全部数据
     */
    @Test
    public void findAllTest(){
        List<UserInfo> all = mongoTemplate.findAll(UserInfo.class);
        for (UserInfo user : all){
            System.out.println(user);
        }
    }

    /**
     * 条件删除，根据匹配条件query删除数据
     */
    @Test
    public void removeQuery(){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("60e2fd8ac618b70a10a4ab67"));//字段名
        // 传递实体类型类对象的时候，会携带类型中定义的所有注解数据，可以做注解配置的转换
        mongoTemplate.remove(query, UserInfo.class);
    }

    /**
     * 删除数据  根据Java对象删除数据   spring Data MongoDB找到这个对象类型对应的集合，使用主键作为条件删除
     */
    @Test
    public void removeTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId("202107050001");
        userInfo.setLoginName("111");
        userInfo.setNickName("111");
        userInfo.setRealName("111");
        userInfo.setPhone("111");
        userInfo.setMail("11111");
        userInfo.setSex("1");
        DeleteResult remove = mongoTemplate.remove(userInfo);
        System.out.println(remove.toString());
    }

    /**
     * 更新 如果更新数据不存在，则新增
     * 最好不要使用，因为新增后 没有类 反向查询时无法变成类对象
     */
    @Test
    public void testUpsert(){
        Query query = new Query();
        query.addCriteria(Criteria.where("login_name").is("zhangsan"));//字段名

        Update update = new Update();
        update.set("login_name","zhangsan")//字段名 不是属性名
            .set("nick_name","zhangsan")
            .set("real_name","张三")
            .set("phone","13006240001")
            .set("mail","06240001@123.com")
            .set("sex","1")
            .set("courses",Arrays.asList("java","vue"));

        UpdateResult updateResult = mongoTemplate.upsert(query, update, UserInfo.class);
        System.out.println(updateResult.toString());
    }

    /**
     * 更新多条数据
     */
    @Test
    public void testUpdateMulti(){
        Query query = new Query();
        query.addCriteria(Criteria.where("phone").is("13006240001"));//字段名

        Update update = new Update();
        update.set("sex","2"); //字段名 不是属性名

        mongoTemplate.updateMulti(query,update,UserInfo.class);

    }


    /**
     * 表达式更新  更新第一条数据据  面向mongodb更新
     */
    @Test
    public void testUpdateFirst(){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("202107050001"));//字段名

        Update update = new Update();
        update.set("login_name","test07050001")//字段名 不是属性名
              .set("nick_name","test07050001");

        /**
         * updateFirst方法的三个参数
         * query - 匹配条件，相当于在mongodb中 db.xxx.update({"_id":"202107050001"},{})
         * update - 更新内容，相当于db.xxx.update({"_id":"202107050001"},{"$set":{"login_name":"test07050001","nick_name","test07050001"}})
         * Class - 用来通过类型找结合名称。相当于db.user_info.update()
         */
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, UserInfo.class);
        System.out.println(updateResult.toString());
    }

    /**
     * 保存数据，保存的数据主键在集合中存在是覆盖，不存在是新增
     */
    @Test
    public void testSave(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId("202107050001");
        userInfo.setLoginName("test06240001");
        userInfo.setNickName("test06240001");
        userInfo.setRealName("测试用户06240001");
        userInfo.setPhone("13006240001");
        userInfo.setMail("06240001@123.com");
        userInfo.setSex("1");
        mongoTemplate.save(userInfo);
    }

    /**
     * 数据新增
     */
    @Test
    public void testInsert(){
        UserInfo userInfo = new UserInfo();
        //id:字符串类型 或 ObjectId类型可以自动生成，其他数据类型不可自动生成
        userInfo.setLoginName("lisi");
        userInfo.setNickName("lisi");
        userInfo.setRealName("李四");
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
