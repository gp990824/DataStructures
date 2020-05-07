/**
 * @author gp
 * @create 2020/1/4 15:22
 */
//线性查找,逐一比对
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {10,20,41,23,54,12,55,66,32,42};
        System.out.println(seqSearch(arr, 41));


    }
    public static int seqSearch(int[] arr,int value){//逐一比对
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
