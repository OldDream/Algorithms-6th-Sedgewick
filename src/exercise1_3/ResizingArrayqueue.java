package exercise1_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayqueue<E> implements Iterable<E> {
    private E[] q;
    private int n;
    private int first;
    private int last;

    public ResizingArrayqueue() {
        q = (E[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        E[] temp = (E[]) new Object[capacity];
        for (int i = first; i < q.length; i++) {
            temp[i] = q[i];
        }
        q = temp;

    }

    public void enqueue(E e) {
        if (n == q.length)
            resize(q.length * 2);
        q[last++] = e;
        n++;
    }

    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        E temp = q[first++];
        n--;
        if (n > 0 && n < q.length / 4)
            resize(q.length / 2);
        return temp;
    }

    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        return q[first];
    }

    public Iterator<E> iterator() {
        return new ArrayIteraqtor();
    }

    private class ArrayIteraqtor implements Iterator<E> {
        private int i = first;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E item = q[i++];
            return item;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
