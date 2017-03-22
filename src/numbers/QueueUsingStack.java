package numbers;

import java.util.Stack;

public class QueueUsingStack {

    private Stack<Integer> st1 = new Stack<Integer>();
    private Stack<Integer> st2 = new Stack<Integer>();

    public void add(int data) {
        st1.push(data);
    }

    public int remove() throws Exception {
        int res = -1;
        if (st2.isEmpty() && !st1.isEmpty()) {
            while (st1.size() > 1) {
                st2.push(st1.pop());
            }
            res = st1.pop();
            while (!st2.isEmpty()) {
                st1.push(st2.pop());
            }
        }
        return res;
    }

    public void display() {
        while (!st1.isEmpty()) {
            st2.push(st1.pop());
        }
        while (!st2.isEmpty()) {
            System.out.print(st2.pop() + ", ");
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        QueueUsingStack q = new QueueUsingStack();
        q.add(1);
        q.add(2);
        q.add(3);
        q.remove();
        q.add(4);
        q.add(5);
        q.add(6);
        q.remove();
        q.remove();
        q.remove();
        q.add(7);
        q.display();
    }

}
