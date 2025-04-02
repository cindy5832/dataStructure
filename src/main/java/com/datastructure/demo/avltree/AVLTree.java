package com.datastructure.demo.avltree;

/**
 * 二叉搜索樹再插入和刪除時，節點可能失衡
 * 若再插入和刪除時通過旋轉，始終讓二叉搜索樹保持平衡，稱為自平衡的二叉搜索樹
 * AVL識字平衡二叉搜索樹的實現之一
 **/
public class AVLTree {
    AVLNode root;

    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1; // 高度

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // 求節點高度
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // 更新節點高度 (新增、刪除、旋轉)
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    // 平衡因子 (balance factor) = 左子樹高度 - 右子樹高度
    private int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }
    // 0, 1, -1 平衡
    // >1 <-1 不平衡
    /*
     * LL
     *  - 失衡節點的bf > 1，即左邊更高
     *  - 失衡節點的左孩子的bf >= 0，即左孩子這也是左邊更高或更高等
     * LR
     *  - 失衡節點的bf > 1，即左邊更高
     *  - 失衡節點的左孩子的bf < 0，即左孩子是右邊更高
     * RL
     *  - 失衡節點的bf < -1，即右邊更高
     *  - 失衡節點的右孩子的bf > 0，即右孩子是左邊更高
     * RR
     *  - 失衡節點的bf < -1，即右邊更高
     *  - 失衡節點的右孩子的bf <= 0，即右孩子是右邊更高或更高等
     **/

    // 參數為要旋轉的節點，返回值新的根節點
    private AVLNode rightRotate(AVLNode red) {
        AVLNode yellow = red.left;
        AVLNode green = yellow.right;
        yellow.right = red;
        red.left = green;
        updateHeight(red);
        updateHeight(green);
        return yellow;
    }

    // 參數為要旋轉的節點，返回值新的根節點
    private AVLNode leftRotate(AVLNode red) {
        AVLNode yellow = red.right;
        AVLNode green = yellow.left;
        yellow.left = red;
        red.right = green;
        updateHeight(red);
        updateHeight(green);
        return yellow;
    }

    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);
        if (bf > 1 && bf(node.left) >= 0) { // LL
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) { // LR
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) { // RL
            return rightLeftRotate(node);
        } else if (bf < -1 && bf(node.left) <= 0) { // RR
            return leftRotate(node);
        }
        return node;
    }

    public void put(int key, Object value) {
        doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        // 1. 找到空位，創建新節點
        if (node == null) {
            return new AVLNode(key, value);
        }
        // 2. key 已存在，更新
        if (key == node.key) {
            node.value = value;
            return node;
        }
        // 3. 繼續查找
        if (key < node.key) {
            node.left = doPut(node.left, key, value); // 向左
        } else {
            node.right = doPut(node.right, key, value); // 向右
        }
        updateHeight(node);
        return balance(node);
    }

    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        // 1. node == null
        if (node == null) {
            return null;
        }
        // 2. 沒找到key
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (node.key < key) {
            node.right = doRemove(node.right, key);
        } else {
            // 3. 找到key a. 沒有 b. 只有一個孩子 c. 有兩個孩子
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        // 4. 更新高度
        updateHeight(node);
        // 5. balance
        return balance(node);
    }
}
