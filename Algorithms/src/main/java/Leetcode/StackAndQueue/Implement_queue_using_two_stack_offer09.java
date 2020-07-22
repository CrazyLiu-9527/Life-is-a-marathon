package Leetcode.StackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * @author liuzy
 * @date 2020/7/22 23:47
 */
public class Implement_queue_using_two_stack_offer09 {
    /*
    成员变量
    维护两个栈 stack1 和 stack2，其中 stack1 支持插入操作，stack2 支持删除操作

    构造方法
    初始化 stack1 和 stack2 为空

    插入元素
    插入元素对应方法 appendTail
    stack1 直接插入元素

    删除元素
    删除元素对应方法 deleteHead
    如果 stack2 为空，则将 stack1 里的所有元素弹出插入到 stack2 里
    如果 stack2 仍为空，则返回 -1，否则从 stack2 弹出一个元素并返回
     */
    class CQueue {
        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public CQueue() {
            stack1 = new LinkedList<Integer>();
            stack2 = new LinkedList<Integer>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            // 如果第二个栈为空
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (stack2.isEmpty()) {
                return -1;
            } else {
                int deleteItem = stack2.pop();
                return deleteItem;
            }
        }
    }
}
