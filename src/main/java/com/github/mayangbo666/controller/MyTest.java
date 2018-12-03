package com.github.mayangbo666.controller;

import com.github.mayangbo666.util.MyStringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyTest {
    public static void main(String[] args) {
//        int i = 10;
//        System.out.println(MyStringUtils.isDouble(i + ""));\

//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//        String[] strs = str.split("\\.");// 匹配小数点
//        for (String s : strs) {
//            System.out.println(s);
//        }

//        Scanner scanner = new Scanner(System.in);
//        String str = "0";
//        while (true){
//            str = scanner.nextLine();
//            Double num = Double.valueOf(str);
//            BigDecimal db = new BigDecimal(num);
//            db = db.setScale(2, BigDecimal.ROUND_FLOOR);
//            System.out.println(db);
//        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));
    }
}
