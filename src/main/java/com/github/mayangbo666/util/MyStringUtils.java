package com.github.mayangbo666.util;

import java.math.BigDecimal;

/**
 * @author mayangbo666
 */
public abstract class MyStringUtils {

    /**
     * 判断字符串数字是不是数字类型
     *
     * @param str
     * @return boolean
     */
    public static boolean isDouble(String str) {

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
        }
        return false;

    }

    /**
     * 返回数字类型的字符串的小数位数
     *
     * @param str
     * @return int
     */
    public static int scale(String str) {

        String[] strs = str.split("\\.");// 匹配小数点
        if (1 == strs.length) {
            return 0;
        }
        String pointers = strs[1];
        int scale = pointers.length();
        return scale;

    }

    /**
     * 根据 "+", "-"类型进行计算
     *
     * @param baseStr
     * @param numStr
     * @param type
     * @return String
     */
    public static String calc(String baseStr, String numStr, String type) {

        BigDecimal baseBD = BigDecimal.valueOf(Double.valueOf(baseStr));
        BigDecimal numBD = BigDecimal.valueOf(Double.valueOf(numStr));
        String resultStr = "NAN";
        if ("-".equals(type)) {
            resultStr = baseBD.subtract(numBD).toString();
        } else if ("+".equals(type)) {
            resultStr = baseBD.add(numBD).toString();
        }
        return resultStr;

    }

    /**
     * 对源字符串数字, 按去尾法进行保留几位小数的操作
     *
     * @param srcStr
     * @return String
     */
    public static String scaleFormat(String srcStr, int pointers) {
        String resultStr = srcStr;
        StringBuilder sb = new StringBuilder("#");

        if (!isDouble(srcStr)) {
            return resultStr;
        }
        // 判断几位小数
        int srcScale = scale(srcStr);
        if (srcScale == pointers) {
            return resultStr;
        }

        if (0 > pointers) {
            return resultStr;
        } else if (0 == pointers) {
        } else {
            sb = sb.append(".");
            for (int i = 0; i < pointers; i++) {
                sb = sb.append("#");
            }
        }

//        DecimalFormat df = new DecimalFormat(sb.toString());
        BigDecimal srcBD = BigDecimal.valueOf(Double.valueOf(srcStr));
        // "去尾法"

        resultStr = srcBD.setScale(pointers, BigDecimal.ROUND_FLOOR).toString();
        return resultStr;
    }
}
