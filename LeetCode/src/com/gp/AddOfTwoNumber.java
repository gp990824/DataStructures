package com.gp;

/**
 * @author Gp
 * @create 2020/4/5 16:20
 */

/**
 * 两数相加, 给定两个三位数, 以链表形式存储, 位数以逆序存在
 * 让这连个整数相加, 得到一个新的三位数, 以链表形式输出
 */
public class AddOfTwoNumber {
    public static int[] getHundredTenBitNum(int value) {
        return new int[]{value % 10, (value % 100) / 10, value / 100};
    }
    public static LinkedTable getNewLinkedTableAddOfTwoNumber(int i, int j){
        int[] arrNewNumber = getHundredTenBitNum(i + j);
        LinkedTable linkedTable = new LinkedTable();
        for (int k = 0; k < arrNewNumber.length ; k++) {
            linkedTable.addNode(new Node(arrNewNumber[k]));
        }
        return linkedTable;
    }

    public static void main(String[] args) {
        int one = 342;
        int two = 465;
//        int[] oneArr = getHundredTenBitNum(one);
//        int[] twoArr = getHundredTenBitNum(two);
//        LinkedTable oneTable = new LinkedTable();
//        LinkedTable twoTable = new LinkedTable();
//        for (int i = 0; i < oneArr.length; i++) {
//            oneTable.addNode(new Node(oneArr[i]));
//        }
//        for (int i = 0; i < twoArr.length; i++) {
//            twoTable.addNode(new Node(twoArr[i]));
//        }
//        oneTable.printLinkedTable();
//        System.out.println();
//        twoTable.printLinkedTable();
        LinkedTable newLinkedTableAddOfTwoNumber = getNewLinkedTableAddOfTwoNumber(one, two);
        newLinkedTableAddOfTwoNumber.printLinkedTable();
    }

    private static class Node {
        int num;
        Node next;

        public Node(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return String.valueOf(num);
        }
    }

    private static class LinkedTable {
        private Node head = new Node(0);

        public void addNode(Node node) {
            Node temp = head.next;
            if (temp == null) {
                head.next = node;
                return;
            }
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = node;
        }

        public void printLinkedTable() {
            Node temp = head.next;
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.next == null) {
                    System.out.print(temp);
                } else {
                    System.out.print(temp + " -> ");
                }
                temp = temp.next;
            }
        }
    }

}
