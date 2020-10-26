package dataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @描述:
 * @开发者: zhaixu
 * @日期: 2020/10/26 15:38
 */
public class PolandNotation {
    public static void main(String[] args) {
        String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression2 = "4 5 * 8 - 60 + 8 2 / +";
        //先将表达式放入进arraylist中
        List<String> rpnList = getListString(suffixExpression2);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println(res);

        String express = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(express);
        System.out.println("中缀表达式对应的list" + strings);
        List<String> strings1 = parseSuffixExpressionList(strings);
        System.out.println("后缀表达式对应的list" + strings1);
        int calculate = calculate(strings1);
        System.out.println("结果" + calculate);
    }

    //将逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {//如果是非数字
                ls.add("" + c);
                i++;//后移
            } else {//如果是一个数，则要考虑拼接
                str = ""; //将str充值为空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建给栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历
        for (String item : ls) {
            //使用正则表达式取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                //判断符号
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if ((item.equals("/"))) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式的list转化为后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //因为s2这个栈，在整个转换的过程中，没有pop操作，而且后面我们还需要逆序输出
        //所有直接使用List
        List<String> s2 = new ArrayList<>();
        //遍历ls
        for (String item : ls) {
            //如果是一个数，入栈，加入到s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);//如果是左括号，直接push
            } else if (item.equals(")")) {
                //如果是右括号,弹出操作符栈，直到遇到左括号
                while (!s1.peek().equals("(")) {  //每次s1弹出时看其当前是否是左括号
                    s2.add(s1.pop()); //s1弹出一个放入s2
                }
                s1.pop();//把找到的左括号弹出    消除括号
            } else {
                //当item的优先级小于等于s1栈顶的运算符，将s1栈顶运算符弹出并加入到s2中
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }
}

//编写一个类，可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应优先级的数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}