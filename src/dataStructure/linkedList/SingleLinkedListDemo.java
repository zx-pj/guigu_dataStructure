package dataStructure.linkedList;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

    }
}

//定义一个SingleLinkedList 单链表
class SingleLinkedList {
    //先初始化一个头结点
    private HeroNode head = new HeroNode(0, "");

    //添加节点到单项链表
    public void add(HeroNode heroNode) {
        //找到当前链表的最后节点（不考虑顺序）
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
            //当退出循环时，temp指向了链表的最后
        }
        temp.next = heroNode;//将新的节点放在最后
    }

    //按照排名添加
    public void addByOrder(HeroNode heroNode) {
        //头结点不能动，所以需要辅助指针帮助找到添加的位置
        //因为单链表，因此找的temp是位于添加位置的前一个节点
        HeroNode temp = head;
        boolean flag = false;//标志添加的编号是否已经存在,默认为false
        while (true) {
            if (temp.next == null) {
                //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                //位置找到了，就是temp的下一个位置
                break;
            } else if (temp.next.no == heroNode.no) {
                //说明新添加的heroNode的编号已经存在，不能再添加
                flag = true;
                break;
            }
            temp = temp.next;  //后移，遍历，继续找有没有符合的位置
        }
        //遍历完后，判断flag的值
        if (flag){//不能添加，说明编号存在
            System.out.println("准备插入的英雄编号"+heroNode.no+"已经存在，不能再添加");
        }else {
            //说明可以添加，直接在当前temp的后边
            heroNode.next = temp.next;
            temp.next=heroNode;
        }
    }

    //根据编号来修改节点信息
    public void update(HeroNode newHeroNode){
        //判断是否空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        //定义一个辅助变量,初始化指向第一个节点
        HeroNode temp = head.next;
        boolean flag  = false;//表示是否找到了该节点
        while (true){
            if (temp==null){
                break;//已经遍历完链表
            }
            if (temp.no==newHeroNode.no){
                //说明找到了
                flag=true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到了该节点
        if (flag){
            temp.name = newHeroNode.name;
        }else {
            //没有找到
            System.out.println("没有找到编号为+"+newHeroNode.no+"的节点");
        }
    }

    //根据编号删除节点
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (temp.next!=null){
            if (temp.next.no==no){
                flag=true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("没有编号为"+no+"的节点");
        }
    }

    //显示链表
    public void show() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要辅助变量来遍历
        HeroNode temp = head.next;//说明至少有一个节点,让临时节点指向第一个实际节点
        while (true) {
            if (temp == null) {
                break;
            }
            //如果不为空，输出该节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    //获取到单链表的节点个数
    public static int getLength(HeroNode head){
        if (head.next==null){
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur!=null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //将单链表进行反转
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next==null||head.next.next==null){
            return;
        }
        //定义一个辅助指针，帮助遍历原来链表
        HeroNode cur = head.next;
        HeroNode next = null;  //指向当前节点的下一个节点,因为在插入前需要往后标注一下
        HeroNode reverseHead = new HeroNode(0,"");
        //遍历原来链表，每遍历一个节点，将其取出，并放在新的链表reverseHead的最前端
        while (cur!=null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
    }
}

//定义一个heroNode
class HeroNode {
    public int no;
    public String name;
    public HeroNode next;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
