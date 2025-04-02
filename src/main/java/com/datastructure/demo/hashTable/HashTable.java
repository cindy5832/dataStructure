package com.datastructure.demo.hashTable;

/**
 * 給每份數據一個編號，放入表格 (數組)
 * 建立編號與表格索引的關係，將來可通過編號快速查找數據
 * 1. 理想情況編號當唯一，數組能容納所有數據
 * 2. 現實不能為了容納所有數據造一個大數組，編號也有可能重複
 * 解決
 * 1. 有限長度的數組，以[拉鍊]方式儲存數據
 * 2. 允許編號適當重複，通過數據自身來進行區分
 **/
public class HashTable {

    static class Entry {
        int hash;
        Object key;
        Object value;
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry[] table = new Entry[16];
    int size = 0; // 元素個數
    float loadFactor = 0.75f; // 負載因子 12 閾值
    int threshold = (int) (loadFactor * table.length);

    /**
     * 求模運算替換"位運算"
     * - 前提：數組長度是2的n次方
     * - hash % 數組長度 等於 hash & (數組長度-1)
     **/
    // 根據hash 獲取value
    Object get(int hash, Object key) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            return null;
        }
        Entry p = table[idx];
        while (p != null) {
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    // 向hash表存入新 key value ，如果key重複，則更新value
    void put(int hash, Object key, Object value) {
        int idx = hash & (table.length - 1);
        // 1. idx有空位，直接新增
        if (table[idx] == null) {
            table[idx] = new Entry(hash, key, value);
        } else {
            // 2. idx 處無空位，延練表查詢，重複更新，否則新增
            Entry p = table[idx];
            while (true) {
                if (p.key.equals(key)) {
                    p.value = value; // update
                    return;
                }
                if (p.next == null) {
                    break;
                }
                p = p.next;
            }
            p.next = new Entry(hash, key, value); // add
        }
        size++;
        if (size > threshold) {
            resize();
        }
    }

    private void resize() {
        Entry[] newTable = new Entry[table.length << 1]; // 乘以2
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i]; // 獲取每個鏈表頭
            if (p != null) {
                // 拆分鏈表，移動到新宿主
                /**
                 * 拆分規律
                 * 一個鏈表最多拆成2個
                 * hash & table.length == 0 的一組
                 * hash & table.length != 0 的一組
                 **/
                Entry a = null;
                Entry b = null;
                Entry aHead = null;
                Entry bHead = null;
                while (p != null) {
                    if ((p.hash & table.length) == 0) {
                        // 分配到a
                        if (a != null) {
                            a.next = p;
                        } else {
                            aHead = p;
                        }
                        a = p;
                    } else {
                        if (b != null) {
                            b.next = p;
                        } else {
                            bHead = p;
                        }
                        // 分配到b
                        b = p;
                    }
                    p = p.next;

                }
                // 規律：a鏈表保持索引位置不變 b鏈表索引位置 + table.length
                if (a != null) {
                    a.next = null;
                    newTable[i] = aHead;
                }
                if (b != null) {
                    b.next = null;
                    newTable[i + table.length] = bHead;
                }
            }
        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

    // 根據hash刪除，返回刪除的Value
    Object remove(int hash, Object key) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            return null;
        }
        Entry p = table[idx];
        Entry prev = null;
        while (p != null) {
            if (p.key.equals(key)) {
                // found it -> delete
                if (prev == null) {
                    table[idx] = p.next;
                } else {
                    prev.next = p.next;
                }
                return p.value;
            }
            prev = p;
            p = p.next;
        }
        return null;
    }

    public Object get(Object key) {
        int hash = getHashedCode(key);
        return get(hash, key);
    }

    public void put(Object key, Object value) {
        int hash = getHashedCode(key);
        put(hash, key, value);
    }

    private static int getHashedCode(Object key) {
        return key.hashCode();
    }

    public Object remove(Object key) {
        int hash = getHashedCode(key);
        return remove(hash, key);
    }

}
