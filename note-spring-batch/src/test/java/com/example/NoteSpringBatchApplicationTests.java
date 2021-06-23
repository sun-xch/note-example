package com.example;

import com.example.business.entity.UserInfo;
import com.example.business.service.IUserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

        for (int i=0;i<5000000;i++){
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

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("php");
        list.add("java");
        list.add("js");
        list.add("mysql");
        list.add("docker");
        System.out.println(list.remove(0));
    }

    @Test
    public void parallelMarkData() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread insertThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                createData(0,500000);
            }
        });

        Thread insertThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                createData(500000,1000000);
            }
        });

        Thread insertThread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                createData(1000000,1500000);
            }
        });

        Thread insertThread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                createData(1500000,2000000);
            }
        });

//        Thread insertThread5 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                createData(2000000,3000000);
//            }
//        });

        insertThread1.start();
        insertThread2.start();
        insertThread3.start();
        insertThread4.start();
//        insertThread5.start();
        insertThread1.join();
        insertThread2.join();
        insertThread3.join();
        insertThread4.join();
//        insertThread5.join();

        long end = System.currentTimeMillis();

        System.out.println("start:" + start +",end:" + end);
        System.out.println(end - start);

    }

    private void createData(int index, int end){

        List<UserInfo> list = new ArrayList<>();

        for (int i=index;i<end;i++){
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

            if(i % 500 == 0){

                userInfoService.batchInsert(list);

                list.clear();
            }


        }

        if(!list.isEmpty()){
            userInfoService.batchInsert(list);
        }

    }
}
