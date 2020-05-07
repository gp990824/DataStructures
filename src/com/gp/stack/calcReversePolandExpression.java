package com.gp.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//利用栈计算逆波兰表达式(后缀表达式)
public class calcReversePolandExpression {
    public static void main(String[] args) {
        String expression = "30 4 + 5 * 6 -";
        //首先将该逆波兰表达式放进ArrayList中
        List<String> list = getList(expression);
        System.out.println("该逆波兰表达式" + expression + "的运算结果为:" + calculateExpression(list));
    }

    //将该逆波兰表达式放进ArrayList中
    public static List<String> getList(String expression) {
        String[] split = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (String item : split) {
            list.add(item);
        }
        return list;
    }

    //计算该逆波兰表达式
    public static int calculateExpression(List<String> list) {

        int sum = 0;
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            //如果是数,将其入栈
            if (item.matches("\\d+")) {//如果集合中该值为一个数
                stack.push(item);
            } else {
                //如果是运算符,就先将栈中的最上面两个数弹出计算然后放入栈中
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());

                sum = calcalateNums(num1, num2, item);
                stack.push(String.valueOf(sum));
            }

        }
        return Integer.parseInt(stack.pop());
    }

    public static int calcalateNums(int num1, int num2, String oper) {
        int sum = 0;
        switch (oper) {
            case "+":
                sum = num1 + num2;
                break;
            case "-":
                sum = num2 - num1;
                break;
            case "*":
                sum = num1 * num2;
                break;
            case "/":
                sum = num2 / num1;
                break;
            default:
                throw new RuntimeException("运算符不正常!");
        }
        return sum;
    }
}
