package com.gp.linkedlist;

/*
单向环形链表(头结点根据具体需求确定)
每个节点有两个域  分别为data域和next域
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode h2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode h3 = new HeroNode(3, "吴用", "智多星");
        HeroNode h4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList single1 = new SingleLinkedList();

        HeroNode H1 = new HeroNode(25, "zs", "二狗");
        HeroNode H2 = new HeroNode(20, "ls", "铁柱");
        HeroNode H3 = new HeroNode(40, "ww", "狗蛋");
        SingleLinkedList single2 = new SingleLinkedList();
        single2.insertHeroNode(H1);
        single2.insertHeroNode(H2);
        single2.insertHeroNode(H3);
//        single1.addHeroNode(h1);
//        single1.addHeroNode(h2);
//        single1.addHeroNode(h3);
//        single1.addHeroNode(h4);
        single1.insertHeroNode(h1);
        single1.insertHeroNode(h4);
        single1.insertHeroNode(h2);
        single1.insertHeroNode(h3);
//        single1.deleteHeroNode(3);
//        single1.deleteHeroNode(1);


        single1.show();
        System.out.println("-----------------");
        single2.show();

        System.out.println("合并以上的环形单向链表");
//        joinLinkedList(single1,single2);
        SingleLinkedList single3 = new SingleLinkedList();
        single3.getHead().next = joinLinkedList(single1, single2).next;
        single3.show();


//        System.out.println("反转后的单链表为:");
//        single1.reverserList();
//        single1.show();

//        System.out.println("链表的有效节点个数为:"+single1.getlength());
//        System.out.println("倒数第二个节点为:"+single1.getLaseIndexNode(2));
//        System.out.println("操作过后的链表为:");
//        single1.update(new HeroNode(2,"小卢","yql"));
//        single1.deleteHeroNode(4);
//        single1.show();


    }

    //合并两个有序的单向链表,并且合并后的链表依旧有序

    public static HeroNode joinLinkedList(SingleLinkedList s1, SingleLinkedList s2) {
        HeroNode temp1 = s1.head.next;
        HeroNode temp2 = s2.head.next;
        HeroNode next1;
        HeroNode next2;

        HeroNode newHead = new HeroNode(0, "", "");
        if (s1.getlength() >= s2.getlength()) {
            newHead.next = temp1;
            while (temp2 != null) {
                next2 = temp2.next;
                while (true) {
                    if (temp1.next == null) {
                        break;
                    }
                    if (temp1.next.no > temp2.no) {
                        break;
                    }
                    temp1 = temp1.next;
                }
                temp2.next = temp1.next;
                temp1.next = temp2;

                temp2 = next2;
            }
        }else{
            newHead.next = temp2;
            while (temp1 != null) {


                next1 = temp1.next;
                while (true) {
                    if (temp2 == null) {
                        break;
                    }
                    if (temp2.next.no > temp1.no) {
                        break;
                    }
                    temp2 = temp2.next;
                }
                temp1.next = temp2.next;
                temp2.next = temp1;

                temp1 = next1;
            }
        }
        return newHead;
    }
}


//创建单向链表
class SingleLinkedList {
    HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /*
        添加节点,将节点添加到链表的末尾(不考虑编号顺序)
        当节点的next域为null时,该节点即为链表的末尾
         */
    public void addHeroNode(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {

                break;
            }
            temp = temp.next;
        }
        //当退出循环时,即说明temp指向链表的最后一个节点
        temp.next = heroNode;
    }


    //反转该单链表
    public void reverserList() {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode temp = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        /*
        思路:先创建一个新的头结点
        在遍历原来的链表,temp每移动一次就取下一个节点放在新的头结点的最前端
        先将temp.next指向reverseHead.next
        在将reverseHead.next指向temp
        最后将原来的头结点的next指向新的头结点的next
         */
        while (temp != null) {

            next = temp.next;

            temp.next = reverseHead.next;
            reverseHead.next = temp;
            temp = next;
        }
        head.next = reverseHead.next;
    }

    //获取链表的倒数第index个节点
    //当链表遍历到 size-index时 temp所指向的位置即为倒数第index个节点
    public HeroNode getLaseIndexNode(int index) {
        if (head.next == null) {
            return null;
        }

        HeroNode temp = head.next;
        int size = getlength();
        if (index <= 0 || index > size) {
            System.out.println(index + "为非法数据!");
        }

        for (int i = 1; i <= size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //获取链表的有效节点个数
    public int getlength() {
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = head.next;

        int num = 1;
        while (temp.next != null) {
            num++;
            temp = temp.next;
        }
        return num;
    }

    //根据编号 插入节点
    public void insertHeroNode(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;//用于判断该链表是否存在该编号的节点(默认为false)
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果temp的下一个节点大于插入的节点的编号,则在temp后插入该节点

            if (temp.next.no > heroNode.no) {

                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;//说明该节点已存在
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("该英雄已存在,请勿重复添加!");
        } else {
            heroNode.next = temp.next;

            temp.next = heroNode;

        }
    }

    //根据编号修改节点信息,编号不能修改
    public void update(HeroNode newHeroNode) {


        if (head.next == null) {
            System.out.println("链表为空,不能进行该操作!");
        }
        HeroNode temp = head.next;
        boolean flag = false;//用于判断是否存在改编号的英雄
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.println("不存在编号为" + newHeroNode.nickname + "的英雄");
        }

    }

    //根据编号删除指定英雄
    public void deleteHeroNode(int no) {

        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("编号为" + no + "的英雄不存在,不能进行该操作!");
        }
    }

    //显示链表的所有节点
    public void show() {
        //如果头结点的next域为null则说明链表没有数据
        if (head.next == null) {
            System.out.println("链表为空,无法显示数据");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            //当temp为null时  表示temp指向最后一个节点
            if (temp == null) {

                break;
            }
            System.out.println(temp);

            temp = temp.next;

        }
    }
}

//创建节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
