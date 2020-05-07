package com.gp.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author gp
 * @create 2020/1/9 21:00
 */
//图
public class Graph {
    ArrayList<String> list;//存储定点的集合
    int[][] arr;//存储图对应的邻结矩阵
    int num;//表示边的数目
    boolean[] isVisited;//判断该顶点是否被访问

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] strs = {"A", "B", "C", "D", "E"};//顶点的数组
        for (String str : strs) {
            graph.addVertex(str);
        }
        //添加边
        graph.addEdge(0, 1, 1);//A-B
        graph.addEdge(0, 2, 1);//A-C
        graph.addEdge(1, 2, 1);//B-C
        graph.addEdge(1, 3, 1);//B-D
        graph.addEdge(1, 4, 1);//B-E

        System.out.println("图对应的邻结矩阵为:");
        graph.showArray();
//        System.out.println("图的深度遍历结果为:");
//        graph.depthFirstSearch();
        System.out.println("图的广度遍历结果为:");
        graph.broadFirstSearch();

    }

    /**
     * 图的广度遍历(broad first search)
     *
     * @param isVisited 顶点是否被访问过
     * @param i         顶点的下标
     */
    public void broadFirstSearch(boolean[] isVisited, int i) {
        Integer u = 0;//表示队列的头元素的下标
        Integer w = 0;//邻接顶点的下标
        LinkedList<Integer> linkedList = new LinkedList<>();
        System.out.print(list.get(i) + "->");
        isVisited[i] = true;
        //将顶点加入队列
        linkedList.addLast(i);
        while (!linkedList.isEmpty()) {
            u = linkedList.removeFirst();//取出队列的头结点下标
            w = getNextVertext(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(list.get(w) + "->");
                    isVisited[w] = true;
                    linkedList.addLast(w);
                }
                //以u为前驱点,查找下一个邻接点
                w = getNextNextVertex(u, w);
            }
        }
    }

    //重载上面的方法
    public void broadFirstSearch() {
        for (int i = 0; i < list.size(); i++) {
            if (!isVisited[i]) {
                broadFirstSearch(isVisited, i);
            }
        }
    }

    /**
     * 图的深度遍历(depth first search)
     *
     * @param isVisited 顶点是否被访问过
     * @param i         顶点的下标
     */
    public void depthFirstSearch(boolean[] isVisited, int i) {
        System.out.print(list.get(i) + "->");
        isVisited[i] = true;
        int nextVertext = getNextVertext(i);//获取邻接顶点的下标
        while (nextVertext != -1) {
            if (!isVisited[nextVertext]) {//如果该邻接顶点没有被访问
                depthFirstSearch(isVisited, nextVertext);
            }
            //如果邻接顶点已被访问,则使该邻接顶点的下标指向邻接顶点的邻接顶点的下标
            nextVertext = getNextNextVertex(i, nextVertext);
        }
    }

    //重载上面的方法
    public void depthFirstSearch() {
        for (int i = 0; i < list.size(); i++) {
            if (!isVisited[i]) {
                depthFirstSearch(isVisited, i);
            }
        }
    }

    //获取该顶点的邻接顶点的下标
    public int getNextVertext(int v) {
        for (int i = 0; i < list.size(); i++) {
            if (arr[v][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    //获取邻接顶点的下一个邻接顶点的下标
    //v: 该顶点的下标 w:邻接顶点的下标
    public int getNextNextVertex(int v, int w) {
        for (int i = w + 1; i < list.size(); i++) {
            if (arr[v][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    //遍历存储图对应的邻结矩阵
    public void showArray() {
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 构造器
     *
     * @param n 定点的数量
     */
    public Graph(int n) {
        arr = new int[n][n];
        list = new ArrayList<>();
        num = 0;
        isVisited = new boolean[n];
    }

    //插入顶点
    public void addVertex(String str) {
        list.add(str);
    }

    /**
     * 添加边
     *
     * @param a      表示顶点的下标即是第几个定点
     * @param b      表示第二个定点对应的下标
     * @param weight 边的值
     */
    public void addEdge(int a, int b, int weight) {
        arr[a][b] = weight;
        arr[b][a] = weight;
        num++;
    }
}
