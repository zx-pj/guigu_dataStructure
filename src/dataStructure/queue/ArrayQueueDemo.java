package dataStructure.queue;


public class ArrayQueueDemo {
    public static void main(String[] args) {

    }
}


//使用数组模拟队列
class ArrayQueue {
    private int maxSize; //使用数组的最大容量
    private int front;  //指向队列投
    private int rear; //指向队尾
    private int[] arr;  //该数组用于存放数据，模拟队列

    //创建队列
    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
        this.front = -1;  //指向队列头部，指向队列头的前一个位置
        this.rear = -1;   //指向队列尾部，就是队尾的数据
    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //获取队列，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取出数据");
        }
        front++;   //front后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队头
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取出数据");
        }
        return arr[front + 1];
    }
}