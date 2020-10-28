package sort;

import java.util.Arrays;

public class Insertion {
    /** 对数组a中元素进行排序
     *
     * **/
    public static void sort(Comparable[] array){
        for (int i = 1;i<array.length;i++){
            for (int j=i;j>0;j--){
                if (greater(array[j-1],array[j])){
                    exch(array,j-1,j);
                }else {
                    break;
                }
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

class TestInsertion{
    public static void main(String[] args) {
        Integer[] a = {4,6,8,7,9,2,10,1};
        Insertion.sort(a);
        System.out.println(Arrays.toString(a));
    }
}