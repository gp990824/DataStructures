import java.util.Arrays;

/**
 * @author gp
 * @create 2020/1/26 15:30
 */
//迪杰斯特拉算法,通过指定的顶点,求出从出发顶点到其他顶点的最短路径
public class Dijkstra {
    public static final int N = 100;

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        Graph graph = new Graph(data, weight);
        graph.showGraph();
        System.out.println("以下标为6作为起始节点最短路径为:");
//        graph.dijkstra(6);
        graph.dijkstra(2);
    }
}

class VisitedVertex {
    int[] alreadyArr;//记录各个顶点是否被访问过
    int[] preVisited;//每个下标对应的值为前一个顶点的下标,会动态更新
    int[] dis;//记录出发顶点到各个顶点的距离,求的最短路径会放在dis数组中

    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];

        Arrays.fill(dis, Dijkstra.N);
        this.alreadyArr[index] = 1;
        this.dis[index] = 0;

    }

    public boolean isVisited(int index) {
        return alreadyArr[index] == 1;
    }

    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    public void updatePre(int index, int len) {
        preVisited[index] = len;
    }

    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问节点,  G -> A
    public int updateArr() {
        int min = Dijkstra.N, index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }


}

class Graph {
    char[] data;
    int[][] weight;
    VisitedVertex vertex;

    public Graph(char[] data, int[][] weight) {
        this.data = data;
        this.weight = weight;
    }

    public void showGraph() {
        for (int[] ints : weight) {
            System.out.println(Arrays.toString(ints));
        }
    }


    //地杰斯特拉算法
    public void dijkstra(int index) {
        int temp = index;
        vertex = new VisitedVertex(data.length, index);
        update(index);
        for (int i = 0; i < data.length; i++) {
            index = vertex.updateArr();
            update(index);
        }

        //显示起始顶点到各个顶点的距离
        int[] dis = vertex.dis;
        for (int i = 0; i < data.length; i++) {
            if (i != temp) {
                System.out.println(data[i] + " 到 " + data[temp] + " 的路径为 " + dis[i]);
            }
        }
    }

    //更新index下标顶点到周围顶点距离和周围顶点的前驱结点
    public void update(int index) {
        int len;
        for (int j = 0; j < weight[index].length; j++) {
            len = vertex.getDis(index) + weight[index][j];
            if (!vertex.isVisited(j) && len < vertex.getDis(j)) {
                vertex.updateDis(j, len);
                vertex.updatePre(j, index);
            }
        }
    }

}
