package com.gp.binarysorttree;

/**
 * @author gp
 * @create 2020/1/7 19:23
 */
//二叉排序树
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree sortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            sortTree.addNode(new Node(arr[i]));
        }
        sortTree.addNode(new Node(22));
        sortTree.addNode(new Node(23));
        sortTree.midOrder();
//        System.out.println(sortTree.queryNode(12));
//        System.out.println(sortTree.queryParentNode(12));
//        sortTree.deleteNode(7);
//        sortTree.deleteNode(3);
//        sortTree.deleteNode(1);
//        sortTree.deleteNode(5);
//        sortTree.deleteNode(9);
//        sortTree.deleteNode(10);
//        sortTree.deleteNode(12);
//        System.out.println("删除节点后:");
//        sortTree.midOrder();
//        System.out.println("二叉排序树的根节点为:"+sortTree.root);
//        sortTree.midOrder();
//        System.out.println("二叉排序树的根节点为:" + sortTree.root);
    }
}

//创建二叉排序树
class BinarySortTree {
    Node root;

    //向二叉排序树添加节点
    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }

    //中序遍历
    public void midOrder() {
        if (root != null) {
            root.midOrder();
        }
    }

    //根据value查找节点
    public Node queryNode(int value) {
        if (root != null) {
            return root.queryNode(value);
        }
        return null;
    }

    //找出父节点
    public Node queryParentNode(int value) {
        if (root != null) {
            return root.queryParentNode(value);
        }
        return null;
    }

    /**
     * 分三种情况:
     * 1.要删除的节点为叶子节点
     * 2.要删除的节点有一棵子树
     * 3.要删除的接电脑有两颗子树
     *
     * @param value 传进来的要删除的节点的值
     */
    //删除节点
    public void deleteNode(int value) {
        Node target = queryNode(value);
        if (target == null) {
            System.out.println("找不到要删除的节点");
            return;
        }
        Node parent = queryParentNode(value);
        if (target.left == null && target.right == null) {//如果是叶子节点
            if (target == root) {//如果此时只有根节点
                root = null;
            } else if (parent.left.value == value && parent.left != null) {//如果目标节点在父节点的左子树上
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (target.left != null && target.right != null) {//如果要删除的节点有两棵子树

            Node minValueNode = target.right.queryMinNode();//最小权值的节点
            int temp = minValueNode.value;
            deleteNode(temp);
            target.value = temp;

        } else {//最后一种情况,要删除的节点有一棵子树

            if (target.left != null) {//如果有左子树
                if (parent != null) {//如果此时要删除的是根节点
                    if (parent.left.value == value && parent.left != null) {//如果目标节点出现在父节点的左子树上
                        parent.left = target.left;
                    } else {
                        parent.right = target.left;
                    }
                } else {
                    root = target.left;
                }
            } else {//如果目标节点有右子树
                if (parent != null) {
                    if (parent.left.value == value && parent.left != null) {//如果目标节点出现在父节点的左子树上
                        parent.left = target.right;
                    } else {
                        parent.right = target.right;
                    }
                } else {
                    root = target.right;
                }
            }
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


    //根据value查找节点
    public Node queryNode(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            if (this.left != null) {
                return this.left.queryNode(value);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.queryNode(value);
            } else {
                return null;
            }
        }
    }

    //查找出要查找的节点的父节点
    public Node queryParentNode(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else if (this.value >= value && this.left != null) {
            return this.left.queryParentNode(value);
        } else if (this.value < value && this.right != null) {
            return this.right.queryParentNode(value);
        } else {
            return null;
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
    }


    //找出value值最小的节点,就是该节点的右子树的左子树的左子树...的叶子节点
    public Node queryMinNode() {
        if (this.right == null && this.left == null) {//如果是叶子节点,直接返回
            return this;
        } else if (this.left != null) {//如果有左节点,向左递归
            return this.left.queryMinNode();
        } else {//只有右子树
            return this;
        }

    }
}
