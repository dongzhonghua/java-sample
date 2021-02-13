package xyz.dsvshx.iterator;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author dongzhonghua
 * Created on 2021-02-13
 */
public class MyList<E> implements MyListInterface<E> {
    private Object[] list = new Object[16];
    private int size = 0;

    @Override
    public void add(E obj) {
        list[size++] = obj;

    }

    @Override
    public void remove(E obj) {
    }

    @Override
    public Iterator<E> iterator() {
        return new ConcreteIterator<>();
    }

    @Override
    public int size() {
        return size;
    }

    private class ConcreteIterator<T> implements ListIterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            T e = null;
            if (hasNext()) {
                e = (T) list[cursor++];
            }
            return e;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(T e) {

        }

        @Override
        public void add(T e) {

        }
    }

}
