package com.gp;

/**
 * @author Gp
 * @create 2020/4/5 16:02
 */

import java.util.Arrays;
import java.util.List;

/**
 * 两数之和, 给定一个数, 在一个整数数组中找出两个数和为目标值, 并返回他们的下标
 * 可以假定只有唯一答案, 但是不能重复利用这个数组中的同样的元素
 */
public class SumOfTwoNumber {
    public static void main(String[] args) {
        int[] arr = {4, 36, 8, 23, 7, 10, 65, 9, 14};
        System.out.println(getIndex(arr, 777));
    }

    public static List<Integer> getIndex(int[] arr, int target) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = target - arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] == temp) {
                    return Arrays.asList(i, j);
                }
            }
        }
        throw new RuntimeException("不存在这两个数!");
    }
}
