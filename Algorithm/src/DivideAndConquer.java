/**
 * @author gp
 * @create 2020/1/10 16:44
 */
//分治算法,可以用来解决汉诺塔问题
public class DivideAndConquer {
//    static long i = 0;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        hanoitTower(32, 'A', 'B', 'C');
        long end = System.currentTimeMillis();
        System.out.println("所用毫秒为:" + (end - start));
//        System.out.println(i);
    }

    /**
     * 汉诺塔问题求解
     *
     * @param num 盘子的数量
     * @param a   盘子的初始位置
     * @param b   辅助塔
     * @param c   盘子放的位置
     */
    public static void hanoitTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从 " + a + " -> " + c);
//            i++;
        } else {
            //如果有两个以上的盘子
            /*
            1.首先将盘子查分成两部分,最后的那一个盘子,和最后的那一个盘子所有盘子
            2.要将最后的盘子移动到c,首先要将上面所有的盘子移动到b
            在把最后的那一个盘子所有盘子分成两部分,重复上面两个步骤
             */

            //先把最后的那一个盘子所有盘子移动到b
            hanoitTower(num - 1, a, c, b);
            //再将最后一个盘子移动到c
            System.out.println("第" + num + "个盘从" + a + "->" + c);
//            i++;
            //再将最后的那一个盘子所有盘子移动到c
            hanoitTower(num - 1, b, a, c);
        }
    }
}
