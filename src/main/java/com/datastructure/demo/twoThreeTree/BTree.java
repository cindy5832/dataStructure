package com.datastructure.demo.twoThreeTree;

import java.util.Arrays;

public class BTree {


    static class Node {
        int[] keys;
        Node[] children;
        int keyNumber; // 有效關鍵字
        boolean leaf = true; // 是否是葉子結構
        int t; // 最小度數

        public Node(int t) {
            this.t = t; // t >= 2
            this.children = new Node[t * 2];
            this.keys = new int[t * 2 - 1];
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        // 多路查找
        Node get(int key) {
            int i = 0;
            while (i < keyNumber) {
                if (keys[i] == key) {
                    return this;
                }
                if (keys[i] < key) {
                    break;
                }
                i++;
            }
            if (leaf) {
                return null;
            }
            return children[i].get(key);
        }

        // 向keys指定索引index處插入key
        void insertKey(int key, int index) {
            for (int i = keyNumber - 1; i >= index; i--) {
                keys[i + 1] = keys[i];
            }
            keys[index] = key;
            keyNumber++;
        }

        void insertKey1(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;

        }

        // 刪除指定位置的key
        public int removeKey(int i) {
            int a = keys[i];
            if (i < keyNumber) {
                // 移動數組元素已覆蓋刪除位置
                System.arraycopy(keys, i + 1, keys, i, keyNumber - i - 1);
            } else {
                System.out.println("invalid index");
            }
            return a;
        }

        // 刪除最右邊的key
        public int removeRightMostKey() {
            return removeKey(keyNumber - 1);
        }

        // 刪除最左邊的key
        public int removeLeftMostKey() {
            return removeKey(0);
        }

        // 刪除當前index的child
        public Node removeChild(int index) {
            Node deleted = children[index];
            if (index < keyNumber - 1) {
                System.arraycopy(children, index + 1, children, index, keyNumber - index);
                children[keyNumber] = null; // 將最右邊的child設置為null
            } else {
                System.out.println("invalid index");
            }
            return deleted;
        }

        // 刪除最左邊的child
        public Node removeLeftMostChild() {
            return removeChild(0);
        }

        // 刪除最右邊的child
        public Node removeRightMostChild() {
            return removeChild(keyNumber);
        }

        // 獲取index處孩子的左兄弟
        public Node childLeftSibling(int i) {
            return i == 0 ? null : children[i - 1];
        }

        // 獲取index處孩子右兄弟
        public Node childRightSibling(int i) {
            return i == 0 ? null : children[i + 1];
        }

        // 將當前節點的所有key和child複製到目標節點
        public void moveToTarget(Node left) {
        }
    }

    Node root;

    int t; // 樹中節點最小數值
    final int MIN_KEY_NUMBER; // 最小key數量
    final int MAX_KEY_NUMBER; // 最大key數輛

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = t * 2 - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    // 是否存在
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    // 新增
    public void put(int key) {
        doPut(root, key, null, 0);
    }

    private void doPut(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                return; // 更新
            }
            if (node.keys[i] > key) {
                break; // 插入位置為i
            }
            i++;
        }
        if (node.leaf) {
            node.insertKey(key, i);
        } else {
            doPut(node.children[i], key, node, i);
        }
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    private void split(Node left, Node parent, int index) {
        if (parent == null) {
            // 分裂的為根節點
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        // 1. 創建right節點，把left中的t之前的key和child移動過去
        Node right = new Node(t);
        right.leaf = left.leaf;
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        if (!left.leaf) {
            System.arraycopy(left.children, t, right.children, 0, t);
        }
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        // 2. 中間的key (t -1處)插入到父節點
        int middle = left.keys[t - 1];
        parent.insertKey(middle, index);
        // 3. right節點作為父節點的孩子
        parent.insertChild(right, index + 1);
    }

    // 刪除
    public void remove(int key) {
        doRemove(null, root, 0, key);
    }

    private void doRemove(Node parent, Node node, int index, int key) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        // i 找到，代表待刪除key的索引
        // i 沒找到，代表到第i個孩子繼續查找
        if (node.leaf) {
            if (!found(node, key, i)) { // case 1 當前節點是葉子節點，沒找到
                return;
            } else { // case 2 當前節點是葉子節點，找到了
                node.removeKey(i);
            }
        } else {
            if (!found(node, key, i)) {// case 3 當前節典是非葉子節點，沒找到
                doRemove(node, node.children[i], i, key);
            } else { // case 4 當前節點是非葉子節點，找到了
                // step 1 找到後繼key
                Node s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                int sKey = s.keys[0];
                // 2. 替換待刪除key
                node.keys[i] = sKey;
                // 3. 刪除後繼key
                doRemove(node, node.children[i + 1], i + 1, sKey);

            }
        }
        if (node.keyNumber < MIN_KEY_NUMBER) {
            // case 5 刪除後key數目 < 下限 (不平衡)
            // case 6 根節點
            // 重新調整平衡 case 5 & case 6
            balance(parent, node, index);
        }
    }

    private void balance(Node parent, Node x, int i) {
        // case 6 根節點
        if (x == root) {
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        Node left = parent.childLeftSibling(i);
        Node right = parent.childRightSibling(i);
        if (left != null && left.keyNumber > MIN_KEY_NUMBER) {
            // case 5-1 左多，右璇
            x.insertKey(parent.keys[i - 1], 0);
            if (!left.leaf) {
                x.insertChild(left.removeRightMostChild(), 0);
            }
            parent.keys[i - 1] = left.removeRightMostKey();
            return;
        }
        if (right != null && right.keyNumber > MIN_KEY_NUMBER) {
            // case 5-2 右多，左旋
            // a) 父節點中後繼的key旋轉下來
            x.insertKey(parent.keys[i], x.keyNumber);
            // b) right中最小的孩子換父母
            if (!right.leaf) {
                x.insertChild(right.removeLeftMostChild(), x.keyNumber + 1);
            }
            // c) right中最小的key旋轉上去
            parent.keys[i] = x.removeLeftMostKey();
            return;
        }
        // case 5-3 兩邊都不夠，向左合併
        if (left != null) {
            // 向左兄弟合併
            parent.removeChild(i);
            left.insertKey(parent.removeKey(i - 1), left.keyNumber);
            x.moveToTarget(left);
        } else {
            // 向自己合併
            parent.removeChild(i + 1);
            x.insertKey(parent.removeKey(i), x.keyNumber);
            right.moveToTarget(x);
        }
    }

    private static boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }
}
