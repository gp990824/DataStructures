/**
 * @author gp
 * @create 2019/12/27 20:59
 */
//冒泡排序:时间复杂度为:O(n^2)
public class BubbleSort {


    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        long start = System.currentTimeMillis();//获取当前时间毫秒值
        sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("冒泡排序所用毫秒为:"+(end-start));//14172ms
    }

    public static void sort(int[] arr) {
        int temp = 0;
        boolean flag = false;//标识符,假如数组为有序数组,直接跳出该循环
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
