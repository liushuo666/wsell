package com.liushuo.wsell.utils;

import java.util.Random;

/**
 * Create by liushuo on 2017/11/30.
 */
public class KeyUtil {
    public static  synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return    System.currentTimeMillis()+number.toString();
    }
}
