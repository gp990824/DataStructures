package org.gp.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IntegerTest {
    static  Integer i ;

    public static void main(String[] args) {
//        Integer a = 100 , b = 100 , c = 200 , d = 200 ;
//        System.out.println(a == b);
//        System.out.println(c == d);
//
//
//        if(i == 0){
//            System.out.println(" 0 ");
//        }else{
//            System.out.println(" others ");
//        }
        String str = "+";
        System.out.println(str);

        ArrayList<String> list = new ArrayList<>();
        String str1="";
        list.add(0,"1");
        System.out.println(list);


        try {
            FileInputStream fileInputStream = new FileInputStream("hello.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
interface  A{

}
