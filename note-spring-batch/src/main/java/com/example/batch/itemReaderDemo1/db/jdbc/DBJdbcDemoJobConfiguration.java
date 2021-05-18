//package com.example.batch.itemReaderDemo1.db.jdbc;
//
//import com.example.business.entity.UserInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.catalina.User;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JdbcPagingItemReader;
//import org.springframework.batch.item.database.Order;
//import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class DBJdbcDemoJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    @Qualifier("dbJdbcDemoWriter")
//    private ItemWriter<? super UserInfo> dbJdbcDemoWriter;
//
//    @Bean
//    public Job dbjdbcDemoJob(){
//        return jobBuilderFactory.get("dbjdbcDemoJob")
//                .start(dbjdbcDemoStep())
//                .build();
//
//    }
//
//    @Bean
//    public Step dbjdbcDemoStep() {
//        return stepBuilderFactory.get("dbjdbcDemoStep")
//                .<UserInfo, UserInfo>chunk(100)
//                .reader(dbjdbcDemoReader())
//                .writer(dbJdbcDemoWriter)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JdbcPagingItemReader<UserInfo> dbjdbcDemoReader() {
//        JdbcPagingItemReader<UserInfo> reader = new JdbcPagingItemReader<>();
//        reader.setDataSource(dataSource);
//        reader.setFetchSize(100);
//        // 将读出来的结果集 映射为UserInfo对象
//        reader.setRowMapper((rs, rowNum)->{
//            return UserInfo.builder().id(rs.getString("id"))
//                    .userId(rs.getString("user_id"))
//                    .loginName(rs.getString("login_name"))
//                    .nickName(rs.getString("nick_name"))
//                    .realName(rs.getString("real_name"))
//                    .phone(rs.getString("phone"))
//                    .mail(rs.getString("mail"))
//                    .sex(rs.getString("sex"))
//                    // .birthday(rs.getDate("birthday").toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                    .birthday(LocalDate.parse(rs.getString("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                    .age(rs.getInt("age"))
//                    .avatar(rs.getString("avatar"))
//                    .address(rs.getString("address"))
//                    .work(rs.getString("work"))
//                    .nativePlace(rs.getString("native_place"))
//                    .creditRating(rs.getString("credit_rating"))
//                    .remarks(rs.getString("remarks"))
//                    .build();
//        });
//
//        // 查询
//        MySqlPagingQueryProvider mySqlPagingQueryProvider = new MySqlPagingQueryProvider();
//        mySqlPagingQueryProvider.setSelectClause("id, user_id, login_name, nick_name, real_name, phone, mail, sex, birthday, age, avatar, address, work, native_place, credit_rating, remarks");
//        mySqlPagingQueryProvider.setFromClause("from user_info");
//        Map<String, Order> sortKeys = new HashMap<>(1);
//        sortKeys.put("phone",Order.ASCENDING);
//        mySqlPagingQueryProvider.setSortKeys(sortKeys);
//
//        reader.setQueryProvider(mySqlPagingQueryProvider);
//
//        return reader;
//    }
//}
