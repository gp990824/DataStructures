package com.gp.linkedlist;

//利用单向环形链表可以解决约瑟夫问题
/*
Josephu(约瑟夫)问题:该编号为1,2...n的n个人围坐在一起,约定编号为k(1<=k<=n)的人从1开始报数,数到m的那个人出列,
他的下一位又从1开始报数,数到m的那个人又出列,以此类推,直到所有人出列为止,由此产生一个出列编号的序列
 */
public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList cir = new CircleSingleLinkedList();
        cir.addChild(5);
//        cir.showChildren();
        cir.outChildren(1,2,5);
    }
}

class CircleSingleLinkedList {

    //首先创建第一个小孩(不动)
    Children first = null;

    //创建单向环形链表
    public void addChild(int nums) {
        if (nums < 1) {
            System.out.println("nums为非法数据!");
            return;
        }
        Children temp = first;//辅助节点
        for (int i = 1; i <= nums; i++) {
            Children child = new Children(i);
            if (i == 1) {
                first = child;
                first.setNext(first);
                temp = first;
            } else {
                temp.setNext(child);
                child.setNext(first);
                temp = child;
            }
        }
    }

    //显示该单向环形链表的所有节点
    public void showChildren() {
        Children temp = first;//辅助节点
        while (true) {
            System.out.println("小孩编号:" + temp.getNo());
            if (temp.getNext() == first) {
                break;
            }
            temp = temp.getNext();
        }
    }

    //根据传进来的参数将小孩出圈
    /*
    startNo:从第几个小孩开始数数
    count:数的次数
    nums:小孩的个数
    其中,创建一个临时节点,让其指向该环形链表的最后一个节点

     */
    public void outChildren(int startNo, int count, int nums) {
        Children temp = first;
        if (startNo < 1 || count >= nums || temp == null) {
            System.out.println("参数有误,请重试!");
        }
        //将temp指向最后一个节点
        while (true) {

            if (temp.getNext() == first) {

                break;
            }
            temp = temp.getNext();
        }
        //首先确认要从第几位小孩开始数数
        //first和temp同时移动 startNo - 1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            temp = temp.getNext();
        }
       //first指向的节点为temp指针的next 即temp.getNext()=first
        while (true) {

            if(temp == first){//当temp和first相等时,即说明该链表中只剩下唯一一个节点
                break;
            }
            //根据数的次数,first和temp同时移动 count - 1 次
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                temp = temp.getNext();
            }
            System.out.println("本次出圈的小孩编号为"+first.getNo());
            first = first.getNext();
            temp.setNext(first);

        }
        System.out.println("最后留在该链表中的小孩编号为:"+first.getNo());


    }

}

class Children {
    private int no;
    private Children next;

    public Children(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Children getNext() {
        return next;
    }

    public void setNext(Children child) {
        this.next = child;
    }
}
