/**
 * @author gp
 * @create 2020/1/5 20:38
 */
//堆排序,时间复杂度(nlogn)
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
//        adjustHeapSort(arr, 1, arr.length);
//        System.out.println(Arrays.toString(arr));
//        adjustHeapSort(arr, 0, arr.length);
//        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[8000000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int)(Math.random()*8000000);
//        }
        long start = System.currentTimeMillis();//获取当前时间毫秒值
        heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("堆排序所用毫秒为:"+(end-start));//10ms

    }

    public static void heapSort(int[] arr) {
        int temp;
        //从下往上找
        for (int i = arr.length / 2 + 1; i >= 0; i--) {
            adjustHeapSort(arr, i, arr.length);
        }
        //交换
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeapSort(arr, 0, j);
        }
    }

    public static void adjustHeapSort(int[] arr, int i, int length) {
        int temp = arr[i];

        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;//当右节点大于左节点时,使指针指向该右节点
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//使i指向k,循环比较
            } else {
                break;
            }
        }
        //当循环结束时,以i为根节点的子树已将最大的值放入根节点
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
