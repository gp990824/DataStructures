package com.gp.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
将中缀表达式转换成后缀表达式:
1.初始化两个栈,运算符栈s1和储存中间结果的栈s2
2.从左至右扫描中缀表达式
3.遇到操作数时,将其压入s2
4.如果遇到运算符,比较其与s1栈运算符的优先级
    1.如果s1为空,或栈顶运算符为左括号"(" ,则直接将此运算符入栈
    2.否则,若优先级比栈顶运算符高,也将运算符入栈
    3.否则,将s1栈顶的运算符弹出并压入到s2中,再次转到4.1与s1中新的栈顶运算符相比较
    注意:括号不算运算符
5.遇到括号时:
    1.若果是左括号"(",则直接压入s1
    2.如果是右括号")",则依次弹出s1栈顶的运算符,并压入s2,直到遇到左括号为止,此时将这一对括号丢弃
6.重复步骤2至5,直到表达式的最右边
7.将s1中剩余的运算符一次弹出并压入s2
8.一次弹出s2中的元素并输出,结果的逆序即为该中缀表达式所对应的逆波兰表达式(后缀表达式)
 */
public class changeToReversePoland {
    public static void main(String[] args) {
        String middleExpression = "1+((2+3)*4)-5";
        //现将中缀表达式放入List中
        System.out.println(getMiddleList(middleExpression));
        System.out.println(parseLastExpression(getMiddleList(middleExpression)));

    }

    //获取中缀表达式的list
    public static List<String> getMiddleList(String expression) {
        int i = 0;//需要一个指针,对每一个字符进行操作
        String temp = "";//用于储存多位数
        List<String> list = new ArrayList<>();
        do {

            if (isOper(expression.charAt(i))) {//如果字符为非数字,直接放入list中
                temp += expression.charAt(i);
                list.add(temp);
                i++;
                temp = "";
            } else {//如果为数字
                temp += expression.charAt(i);
                while (true) {
                    if (i == expression.length() - 1) {
                        break;
                    } else if (!isOper(expression.charAt(i + 1))) {//如果下一个字符也为数字
                        temp += expression.charAt(++i);
                    } else {
                        break;
                    }
                }
                i++;
                list.add(temp);
                temp = "";
            }

        } while (i < expression.length());//当不大于表达式的长度时,就使i遍历该表达式字符串
        return list;
    }

    //!!!将中缀表达式转换成为后缀表达式
    public static List<String> parseLastExpression(List<String> list) {
        Stack<String> s1 = new Stack<>();//用于暂时存放运算符
        //由于s2没有出栈操作,并且最终的结果还是逆序的,建议使用List
        List<String> s2 = new ArrayList<>();//用于存放后缀表达式的结果
        for (String item : list) {
            if (item.matches("\\d+")) {//如果为数字
                s2.add(item);
            } else if (item.equals("(")) {//如果是左括号,直接压入s1
                s1.push(item);
            } else if (item.equals(")")) {//如果为右括号

                while (!s1.peek().equals("(")) {//依次弹出s1栈并加入到s2
                    s2.add(s1.pop());
                }
                s1.pop();//舍弃"(" (完成一对括号的匹配)

            } else {
               /*
               4.如果遇到运算符
                    1.如果s1为空,或栈顶运算符为左括号"(" ,则直接将此运算符入s1栈
                    2.否则,若优先级比s1栈顶运算符高,也将运算符入s1栈
                    3.否则,将s1栈顶的运算符弹出并压入到s2中,再次转到4.1与s1中新的栈顶运算符相比较
                    注意:括号不算运算符
                */

                while (true) {
                    if (s1.size() == 0 || s1.peek().equals("(")) {
                        break;
                    } else if (whichOperBigger(item) > whichOperBigger(s1.peek())) {
                        break;
                    }
                    s2.add(s1.pop());
                }
                s1.push(item);

            }
        }
        while (s1.size() != 0) {//依次将s1中的运算符加入到s2
            s2.add(s1.pop());
        }

        return s2;
    }

    //判断扫描到的字符是否为非数字
    public static boolean isOper(char c) {
        return c == '*' || c == '/' || c == '+' || c == '-' || c == '(' || c == ')';
    }

    //判断运算符的优先级
    public static int whichOperBigger(String oper) {
        int sum = 0;
        if (oper == "*" || oper == "/") {
            sum = 1;
        } else if (oper == "+" || oper == "-") {
            sum = 2;
        }
        return sum;
    }
}
