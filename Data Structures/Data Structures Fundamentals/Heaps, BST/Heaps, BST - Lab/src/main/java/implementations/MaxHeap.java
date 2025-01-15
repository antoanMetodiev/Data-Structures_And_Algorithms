package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private List<E> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && parentIsLess(index, parentIndex)) {
            Collections.swap(this.elements, index, parentIndex);
            index = parentIndex;
        }
    }

    private boolean parentIsLess(int lastIndex, int parentIndex) {
        return this.elements.get(lastIndex).compareTo(this.elements.get(parentIndex)) > 0;
    }

    @Override
    public E peek() {
        if (this.elements.isEmpty()) throw new IllegalArgumentException();
        return this.elements.get(0);
    }
}
