package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private int size;

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    private class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    @Override
    public void offer(E element) {
        if (this.head == null) {
            this.head = new Node<>(element);
            this.size++;
            return;
        }

        Node<E> current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(element);
        this.size++;

        //
        // Намираме последния възел
//        Node<E> current = this.head;
//        while (current.next != null) {
//            current = current.next;
//        }
//        // Свързваме последния възел с новия възел
//        current.next = newNode;
    }

    @Override
    public E poll() {
        insureNonEmpty();
        E tmp = this.head.element;
        this.head = this.head.next;
        this.size--;

        return tmp;
    }

    @Override
    public E peek() {
        insureNonEmpty();
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

    private void insureNonEmpty() {
        if (this.head == null) {
            throw new IllegalStateException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return head != null;
            }

            @Override
            public E next() {
                insureNonEmpty();
                E tmp = head.element;
                head = head.next;

                return tmp;
            }
        };
    }
}
