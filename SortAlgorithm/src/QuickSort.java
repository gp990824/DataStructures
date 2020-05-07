/**
 * @author gp
 * @create 2019/12/28 16:19
 */
//快速排序:时间复杂度为:O(nlogn)
public class QuickSort {
    public static void main(String[] args) {

        int[] arr = {-14, 0, 21, 0, 50, 10, -2};
//        quick(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[800000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 8000000);
//        }
        long start = System.currentTimeMillis();//获取当前时间毫秒值
        quick(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("快速排序所用毫秒为:" + (end - start));//16
//        System.out.println(Arrays.toString(arr));
    }

    public static void quick(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];//数组中间的值
        int temp = 0;
        while (l < r) {//让比pivot值小的放右边,反之放左边
            while (arr[l] < pivot) {//在pivot左边找,直到找到大于等于pivot的值才推出
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {//如果l>=r说明pivot的左边的值都小于pivot,右边反之
                break;
            }
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            if (arr[l] == pivot) {//如果交换完成后,发现左边的值与pivot相等,则让右指针前移,防止最后发生死循环(即arr[l]=pivot=arr[r]
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        if (l == r) {//如果l=r必须处理l和r否者会栈溢出异常
            l++;
            r--;
        }
        if (left < r) {//向左递归
            quick(arr, left, r);
        }
        if (right > l) {//向右递归
            quick(arr, l, right);
        }
    }
}
