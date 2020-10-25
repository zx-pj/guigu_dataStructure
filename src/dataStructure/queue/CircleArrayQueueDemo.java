package dataStructure.queue;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

    }
}

class CircleArray {
    private int maxSize; //使用数组的最大容量
    private int front;  //指向队列投
    private int rear; //指向队尾+1
    private int[] arr;  //该数组用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取出数据");
        }
        //先把front对应的值保留到一个临时变量
        int value = arr[front];
        //考虑取模
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        //从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列的有效个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队头
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取出数据");
        }
        return arr[front];
    }

}