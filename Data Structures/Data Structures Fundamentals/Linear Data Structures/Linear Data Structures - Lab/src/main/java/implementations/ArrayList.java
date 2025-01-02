package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private Object[] elements;
    private static final int INITIAL_SIZE = 4;
    private int capacity;
    private int size; // големината на заетото пространство (елементите които не сами null в масива)!!!

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        this.size = 0;
        this.capacity = INITIAL_SIZE;
    }

    @Override
    public boolean add(E element) {
        if (this.size == this.capacity) {
            resize();
        }

        this.elements[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Cannot ADD this index because the index is bigger than Array!!!");
        }

        if (this.size == this.capacity) {
            resize();
        }

        shiftRight(index, element);
        this.size++;
        return true;
    }

    private void shiftRight(int index, E element) {
        for (int i = this.size; i > index ; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[index] = element;
    }

    private boolean checkIfIndexIsInvalid(int index) {
        return index >= 0 && index <= this.size;
    }

    @Override
    public E get(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Cannot GET this index because the index is bigger than Array!!!");
        }

        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Cannot GET this index because the index is bigger than Array!!!");
        }

        this.elements[index] = element;
        return (E) this.elements[index];
    }

    @Override
    public E remove(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Cannot GET this index because the index is bigger than Array!!!");
        }

        Object elForReturn = this.elements[index];
        this.elements[index] = null;
        shiftLeft(index);
        this.size--; // намалявам си size-a!!!
        shrinkArr();

        return (E) elForReturn;
    }

    private void shrinkArr() {
        if (this.size > this.capacity / 3) {
            return;
        }

        this.capacity /= 2;
        this.elements = Arrays.copyOf(this.elements, capacity);
    }

    private void shiftLeft(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        this.elements[this.size - 1] = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (Object el : elements) {
            if ((el != null && element != null) && el.equals(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                return (E) elements[index++];
            }
        };
    }

    private void resize() {
        this.capacity *= 2;  // удвоявам си буферното място!!!
        Object[] tmp = new Object[capacity];

        for (int i = 0; i < this.elements.length; i++) {
            tmp[i] = this.elements[i];
        }

        this.elements = tmp;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < this.size;
    }
}
