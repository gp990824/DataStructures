import java.util.Arrays;

/**
 * @author gp
 * @create 2019/12/28 14:45
 */
//时间复杂度为O(nlogn)
public class ShellSort {
    public static void main(String[] args) {
        int arr[] = {8, 9, 2, 4, 6, 5, 3, 1, 0, 7};
//        shell(arr);
//        shellMove(arr);
//        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[80000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 800000);
//        }
        long start = System.currentTimeMillis();//获取当前时间毫秒值
//        shell(arr);
        shellMove(arr);
        long end = System.currentTimeMillis();
        System.out.println("希尔排序所用毫秒为:" + (end - start));//20
        System.out.println(Arrays.toString(arr));

    }

    //利用交换法进行希尔排序
    public static void shell(int[] arr) {
        int temp;
        //将该数组分成arr.lengh/2组,如果步长大于0,则继续分组,分成gap=gap/2组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //与当前元素的后步数位进行比较
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //利用移动法进行希尔排序,每分一次组,就将每个组进行插入排序
    public static void shellMove(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //基于插入排序优化希尔排序
                int j = i;//保存待插入的位置
                int temp = arr[j];
                while (j -gap >= 0 && temp < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }

        }

    }
}
