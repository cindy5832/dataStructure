package com.datastructure.demo.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSort {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("網頁基礎");
        Vertex v2 = new Vertex("Java 基础");
        Vertex v3 = new Vertex("JavaWeb");
        Vertex v4 = new Vertex("Spring");
        Vertex v5 = new Vertex("微服務框架");
        Vertex v6 = new Vertex("Database");
        Vertex v7 = new Vertex("實戰項目");

        v1.edges = List.of(new Edge(v3));
        v2.edges = List.of(new Edge(v3));
        v3.edges = List.of(new Edge(v4));
        v6.edges = List.of(new Edge(v4));
        v4.edges = List.of(new Edge(v5));
        v5.edges = List.of(new Edge(v7));
        v7.edges = List.of();

        // 1. 統計每個頂點的入度
        List<Vertex> graph = new ArrayList<>(List.of(v1, v2, v3, v4, v5, v6, v7));
        for (Vertex v : graph) {
            for (Edge e : v.edges) {
                e.linked.inDegree++;
            }
        }

        // 2. 將入度為0的頂點加入隊列
        LinkedList<Vertex> queue = new LinkedList<>();
        for (Vertex vertex : graph) {
            if (vertex.inDegree == 0) {
                queue.add(vertex);
            }
        }
        // 3. 隊列中不斷移除頂點，每移除一個頂點，把它相鄰頂點入度減1，若撿到0則入隊
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            result.add(poll.name);
            for (Edge e : poll.edges) {
                e.linked.inDegree--;
                if (e.linked.inDegree == 0) {
                    queue.offer(e.linked);
                }
            }
        }
        // 檢測環方式：若兩者相同 → 非環
        System.out.println(result.size());
        System.out.println(graph.size());
    }
}
