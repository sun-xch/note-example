package com.example;

import com.example.business.entity.UserInfo;
import com.example.business.service.IUserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class NoteSpringBatchApplicationTests {

    @Autowired
    private IUserInfoService userInfoService;

    @Test
    void contextLoads() {
    }

    @Test
    public void make(){

        List<UserInfo> list = new ArrayList<>();

        for (int i=0;i<10;i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setLoginName("test"+i);
            userInfo.setNickName("test"+i);
            userInfo.setRealName("测试用户"+i);
            String s = autoGenericCode(String.valueOf(i), 8);
            userInfo.setPhone("130"+s);
            userInfo.setMail(s+"@123.com");
            userInfo.setSex("1");
            userInfo.setBirthday(LocalDate.now());
            userInfo.setAge(10);
            userInfo.setAvatar("www.123123serfsfdswesf443fs4353fd"+i+".com");
            userInfo.setAddress("测试用户"+i+"的地址信息");
            userInfo.setWork("Java");
            userInfo.setNativePlace("山东");
            userInfo.setCreditRating("10");
            userInfo.setRemarks("测试用户"+i+"的备注信息");
            userInfo.setFlag("T");

            list.add(userInfo);

            if(i % 300 == 0){

                userInfoService.batchInsert(list);

                list.clear();
            }


        }

        if(!list.isEmpty()){
            userInfoService.batchInsert(list);
        }

    }

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    private String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数
        // 0 代表前面补充0
        // num 代表长度为4
        // d 代表参数为正数型
        result = String.format("%0" + num + "d", Integer.parseInt(code) + 1);

        return result;
    }

}
