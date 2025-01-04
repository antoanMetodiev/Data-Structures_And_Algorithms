package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private Node<E> lastNodeElement;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        private Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    public Queue() {
        this.head = null;
        this.lastNodeElement = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> newElement = new Node<>(element);
        if (this.head == null) {
            this.head = newElement;
            this.lastNodeElement = newElement;
            this.size++;
            return;
        }

        this.lastNodeElement.next = newElement;
        this.lastNodeElement = newElement;
        this.size++;
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E temp = this.head.element;
        if (this.size == 1) {
            this.head = null;
            this.size--;
            return temp;
        }
        // My:
        this.head = this.head.next;
        this.size--;

        // On Lecture:
//        else {
//            Node<E> next = this.head.next;
//            this.head.next = null;
//            this.head = next;
//        }
//        this.size--;

        return temp;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.head.element;
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
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public E next() {
                E temp = this.current.element;
                this.current = this.current.next;
                return temp;
            }
        };
    }

    private void ensureNonEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Illegal operation on empty stack");
        }
    }
}
