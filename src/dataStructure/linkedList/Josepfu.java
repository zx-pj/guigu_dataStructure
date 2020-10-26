package dataStructure.linkedList;

/**
 * @描述:
 * @开发者: zhaixu
 * @日期: 2020/10/26 11:13
 */
public class Josepfu {
    public static void main(String[] args) {

    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first'节点，当前没有编号
    private Boy first = new Boy(-1);

    //添加节点，构建成一个环形的链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        //构建辅助指针
        Boy curBoy = null;  //帮助构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建节点
            Boy boy = new Boy(i);
            //如果是第一个节点
            if (i == 1) {
                first = boy;
                first.setNext(first); //构成环
                curBoy = first;  //让curBoy指向第一个小孩，first不动
            } else {
                //三个动作，构建一个环
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历环形链表
    public void show() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，所以使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {//此时已经不是空链表
            System.out.println("当前小孩编号为：" + curBoy.getNo());
            if (curBoy.getNext() == first) {//此条件用于判断是否已经遍历结束
                break;
            }
            curBoy = curBoy.getNext();  //让辅助指针后移，用于遍历
        }
    }


    /**
     * 根据用户的输入，计算出出圈顺序
     *
     * @param startNo  表示从第几个小孩开始数
     * @param countNum 表示每次数几下
     * @param nums     表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //对数据校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成出圈
        Boy helper = first;
        while (true) {
            //将helper初始化指向环形链表的最后一个节点
            if (helper.getNext() == first) {
                //此时helper已经指向了最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //此时helper在first的前一个位置
        //在小孩报数前，先让first与helper移动k-1次，指定从第几个小孩开始报数
        for (int i = 0; i < startNo-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //循环，直到圈中只有一个节点
        while (true){
            if (helper==first){//此时说明圈中只有一个节点了
                break;
            }
            //让两个指针同时移动countNum-1次，然后出圈
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时first指向的节点，就是要出圈的小孩节点
            System.out.println("小孩"+first.getNo()+"出圈");
            //将first节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩编号"+first.getNo());
    }
}


//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编写
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}