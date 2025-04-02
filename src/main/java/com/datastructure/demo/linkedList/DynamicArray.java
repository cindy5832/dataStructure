package com.datastructure.demo.linkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

// 動態數組
public class DynamicArray implements Iterable<Integer> {
    private int size = 0; // 邏輯大小
    private int capacity = 8; // 容量
    private int[] array = {}; // 需要再給予初始值

    /**
     * @return void
     * @description: 向最後位置[size]添加元素
     * @param: [element] 待添加元素
     **/
    public void addLast(int element) {
//        array[size] = element;
//        size++;
        add(size, element);
    }

    /**
     * @return void
     * @description: 向[0, size]位置添加元素
     * @param: [index, element] index= 索引位置, element = 待添加元素
     **/
    public void add(int index, int element) {
        checkAndGrow();
        if (index >= 0 && index < size) {
            System.arraycopy(array, index, array, index + 1, size - index); // 複製物件, 從哪複製, 複製到哪, 目標的起始位置, 複製幾個元素
            array[index] = element;
            size++;
        }
        // addLast 方法
        array[index] = element;
        size++;
    }

    private void checkAndGrow() {
        // 容量檢查
        if (size == 0) {
            array = new int[capacity];
        } else if (size == capacity) {
            // 增加容量 => 1.5N 1.618N 2N
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    /**
     * @return int 被刪除的元素
     * @description: 從[0-size]範圍內刪除元素
     * @param: [index]
     **/
    public int remove(int index) {
        int remove = array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return remove;
    }

    /**
     * @return int 該索引位置的元素
     * @description: 查詢元素
     * @param: [index]
     **/
    public int get(int index) {
        return array[index];
    }

    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
//            System.out.println(array[i]);
            consumer.accept(array[i]);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public Integer next() {
                return array[i++];
            }
        };
    }

    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array, 0, size)); // from 0 to size (包含0不包含size)
    }
}
