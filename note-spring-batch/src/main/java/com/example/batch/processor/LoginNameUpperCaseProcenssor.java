//package com.example.batch.processor;
//
//import com.example.business.entity.UserInfo;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LoginNameUpperCaseProcenssor implements ItemProcessor<UserInfo, UserInfo> {
//    @Override
//    public UserInfo process(UserInfo item) throws Exception {
//        return new UserInfo(item.getId(),
//                item.getUserId(),
//                item.getLoginName().toUpperCase(),
//                item.getNickName(),
//                item.getRealName(),
//                item.getPhone(),
//                item.getMail(),
//                item.getSex(),
//                item.getBirthday(),
//                item.getAge(),
//                item.getAvatar(),
//                item.getAddress(),
//                item.getWork(),
//                item.getNativePlace(),
//                item.getCreditRating(),
//                item.getRemarks(),
//                item.getFlag(),
//                item.getEditTime(),
//                item.getEditUserId(),
//                item.getEditUserName(),
//                item.getCreateTime(),
//                item.getCreateUserId(),
//                item.getCreateUserName());
//    }
//}
