package org.gp.test.sort;

import java.util.Arrays;

/**
 * @author gp
 * @create 2020/2/11 16:53
 */
public class Select {
    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 1};
        select(arr);
    }

    private static void select(int[] arr) {
        int temp = 0;
        for (int j = 0; j < arr.length - 1; j++) {
            int index = j;
            temp = arr[j];
            for (int i = j+1; i < arr.length; i++) {
                if (arr[i] < temp) {
                    temp = arr[i];
                    index = i;
                }
            }
            arr[index] = arr[j];
            arr[j] = temp;
        }

        System.out.println(Arrays.toString(arr));
    }
}
