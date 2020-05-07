package com.gp.stack;

/*
使用栈完成表达式的计算思路(首先创建两个栈,分别为数栈和符号栈)
1.通过一个index值(索引),来遍历表达式(该表达式为中缀表达式)
2.如果发现是一个数字,就直接入数栈
3.  如果符号栈为空,就直接入栈
    如果符号栈有操作符,就进行比较
        如果当前的操作符的优先级小于等于栈中(指的就是栈顶)的操作符
        就需要从数栈中pop出两个数字,在从符号栈中pop出一个运算符进行运算,在将结果入数栈
        然后将当前符号入符号栈
        如果当前操作符大于栈中的操作符,就直接入数栈
4.当表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的数和符号,并运算
5.最后在数栈只有一个数字时,该数即为运算结果

注意:这里没有考虑到括号
 */

/**
 * 一般先将中缀表达式转成后缀表达式在计算,这里直接计算中缀表达式
 */
public class Calculator {
    public static void main(String[] args) {
        String str = "52+22*6+3-2";
        int maxSize =str.length();
        CalcArrayStack numStack = new CalcArrayStack(maxSize);
        CalcArrayStack operStack = new CalcArrayStack(maxSize);
        int num1;
        int num2;
        int oper;
        int index = 0;
        int sum;
        String keepNums = "";//用于连接多位数的运算
        char c = ' ';//将每次扫描得到的char保存在c
        while (true) {
            c = str.substring(index, index + 1).charAt(0);//得到str的字符
            if (isOper(c)) {//如果是运算符
                if (!operStack.isEmpty()) {//如果字符栈不为空

                    //如果扫描得到的字符优先级小于等于栈运算符的优先级
                    if (cacllevel(c) <= cacllevel(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        sum = calcnums(num1, num2, oper);
                        numStack.push(sum);
                        operStack.push(c);
                    } else {
                        operStack.push(c);
                    }
                } else {
                    operStack.push(c);
                }

            } else {
//               numStack.push(c - 48);//入栈为字符不是数字,必须转换成数组,参照ASC2表
                keepNums += c;
                if (index == str.length() - 1) {
                    numStack.push(Integer.parseInt(keepNums));//将多位数入数栈
                } else {
                    if (isOper(str.substring(index + 1, index + 2).charAt(0))) {//如果下一个字符为运算符
                        numStack.push(Integer.parseInt(keepNums));//将多位数入数栈
                        keepNums = "";
                    }
                }


            }
            index++;
            if (index >= str.length()) {
                break;
            }
        }
        while (true) {//扫描完毕,运算
            if (operStack.isEmpty()) {//当字符栈为空时,说明运算结束
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            sum = calcnums(num1, num2, oper);
            numStack.push(sum);
        }

        sum = numStack.pop();

        System.out.println("表达式" + str + "的运算结果为:" + sum);

    }

    //用于判断扫描到的是否为运算符
    public static boolean isOper(int c) {
        return c == '*' || c == '/' || c == '+' || c == '-';
    }

    //用于计算
    public static int calcnums(int num1, int num2, int c) {
        int sum = 0;
        switch (c) {
            case '*':
                sum = num1 * num2;
                break;
            case '/':
                sum = num2 / num1;
                break;
            case '+':
                sum = num1 + num2;
                break;
            case '-':
                sum = num2 - num1;
                break;
        }
        return sum;
    }

//    //判断是否为括号
//    public static boolean isParentheses(int c) {
//        return c == '(' || c == ')';
//    }

    //判断运算符的优先级,完全由程序员自己定
    public static int cacllevel(int c) {
        if (c == '*' || c == '/') {
            return 1;
        } else if (c == '+' || c == '-') {
            return 0;
        } else {
            return -1;
        }
    }
}

class CalcArrayStack {
    private int maxSize;
    private int stack[];
    private int top = -1;

    public CalcArrayStack(int maxSize) {
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
        top++;
        stack[top] = num;
    }

    //出栈操作
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,无法进行该操作!");
        }
        int value = stack[top];
        top--;
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


    //查看栈顶的数据,但不将数据弹出栈
    public int peek() {
        return stack[top];
    }


}