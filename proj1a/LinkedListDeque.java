public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        public Node pre;
        public T item;
        public Node next;

        //Node class constructor
        public Node(Node p, T i, Node n) {
            pre = p;
            item = i;
            next = n;
        }
    }

    //LinkedListDeque constructor
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    public LinkedListDeque(T x) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, x, sentinel);
        size = 1;
    }

    public void addFirst(T item) {
        Node temp = sentinel.next;
        sentinel.next = new Node(sentinel, item, temp);
        temp.pre = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        Node temp = sentinel.pre;
        sentinel.pre = new Node(temp, item, sentinel);
        temp.next = sentinel.pre;
        size += 1;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node temp = sentinel.next;
        for(int i = 0; i < size; i++) {
            System.out.print(temp.item);
            System.out.print(" ");
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if(size == 0) return null;

        Node temp = sentinel.next;
        sentinel.next = temp.next;
        temp.next.pre = sentinel;
        size -= 1;
        return temp.item;
    }

    public T removeLast() {
        if(size == 0) return null;

        Node temp = sentinel.pre;
        sentinel.pre = temp.pre;
        temp.pre.next = sentinel;
        size -= 1;
        return temp.item;
    }

    public T get(int index) {
        Node temp = sentinel.next;
        if(index > size - 1) return null;
        int count = 0;
        while(count != index) {
            temp = temp.next;
            count++;
        }
        return temp.item;
    }

    public T getRecursiveHelp(Node p, int i) {
        if (i == 0){
            return p.item;
        } else {
            return getRecursiveHelp(p.next, i-1);
        }
    }

    public T getRecursive(int index) {
        int length = size;
        if (index > length -1){
            return null;
        } else {
            return getRecursiveHelp(sentinel.next, index);
        }
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        LinkedListDeque<Integer> l = new LinkedListDeque<>(1);
        System.out.println(l.size);
    }

}