package com.gp.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author gp
 * @create 2020/1/6 16:19
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        huffmanTree(arr);
    }

    public static void huffmanTree(int[] arr) {
        List<HuffmanNode> list = new ArrayList<>();
        for (int i : arr) {
            list.add(new HuffmanNode(i));
        }

        while (list.size() > 1){

        //排序集合中的元素
        Collections.sort(list);
        HuffmanNode leftNode = list.get(0);
        HuffmanNode rightNode = list.get(1);
        HuffmanNode root = new HuffmanNode(leftNode.num + rightNode.num);
        root.left = leftNode;
        root.right = rightNode;
        list.remove(leftNode);
        list.remove(rightNode);
        list.add(root);
        Collections.sort(list);

        System.out.print("当前集合为:");
        System.out.println(list);

        }
        System.out.println("赫夫曼树前序遍历结果为:");
        list.get(0).preOrder();

    }
}

class HuffmanNode implements Comparable<HuffmanNode> {
    int num;
    HuffmanNode left;
    HuffmanNode right;

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    public HuffmanNode(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "num=" + num +
                '}';
    }

    //比较节点的num值的大小,从到到排序
    public int compareTo(HuffmanNode o) {
        return this.num - o.num;
    }
}
