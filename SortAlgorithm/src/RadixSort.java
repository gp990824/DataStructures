/**
 * @author gp
 * @create 2019/12/28 21:23
 */
//基数排序:是使用空间换时间的经典算法O(n*k)k:桶的个数
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 718, 14, 214};
//        radix(arr);
//        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[80000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int)(Math.random()*800000);
//        }
        long start = System.currentTimeMillis();//获取当前时间毫秒值
        radix(arr);
        long end = System.currentTimeMillis();
        System.out.println("基数排序所用毫秒为:"+(end-start));//20
//        System.out.println(Arrays.toString(arr));
    }

    public static void radix(int[] arr) {

        int[][] bucket = new int[10][arr.length];//创建十个桶,假设每个桶在最差的情况下有arr.lengh个数

        int[] bucketCount = new int[10];//计数桶,每个元素对应的值=该元素下标对应的桶存放数的个数

        int maxNum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxNum) {
                maxNum = arr[i];
            }
        }
        int len = (maxNum + "").length();//得到的最大位数为几位数

        //循环的次数为数组中最高位数的处于什么位  如 754 -> 3次
        for (int l = 0, n = 1; l < len; l++, n *= 10 ){
            for (int i = 0; i < arr.length; i++) {//遍历数组
                int num = arr[i] / n % 10;//获取改元素的n位数
                bucket[num][bucketCount[num]] = arr[i];//向桶中存放数据
                bucketCount[num]++;
            }
            int index = 0;
            //取出桶中的数据,然后放回数组中
            for (int t = 0; t < bucketCount.length; t++) {//遍历每一个桶
                if (bucketCount[t] != 0) {//如果每个桶元素的指针不等于0,说明改桶有元素
                    for (int i = 0; i < bucketCount[t]; i++) {//遍历改桶中的所有元素
                        arr[index] = bucket[t][i];
                        index++;//指针前移
                    }
                }
                bucketCount[t] = 0;//将10个计数桶的数据清空
            }
        }

    }
}
