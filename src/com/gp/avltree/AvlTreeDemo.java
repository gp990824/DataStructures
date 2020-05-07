package com.gp.avltree;

/**
 * @author gp
 * @create 2020/1/8 15:22
 */

/**
 * 平衡二叉树是对二叉排序树的一个优化:如果二叉排序树总有一个子树,其另一个子树永远为空
 * 这时的二叉排序树就相当于一个单链表,遍历起来还要判断另一个子树是否为空,就很费时费力
 * 平衡二叉树的特点:根节点的左右两颗子树的高度差不超过1
 */
public class AvlTreeDemo {
    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        int[] arr = {-1, -2, 4, 5, 3, 2, 1};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        for (int i = 0; i < arr.length; i++) {
            tree.addNode(new Node(arr[i]));
        }

        tree.midOrder();
        System.out.println("左子树的高度为:" + tree.root.leftHeight());
        System.out.println("右子树的高度为:" + tree.root.rightHeight());
        System.out.println(tree.root.height());
        System.out.println(tree.root);

    }
}

class AvlTree {
    Node root;

    //中序遍历
    public void midOrder() {
        if (root != null) {
            root.midOrder();
        }
    }

    //向二叉排序树添加节点
    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //右旋转
    public void rightRotate() {
        Node temp = new Node(this.value);
        temp.right = this.right;
        temp.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = temp;
    }

    //左旋转
    public void leftRotate() {
        Node temp = new Node(this.value);
        temp.left = this.left;
        temp.right = this.right.left;
        this.value = this.right.value;
        this.right = this.right.right;
        this.left = temp;

    }

    //该节点左子树的高度
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        } else {
            return this.left.height();
        }
    }

    //该节点右子树的高度
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        } else {
            return this.right.height();
        }
    }

    //当前节点作为根节点的子树的高度
    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
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

    //添加节点
    public void addNode(Node node) {
        if (this.value >= node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.addNode(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.addNode(node);
            }
        }
        if (this.rightHeight() - this.leftHeight() > 1) {//如果右子树的高度大于左子树的高度
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {//右子树的左子树高度大于右子树的有子树的高度
                this.right.rightRotate();//先向右旋转
            }
            this.leftRotate();

            return;//直接返回,不用往下判断
        }
        if (this.leftHeight() - this.rightHeight() > 1) {
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                this.left.leftRotate();
            }

            this.rightRotate();
        }
    }


}
