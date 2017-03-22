package numbers;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {

    private Queue<Integer> q1 = new LinkedList<Integer>();
    private Queue<Integer> q2 = new LinkedList<Integer>();

    public void push(int data) {
        q1.add(data);
    }

    public int pop() {
        int res;
        while (q1.size() > 1) {
            q2.add(q1.remove());
        }
        res = q1.remove();

        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
        return res;
    }

    public void display() {
        if (!q1.isEmpty()) {
            System.out.print("Q1: ");
            for (int eachInt : q1) {
                System.out.print(eachInt + ", ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        StackUsingQueue st = new StackUsingQueue();
        st.push(1);
        st.push(2);
        st.push(3);
        st.display();
        st.pop();
        st.display();
        st.push(4);
        st.push(5);
        st.display();
        st.pop();
        st.display();
    }

}
