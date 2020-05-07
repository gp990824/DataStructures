package com.gp;

/**
 * @author Gp
 * @create 2020/4/8 19:12
 */

/**
 * 模拟正则表达式匹配, '.' 表示一个字符, '*' 表示零个或多个字符
 * 例如 : "aaab" 和 "b" 都与 "a*b" , 匹配成功
 */
public class RegularMatching {
    private static boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        int rows = s.length();
        int columns = p.length();
        boolean[][] dp = new boolean[rows + 1][columns + 1];
        //当s=a，p=a，那么dp[1][1] = dp[0][0]。因此dp[0][0]必须为true。
        dp[0][0] = true;
        for (int j = 1; j <= columns; j++) {
            //p[j-1]为*可以把j-2和j-1处的字符删去，只有[0,j-3]都为true才可以
            //因此dp[j-2]也要为true，才可以说明前j个为true
            if (p.charAt(j - 1) == '*' && dp[0][j - 2])
                dp[0][j] = true;
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                char nows = s.charAt(i - 1);
                char nowp = p.charAt(j - 1);
                if (nows == nowp) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (nowp == '.')
                        dp[i][j] = dp[i - 1][j - 1];
                    else if (nowp == '*') {
                        //p需要能前移1个。（当前p指向的是j-1，前移1位就是j-2，因此为j>=2）
                        if (j >= 2) {
                            char nowpLast = p.charAt(j - 2);
                            //只有p[j-2]==s[i-1]或p[j-2]==‘.’才可以让*取1个或者多个字符：
                            if (nowpLast == nows || nowpLast == '.')
                                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                            //不论p[j-2]是否等于s[i-1]都可以删除掉j-1和j-2处字符：
                            dp[i][j] = dp[i][j] || dp[i][j - 2];
                        }
                    } else
                        dp[i][j] = false;
                }
            }
        }
        return dp[rows][columns];
    }


    public static void main(String[] args) {
        System.out.println(isMatch("bb", "a*bb"));
    }
}
