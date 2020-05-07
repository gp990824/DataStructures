package com.gp.stack;

//用数组模拟栈
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);

        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);

        arrayStack.pop();
        arrayStack.show();
    }
}

class ArrayStack {
    private int maxSize;
    private int stack[];
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈是否为空
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈是否满
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈操作
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满,无法进行该操作!");
        }
        stack[++top] = num;
    }

    //出栈操作
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,无法进行该操作!");
        }
        int value = stack[top--];
        return value;
    }

    //显示栈的所有元素,注意:先从栈顶开始遍历,直至栈底
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空,无法进行该操作!");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "] = " + stack[i]);
        }
    }
}