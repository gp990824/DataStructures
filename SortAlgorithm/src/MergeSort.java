/**
 * @author gp
 * @create 2019/12/28 17:20
 */
//归并排序:时间复杂度为:O(nlogn)
public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 9, 2, 4, 6, 5, 3, 1, 0, 7};
//        int[] arr = new int[80000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 800000);
//        }
        int[] temp = new int[arr.length];
        long start = System.currentTimeMillis();//获取当前时间毫秒值
        resolve(arr, 0, arr.length - 1, temp);
        long end = System.currentTimeMillis();
        System.out.println("归并排序所用毫秒为:" + (end - start));//15
//        System.out.println(Arrays.toString(arr));

    }

    //排序的具体算法
    public static void resolve(int[] arr, int left, int right, int[] temp) {

        if (left < right) {//一直拆分,直到无法再拆的时候将左右两边的数组合并
            int mid = (left + right) / 2;//中间索引
            resolve(arr, left, mid, temp);//向左递归进行拆解
            resolve(arr, mid + 1, right, temp);//向右递归进行拆解
            merge(arr, left, mid, right, temp);//将分解完毕的数组,合并
        }
    }

    /**
     * 利用一个临时数组,将两个数组合成一个有序的数组
     *      遍历两个子数组的每一个元素,选择相对较小的数放入临时数组,直到两个子数组都遍历完
     * @param arr 将要合并的两个数组看成一个数组 , 但是不是有序的    以中间分隔,左右两边为有序
     * @param left 数组的第一个索引
     * @param mid 中间索引
     * @param right 数组的最后一个索引
     * @param temp 临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int leftArrIndex = left;//指向左边数组的第一个元素
        int rightArrIndex = mid + 1;//指向右边数组的第一个元素
        int tempArrIndex = 0;//指向temp的指针
        while (leftArrIndex <= mid && rightArrIndex <= right) {//将两边有序的数组填充到临时数组中,直到有一边处理完毕
            if (arr[leftArrIndex] <= arr[rightArrIndex]) {//左边的元素较小,将其复制到临时数组
                temp[tempArrIndex] = arr[leftArrIndex];
                leftArrIndex++;
                tempArrIndex++;
            } else {
                temp[tempArrIndex] = arr[rightArrIndex];
                rightArrIndex++;
                tempArrIndex++;
            }
        }
        //如果退出循环,此时有一方还要剩余元素,需要将该方的元素复制到temp中
        while (leftArrIndex <= mid) {//说明左边的数组有剩余
            temp[tempArrIndex] = arr[leftArrIndex];
            leftArrIndex++;
            tempArrIndex++;
        }
        while (rightArrIndex <= right) {//右边的数组有剩余
            temp[tempArrIndex] = arr[rightArrIndex];
            rightArrIndex++;
            tempArrIndex++;
        }
        //最后将temp中的所有元素复制到arr中
        tempArrIndex = 0;
        int i = left;
        while (i <= right) {
            arr[i] = temp[tempArrIndex];
            tempArrIndex++;
            i++;
        }
    }
}
