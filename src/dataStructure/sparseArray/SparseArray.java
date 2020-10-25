package dataStructure.sparseArray;

public class SparseArray {

    public static int[][] press(int[][] array){
        // 将二维数组 转 稀疏数组的思
        // 1. 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 2. 创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = array.length;
        sparseArr[0][1] = array[0].length;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0; //count 用于记录是第几个非0数据
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = array[i][j];
                }
            }
        }
        return sparseArr;
    }

    public static void printSparseArr(int[][] array){
        // 输出稀疏数组的形式
        System.out.println("得到稀疏数组为~~~~");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", array[i][0], array[i][1], array[i][2]);
        }
        System.out.println();
    }


    public static int[][] reverse(int[][] array){
        int[][] sparseArr = press(array);
        //将sparseArr的0,1列互换
        int temp = 0;
        for (int[] line : sparseArr) {
            temp = line[0];
            line[0] = line[1];
            line[1] = temp;
        }
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可

        for(int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return chessArr2;
    }

    public static void printReserve(int[][] chessArr2){
        System.out.println("得到的转置矩阵为");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }



    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int chessArr1[][] = new int[7][4];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][0] = 6;
        chessArr1[6][1] = 5;
        // 输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        int[][] sparseArray = press(chessArr1);
        printSparseArr(sparseArray);
        int[][] reverse = reverse(chessArr1);
        printReserve(reverse);
    }
}
