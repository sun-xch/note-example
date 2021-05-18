//package com.example.batch.itemReaderDemo1.xmlFile;
//
//import com.example.business.entity.UserInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.xml.StaxEventItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.PathResource;
//import org.springframework.oxm.xstream.XStreamMarshaller;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class XmlFileDemoJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    @Qualifier("xmlFileDemoWriter")
//    private ItemWriter<? super UserInfo> xmlFileDemoWriter;
//
//    @Bean
//    public Job xmlFileDemoJob(){
//        return jobBuilderFactory.get("xmlFileDemoJob")
//                .start(xmlFileDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step xmlFileDemoStep() {
//        return stepBuilderFactory.get("xmlFileDemoStep")
//                .<UserInfo, UserInfo>chunk(400)
//                .reader(xmlFileDemoReader())
//                .writer(xmlFileDemoWriter)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public StaxEventItemReader<UserInfo> xmlFileDemoReader() {
//        StaxEventItemReader<UserInfo> reader = new StaxEventItemReader<>();
//        reader.setResource(new PathResource("C:\\Users\\13050\\Desktop\\user_info.xml"));
//        //  处理的节点名称
//        reader.setFragmentRootElementName("USERINFO");
//        // 反序列化 xml 转化为 java对象
//        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
//        Map<String, Class> map = new HashMap<>();
//        map.put("USERINFO",UserInfo.class);
//        map.put("id",String.class);
//        map.put("user_id",String.class);
////        map.put("login_name",String.class);
////        map.put("nick_name",String.class);
////        map.put("real_name",String.class);
////        map.put("phone",String.class);
////        map.put("mail",String.class);
////        map.put("sex",String.class);
////        map.put("birthday",LocalDate.class);
////        map.put("age",Integer.class);
////        map.put("avatar",String.class);
////        map.put("address",String.class);
////        map.put("work",String.class);
////        map.put("native_place",String.class);
////        map.put("credit_rating",String.class);
////        map.put("remarks",String.class);
////        map.put("flag",String.class);
////        map.put("edit_time", LocalDateTime.class);
////        map.put("edit_user_id",String.class);
////        map.put("edit_user_name",String.class);
////        map.put("create_time", LocalDateTime.class);
////        map.put("create_user_id",String.class);
////        map.put("create_user_name",String.class);
//        xStreamMarshaller.setAliases(map);
//        Map<String, String> fileMap = new HashMap<>();
//        fileMap.put("id","id");
//        fileMap.put("user_id","userId");
////        fileMap.put("login_name","loginName");
////        fileMap.put("nick_name","nickName");
////        fileMap.put("real_name","realName");
////        fileMap.put("phone","phone");
////        fileMap.put("mail","mail");
////        fileMap.put("sex","sex");
////        fileMap.put("birthday","birthday");
////        fileMap.put("age","age");
////        fileMap.put("avatar","avatar");
////        fileMap.put("address","address");
////        fileMap.put("work","work");
////        fileMap.put("native_place","nativePlace");
////        fileMap.put("credit_rating","creditRating");
////        fileMap.put("remarks","remarks");
////        fileMap.put("flag","flag");
////        fileMap.put("edit_time","editTime");
////        fileMap.put("edit_user_id","editUserId");
////        fileMap.put("edit_user_name","editUserName");
////        fileMap.put("create_time","createTime");
////        fileMap.put("create_user_id","createUserId");
////        fileMap.put("create_user_name","createUserName");
//        xStreamMarshaller.setFieldAliases(fileMap);
//        reader.setUnmarshaller(xStreamMarshaller);
//
//        return reader;
//    }
//}
