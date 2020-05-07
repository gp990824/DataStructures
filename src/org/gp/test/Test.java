package org.gp.test;

/**
 * @author gp
 * @create 2019/12/21 16:22
 */
public class Test {
    public static void main(String[] args) {
//        System.out.println(print(8));

//        fornum(3);
//        String str = "woainia";
//        String[] s = new String[3];
//        int index = 0;
//        for (int i = 0; i < str.length(); i += 3) {
//            if(i+3>str.length()){
//                s[index] = str.substring(i);
//            }else{
//                s[index] = str.substring(i,i+3);
//                index++;
//            }
//
//        }
//        System.out.println(Arrays.toString(s));
//        String str1 = "123";
//        String str2 = "123";
//        String str3 = new String("123");
//        String str4 = new String("123");
//        System.out.println(str1==str2);
//        System.out.println(str1==str3);
//        System.out.println(str1.equals(str2));
//        System.out.println(str1.equals(str3));
//        System.out.println(str3==str4);
//        System.out.println(str3.equals(str4));

//        System.out.println(isInteger("1"));
        System.out.println(isNumber(186));
    }

    public static int print(int i) {
        if (i == 1) {
            return 1;
        }
        return print(i - 1) * i;
    }

    public static void fornum(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(10 ^ n);

        }
    }

    public static boolean isInteger(String i){
        return (Integer)Integer.parseInt(i) instanceof Integer;
    }
    public static boolean isNumber(int i){
        String str = "18[0-9]";
        return String.valueOf(i).matches(str);
    }
}

class First{
    private String name;

    public  First(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
