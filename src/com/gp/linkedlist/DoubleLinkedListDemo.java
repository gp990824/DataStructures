package com.gp.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 h1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 h2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 h3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 h4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList double1 = new DoubleLinkedList();
//        double1.addHeroNode(h1);
//        double1.addHeroNode(h2);
//        double1.addHeroNode(h4);
//        double1.addHeroNode(h3);

        double1.insertHeroNode(h2);
        double1.insertHeroNode(h4);
        double1.insertHeroNode(h3);
        double1.insertHeroNode(h1);

        double1.show();

//        System.out.println("删除操作的链表为:");
//
//        double1.deleteHeroNode(4);
//        double1.show();

    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    //根据编号 插入节点
    public void insertHeroNode(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("该英雄已存在,请勿重复添加!");
        } else {
            if (temp.next != null) {//排空
                heroNode.next = temp.next;
                temp.next.pre = heroNode;
            }
            heroNode.pre = temp;
            temp.next = heroNode;

        }
    }

    //根据编号修改节点信息,编号不能修改
    public void update(HeroNode2 newHeroNode) {


        if (head.next == null) {
            System.out.println("链表为空,不能进行该操作!");
        }
        HeroNode2 temp = head.next;
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

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {//当要删除的节点恰好是链表的最后一个节点时,有可能会空指针异常
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.println("编号为" + no + "的英雄不存在,不能进行该操作!");
        }
    }

    /*
        添加节点,将节点添加到链表的末尾(不考虑编号顺序)
        当节点的next域为null时,该节点即为链表的末尾
         */
    public void addHeroNode(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {

                break;
            }
            temp = temp.next;
        }
        //当退出循环时,即说明temp指向链表的最后一个节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //显示链表的所有节点
    public void show() {
        //如果头结点的next域为null则说明链表没有数据
        if (head.next == null) {
            System.out.println("链表为空,无法显示数据");
            return;
        }
        HeroNode2 temp = head.next;
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

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
