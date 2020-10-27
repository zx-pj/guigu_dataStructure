package dataStructure.recursion;

/**
 * @描述:
 * @开发者: zhaixu
 * @日期: 2020/10/27 10:12
 */
public class Queue8 {
    //定义一个max表示有多少个皇后
    int max = 8;
    //定义数组，保存皇后放置位置的结果
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        //测试一把，8皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("解法总个数===="+count);
    }

    //编写一个方法，放置第n个皇后
    private void check(int n){
        if (n==max){  //n从0开始,当8个皇后就已经放好
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放在该行的第一列
            array[n]=i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){//不冲突
                //接着放n+1个皇后,即开始递归
                check(n+1);
            }
            //如果冲突，继续执行array[n] = i;  即将第b个皇后，放置在本行的后移的一个位置
        }
    }

    //查看当我们放第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //array[i] == array[n]     同一列与一条直线上
            //Math.abs(n - i) == Math.abs(array[n] - array[i])  同一个斜线上   等腰三角形原理
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
