package dataStructure.linkedList;

/**
 * @描述:
 * @开发者: zhaixu
 * @日期: 2020/10/26 10:16
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头结点
    private HeroNode2 head = new HeroNode2(0, "");

    //返回头结点
    public HeroNode2 getHead(){
        return head;
    }

    //遍历双向链表的方法
    public void show() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要辅助变量来遍历
        HeroNode2 temp = head.next;//说明至少有一个节点,让临时节点指向第一个实际节点
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

    //添加节点到单项链表,末尾添加
    public void add(HeroNode2 heroNode) {
        //找到当前链表的最后节点（不考虑顺序）
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
            //当退出循环时，temp指向了链表的最后
        }
        temp.next = heroNode;//将新的节点放在最后
        heroNode.pre = temp;//形成双向链表
    }

    //添加节点，按照编号顺序添加
    public void addByOrder(HeroNode2 heroNode) {
        //头结点不能动，所以需要辅助指针帮助找到添加的位置
        //因为单链表，因此找的temp是位于添加位置的前一个节点
        HeroNode2 temp = head;
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
            if (temp.next!=null){
                temp.next.pre = heroNode;
            }
            temp.next = heroNode;
            heroNode.pre = temp;
        }
    }

    //根据编号来修改节点信息，双向链表的修改与单向链表一样
    public void update(HeroNode2 newHeroNode){
        //判断是否空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        //定义一个辅助变量,初始化指向第一个节点
        HeroNode2 temp = head.next;
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
    //对于双向链表，可以直接找到要删除的节点，然后自我删除，不用找到其前一个节点
    public void delete(int no){
        if (head.next==null){
            System.out.println("链表为空，无法删除、");
            return;
        }
        HeroNode2 temp = head.next;  //让temp初始化指向第一个实际的节点
        boolean flag = false;
        while (true){
            if (temp==null){  //已经到链表的最后节点了
                break;
            }
            if (temp.no==no){
                flag=true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        if (flag){
            //temp.next = temp.next.next;
            temp.pre.next = temp.next;
            if (temp.next!=null){
                temp.next.pre = temp.pre;//此处有问题，如果是最后一个节点，不需要执行这句话，否则空指针
            }
        }else {
            System.out.println("没有编号为"+no+"的节点");
        }
    }
}



//定义一个heroNode
class HeroNode2 {
    public int no;
    public String name;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name) {
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
