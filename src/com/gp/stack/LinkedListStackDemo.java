package com.gp.stack;

//模拟链表实现栈
public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack();
        NumNode numNode1 = new NumNode(1);
        NumNode numNode2 = new NumNode(2);
        NumNode numNode3 = new NumNode(3);
        NumNode numNode4 = new NumNode(4);
        linkedListStack.push(numNode1);
        linkedListStack.push(numNode2);
        linkedListStack.push(numNode3);
        linkedListStack.push(numNode4);
        linkedListStack.pop();

        linkedListStack.show();
    }

}
class LinkedListStack{
    NumNode head = new NumNode(0);
    NumNode top = head;
    //栈是否为空
    public boolean isEmpty(){
        return top == head;
    }
    //入栈
    public void push(NumNode numNode){
        top.setNext(numNode);
        numNode.setPre(top);
        top = top.getNext();
    }
    //出栈
    public NumNode pop(){
        if(isEmpty()){{
            throw new RuntimeException("栈空,无法进行出栈操作!");
        }}
        NumNode value = top;
        top = top.getPre();
        return value;
    }
    //查看该栈的所有节点
    public void show(){
        if(isEmpty()){
            System.out.println("栈空,没有数据可以查看!");
            return;
        }
        while (true){

            if(top == head){
                break;
            }
            System.out.println("该节点编号为:"+top.getNo());
            top = top.getPre();
        }
    }
}
class NumNode{
    private  int no;
    private NumNode next;
    private NumNode pre;

    public NumNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public NumNode getNext() {
        return next;
    }

    public void setNext(NumNode next) {
        this.next = next;
    }

    public NumNode getPre() {
        return pre;
    }

    public void setPre(NumNode pre) {
        this.pre = pre;
    }
}
