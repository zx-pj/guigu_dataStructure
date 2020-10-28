package sort;

import java.util.Arrays;

public class Quick {
    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        //对a数组中，从lo到hi的元素进行切分
        int partition = partition(a, lo, hi);
        //对左边分组中的元素进行排序
        sort(a, lo, partition - 1);
        //对右边分组中的元素进行排序
        sort(a, partition + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        Comparable key = a[lo];//把左边的元素当做基准值
        int left = lo;//定义一个左侧指针，初始指向左边的元素
        int right = hi + 1;//定义一个右侧指针，初始指向左右侧的元素下一个位置
        // 进行切分
        while (true) {
            //先从右往左扫描，找到一个比基准值小的元素
            while (less(key, a[--right])) {
                //循环停止，证明找到了一个比基准值小的元素
                if (right == lo) {
                    break;//已经扫描到左边了，无需继续扫描
                }
            }
            //再从左往右扫描，找一个比基准值大的元素
            while (less(a[++left], key)) {
                //循环停止，证明找到了一个比基准值大的元素
                if (left == hi) {
                    break;//已经扫描到了右边了，无需继续扫描
                }
            }
            if (left >= right) {
                //扫描完了所有元素，结束循环
                break;
            } else {                //交换left和right索引处的元素
                exch(a, left, right);
            }
        }
        //交换后right索引处和基准值所在的索引处的值
        exch(a, lo, right);
        return right;
        //right就是切分的界限
    }

    /**
     * 比较v元素是否大于w元素
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换数组元素中 i 和 j 的位置
     **/
    private static void exch(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

//测试代码
class TestQuick {
    public static void main(String[] args) throws Exception {
        Integer[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 8};
        Quick.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
