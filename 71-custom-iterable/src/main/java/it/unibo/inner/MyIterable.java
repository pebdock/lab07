package it.unibo.inner;

import java.util.Iterator;
import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class MyIterable<T> implements IterableWithPolicy<T> { 
    private T[] items;
    private Predicate<T> filter;

    public MyIterable(final T[] items, final Predicate<T> filter) {
        this.items = items;
        this.filter = filter;
    }

    public MyIterable(final T[] items) {
        this(items, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    public class InnerIterator implements Iterator<T> {
        private int idx = 0;

        @Override
        public boolean hasNext() {

            while(idx < items.length) {
                if(filter == null || filter.test(items[idx])) {
                    return true;
                }
                ++idx;
            }
            return false;
        }

        @Override
        public T next() {
            if(hasNext()) {
                return items[idx++];
            } else {
                throw new ArrayIndexOutOfBoundsException("Raggiunto limite array");
            }
        }


    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter;
    }
}

