package com.datastructure.demo.queue;

public interface Queue<E> {
    /**
     * @description: 向尾部插入值
     * @param: [value]
     * @return boolean 成功返回true 失敗返回false
     **/
    boolean offer(E value);

    /**
     * @description: 從對列頭取值並移除
     * @param: []
     * @return E 如果對列非空返回對頭值，否則返回null
     **/
    E poll();

    /**
     * @description: 從對列頭取值，不移除
     * @param: []
     * @return E 若對列非空，返回對列頭值，否則返回null
     **/
    E peek();

    /**
     * @description: 檢查對列是否為空
     * @param: []
     * @return boolean 空返回true，否則返回false
     **/
    boolean isEmpty();
    
    /**
     * @description: 檢查對列是否已滿
     * @param: []
     * @return boolean 滿了返回true 反之返回false
     **/
    boolean isFull();
}
