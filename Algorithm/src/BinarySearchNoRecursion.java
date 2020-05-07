import java.util.Arrays;

/**
 * @author gp
 * @create 2020/1/10 15:56
 */
//利用非递归实现二分查找算法
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 100);
        }
        Arrays.sort(arr);
        System.out.println(binarySearchNoRecursion(arr, 12));
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 返回要查找的数的下标
     *
     * @param arr   要查找的数组
     * @param value 要查找的值
     * @return
     */
    public static int binarySearchNoRecursion(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (right >= left) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
