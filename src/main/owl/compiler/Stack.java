/*
 * Copyright 2016 Igor Demura
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
