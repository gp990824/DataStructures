package com.gp.recursion;


/**
 * @author gp
 * @create 2019/12/27 18:41
 */
/*
利用回溯算法求解八皇后问题
 */
public class EightKing {
    int max;
    //    其中:数组中的第i个元素表示:在第i行的第arr[i]列放置该皇后
    int[] arr;//用于存放八皇后的解法
    int count;

     public EightKing(int max){
         this.max = max;
         arr = new int[max];
     }
    public static void main(String[] args) {
        EightKing king = new EightKing(8);
        king.check(0);
    }

    //放置第n个皇后
    private void check(int n) {
        if (n == max) {//当n=8时,这八个皇后已经放好
            System.out.print("第" + ++count + "种解法为:" + "  ");
            for (int i : arr) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        //依次放入皇后,并判断是否冲突
        for (int i = 0; i < max; i++) {
            //将当前皇后放在改行的第i列
            arr[n] = i;
            if (juege(n)) {//如果返回true则说明不冲突
                check(n + 1);//放置下一个皇后
            }
            //如果冲突,在放在下一列
        }
    }


    //查看当我们放置第n个皇后,就去检测该皇后是否跟前面已经摆放的皇后冲突
    private boolean juege(int n) {
        for (int i = 0; i < n; i++) {
            /*
            Math.abs(n-i) == Math.abs(arr[n] - arr[i]  用于判断两个皇后是否处于在斜线上是否冲突
             */
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

}
