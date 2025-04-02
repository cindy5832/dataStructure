package com.datastructure.demo.binarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BSTTree1 {
    public static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    public BSTNode root;

    // 查詢key對應的value
    public Object get(int key) {
        BSTNode current = root;
        while (current != null) {
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    public Object get1(int key) {
        return doGet(root, key);
    }

    // method 1
    private Object doGet(BSTNode node, int key) {
        if (node == null) return null;
        if (key < node.key) {
            return doGet(node.left, key);
        } else if (node.key < key) {
            return doGet(node.right, key);
        } else {
            return node.value;
        }
    }

    // 查詢最小關鍵字對應的值
    public Object min() {
        return min(root);
    }

    private Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    // 查詢最小關鍵字對應的值
    public Object min1() {
        return doMin(root);
    }

    private Object doMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return doMin(node.left);
    }

    // 查詢最大關鍵字對應的值
    public Object max() {
        if (root == null) {
            return null;
        }
        BSTNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    public Object max1() {
        return max(root);
    }

    private Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    // 存key & value
    public void put(int key, Object value) {
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                // key有 更新
                node.value = value;
                return;
            }
        }
        if (parent == null) {
            root = new BSTNode(key, value);
            return;
        }
        // key沒有 加入
        if (key < parent.key) {
            parent.left = new BSTNode(key, value);
        } else if (key > parent.key) {
            parent.right = new BSTNode(key, value);
        }

    }

    // 查關鍵字的前驅值
    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFormRight = null;
        while (p != null) {
            if (key < p.key) {
                ancestorFormRight = p;
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) return null;
        if (p.right != null) {
            return min(p.right);
        }
        return ancestorFormRight != null ? ancestorFormRight.value : null;
    }

    // 查詢關鍵字的後繼值
    public Object predecessor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                ancestorFromLeft = p;
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) {
            return null;
        }
        if (p.left != null) {
            return max(p.left);
        }
        return ancestorFromLeft != null ? ancestorFromLeft.value : null;
    }

    // 根據關鍵字刪除 返回被刪除的對應值
    public Object delete(int key) {
        BSTNode p = root;
        BSTNode parent = null;
        while (p != null) {
            if (key < p.key) {
                parent = p;
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) return null;
        if (p.left == null) { // 沒有左孩子
            shift(parent, p, p.right);
        } else if (p.right == null) { // 沒有右孩子
            shift(parent, p, p.left);
        } else {
            BSTNode s = p.right;
            BSTNode sParent = p;
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }
            if (sParent != p) {
                shift(sParent, s, s.right);
                s.right = p.right;
            }
            shift(parent, p, s);
            s.left = p.left;
        }
        return p.value;
    }

    public Object delete1(int key) {
        ArrayList<Object> result = new ArrayList<>();
        root = doDelete(root, key, result);
        return result.isEmpty() ? null : result.get(0);
    }

    private BSTNode doDelete(BSTNode node, int key, ArrayList<Object> result) {
        if (node == null) return null;
        if (key < node.key) {
            node.left = doDelete(node.left, key, result);
            return node;
        }
        if (key > node.key) {
            node.right = doDelete(node.right, key, result);
            return node;
        }
        result.add(node.value);
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        BSTNode s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        s.right = doDelete(node.right, s.key, new ArrayList<>());
        s.left = node.left;
        return s;
    }

    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    // 找 < key 的所有value
    public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 處理值
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    // 找 > key 的所有value
    public List<Object> greater(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 處理值
                if (pop.key > key) {
                    result.add(pop.value);
                }
                p = pop.right;
            }
        }
        return result;
    }

    // 找 >= key1 且 <= key2 的所有值
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 處理值
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }


}
