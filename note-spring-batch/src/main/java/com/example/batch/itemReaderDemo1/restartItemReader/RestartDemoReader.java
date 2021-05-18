//package com.example.batch.itemReaderDemo1.restartItemReader;
//
//import com.example.business.entity.UserInfo;
//import org.springframework.batch.item.*;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.core.io.PathResource;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//@Component("restartDemoReader")
//public class RestartDemoReader implements ItemStreamReader<UserInfo> {
//
//    private Long curLine = 0L;
//
//    private boolean restart = false;
//
//    private FlatFileItemReader<UserInfo> reader = new FlatFileItemReader<>();
//
//    private ExecutionContext executionContext;
//
//    public RestartDemoReader() {
//
//        reader.setResource(new PathResource("C:\\Users\\13050\\Desktop\\user_info.csv"));
//        reader.setLinesToSkip(1);
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames(new String[]{"id","user_id",	"login_name", "nick_name", "real_name", "phone", "mail", "sex", "birthday", "age", "avatar", "address", "work", "native_place", "credit_rating", "remarks"});
//
//        DefaultLineMapper<UserInfo> lineMapper = new DefaultLineMapper<>();
//        lineMapper.setLineTokenizer(tokenizer);
//        lineMapper.setFieldSetMapper(fieldSet -> {
//            return UserInfo.builder().id(fieldSet.readString("id"))
//                    .userId(fieldSet.readString("user_id"))
//                    .loginName(fieldSet.readString("login_name"))
//                    .nickName(fieldSet.readString("nick_name"))
//                    .realName(fieldSet.readString("real_name"))
//                    .phone(fieldSet.readString("phone"))
//                    .mail(fieldSet.readString("mail"))
//                    .sex(fieldSet.readString("sex"))
//                    // .birthday(rs.getDate("birthday").toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                    .birthday(LocalDate.parse(fieldSet.readString("birthday"), DateTimeFormatter.ofPattern("yyyy/M/dd")))
//                    .age(Integer.valueOf(fieldSet.readString("age")))
//                    .avatar(fieldSet.readString("avatar"))
//                    .address(fieldSet.readString("address"))
//                    .work(fieldSet.readString("work"))
//                    .nativePlace(fieldSet.readString("native_place"))
//                    .creditRating(fieldSet.readString("credit_rating"))
//                    .remarks(fieldSet.readString("remarks"))
//                    .build();
//        });
//
//        lineMapper.afterPropertiesSet();
//
//        reader.setLineMapper(lineMapper);
//
//    }
//
//    @Override
//    public UserInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//
//        UserInfo userInfo = null;
//
//        this.curLine++;
//
//        if(restart){
//            reader.setLinesToSkip(this.curLine.intValue() - 1);
//            restart = false;
//            System.out.println("start reading from line: " + this.curLine);
//        }
//
//        reader.open(this.executionContext);
//
//        userInfo = reader.read();
//
//        if(userInfo != null){
//            if(userInfo.getPhone().equals("15000000108")){
//                throw new RuntimeException("exception phone: " + userInfo.getPhone());
//            }
//        }else{
//            curLine--;
//        }
//        return userInfo;
//    }
//
//    @Override
//    public void open(ExecutionContext executionContext) throws ItemStreamException {
//        this.executionContext = executionContext;
//        if(executionContext.containsKey("curLine")){
//            this.curLine = executionContext.getLong("curLine");
//            this.restart = true;
//        }else{
//            this.curLine = 0L;
//            executionContext.put("curLine",this.curLine.intValue());
//        }
//    }
//
//    /**
//     * 没执行完一个批次
//     * @param executionContext
//     * @throws ItemStreamException
//     */
//    @Override
//    public void update(ExecutionContext executionContext) throws ItemStreamException {
//        System.out.println("update curLine: " + this.curLine);
//        executionContext.put("curLine",this.curLine);
//    }
//
//    /**
//     * 所有chunk结束后最后 调用 close
//     * @throws ItemStreamException
//     */
//    @Override
//    public void close() throws ItemStreamException {
//
//    }
//}
