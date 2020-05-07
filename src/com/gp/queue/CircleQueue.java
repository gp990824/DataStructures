package com.gp.queue;

import java.util.Scanner;

//测试环形队列
public class CircleQueue {
    public static void main(String[] args) {
        MyCircleQueue myCircleQueue = new MyCircleQueue(4);
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        char c = ' ';
        while (loop) {
            System.out.println("-----------------------");
            System.out.println("s(show): 查看队列所有元素");
            System.out.println("a(add):  向队列添加元素");
            System.out.println("g(get):  取出队列头元素");
            System.out.println("h(head): 查看队列头元素");
            System.out.println("e(exit): 退出循环");
            System.out.println("输入指令:");
            c = sc.next().charAt(0);//接受一个字符
            switch (c) {
                case 's':
                    myCircleQueue.showArr();
                    break;
                case 'a':
                    System.out.println("请输入你要添加的数:");
                    int num = sc.nextInt();
                    myCircleQueue.addnum(num);
                    break;
                case 'g':
                    try {
                        System.out.println("取出的数据为:" + myCircleQueue.getnum());
                    } catch (Exception e) {
                        System.out.println("队列为空,无法取出数据!");
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("头数据为:" + myCircleQueue.headnum());
                    } catch (Exception e) {
                        System.out.println("队列已空,无法查看头数据!");
                    }

                    break;
                case 'e':
                    loop = false;
                    sc.close();
                    System.out.println("退出循环!");
                    break;
                default:
                    System.out.println("指令有误,请重新输入!");
                    break;

            }
        }
    }

}

class MyCircleQueue {
    private int maxSize;
    int start;
    int end;//该指针指向队列最后元素的后一个位置
    int arr[];

    /*
    start 默认值为 0
    end 默认值也为 0
    start 指向队列的第一个元素
    end 指向队列的最后一个元素的前一个位置
     */
    public MyCircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //当 (end+1)%Maxsize == start  时  队列已满
    public boolean isfull() {
        return (end + 1) % maxSize == start;
    }

    //当start == end 时队列为空
    public boolean isEmety() {
        return start == end;
    }

    public void addnum(int num) {
        if (isfull()) {
            System.out.println("队列已满,请不要添加数据!");
            return;
        }

        arr[end] = num;
        //将end下标后移  值为  (end + 1)%Maxsize
        end = (end + 1) % maxSize;

    }

    public int getnum() {
        if (isEmety()) {
            throw new RuntimeException("队列为空,无法取出数据!");

        }
        /*
       先将第一个元素保存到一个临时变量中
       在将start后移
       然后再返回第一个元素
       其中start后移的值为 (start+1)%Maxsize
         */
        int temp = arr[start];
        start = (start + 1) % maxSize;
        return temp;
    }

    /*
        显示所有的元素
        先从start开始遍历
        遍历的个数为 start+队列有效元素的格式
     */
    public void showArr() {
        if (isEmety()) {
            System.out.println("队列为空,没有数据!");
            return;
        }
        for (int i = start; i < start + size(); i++) {
            System.out.println("arr[" + i % maxSize + "]=" + arr[i % maxSize]);
        }
    }

    /*
    求队列中的有效元素的个数
    其中: 有效格个数为 (start+Maxsize-end)%Maxsize
     */
    public int size() {
        if (start > end){
            return end + maxSize - start;
        }else if (start == end){
            return 1;
        }else {
            return end - start;
        }
//        return (end + maxSize - start) % maxSize;
    }

    public int headnum() {
        if (isEmety()) {
            throw new RuntimeException("队列已空,无法查看头数据!");
        }
        return arr[start];
    }
}

