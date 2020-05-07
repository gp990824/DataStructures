import java.util.Arrays;

/**
 * @author gp
 * @create 2020/1/26 20:53
 */
//弗洛伊德算法,每一个顶点都是出发访问点,求出每一个顶点到其他顶点的最短路径
public class Floyd {
    public static final int N = 100;

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        FGraph graph = new FGraph(data, weight, data.length);
        graph.floyd();
        graph.show();


    }
}

class FGraph {
    char[] vertex;
    int[][] dis;//用于存放各个顶点到其他顶点的距离
    int[][] pre;//储存前驱结点

    public FGraph(char[] vertex, int[][] dis, int lenght) {
        this.vertex = vertex;
        this.dis = dis;
        this.pre = new int[lenght][lenght];
        for (int i = 0; i < lenght; i++) {
            Arrays.fill(pre[i], i);

        }
    }

    public void show() {
        System.out.println("各个顶点的距离表:");
//        for (int[] di : dis) {
//            System.out.println(Arrays.toString(di));
//        }
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis[i].length; j++) {
                if (i != j) {
                    System.out.print(vertex[i] + " 到 " + vertex[j] + " 的最短距离为: " + dis[i][j]);
                    if(j != dis[i].length -1){
                        System.out.print(", ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("顶点的前驱关系:");
        for (int[] ints : pre) {
            System.out.println(Arrays.toString(ints));
        }
    }

    //弗洛伊德算法的具体实现
    public void floyd() {
        int len;//变量保存距离
        //对中间顶点遍历,
        for (int i = 0; i < dis.length; i++) {
            //从i顶点出发,
            for (int j = 0; j < dis.length; j++) {
                for (int k = 0; k < dis.length; k++) {
                    //求出从j顶点出发,经过i中间结点,到达k顶点的距离
                    len = dis[j][i] + dis[i][k];
                    if (len < dis[j][k]) {//如果len小于连个节点的直连距离,替换
                        dis[j][k] = len;
                        pre[j][k] = pre[i][k];//更新前驱结点
                    }
                }
            }
        }
    }
}
