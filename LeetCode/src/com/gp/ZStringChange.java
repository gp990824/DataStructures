package com.gp;

/**
 * @author Gp
 * @create 2020/4/8 14:22
 */

/**
 * Z 字型变换, 将一个字符串根据给定指定行数进行Z字形排列
 *
 *                                   L       D        R
 *convert("LEETCODEISHIRING", 4) ->  E    O  E     I  I  ->   要求输出的格式为 :  LDREOEIIECIHNTSG
 *                                   E  C    I  H     N
 *                                   T       S        G
 */
public class ZStringChange {
    private static String convert(String str, int conut) {
        int i = (str.length() - conut) % (2 * conut - 2);//多余的字符
        int j = (str.length() - conut) / (2 * conut - 2);//一共有几个 V 形状
        int weight = i == 0 ? (conut - 1) * j + 1 : (conut - 1) * j + 1 + i;
        char[][] chars = new char[conut][weight];
        char[] strChar = str.toCharArray();
        int p = 0;
        for (int l = 0; l < chars[0].length; l++) {//竖向遍历, l 代表纵坐标
            loop:
            for (int k = 0; k < chars.length; k++) {// k 代表横坐标
                if (l % (conut - 1) == 0) {
                    chars[k][l] = strChar[p++];
                } else if ((k + l) % (conut - 1) == 0) {
                    chars[k][l] = strChar[p++];
                    break loop;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int k = 0; k < chars.length; k++) {
            for (int l = 0; l < chars[0].length; l++) {
                if (chars[k][l] != '\u0000') {
                    builder.append(chars[k][l]);
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 5));
    }
}
