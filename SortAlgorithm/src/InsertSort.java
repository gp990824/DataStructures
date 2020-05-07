/**
 * @author gp
 * @create 2019/12/27 23:05
 */
//插入排序:时间复杂度为:O(n^2)
public class InsertSort {
    public static void main(String[] args) {
        int arr[] = {3, 4, 1};
//        insert(arr);
//        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[80000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 800000);
//        }
        long start = System.currentTimeMillis();//获取当前时间毫秒值
        insert(arr);
        long end = System.currentTimeMillis();
        System.out.println("插入排序所用毫秒为:" + (end - start));//512
    }

    public static void insert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i - 1;
            int temp = arr[i];
            while (index >= 0 && temp < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = temp;
//            if (index + 1 != i) {
//            }
        }
    }
}
