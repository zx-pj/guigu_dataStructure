package search;

import java.util.Arrays;

/**
 * @author ：zhaixu
 * @description：插值查找算法 对于庞大的、分步平均的有序数组，利用插值查找优势更大
 * 对于分布不均匀的有序数组，该方法不一定比二分查找要好0
 * 就是在二分查找的基础上，对mid的计算方法进行了改变
 * @date ：2020/11/7 20:22
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    //编写插值查找算法
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {//此处优化必须有，否则会mid越界
            return -1;
        }
        //求出mid，自适应的写法
        int mid = left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if (findVal>midVal){//向右递归
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

}
