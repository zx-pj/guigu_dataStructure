package sort;

import java.util.Arrays;

public class Shell {
    /** 希尔排序 **/
    public static void sort(Comparable[] array){
        //首先确定增量h的值
        int N = array.length;
        int h = 1;
        while (h < N/2){
            h = 2*h+1;
        }

        //排序，每次循环h变为1/2，当h<1,循环结束
        while (h>=1){
            for (int i=h;i<N;i++){  //从增量处开始
                for (int j=i;j>=h;j-=h){
                    if (greater(array[j-h],array[j])){
                        exch(array,j-h,j);
                    }else {
                        break;
                    }
                }
            }
            h = h/2;
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


class TestShell{
    public static void main(String[] args) {
        Integer[] array = {9,1,2,5,7,4,8,6,3,9};
        Shell.sort(array);
        System.out.println(Arrays.toString(array));
    }
}