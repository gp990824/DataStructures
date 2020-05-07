import java.util.Arrays;

/**
 * @author gp
 * @create 2020/1/11 16:16
 */
//利用动态规划算法求解01背包问题
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//各个商品的重量
        int[] val = {1500, 3000, 2000};//各个商品对应的价值
        int n = 4;//背包的最大容量

        //v[i][j]表示在前i个物品中能装入容量为j的背包的最大价值
        int[][] v = new int[w.length + 1][n + 1];
        //标记商品是怎样放入背包的
        int[][] path = new int[w.length + 1][n + 1];
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }

        }

        for (int[] ints : v) {
            System.out.println(Arrays.toString(ints));
        }

        //查看商品是怎样放入背包的
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {//从后向前遍历
                System.out.println("第" + i + "个商品放入背包!");
                j -= w[i - 1];
            }
            i--;
        }
    }
}
