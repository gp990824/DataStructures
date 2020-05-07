import java.util.ArrayList;
import java.util.List;

/**
 * @author gp
 * @create 2020/1/4 16:26
 */
//插值查找算法
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        System.out.println(insertValueSearchTotal(arr, 0, arr.length - 1, 1));
    }

    //查找出所有的值的下标
    public static List<Integer> insertValueSearchTotal(int[] arr, int left, int right, int value) {

        int mid = left + (right - left) * ((value - arr[left]) / (arr[right] - arr[left]));
        if (left > right || value < arr[0] || arr[arr.length - 1] < value) {//递归退出条件
            return null;
        } else if (arr[mid] > value) {//向左递归
            return insertValueSearchTotal(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {
            return insertValueSearchTotal(arr, mid + 1, right, value);
        } else {
            List<Integer> list = new ArrayList<>();
            /*
            如果已找到.则需要向该数组的左边扫描
            然后再向右扫描,一次添加到该集合中
             */
            int temp1 = mid - 1;
            while (true) {//向左扫描
                if (temp1 < 0 || arr[temp1] != value) {
                    break;
                }
                list.add(temp1);
                temp1--;
            }
            list.add(mid);
            int temp2 = mid + 1;
            while (true) {//向右扫描
                if (temp2 > arr.length - 1 || arr[temp2] != value) {
                    break;
                }
                list.add(temp2);
                temp2++;
            }
            return list;
        }
    }
}
