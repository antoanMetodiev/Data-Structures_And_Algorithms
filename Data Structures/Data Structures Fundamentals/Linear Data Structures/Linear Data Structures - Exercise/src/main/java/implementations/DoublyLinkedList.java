package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> previous;
        private Node<E> next;

        public Node(E value) {
            this.element = value;
            this.previous = this.next = null;
        }
    }

    public DoublyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            this.head = this.tail = newNode;
            this.size++;
            return;
        }

        // () -> () -> ()
        this.head.previous = newNode;
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.tail == null) {
            this.head = this.tail = newNode;
            this.size++;
            return;
        }

        // () -> ()
        if (this.size == 1) {
            this.head.next = newNode;
            this.tail = newNode;
            this.tail.previous = this.head;
            this.size++;
            return;
        }

        // (prev-El-next) -> (prev-El-next) -> (prev-El-next)
        newNode.previous = this.tail;
        this.tail.next = newNode;
        this.tail = newNode;
        this.size++;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();

        // () ->
        E removedEl = this.head.element;
        if (this.size == 1) {
            this.head = this.tail = null;
            this.size--;
            return removedEl;
        }

        // () -> () -> () ->
        this.head = this.head.next;
        this.head.previous = null;
        this.size--;
        return removedEl;
    }

    private void ensureNotEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Illegal remove for empty LinkedList");
        }
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();

        E removedEl = this.tail.element;

        // () ->
        if (this.size == 1) {
            this.head = this.tail = null;
            this.size--;
            return removedEl;
        }

        // () -> ()
        this.tail = this.tail.previous;
        this.tail.next = null;
        this.size--;

        return removedEl;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.element;
    }

    @Override
    public E getLast() {
        return this.tail.element;
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
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }
}
