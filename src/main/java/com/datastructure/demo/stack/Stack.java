package com.datastructure.demo.stack;

public interface Stack<E> {
    /**
     * @description: 向stack加入元素
     * @param: [value] 待加入的值
     * @return boolean 成功添加返回true
     **/
    boolean push(E value);

    /**
     * @description: 從stack頂端彈出元素
     * @param: []
     * @return E 返回頂元素，為空反為null
     **/
    E pop();

    /**
     * @description: 返回頂元素
     * @param: []
     * @return E 非空返回頂元素
     **/
    E peek();

    /**
     * @description: 判斷stack是否為空
     * @param: []
     * @return boolean 空返回true
     **/
    boolean isEmpty();

    /**
     * @description: 判斷stack是否已滿
     * @param: []
     * @return boolean 滿返回true
     **/
    boolean isFull();
}
