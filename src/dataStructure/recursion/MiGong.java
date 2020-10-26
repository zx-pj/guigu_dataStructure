package dataStructure.recursion;

/**
 * @描述:
 * @开发者: zhaixu
 * @日期: 2020/10/26 18:26
 */
public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
            if (i < 7) {
                map[0][i] = 1;
                map[7][i] = 1;
            }
        }
        //设置挡板 值为1的位置是挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[3][3] = 1;
//        map[2][2] = 1;
        setWay(map,1,1);

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路，找出最短路径
     * 如果小球能找到map[6][5]位置，则说明通路能找到
     * 约定：map[i][j]=0表示该点没有走过，1代表墙，2表示通路可以走，3代表该点已经走过，但是走不通
     * 在走迷宫时，需要确定一个策略  下->右->上->左  顺序,如果该点走不通，再回溯
     * @param map 表示地图
     * @param i 从哪个位置找
     * @param j 从哪个位置找
     * @return 如果找到通路，就返回true，否则返回false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5]==2){//通路已经找到
            return true;
        }else {
            if (map[i][j]==0){//如果当前这个点还没有走过
                //按照上右下左
                map[i][j]=2;//假定该点是可以走通
                if (setWay(map,i+1,j)){//向上走
                    return true;
                }else if (setWay(map,i,j+1)){//向右
                    return true;
                }else if (setWay(map,i-1,j)){//向上
                    return true;
                }else if (setWay(map,i,j-1)){
                    return true;
                }else {
                    //说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {//如果map[i][j] != 0,可能是1,2,3
                return false;
            }
        }
    }
}
