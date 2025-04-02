package com.datastructure.demo.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 多源最短路徑算法
public class FloydWarshall {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        /**
         *    (4) v1 ↘ (-2)
         * v2 ↗  -(3)-→ v3
         *   ↖ (-1)v4 ↙ (2)
         **/

        v1.edges = List.of(new Edge(v3, -2));
        v2.edges = List.of(new Edge(v1, 4), new Edge(v3, 3));
        v3.edges = List.of(new Edge(v4, 2));
        v4.edges = List.of(new Edge(v2, -1));

        List<Vertex> graph = List.of(v1, v2, v3, v4);
        floydWarshall(graph);
        /**
         *     k = 0 直接連通
         *     v1 v2 v3 v4
         * v1  0  ∞  -2  ∞
         * v2  4  0   3  ∞
         * v3  ∞  ∞   0  2
         * v4  ∞ -1   ∞  0
         **/

        /**
         *     k = 1 借助 v1 到達其他頂點
         *     v1 v2 v3 v4
         * v1  0  ∞  -2  ∞
         * v2  4  0   2  ∞
         * v3  ∞  ∞   0  2
         * v4  ∞ -1   ∞  0
         **/

        /**
         *     k = 2 借助 v2 到達其他頂點
         *     v1 v2 v3 v4
         * v1  0  ∞  -2  ∞
         * v2  4  0   2  ∞
         * v3  ∞  ∞   0  2
         * v4  3 -1   1  0
         **/

        /**
         *     k = 3 借助 v3 到達其他頂點
         *     v1 v2 v3 v4
         * v1  0  ∞  -2  0
         * v2  4  0   2  4
         * v3  ∞  ∞   0  2
         * v4  3 -1   1  0
         **/

        /**
         *     k = 4 借助 v4 到達其他頂點
         *     v1 v2 v3 v4
         * v1  0 -1  -2  0
         * v2  4  0   2  4
         * v3  5  1   0  2
         * v4  3 -1   1  0
         **/

    }

    private static void floydWarshall(List<Vertex> graph) {
        int size = graph.size();
        int[][] dist = new int[size][size];
        Vertex[][] prev = new Vertex[size][size];
        // 1. 初始化
        for (int i = 0; i < size; i++) {
            Vertex v = graph.get(i);
            for (int j = 0; j < size; j++) {
                Vertex u = graph.get(j);
                Map<Vertex, Integer> map = v.edges.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight));
                if (v == u) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = map.getOrDefault(u, Integer.MAX_VALUE);
                    prev[i][j] = map.get(u) != null ? v : null;
                }
            }
        }
//        print(prev);
        // 2. 是否能藉由其他路徑到達其他頂點
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
//                    dist[i][k] + dist[k][j] // i行頂點，借助k頂點到達j頂點
//                    dist[i][j] // i 行頂點，直接到達j列頂點
                    if (dist[i][k] != Integer.MAX_VALUE &&
                            dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        prev[i][j] = prev[k][j];
                    }
                }
            }
//            print(dist);
        }
        print(prev);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path(prev, graph, i, j);
            }
        }
    }

    static void print(int[][] dist) {
        for (int[] i : dist) {
            System.out.print("[");
            for (int i1 : i) {
                String s = null;
                if (i1 == Integer.MAX_VALUE) {
                    s = "∞";
                } else {
                    s = String.valueOf(i1);
                }
                System.out.print(s + "\t");
            }
            System.out.print("]" + "\n");
        }
        System.out.println("=====================");
    }

    static void path(Vertex[][] prev, List<Vertex> graph, int i, int j) {
        LinkedList<String> stack = new LinkedList<>();
        System.out.print("[" + graph.get(i).name + "," + graph.get(j).name + "]");
        stack.push(graph.get(j).name);
        while (i != j) {
            Vertex p = prev[i][j];
            stack.push(p.name);
            j = graph.indexOf(p);
        }
        System.out.println(stack);
    }

    static void print(Vertex[][] prev) {
        System.out.println("======================");
        for (Vertex[] row : prev) {
            System.out.println(Arrays.stream(row).map(v -> v == null ? "null" : v.name)
                    .map(s -> String.format("%5s", s))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }

    /**
     *      v2
     *  (2)↗    ↘ (-4)
     * v1 <--(1) v3 --(1)--> v4
     *==== 初始化 =========     =======k = 0  =====     =======k = 1  =====  出現負環 最小路徑無解 (當自己到自己的距離為負數時 ex v3-v3)
     *     v1  v2  v3  v4          v1  v2  v3  v4          v1  v2  v3  v4
     * v1   0   2   ∞   ∞      v1   0   2   ∞   ∞      v1   0   2  -2   ∞
     * v2   ∞   0   -4  ∞      v2   ∞   0   -4  ∞      v2   ∞   0  -4   ∞
     * v3   1   ∞    0  1      v3   1   3    0  1      v3   1   3  -1   1
     * v4   ∞   ∞    ∞  0      v4   ∞   ∞    ∞  0      v4   ∞   ∞   ∞   0
     **/
}
