package org.gp.test;

/**
 * @author gp
 * @create 2019/12/21 14:09
 */
public  class Hello {

    static {
        System.out.println("静态方法区");
    }

    public Hello(){
        System.out.println("无参构造");
    }
    public Hello(String name){
        System.out.println("有参构造");
    }

    {
        System.out.println("非静态方法区!");
    }
    public static void main(String[] args) {
//        new Hello();
//        new Hello("zs");
//        Integer integer = new Integer(1);
//        System.out.println(integer.hashCode());
        int i = 64 >> 5;
        System.out.println(i);
    }
}
