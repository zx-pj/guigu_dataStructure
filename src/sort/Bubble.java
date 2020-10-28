package sort;

import java.util.Arrays;

public class Bubble {

    /** 对数组a中元素进行排序**/
    public static void sort(Comparable[] array){
        //第一次排，可以将最大的找到，放在最后
        //下次排就可以忽略最后这个
        //第一次排，需要比较待排序个数length-1次
        for (int i = array.length-1;i>0;i--){
            boolean flag = false;//优化使用
            for (int j = 0;j<i;j++){
                if (greater(array[j],array[j+1])){
                    exch(array,j,j+1);
                    flag = true;
                }
            }
            if (!flag){//优化！ 证明本趟排序中没有进行过一次交换，说明当前已经排好序
                break;
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

class TestBubble{
    public static void main(String[] args) {
        Integer[] array = {3, 9, -1, 3, 2, 1};
        Bubble.sort(array);
        System.out.println(Arrays.toString(array));
    }
}