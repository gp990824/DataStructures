import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author gp
 * @create 2020/1/27 15:29
 */
//马踏棋盘算法
public class HorseChessBoard {
    public static int X;//棋盘的行列
    public static int Y;
    public static boolean[] visited;//记录该位置是否被访问过
    public static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = X;
        visited = new boolean[X * Y];
        int[][] chessboard = new int[X][Y];
        long start = System.currentTimeMillis();
        traversalChessBoard(chessboard, 0, 0, 1);
        long end = System.currentTimeMillis();
        System.out.println("马踏棋盘在" + X + "*" + Y + "的棋盘中耗时:(毫秒) " + (end - start));
        showChessBoard(chessboard);
    }

    public static void showChessBoard(int[][] chessBoard) {
        for (int[] ints : chessBoard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 完成马踏棋盘的具体算法
     *
     * @param chessboard 棋盘
     * @param x          马儿的x轴坐标
     * @param y          马儿的y轴坐标
     * @param step       是第几步,初值位置就是第一步
     */
    public static void traversalChessBoard(int[][] chessboard, int x, int y, int step) {
        chessboard[x][y] = step;
        visited[x * X + y] = true;
        //获取当前位置可以走的位置集合
        ArrayList<Point> points = next(new Point(y, x));
        sort(points);
        while (!points.isEmpty()) {
            Point point = points.remove(0);
            if (!visited[point.y * X + point.x]) {
                traversalChessBoard(chessboard, point.y, point.x, step + 1);
            }
        }
        //判断马儿是否完成了任务
        if (step < X * Y && !finished) {
            chessboard[x][y] = 0;//马儿回到当前位置,没有走完
            visited[x * X + y] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置,(point对象),计算马儿还能走多少位置(point),并放入到一个集合中,(ArrayList),最多可以有八个位置
     *
     * @param curPoint 传进来的点
     * @return 可以走的点的集合
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> points = new ArrayList<>();
        Point p = new Point();
        //可以向左上走
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p));
        }
        //可以向上左走
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p));
        }
        //可以向右上走
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p));
        }
        //可以向上右走
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p));
        }
        //可以向左下走
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y + 1) < Y) {
            points.add(new Point(p));
        }
        //可以向下左走
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y + 2) < Y) {
            points.add(new Point(p));
        }
        //可以向右下走
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y + 1) < Y) {
            points.add(new Point(p));
        }
        //可以向下右走
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y + 2) < Y) {
            points.add(new Point(p));
        }
        return points;
    }

    //根据当前这个位置可以走的位置的数量进行非递减排序
    public static void sort(ArrayList<Point> points) {
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int size1 = next(o1).size();//获取o1下一步可以走的所有点
                int size2 = next(o2).size();//获取o2下一步可以走的所有点
                if (size1 < size2) {
                    return -1;
                } else if (size1 == size2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
