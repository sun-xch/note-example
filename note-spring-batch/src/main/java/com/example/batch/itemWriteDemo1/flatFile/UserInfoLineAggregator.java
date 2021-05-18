//package com.example.batch.itemWriteDemo1.flatFile;
//
//import com.example.business.entity.UserInfo;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.batch.item.file.transform.LineAggregator;
//
//public class UserInfoLineAggregator implements LineAggregator<UserInfo> {
//
//    //json
//    private ObjectMapper mapper = new ObjectMapper();
//
//    /**
//     * 将 对象转换成字符串写到文件中
//     * @param item
//     * @return
//     */
//    @Override
//    public String aggregate(UserInfo item) {
//        try {
//            String str = mapper.writeValueAsString(item);
//            return str;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//    }
//
//}
