/**
 * @author gp
 * @create 2019/12/27 22:29
 */
//选择排序:时间复杂度为:O(n^2)
public class SelectSort {
    public static void main(String[] args) {
//        int arr[] = {54, 24, 34, 47, 89, 15, 55};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        long start = System.currentTimeMillis();//获取当前时间毫秒值
        select(arr);
        long end = System.currentTimeMillis();
        System.out.println("选择排序所用毫秒为:" + (end - start));//1785ms

    }

    public static void select(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            int minNum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (minNum > arr[j]) {
                    minNum = arr[j];
                    min = j;
                }
            }
            if (min != i) {
                arr[min] = arr[i];
                arr[i] = minNum;
            }
        }
    }
}
