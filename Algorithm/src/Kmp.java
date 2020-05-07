/**
 * @author gp
 * @create 2020/1/14 14:35
 */
public class Kmp {
    public static void main(String[] args) {

        String str1 = "ABCDACABCDAB";
        String str2 = "ABCDAB";
        int[] ints = kmpNext(str2);
        System.out.println(kmp(str1, str2, ints));
    }

    /**
     * kmp查找算法
     * 移动位数 = 已匹配的字符数 - 对应的部分匹配值
     *
     * @param str1 主串
     * @param str2 子串
     * @param next 匹配值表
     * @return 子串在主串第一次出现的位置, 下标
     */
    public static int kmp(String str1, String str2, int[] next) {
        // i 为主串下标, j 为子串下标
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //kmp算法的核心思想, 找到相等的字符则退出, 否者需要从 next[j-1] 获取新的 j 值
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                //子串下标回退
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取子串的部分匹配表
     * 部分匹配值 就是 前缀 和 后缀 的最长的共有元素的长度, 以 ABCAB 为例
     * A 前缀后缀都为空集, 共有元素长度为 0 , 部分匹配表为 [0]
     * AB 前缀为 [A] , 后缀为 [B] , 共有元素长度为 0 , 部分匹配表为 [0, 0]
     * ABC 前缀为 { [A, B], [A] }, 后缀为 { [B, C], [C] }, 共有元素长度为 0 ,部分匹配表为 [0, 0, 0]
     * ABCA 前缀为 { [A, B, C], [A, B], [A] }, 后缀为 { [B, C, A], [C, A], [A] }, 共有元素长度为 1, 部分匹配表为 [0, 0, 0, 1]
     * ABCAB 前缀为 { [A, B, C, A], [A, B, C], [A, B], [A] }, 后缀为 { [B, C, A, B], [C, A, B], [A, B], [A] }, 共有元素长度为 2,部分匹配表为 [0, 0, 0, 1, 2]
     *
     * @param str 子串
     * @return 部分匹配表
     */
    public static int[] kmpNext(String str) {
        int[] next = new int[str.length()];
        for (int i = 1, j = 0; i < next.length; i++) {
            //找到相等的字符则退出, 否者需要从 next[j-1] 获取新的 j 值
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            //满足条件则将部分匹配值 +1
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    //暴力匹配法
    public static int violenceMatch(String str1, String str2) {
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int i = 0, j = 0;
        while (i < c1.length && j < c2.length) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == c2.length) {
            return i - j;
        } else {
            return -1;
        }
    }

}
