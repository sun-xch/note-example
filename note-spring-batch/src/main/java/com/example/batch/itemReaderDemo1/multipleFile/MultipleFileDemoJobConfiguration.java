package com.example.batch.itemReaderDemo1.multipleFile;

import com.example.business.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Configuration
@EnableBatchProcessing
public class MultipleFileDemoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("flatFileDemoWriter")
    private ItemWriter<? super UserInfo> flatFileDemoWriter;

    @Bean
    public Job multipleFileDemoJob(){
        return jobBuilderFactory.get("multipleFileDemoJob")
                .start(multipleFileDemoStep())
                .build();
    }

    @Bean
    public Step multipleFileDemoStep() {
        return stepBuilderFactory.get("multipleFileDemoStep")
                .<UserInfo, UserInfo>chunk(1000)
                .reader(multipleFileResourceItemReader())
                .writer(flatFileDemoWriter)
                .build();
    }


    private MultiResourceItemReader<UserInfo> multipleFileResourceItemReader() {
        MultiResourceItemReader<UserInfo> reader = new MultiResourceItemReader<>();

        reader.setDelegate(flatFileDemoReader());
        reader.setResources(new PathResource[]{new PathResource("C:\\Users\\13050\\Desktop\\user_info.csv"),new PathResource("C:\\Users\\13050\\Desktop\\user_info.csv")});

        return reader;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<UserInfo> flatFileDemoReader(){
        FlatFileItemReader<UserInfo> reader = new FlatFileItemReader<>();
        // reader.setResource(new PathResource("C:\\Users\\13050\\Desktop\\user_info.csv"));
        reader.setLinesToSkip(1);
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id","user_id",	"login_name", "nick_name", "real_name", "phone", "mail", "sex", "birthday", "age", "avatar", "address", "work", "native_place", "credit_rating", "remarks"});

        DefaultLineMapper<UserInfo> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet -> {
            return UserInfo.builder().id(fieldSet.readString("id"))
                    .userId(fieldSet.readString("user_id"))
                    .loginName(fieldSet.readString("login_name"))
                    .nickName(fieldSet.readString("nick_name"))
                    .realName(fieldSet.readString("real_name"))
                    .phone(fieldSet.readString("phone"))
                    .mail(fieldSet.readString("mail"))
                    .sex(fieldSet.readString("sex"))
                    // .birthday(rs.getDate("birthday").toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .birthday(LocalDate.parse(fieldSet.readString("birthday"), DateTimeFormatter.ofPattern("yyyy/M/dd")))
                    .age(Integer.valueOf(fieldSet.readString("age")))
                    .avatar(fieldSet.readString("avatar"))
                    .address(fieldSet.readString("address"))
                    .work(fieldSet.readString("work"))
                    .nativePlace(fieldSet.readString("native_place"))
                    .creditRating(fieldSet.readString("credit_rating"))
                    .remarks(fieldSet.readString("remarks"))
                    .build();
        });

        lineMapper.afterPropertiesSet();

        reader.setLineMapper(lineMapper);
        return reader;
    }

}
