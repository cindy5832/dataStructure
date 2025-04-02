package com.datastructure.demo.redBlaackTree;


import org.w3c.dom.Node;

/**
 * 紅黑樹：也是一種自平衡的二叉搜索樹，較AVL，插入和旋轉次數更少
 * 1. 所有節點都有2種顏色
 * 2. 所有null視為黑色
 * 3. 紅色節點不能相鄰
 * 4. 根節點是黑色
 * 5. 從根到任意一個葉子節點，路徑中的黑色節點數一樣 (黑色完美平衡)
 **/
public class RedBlackTree {
    enum Color {
        RED, BLACK;
    }

    private Node root;

    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent; // 節點的父節點
        Color color = Color.RED;

        public Node(int key, Object value) {
        }

        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

        // 判斷紅黑

    }

    boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    boolean isBlack(Node node) {
        return node == null || node.color == Color.BLACK;
    }

    // 右旋 1. parent處理 2. 旋轉後新的父子關係
    private void rightRotate(Node node) {
        Node parent = node.parent;
        Node yellow = node.left;
        Node green = yellow.right;
        if (green != null) {
            green.parent = node;
        }
        yellow.right = node;
        yellow.parent = parent;
        node.left = green;
        node.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.left == node) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    // 左旋
    private void leftRotate(Node node) {
        Node parent = node.parent;
        Node lx = node.left;
        Node y = node.right;
        if (lx != null) {
            lx.parent = node;
        }
        y.left = node;
        y.parent = parent;
        node.left = lx;
        node.parent = y;
        if (parent == null) {
            root = y;
        } else if (parent.left == node) {
            parent.left = y;
        } else {
            parent.right = y;
        }
    }

    // 新增或更新
    // 正常增、遇到紅紅不平衡進行調整
    private void put(int key, Object value) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                p.value = value; // update
                return;
            }
        }
        Node inserted = new Node(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);
    }

    void fixRedRed(Node x) {
        // case 1 插入節點是節點，變黑即可
        if (x == root) {
            x.color = Color.BLACK;
            return;
        }
        // case 2 插入節點父親是黑色，無須調整
        if (isBlack(x.parent)) {
            return;
        }
        // case 3 當紅紅相鄰，叔叔為紅時
        // 需要將父親、叔叔變黑，祖父變紅，然後對祖父做遞歸處理
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        if (isRed(uncle)) {
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandparent.color = Color.RED;
            fixRedRed(grandparent);
            return;
        }
        // case 4 插入節點的父親為紅色，發生紅紅相鄰，叔叔為黑
        // 若父親為左孩子，插入節點也是左孩子，會有LL不平衡
        if (parent.isLeftChild() && x.isLeftChild()) {
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            rightRotate(grandparent);
        } else if (parent.isLeftChild()) {
            // 父親為左孩子，插入節點是右孩子，LR不平衡
            leftRotate(parent);
            x.color = Color.BLACK;
            grandparent.color = Color.RED;
            rightRotate(grandparent);
        } else if (!x.isLeftChild()) {
            // 父親為右孩子，插入節點為右孩子，RR不平衡
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(grandparent);
        } else {
            // 父親為右孩子，插入節點是左孩子，RL不平衡
            rightRotate(parent);
            x.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(grandparent);
        }
    }

    // 刪除
    // 正常刪、遇到黑黑不平衡進行調整
    private void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    private void fixDoubleBlack(Node x) {
        if (x == root) {
            return;
        }
        Node parent = x.parent;
        Node sibling = x.sibling();
        // case 3 兄弟節點是紅色
        if (isRed(sibling)) {
            if (x.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = Color.RED;
            sibling.color = Color.BLACK;
            fixDoubleBlack(x);
            return;
        }
        // 兄弟為黑色
        if (sibling != null) {
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = Color.RED;
                if (isRed(parent)) {
                    parent.color = Color.BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            }
            // case 5 兄弟是黑色 侄子是紅色
            else {
                // LL
                if (sibling.isLeftChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = Color.BLACK;
                    sibling.color = parent.color;
                }
                // LR
                else if (sibling.isLeftChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                // RL
                else if (!sibling.isLeftChild() && isRed(sibling.left)) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                // RR
                else {
                    leftRotate(parent);
                    sibling.right.color = Color.BLACK;
                    sibling.color = parent.color;
                }
                parent.color = Color.BLACK;
            }
        } else {
            fixDoubleBlack(parent);
        }

    }

    private void doRemove(Node deleted) {
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;
        // no child
        if (replaced == null) {
            // case 1 刪除的是根節點
            if (deleted == root) {
                root = null;
            } else {
                if (isBlack(deleted)) {
                    fixDoubleBlack(deleted);
                } else {

                }

                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }
        // hava one child
        if (deleted.left == null || deleted.right == null) {
            // case 1 刪除的是根節點
            if (deleted == root) {
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;
            } else {
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;

                if (isBlack(deleted) && isRed(replaced)) {
                    fixDoubleBlack(replaced);
                } else {
                    replaced.color = Color.BLACK;
                }
            }
            return;
        }
        // have two children
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.key;
        deleted.value = replaced.value;
        replaced.value = v;
        doRemove(replaced);

    }

    // 查找刪除節點
    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    // 查找剩餘節點
    private Node findReplaced(Node deleted) {
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        Node s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }
}
