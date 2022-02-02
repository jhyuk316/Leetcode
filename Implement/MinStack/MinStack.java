package Implement.MinStack;
// 155. Min Stack
// https://leetcode.com/problems/min-stack/

import java.util.ArrayList;
import java.util.List;

// linked-list로 구현
class MinStack {
    private class Node {
        int val;
        int min;
        Node next;

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    Node head;

    public MinStack() {}

    public void push(int val) {
        if (head == null) {
            head = new Node(val, val, null);
        } else {
            int min = Math.min(head.min, val);
            head = new Node(val, min, head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}


// min값을 따로 저장하는 방식
class MinStack1 {
    List<Integer> stack;
    int minVal;
    boolean findMin;

    public MinStack1() {
        this.stack = new ArrayList<>();
        this.minVal = (int) Math.pow(2, 31);
        this.findMin = false;
    }

    public void push(int val) {
        if (this.minVal > val) {
            this.findMin = true;
            this.minVal = val;
        }
        this.stack.add(val);
    }

    public void pop() {
        if (this.minVal == top()) {
            this.findMin = false;
        }
        this.stack.remove(this.stack.size() - 1);
    }

    public int top() {
        return this.stack.get(this.stack.size() - 1);
    }

    public int getMin() {
        if (findMin == true) {
            return this.minVal;
        }

        int tempMin = (int) Math.pow(2, 31);

        for (int i : this.stack) {
            tempMin = Math.min(tempMin, i);
        }

        this.minVal = tempMin;
        findMin = true;

        return tempMin;
    }
}

