import java.util.Arrays;

/**
 * @author gp
 * @create 2020/1/26 12:14
 */
//克鲁斯卡尔算法,同样用于解决最小生成树问题
public class Kruskal {
    public int edges;
    public char[] data;
    public int[][] weight;
    public static final int MAX = 100;

    public Kruskal(char[] data, int[][] weight) {
        this.data = data;
        this.weight = weight;
        //统计边得数量
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (this.weight[i][j] != MAX) {
                    edges++;//此时边的实际数量为 edge/2
                }
            }
        }
    }

    //显示邻接矩阵
    public void show() {
        for (int[] ints : weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    //创建边数组
    public EdgeData[] getEdgeData() {
        int index = 0;
        EdgeData[] edgeData = new EdgeData[edges / 2];
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (weight[i][j] != MAX) {
                    edgeData[index++] = new EdgeData(data[i], data[j], weight[i][j]);
                }
            }

        }
        return edgeData;
    }

    //将边按照权值从小到大排序
    public EdgeData[] sortEdgeData(EdgeData[] edgeData) {
        EdgeData temp;
        for (int i = 1; i < edgeData.length; i++) {
            for (int j = 0; j < edgeData.length - i; j++) {
                if (edgeData[j].weight > edgeData[j + 1].weight) {
                    temp = edgeData[j];
                    edgeData[j] = edgeData[j + 1];
                    edgeData[j + 1] = temp;
                }
            }
        }
        return edgeData;
    }

    //获取顶点对应的下标
    public int getPosition(char ch) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    //获取下标为i的顶点的终点的下标,当两个顶点的终点的下标一致时,说明构成回路
    public int getEnd(int[] end, int i) {
        while (end[i] != 0) {
            i = end[i];
        }
        return i;
    }

    //克鲁斯卡尔算法的具体实现
    public void kruskal() {
        int index = 0;
        int[] end = new int[edges / 2];
        EdgeData[] result = new EdgeData[edges / 2];//存储最终的边连接结果
        EdgeData[] datas = sortEdgeData(getEdgeData());//已排序好的边数组
        for (int i = 0; i < edges / 2; i++) {//遍历每条边
            int p1 = getPosition(datas[i].start);
            int p2 = getPosition(datas[i].end);

            int m = getEnd(end, p1);
            int n = getEnd(end, p2);

            if (m != n) {//如果不构成回路,则添加到最小生成树中
                end[m] = n;
                result[index++] = datas[i];
            }
        }
        System.out.println("最小生成树为:");
        for (int i = 0; i < index; i++) {
            System.out.println(result[i]);
        }
    }

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {MAX, 12, MAX, MAX, MAX, 16, 14},
                {12, MAX, 10, MAX, MAX, 7, MAX},
                {MAX, 10, MAX, 3, 5, 6, MAX},
                {MAX, MAX, 3, MAX, 4, MAX, MAX},
                {MAX, MAX, 5, 4, MAX, 2, 8},
                {16, 7, 6, MAX, 2, MAX, 9},
                {14, MAX, MAX, MAX, 8, 9, MAX}
        };
        Kruskal kruskal = new Kruskal(data, weight);
//        kruskal.show();
//        EdgeData[] edgeDatas = kruskal.getEdgeData();

//        System.out.println(Arrays.toString(kruskal.sortEdgeData(edgeDatas)));
        kruskal.kruskal();
    }
}

//边,它的对象表示一条边
class EdgeData {
    char start;
    char end;
    int weight;

    public EdgeData() {
    }

    public EdgeData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[start:" + start +
                ", end:" + end +
                ", weight:" + weight + "]";
    }
}