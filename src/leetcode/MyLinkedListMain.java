package leetcode;

import java.util.LinkedList;

/**
 * Created by author on 2021/10/17 12:26
 */
public class MyLinkedListMain {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        System.out.println(list.get(1));
        list.deleteAtIndex(1);
        System.out.println(list.get(1));
    }
}


class MyLinkedList {
    static class Node {
        int val;
        Node next;

        public Node(int val, Node node) {
            this.val = val;
            this.next = node;
        }
    }

    LinkedList<Node> list;

    public MyLinkedList() {
        this.list = new LinkedList<Node>();
    }

    public int get(int index) {
        if(index >= list.size()) {
            return -1;
        }
        return this.list.get(index).val;
    }

    public void addAtHead(int val) {
        if(this.list.size() == 0) {
            this.list.addFirst(new Node(val, null));
        } else {
            this.list.addFirst(new Node(val, this.list.get(0)));
        }
    }

    public void addAtTail(int val) {
        Node node = new Node(val, null);
        this.list.getLast().next = node;
        this.list.add(node);
    }

    public void addAtIndex(int index, int val) {
        if(index >= this.list.size()) {
            this.addAtTail(val);
        } else {
            if(index == 0) {
                this.addAtHead(val);
            } else {
                Node pre = this.list.get(index - 1);
                Node last = this.list.get(index);
                Node node = new Node(val, last);
                pre.next = node;
                this.list.add(1, node);
            }
        }
    }

    public void deleteAtIndex(int index) {
        if(index >= 0 && index < this.list.size()) {
            this.list.remove(index);
        }
    }
}
