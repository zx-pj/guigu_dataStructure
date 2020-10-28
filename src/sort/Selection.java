package sort;

import java.util.Arrays;

public class Selection {

    /** 对数组a中元素进行排序
     *  每次选出最小的值索引，与第一位交换
     *
     * **/
    public static void sort(Comparable[] array){
        for (int i = 0;i<array.length;i++){
            int min = i;
            for (int j = i+1;j<array.length;j++){
                if (greater(array[min],array[j])){
                    min = j;
                }
                exch(array,min,i);
            }
        }
    }

    /** 比较v元素是否大于w元素*/
    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /** 交换数组元素中 i 和 j 的位置**/
    private static void exch(Comparable[] array,int i, int j){
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class TestSelection{
    public static void main(String[] args) {
        Integer[] a = {4,6,8,7,9,2,10,1};
        Selection.sort(a);
        System.out.println(Arrays.toString(a));
    }
}