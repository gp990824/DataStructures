package com.gp.queue;

import java.util.Scanner;

public class Queue {
    public static void main(String[] args) {
        myQueue queue = new myQueue(3);
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
                    queue.showArr();
                    break;
                case 'a':
                    System.out.println("请输入你要添加的数:");
                    int num = sc.nextInt();
                    queue.addnum(num);
                    break;
                case 'g':
                    try {
                        System.out.println("取出的数据为:" + queue.getnum());
                    } catch (Exception e) {
                        System.out.println("队列为空,无法取出数据!");
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("头数据为:" + queue.headnum());
                    } catch (Exception e) {
                        System.out.println("队列已空,无法查看头数据!");
                    }
                    break;
                case 'e':
                    loop = false;
                    sc.close();
                    System.out.println("退出循环!");
                default:
                    break;

            }
        }
    }
}

class myQueue {
    private int Maxsize;
    int start;
    int end;
    int arr[];

    public myQueue(int Maxsize) {
        this.Maxsize = Maxsize;
        arr = new int[Maxsize];
        start = -1;
        end = -1;
    }

    public boolean isfull() {
        return end == Maxsize - 1;
    }

    public boolean isEmety() {
        return start == end;
    }

    public void addnum(int num) {
        if (isfull()) {
            System.out.println("队列已满,请不要添加数据!");
        }
        end++;
        arr[end] = num;
    }

    public int getnum() {
        if (isEmety()) {
            throw new RuntimeException("队列为空,无法取出数据!");

        }
        start++;
        int temp = arr[start];
        arr[start] = 0;
        return temp;
    }

    public void showArr() {
        for (int i = 0; i < Maxsize; i++) {
            System.out.println("队列中第" + (i + 1) + "个元素为:" + arr[i]);
        }
    }

    public int headnum() {
        if (isEmety()) {
            throw new RuntimeException("队列已空,无法查看头数据!");

        }
        return arr[start + 1];
    }
}
