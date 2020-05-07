import java.util.Arrays;

/**
 * @author gp
 * @create 2020/1/26 10:43
 */
//普利姆算法,最短修路问题,就是最小生成树问题
public class Prim {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verx = data.length;
        //邻接矩阵使用二维数组表示,使用较大的值表示两个顶点之间不连通
        int[][] weight = {
                {100, 5, 7, 100, 100, 100, 2},
                {5, 100, 100, 9, 100, 100, 3},
                {7, 100, 100, 100, 8, 100, 100},
                {100, 9, 100, 100, 100, 4, 100},
                {100, 100, 8, 100, 100, 5, 4},
                {100, 100, 100, 4, 5, 100, 6},
                {2, 3, 100, 100, 4, 6, 100}
        };

        MGraph graph = new MGraph(verx);
        MinTree tree = new MinTree();

        tree.createGraph(graph, verx, data, weight);
        tree.show(graph);
        System.out.println("--------------------");
        tree.prim(graph, 0);
    }
}

//创建最小生成树
class MinTree {
    public void createGraph(MGraph mGraph, int verx, char[] data, int[][] weight) {
        for (int i = 0; i < verx; i++) {
            mGraph.data[i] = data[i];
            for (int j = 0; j < verx; j++) {
                mGraph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void show(MGraph mGraph) {
        for (int[] link : mGraph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 普利姆算法,最小生成树
     *
     * @param mgraph
     * @param v      先从哪一个顶点开始
     */
    public void prim(MGraph mgraph, int v) {
        //记录每个节点的下标,判断是否被访问过
        int[] visited = new int[mgraph.verx];

        visited[v] = 1;

        //记录顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 100;
        for (int i = 0; i < mgraph.verx - 1; i++) {//当存在n个顶点时,必有n-1条边
            for (int j = 0; j < mgraph.verx; j++) {
                for (int k = 0; k < mgraph.verx; k++) {
                    if (visited[j] == 1 && visited[k] == 0 && mgraph.weight[j][k] < minWeight) {//记录最小的权值
                        minWeight = mgraph.weight[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }

            System.out.println("边<" + mgraph.data[h1] + "," + mgraph.data[h2] + ">   权值为:" + mgraph.weight[h1][h2]);
            visited[h2] = 1;
            minWeight = 100;

        }
    }
}

//无向图
class MGraph {
    int verx;//表示图的顶点
    char[] data;//存放节点的数量
    int[][] weight;//存放边,邻接矩阵

    public MGraph(int n) {
        this.verx = n;
        data = new char[n];
        weight = new int[n][n];
    }
}
