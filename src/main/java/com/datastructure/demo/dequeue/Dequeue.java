package com.datastructure.demo.dequeue;
// 雙端對列接口 double ended queue
public interface Dequeue<E> {
    boolean offerFirst(E e);

    boolean offerLast(E e);

    E pollFirst();

    E pollLast();

    E peekFirst();

    E peekLast();

    boolean isEmpty();

    boolean isFull();
}
