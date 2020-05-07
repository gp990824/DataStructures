package com.gp;

/**
 * @author Gp
 * @create 2020/4/5 17:03
 */

import java.util.*;

/**
 * 无重复字符最长字符子串
 * 在一个字符串中找出一个子串, 该子串不能含有相同字符, 找到最长的那一个
 */
public class NoSameCharLongestString {

    public static void main(String[] args) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        char[] chars = str.toCharArray();
        int[] arr = getMostLength(chars);
        System.out.println(str.substring(arr[0], arr[0] + arr[1]));
    }

    public static int[] getMostLength(char[] chars) {
        Map<Integer, Integer> map = new HashMap<>();
        int temp = 0;
        boolean flag = true;
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (flag && chars[i] == chars[j]) {
                    temp = j;
                    map.put(i, (j - i));
                    flag = false;
                } else if (temp != 0 && chars[temp] == chars[j]) {
                    map.put(j, (j - temp));
                    temp = j;
                }
            }
            temp = 0;
            flag = true;
        }
        int value = 0;
        int key = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > value) {
                value = entry.getValue();
                key = entry.getKey();
            }
        }
        return new int[]{key, value};
    }
}
