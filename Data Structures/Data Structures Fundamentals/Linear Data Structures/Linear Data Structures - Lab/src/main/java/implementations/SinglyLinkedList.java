package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        private Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    @Override
    public void addFirst(E element) {
        Node<E> newFirstEl = new Node<>(element);
        newFirstEl.next = this.head;
        this.head = newFirstEl;
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newLastEl = new Node<>(element);
        if (this.head == null) {
            this.head = newLastEl;
            this.size++;
            return;
        }

        Node<E> current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newLastEl;
        this.size++;
    }

    @Override
    public E removeFirst() {
        ensureNonEmpty();
        E removedEl = this.head.element;
        this.head = this.head.next;
        this.size--;

        return removedEl;
    }

    @Override
    public E removeLast() {
        ensureNonEmpty();
        if (this.size == 1) {
            E value = this.head.element;
            this.head = null;
            return value;
        } else if (this.size == 2) {
            E value = this.head.next.element;
            this.head.next = null;
            return value;
        }

        Node<E> lastNodeHaveNextEl = this.head;
        Node<E> current = this.head;
        while (current != null && current.next != null) {
            lastNodeHaveNextEl = current;
            current = current.next;
        }

        E tmp = lastNodeHaveNextEl.next.element;
        lastNodeHaveNextEl.next = null;

        this.size--;
        return tmp;
    }

    @Override
    public E getFirst() {
        ensureNonEmpty();
        return this.head.element;
    }

    private void ensureNonEmpty() {
        if (this.head == null) {
            throw new IllegalStateException();
        }
    }

    @Override
    public E getLast() {
        ensureNonEmpty();

        Node<E> current = this.head;
        while (current.next != null) {
            current = current.next;
        }

        return current.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                ensureNonEmpty();
                E temp = current.element;
                current = current.next;

                return temp;
            }
        };
    }
}
