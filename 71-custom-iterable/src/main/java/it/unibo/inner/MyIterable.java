package it.unibo.inner;

import java.util.Iterator;
import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class MyIterable<T> implements IterableWithPolicy<T> { 
    private final T[] items;

    public MyIterable(T[] items) {
        this.items = items;
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    public class InnerIterator implements Iterator<T> {
        private int idx = 0;

        @Override
        public boolean hasNext() {
            return idx < items.length;
        }

        @Override
        public T next() {
            if(hasNext()) {
                return items[idx++];
            } else {
                throw new ArrayIndexOutOfBoundsException("Reached the maximum array lenght");
            }
        }
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        
    }
}

