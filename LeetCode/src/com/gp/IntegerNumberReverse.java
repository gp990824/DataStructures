package com.gp;

/**
 * @author Gp
 * @create 2020/4/8 17:11
 */
//整数反转, 给定一个int型整数, 使该整数前后位数位置互换,符号不变
public class IntegerNumberReverse {
    private static int reverse(int number) {
        int symbol = number / Math.abs(number);
        int length = (number * symbol + "").length();
        number = Math.abs(number);
        int[] arr = new int[length];
        for (int i = 0, n = 1; i < length; i++, n *= 10) {
            arr[i] = number % (n * 10) / n;
        }
        int result = 0;
        for (int i = arr.length - 1, n = 1; i > -1; i--, n *= 10) {
            result += arr[i] * n;
        }
        return result * symbol;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-3400002));
    }
}
