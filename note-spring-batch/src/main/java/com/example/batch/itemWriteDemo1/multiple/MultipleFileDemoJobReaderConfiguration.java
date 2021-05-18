//package com.example.batch.itemWriteDemo1.multiple;
//
//import com.example.business.entity.UserInfo;
//import org.springframework.batch.item.database.JdbcPagingItemReader;
//import org.springframework.batch.item.database.Order;
//import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class MultipleFileDemoJobReaderConfiguration {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public JdbcPagingItemReader<UserInfo> multipleFileDemoJdbcPagingReader() {
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
