package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private Node<E> top;
    private int size;

    public Stack() {
        this.top = null;
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
    public void push(E element) {
        // 1. Създаваме нова кутиика и добавяме стойност в нея:
        Node<E> newStackHead = new Node<>(element);

        // 2. Казваме си към коя кутиика под него да пази референция:
        newStackHead.next = top;

        // 3. Накрая сменямяме head Node-a на стека, защото сме добавими още една кутиика и той трябва да за нея:
        this.top = newStackHead;

        this.size++;
    }

    @Override
    public E pop() {
        ensureIsNotEmpty();

        Node<E> temp = top;
        top = top.next;

        this.size--;
        return temp.element;
    }

    @Override
    public E peek() {
        ensureIsNotEmpty();
        return this.top.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void ensureIsNotEmpty() {
        // Ако сме повикали този метод - означава че искаме да изтрием нещо:
        if (this.size == 0) {
            throw new IllegalStateException();
        }
    }

  @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = top;  // Правим корекция: използваме top вместо head

            @Override
            public boolean hasNext() {
                // Проверяваме дали има още елементи в стека
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E element = current.element;  // Запазваме стойността на текущия елемент
                current = current.next;       // Преминаваме към следващия елемент
                return element;
            }
        };
    }
    }
}
