//package com.example.batch.itemWriteDemo1.dbDemo;
//
//import com.example.business.entity.UserInfo;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DbDemoWriteConfiguration {
//
//    @Autowired
//    public DataSource dataSource;
//
//    @Bean
//    public JdbcBatchItemWriter<UserInfo> jdbcBatchItemWriterDemo(){
//        JdbcBatchItemWriter<UserInfo> itemWriter = new JdbcBatchItemWriter<>();
//        itemWriter.setDataSource(dataSource);
//        itemWriter.setSql("insert into user_info(id, user_id, login_name, nick_name, real_name, phone, mail, sex, birthday, age, avatar, address, work, native_place, credit_rating, remarks) values "+
//                "(:id,:userId,:loginName,:nickName,:realName,:phone,:mail,:sex,:birthday,:age,:avatar,:address,:work,:nativePlace,:creditRating,:remarks)");
//        // 设置sql参数来源提供者 根据bean的属性进行赋值
//        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        return itemWriter;
//    }
//}
