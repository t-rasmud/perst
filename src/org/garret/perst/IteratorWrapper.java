package org.garret.perst;

import java.util.*;

public class IteratorWrapper<T> extends IterableIterator<T> 
{ 
    private Iterator<T> iterator;

    public IteratorWrapper(Iterator<T> iterator) { 
        this.iterator = iterator;
    }
    
    public boolean hasNext() { 
        return iterator.hasNext();
    }

    @SuppressWarnings("iteration:method.invocation")    // next implementation: call to next in Iterator implementation
    public T next() {
        return iterator.next();
    }

    public void remove() {
        iterator.remove();
    }
}

