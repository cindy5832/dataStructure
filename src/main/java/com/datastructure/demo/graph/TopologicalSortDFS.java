package com.datastructure.demo.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSortDFS {
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
        LinkedList<String > stack = new LinkedList<>();
        for (Vertex vertex : graph) {
            dfs(vertex, stack);
        }
        System.out.println(stack);
    }
    private static void dfs(Vertex v, LinkedList<String> stack) {
        if(v.status == 2) return;
        if(v.status == 1){
            throw new RuntimeException("there is a loop");
        }
        v.status = 1;
        for (Edge edge : v.edges) {
            dfs(edge.linked, stack);
        }
        v.status = 2;
        stack.push(v.name);
    }
}
