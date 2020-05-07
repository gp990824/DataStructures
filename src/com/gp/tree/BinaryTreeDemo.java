package com.gp.tree;

/**
 * @author gp
 * @create 2020/1/5 12:04
 */
//二叉树
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node zs = new Node(1, "zs");
        Node ls = new Node(2, "ls");
        Node ww = new Node(3, "ww");
        Node zl = new Node(4, "zl");
        Node we = new Node(5, "we");
        tree.setRoot(zs);
        zs.setLeft(ls);
        zs.setRight(ww);
        ww.setLeft(we);
        ww.setRight(zl);
        System.out.println("删除之前的树,前序遍历出的结果为:");
        tree.preOrder();
        tree.delNodeById(5);
        System.out.println("删除之后的树,前序遍历出的结果为:");
        tree.preOrder();
//        System.out.println("中序遍历:");
//        tree.midOrder();
//        System.out.println("后序遍历:");
//        tree.lastOrder();
//        System.out.println(tree.queryNodeByPreOrder(5));

    }
}

//二叉树的节点
class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //前序遍历查找
    public Node queryNodeByPreOrder(int id) {
        Node node = null;
        if (this.id == id) {
            return this;
        }
        if (this.left != null) {
            node = this.left.queryNodeByPreOrder(id);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.queryNodeByPreOrder(id);
        }
        if (node != null) {
            return node;
        }
        return node;
    }

    /*
     * 如果要删除的节点时叶子节点,则使该叶子节点的父节点.left(right)=null
     * 如果不是叶子节点,则直接删除该子树
     */
    //根据id删除节点
    public void delNodeById(int id) {

        if (this.left != null && this.left.id == id) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNodeById(id);
        }
        if (this.right != null) {
            this.right.delNodeById(id);
        }
    }

    //中序遍历
    public void midOrder() {

        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    //中序遍历查找
    public Node queryNodeByMidOrder(int id) {
        Node node = null;

        if (this.left != null) {
            node = this.left.queryNodeByMidOrder(id);
        }
        if (node != null) {
            return node;
        }
        if (this.id == id) {
            return this;
        }
        if (this.right != null) {
            node = this.right.queryNodeByMidOrder(id);
        }
        if (node != null) {
            return node;
        }
        return node;


    }

    //后续遍历
    public void lastOrder() {
        if (this.left != null) {
            this.left.lastOrder();
        }

        if (this.right != null) {
            this.right.lastOrder();
        }
        System.out.println(this);
    }

    //后序遍历查找
    public Node queryNodeByLastOrder(int id) {
        Node node = null;

        if (this.left != null) {
            node = this.left.queryNodeByLastOrder(id);
        }
        if (node != null) {
            return node;
        }

        if (this.right != null) {
            node = this.right.queryNodeByLastOrder(id);
        }
        if (node != null) {
            return node;
        }
        if (this.id == id) {
            return this;
        }
        return node;


    }
}

//创建二叉树
class BinaryTree {
    private Node root;//根节点

    public void setRoot(Node root) {
        this.root = root;
    }


    //根据id删除节点
    public void delNodeById(int id){
        if (root != null) {
           if(root.getId() == id){
               root = null;
           }
           root.delNodeById(id);
        }else {
            System.out.println("this tree is null, can not delete !");
        }
    }
    //调用前序遍历
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        }
    }

    //前序遍历查找
    public Node queryNodeByPreOrder(int id) {
        return root.queryNodeByPreOrder(id);
    }

    //调用中序遍历
    public void midOrder() {
        if (root != null) {
            root.midOrder();
        }
    }

    //中序遍历查找
    public Node queryNodeByMidOrder(int id) {
        return root.queryNodeByMidOrder(id);
    }

    //调用后序遍历
    public void lastOrder() {
        if (root != null) {
            root.lastOrder();
        }
    }

    //后续遍历查找
    public Node queryNodeByLastOrder(int id) {
        return root.queryNodeByLastOrder(id);
    }
}
