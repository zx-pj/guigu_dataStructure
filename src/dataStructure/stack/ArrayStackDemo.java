package dataStructure.stack;

/**
 * @描述:
 * @开发者: zhaixu
 * @日期: 2020/10/26 12:50
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数
        //循环
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)){
                //如果是运算符,判断当前符号栈是否为空
                if (!operStack.isEmpty()){
                    //不为空。进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符
                    //就从符号栈中pop出一个符号，从数栈中pop出两个数
                    //进行运算，得到结果，入数栈，再将当前操作符入符号栈
                    if (operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        //需要弹栈计算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //再把结果入数栈
                        numStack.push(res);
                        //把当前操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前操作符优先级大于栈中操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else {
                    //当前符号栈为空,直接入栈
                    operStack.push(ch);
                }
            }else {
                //如果当前扫描的是数字,则直接入数栈
                //需要将字符数字转为真正的数字 字节码
//                numStack.push(ch-48);
                //当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //在处理数时，需要向expression的表达式的index后再看一位，如果是数，继续扫描，是符号入栈
                //因此需要定义一个字符串变量，用于拼接
                keepNum+=ch;
                //判断下一个字符是否为数字，如果是，继续扫描，如果是运算符，入栈
                //如果ch已经是expression的最后一位了，就直接入栈
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符，入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //刷新keepNum
                        keepNum="";
                    }
                }

            }
            //让index+1，判断是否扫描到expression最后
            index++;
            if (index>=expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true){
            //如果符号栈为空，则计算到最后的结果了，数栈只有一个数字
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.println("表达式："+expression+"的结果为"+numStack.pop());//最后这个数就是结果
    }
}

//用数组表示栈结构
class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack; //数组模拟战，存储数据
    private int top = -1; // top表示栈顶，初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法，可以返回当前栈顶的值，但不是pop
    public int peek(){
        return stack[top];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈pop
    public int pop() {
        //将栈顶的数据返回
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int temp = stack[top];
        top--;
        return temp;
    }

    //遍历栈,从栈顶开始遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无数据?");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来确定的，使用数字表示
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//如果是除去+-*/运算符，返回-1
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;//用于存放计算的结果
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}