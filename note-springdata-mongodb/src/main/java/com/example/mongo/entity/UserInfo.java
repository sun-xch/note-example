package com.example.mongo.entity;

import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 通过注解，描述实体类型和MongoDB中集合的关系。
 * 类型对应哪个集合，属性对应集合中哪个字段.
 * Document注解描述类型，属性value/collecion用来指定当前类型对应哪个集合。如果不提供属性，则使用默认规则，类名的首字母转小写。
 */
@ToString
@Data
@Document("user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 7633838028808016626L;

    /**
     * Id注解：是springData提供的一个公共的注解，用来描述实体类型中的主键属性
     * 在springData MongoDB环境中，如果实体类型的属性名为id或_id时，且这个属性就是主键属性的时候，可以省略Id注解
     */
    @Id
    private String id;
    //private ObjectId id;

    /**
     * Field注解，用来描述类型的属性和集合中的字段的映射关系。如果不使用注解描述属性，则集合中的字段名和类型中的属性名相同
     * 可以使用注解的属性value/name来指定属性对应的字段名是什么
     */
    @Field("user_id")
    private String userId;

    @Field("login_name")
    private String loginName;

    @Field("nick_name")
    private String nickName;

    @Field("real_name")
    private String realName;

    @Field("phone")
    private String phone;

    @Field("mail")
    private String mail;

    @Field("sex")
    private String sex;

    @Field("birthday")
    private LocalDate birthday;

    @Field("age")
    private Integer age;

    @Field("avatar")
    private String avatar;

    @Field("address")
    private String address;

    @Field("work")
    private String work;

    @Field("native_place")
    private String nativePlace;

    @Field("credit_rating")
    private String creditRating;

    @Field("remarks")
    private String remarks;

    @Field("flag")
    private String flag;

    @Field("edit_time")
    private LocalDateTime editTime;

    @Field("edit_user_id")
    private String editUserId;

    @Field("edit_user_name")
    private String editUserName;

    @Field("create_time")
    private LocalDateTime createTime;

    @Field("create_user_id")
    private String createUserId;

    @Field("create_user_name")
    private String createUserName;

    @Field("courses")
    private List<String> courses;

}
