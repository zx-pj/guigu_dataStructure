package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zhaixu
 * @description：二分查找算法  在有序数组中找出指定值的所有索引
 * 要求必须是排好序的数组，并且返回目标的索引
 * @date ：2020/11/7 9:38
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000,1000,1000,1234};
        System.out.println(binarySearch(arr, 0, arr.length, 1000));
    }

    public static List<Integer> binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) { //右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            List<Integer> resIndexList = new ArrayList<>();
            //向mid左表扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //否则，就temp，放入到resIndexList中
                resIndexList.add(temp);
                temp -= 1;//左移
            }
            resIndexList.add(mid);
            //向mid索引右边扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                //否则，就temp，放入到resIndexList中
                resIndexList.add(temp);
                temp += 1;//右移
            }
            return resIndexList;
        }
    }
}