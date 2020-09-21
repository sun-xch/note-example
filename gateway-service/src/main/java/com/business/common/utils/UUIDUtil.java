package com.business.common.utils;

import java.util.UUID;

public class UUIDUtil {

    public static String get32UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String get36UUID(){
        return UUID.randomUUID().toString();
    }
}
