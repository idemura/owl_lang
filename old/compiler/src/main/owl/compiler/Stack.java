package owl.compiler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class Stack<T> implements Iterable<T> {
    private List<T> stack = new ArrayList<>();

    void push(T x) {
        stack.add(x);
    }

    T pop() {
        T x = top();
        stack.remove(stack.size() - 1);
        return x;
    }

    void pop(int n) {
        stack.remove(stack.size() - n);
    }

    T top() {
        return stack.get(stack.size() - 1);
    }

    T get(int n) {
        return stack.get(n);
    }

    int size() {
        return stack.size();
    }

    boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator(stack.size());
    }

    private class StackIterator implements Iterator<T> {
        private int n;

        StackIterator(int n) {
            this.n = n;
        }

        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public T next() {
            return stack.get(--n);
        }
    }
}
