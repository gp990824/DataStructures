package com.gp.hashtable;

/**
 * @author gp
 * @create 2020/1/4 20:47
 */
//哈希表
public class HashTableDemo {
    public static void main(String[] args) {
        Emp zs = new Emp(1, "zs");
        Emp ls = new Emp(2, "ls");
        EmpLinkedList linkedList = new EmpLinkedList();
        HashTable table = new HashTable(7);
        table.add(zs);
        table.add(ls);

//        System.out.println(table.queryEmp(3));
        table.delEmp(1);
        table.showHashTab();
    }
}
//创建哈希表
class HashTable{
    EmpLinkedList[] tab ;//创建哈希表
    int size;
    public HashTable(int size){
        this.size = size;
        tab = new  EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            tab[i] = new EmpLinkedList();
        }
    }
    //根据id删除员工节点
    public void delEmp(int id){
        int i = hashFun(id);
        tab[i].delEmp(id);
    }
    //添加员工节点
    public  void add(Emp emp){
        int empNo = hashFun(emp.id);//根据员工的id,确定将其放入哪一条链表中
        tab[empNo].addEmp(emp);
    }

    //根据id查找员工信息
    public Emp queryEmp(int id){
        int i = hashFun(id);
        return tab[i].queryEmp(id);
    }
    //遍历哈希表
    public void showHashTab(){
        for (int i = 0; i < size; i++) {
            System.out.print("第"+(i+1)+"条链表:");
            tab[i].showEmps();
            System.out.println();
        }
    }

    //编写散列函数,使用一个简单的取余法
    public  int hashFun(int id){
        return id % size;
    }
}
//员工信息
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
//创建员工链表
class EmpLinkedList{
    Emp head = new Emp(0,"");//头结点,直接指向第一个员工节点

    //添加员工(默认添加到链表的最后)
    public  void addEmp(Emp emp){
        Emp temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = emp;
    }
    //根据id,删除员工信息
    public void delEmp(int id){
        Emp temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.id == id){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }
    //显示该链表的所有员工节点
    public  void showEmps(){
        Emp temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.print(temp);
            temp = temp.next;
        }
    }
    //根据员工编号查找员工信息
    public Emp queryEmp(int id){
        Emp temp = head;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.id == id){
                break;
            }
            temp = temp.next;
        }
        return temp;
    }
}