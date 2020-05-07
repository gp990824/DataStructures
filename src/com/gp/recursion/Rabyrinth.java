package com.gp.recursion;

/**
 * @author gp
 * @create 2019/12/27 17:04
 */
//迷宫问题(使用递归算法求解)
public class Rabyrinth {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("迷宫情况如下:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        setWay(map, 1, 1);
        System.out.println("迷宫算法情况如下:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    //迷宫回溯算法
    /*
    约定:优先行走顺序:下->右->上->左
    当迷宫的值为0时说明这里没有走过
    当迷宫的值为1时说明这里是障碍,不能走
    当迷宫的值为2时说明这里是通路可以走
    当迷宫的值为3时说明这里已经走过,但是走不通
    参数i,j表示起点的位置
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//这里是终点
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前位置没有走过
                map[i][j] = 2;//假定可以走
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {//如果都走不通,则返回false
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j]!=0 可能是 1 2 3
                return false;
            }
        }
    }
}
