import java.util.ArrayList;
import java.util.List;

/**
 * @author gp
 * @create 2020/1/4 15:37
 */
//二分查找(前提是 该数组是有序的)
public class BinarySearch {
    public static void main(String[] args) {

        int[] arr = {10,20,20,20,41,54,55,66};
//        System.out.println(binarySearch(arr, 0, arr.length - 1, 4881));

        System.out.println(binarySearchTotal(arr, 0, arr.length - 1, 20));
    }

    public static int binarySearch(int[] arr, int left, int right, int value) {

        int mid = (left + right) / 2;
        if (left > right) {//递归退出条件
            return -1;
        } else if (arr[mid] > value) {//向左递归
            return binarySearch(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {
            return binarySearch(arr, mid + 1, right, value);
        } else {
            return mid;
        }
    }
    //查找出所有的值的下标
    public static List<Integer> binarySearchTotal(int[] arr,int left,int right,int value){

        int mid = (left + right) / 2;
        if (left > right) {//递归退出条件
            return null;
        } else if (arr[mid] > value) {//向左递归
            return binarySearchTotal(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {
            return binarySearchTotal(arr, mid + 1, right, value);
        } else {
            List<Integer> list = new ArrayList<>();
            /*
            如果已找到.则需要向该数组的左边扫描
            然后再向右扫描,一次添加到该集合中
             */
            int temp1 = mid - 1;
            while (true){//向左扫描
                if(temp1 < 0 || arr[temp1] != value){
                    break;
                }
                list.add(temp1);
                temp1--;
            }
            list.add(mid);
            int temp2 = mid + 1;
            while (true){//向右扫描
                if(temp2 > arr.length-1 || arr[temp2] != value){
                    break;
                }
                list.add(temp2);
                temp2++;
            }
            return list;
        }
    }

}
