package com.example.batch.processor;

import com.example.batch.itemWriteDemo1.flatFile.UserInfoLineAggregator;
import com.example.business.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@EnableBatchProcessing
public class ProcessorDemoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ItemProcessor<UserInfo, UserInfo> loginNameUpperCaseProcenssor;

    @Autowired
    private ItemProcessor<UserInfo, UserInfo> filterSexProcessor;

    @Bean
    public Job processorDemoJob() throws Exception {
        return jobBuilderFactory.get("processorDemoJob")
                .start(processorDemoStep())
                .build();
    }

    @Bean
    public Step processorDemoStep() throws Exception {
        return stepBuilderFactory.get("processorDemoStep")
                .<UserInfo, UserInfo>chunk(100)
                .reader(dbjdbcDemoReader())
                .processor(processorDemo())
                .writer(flatFileDemoFlatFileWrite())
                .build();
    }

    @Bean
    public CompositeItemProcessor<UserInfo, UserInfo> processorDemo(){
        CompositeItemProcessor<UserInfo, UserInfo> processor = new CompositeItemProcessor<>();
        List<ItemProcessor<UserInfo,UserInfo>> list = new ArrayList<>();
        list.add(loginNameUpperCaseProcenssor);
        list.add(filterSexProcessor);

        // 自己不处理 找代理处理多个
        processor.setDelegates(list);


        return processor;
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<UserInfo> dbjdbcDemoReader() {
        JdbcPagingItemReader<UserInfo> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        // 将读出来的结果集 映射为UserInfo对象
        reader.setRowMapper((rs, rowNum)->{
            return UserInfo.builder().id(rs.getString("id"))
                    .userId(rs.getString("user_id"))
                    .loginName(rs.getString("login_name"))
                    .nickName(rs.getString("nick_name"))
                    .realName(rs.getString("real_name"))
                    .phone(rs.getString("phone"))
                    .mail(rs.getString("mail"))
                    .sex(rs.getString("sex"))
                    // .birthday(rs.getDate("birthday").toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .birthday(LocalDate.parse(rs.getString("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .age(rs.getInt("age"))
                    .avatar(rs.getString("avatar"))
                    .address(rs.getString("address"))
                    .work(rs.getString("work"))
                    .nativePlace(rs.getString("native_place"))
                    .creditRating(rs.getString("credit_rating"))
                    .remarks(rs.getString("remarks"))
                    .build();
        });

        // 查询
        MySqlPagingQueryProvider mySqlPagingQueryProvider = new MySqlPagingQueryProvider();
        mySqlPagingQueryProvider.setSelectClause("id, user_id, login_name, nick_name, real_name, phone, mail, sex, birthday, age, avatar, address, work, native_place, credit_rating, remarks");
        mySqlPagingQueryProvider.setFromClause("from user_info");
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("phone",Order.ASCENDING);
        mySqlPagingQueryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(mySqlPagingQueryProvider);

        return reader;
    }

    @Bean
    public FlatFileItemWriter<UserInfo> flatFileDemoFlatFileWrite() throws Exception {
        FlatFileItemWriter<UserInfo> itemWriter = new FlatFileItemWriter<>();
        String path = File.createTempFile("userInfo",".data").getAbsolutePath();
        System.out.println("file is create in: " + path);
        itemWriter.setResource(new FileSystemResource(path));
        itemWriter.setLineAggregator(new UserInfoLineAggregator());
        itemWriter.afterPropertiesSet();
        return itemWriter;
    }
}
