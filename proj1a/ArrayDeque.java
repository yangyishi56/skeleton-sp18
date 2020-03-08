public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    // create an empty list
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    //resize the underlying array to the target capacity
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = items[(nextFirst + 1 + i) % items.length];
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    // public void addFirst()
    public void addFirst(T x) {
        if (size == items.length) {
            resize((int) (size / 0.25));
        }

        items[nextFirst] = x;
        nextFirst = nextFirst - 1;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        size = size + 1;

    }

    //Insert x into the back of the list
    public void addLast(T x) {
        if (size == items.length) {
            resize((int) (size / 0.25));
        }

        items[nextLast] = x;
        nextLast = (nextLast + 1) % items.length;
        size = size + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            System.out.print(items[index % (items.length)]);
            System.out.print(" ");
            index++;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16 && size - 1 < items.length * 0.25) {
            resize((int) (items.length * 0.5));
        }

        nextFirst = (nextFirst + 1) % items.length;
        size = size - 1;
        return items[nextFirst];
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16 && size - 1 < items.length * 0.25) {
            resize((int) (items.length * 0.5));
        }

        nextLast = nextLast - 1;
        if (nextLast == -1) {
            nextLast = items.length - 1;
        }
        size = size - 1;
        return items[nextLast];
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }
}
