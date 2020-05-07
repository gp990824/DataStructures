package com.gp.tree;

/**
 * @author gp
 * @create 2020/1/5 17:11
 */
//用数组方式实现二叉树
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
    }
}

//创建二叉树类
class ArrBinaryTree {

    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //前序遍历
    public void preOrder(int n) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空哦");
        } else {
            System.out.println(arr[n]);
            if (n * 2 + 1 < arr.length) {
                preOrder(n * 2 + 1);
            }

            if (n * 2 + 2 < arr.length) {
                preOrder(n * 2 + 2);
            }
        }
    }
}
