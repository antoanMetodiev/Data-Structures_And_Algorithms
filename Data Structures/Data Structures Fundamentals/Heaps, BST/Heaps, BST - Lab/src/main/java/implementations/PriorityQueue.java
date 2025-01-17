package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private List<E> elements;

    public PriorityQueue() {
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
            parentIndex = (index - 1) / 2;
        }
    }

    private boolean parentIsLess(int lastIndex, int parentIndex) {
        if (lastIndex < 0 || parentIndex < 0) return false;
        return this.elements.get(lastIndex).compareTo(this.elements.get(parentIndex)) > 0;
    }
    @Override
    public E poll() {
        ensureNonEmpty();

        // 1.Разменям позициите на първия и на последния елемент:
        Collections.swap(this.elements, 0, this.elements.size() - 1);

        // 2. Изтривам последния елемент, защото това е приоритетния такъв, който съм разменил в миналата стъпка:
        E removedEl = this.elements.remove(this.elements.size() - 1);


        // 3. Следва да проверя, дали последния елемент, който съм добавил най-отгоре - си е на мястото:
        heapifyDown(0);

        return removedEl;
    }

    private void ensureNonEmpty() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException("List is empty!");
        }
    }

    private void heapifyDown(int heapifyElIndex) {
        if (this.size() == 0) return;
        // 1. Проверявам дали, някой от children-ите ми е по-голям от мен:
        E heapifyEl = this.elements.get(heapifyElIndex);
        E leftChildEl = this.getLeftEl((heapifyElIndex * 2) + 1);
        E rightChildEl = this.getRightEl((heapifyElIndex * 2) + 2);

        int leftChildIndex = (heapifyElIndex * 2) + 1;
        int rightChildIndex = (heapifyElIndex * 2) + 2;

        if (leftChildEl != null && leftChildEl.compareTo(heapifyEl) > 0) {

            // 1. Провярявам дали rightChild е по-голям от leftChild:
            if (rightChildEl != null && rightChildEl.compareTo(leftChildEl) > 0) {
                Collections.swap(this.elements, rightChildIndex, heapifyElIndex);
                heapifyElIndex = rightChildIndex;
                heapifyDown(heapifyElIndex);
            } else {
                Collections.swap(this.elements, leftChildIndex, heapifyElIndex);
                heapifyElIndex = leftChildIndex;
                heapifyDown(heapifyElIndex);
            }


        } else if (rightChildEl != null && rightChildEl.compareTo(heapifyEl) > 0) {

            // 1. Провярявам дали leftChild е по-голям от rightChild:
            if (leftChildEl != null && leftChildEl.compareTo(rightChildEl) > 0) {
                Collections.swap(this.elements, leftChildIndex, heapifyElIndex);
                heapifyElIndex = leftChildIndex;
                heapifyDown(heapifyElIndex);
            } else {
                Collections.swap(this.elements, rightChildIndex, heapifyElIndex);
                heapifyElIndex = rightChildIndex;
                heapifyDown(heapifyElIndex);
            }
        }
    }

    private E getRightEl(int rightChildIndex) {
        if (rightChildIndex >= this.size()) return null;
        return this.elements.get(rightChildIndex);
    }

    private E getLeftEl(int leftChildIndex) {
        if (leftChildIndex >= this.size()) return null;
        return this.elements.get(leftChildIndex);
    }

    @Override
    public E peek() {
        if (this.elements.isEmpty()) throw new IllegalStateException("Not exists elements!");
        return this.elements.get(0);
    }
}
