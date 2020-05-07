package com.gp.sparseArray;

public class SparseArray {
    public static void main(String[] args) {
        int oldArr[][] = new int[11][11];
        oldArr[1][2] = 1;
        oldArr[2][3] = 2;
        for (int i = 0; i < oldArr.length; i++) {
            for (int j = 0; j < oldArr.length; j++) {
                System.out.print("\t" + oldArr[i][j]);
            }
            System.out.println();
        }

        //获取非零个数
        int sum = 0;
        for (int i = 0; i < oldArr.length; i++) {
            for (int j = 0; j < oldArr[i].length; j++) {
                if (oldArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        //创建稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int count = 0;
        for (int i = 0; i < oldArr.length; i++) {
            for (int j = 0; j < oldArr[i].length; j++) {
                if (oldArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = oldArr[i][j];
                }
            }
        }
        System.out.println("优化后的稀疏数组为:");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.print("\t" + sparseArr[i][j]);
            }
            System.out.println();
        }

        System.out.println("将稀疏数组转化为标准二维数组:");
        int newArr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {

            newArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[i].length; j++) {
                System.out.print("\t" + newArr[i][j]);
            }
            System.out.println();
        }

    }
}
