package com.xuebusi.springboot.maven;

public class Main {

    public static void main(String[] args) {
        //int最大值：2147483647
//        String str = "" + Integer.MAX_VALUE ;
//        String str = "" + Integer.MIN_VALUE ;
        String str = "-2147483648";

        System.out.println(parseInt(str));
//        System.out.println(parseInt2(str));
//        System.out.println(Integer.valueOf(str));
    }

    /**
     * 自己实现字符串转整数
     * 基本思路：字符串 -> 字符 -> 整数
     * 整数拆分规律: 3256 = 3*10^3 + 2*10^2 + 5*10^1 + 6*10^0
     * @param s
     * @return
     */
    public static int parseInt(String s) {
        //保留原始参数
        String str = s;
        if (str == null || str == "") throw new NumberFormatException("For input string:\"" + s + "\"");
        //是否为负数
        boolean negative = false;
        // 是否以+号或-号开头
        if(str.startsWith("+") || str.startsWith("-")) {
            if (str.startsWith("-")) negative = true;
            str = str.substring(1);
            // 以+或-开头，但是后面没有数字
            if (str == "" || str.length() == 0) throw new NumberFormatException("For input string:\"" + s + "\"");
        }
        char[] chars = str.toCharArray();
        long result = 0;
        for (int i = 0; i < chars.length; i++) {
            // 是否是'0'到'9'之间的字符
            if (chars[i] < '0' || chars[i] > '9') throw new NumberFormatException("For input string:\"" + s + "\"");
            // 先根据字符之间进行运算来得到int值，再根据每个数字所在的位数来计算应该乘10的几次幂(Math.pow()函数用于求幂)，最后累加。
            result += (chars[i] - '0') * Math.pow(10, chars.length - i - 1);
            // 是否超出int的最小值
            if (negative && -result < Integer.MIN_VALUE) {
                throw new NumberFormatException("For input string:\"" + s + "\"");
            }
            // 是否超出int的最大值
            if (!negative && result > Integer.MAX_VALUE) {
                throw new NumberFormatException("For input string:\"" + s + "\"");
            }
        }
        if (negative) result = -result;
        return (int) result;
    }

    /**
     * 字符串转整数的另一种实现，来自网络
     * 思路：3256 = ((3*10 + 2)*10 + 5)*10 + 6
     * @param str
     * @return
     */
    public static int parseInt2(String str) {
        /* 异常情况1：字符串为null */
        if (str == null) {
            throw new NumberFormatException("字符串为null!");
        }
        int length = str.length(), offset = 0;
        /* 异常情况2：字符串长度为0 */
        if (length == 0) {
            throw new NumberFormatException("字符串长度为0！");
        }
        boolean negative = str.charAt(offset) == '-';
        /* 异常情况3：字符串为'-' */
        if (negative && ++offset == length) {
            throw new NumberFormatException("字符串为：'-'！");
        }
        int result = 0;
        char[] temp = str.toCharArray();
        while (offset < length) {
            char digit = temp[offset++];
            if (digit <= '9' && digit >= '0') {
                int currentDigit = digit - '0';
                /*
                 * 异常情况4：已经等于Integer.MAX_VALUE / 10，判断要添加的最后一位的情况：
                 * 如果是负数的话，最后一位最大是8 如果是正数的话最后一位最大是7
                 */
                if (result == Integer.MAX_VALUE / 10) {

                    if ((negative == false && currentDigit > 7)
                            || (negative && currentDigit > 8)) {
                        throw new NumberFormatException("溢出！");
                    }
                    /*
                     * 异常情况5：已经大于Integer.MAX_VALUE / 10
                     * 无论最后一位是什么都会超过Integer.MAX_VALUE
                     */
                } else if (result > Integer.MAX_VALUE / 10) {
                    throw new NumberFormatException("溢出！");
                }
                int next = result * 10 + currentDigit;
                result = next;
            }
        }
        if (negative) {
            result = -result;
        }
        return result;
    }
}
