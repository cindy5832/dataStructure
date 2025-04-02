package com.datastructure.demo.graph;

import java.util.ArrayList;
import java.util.List;

public class BellmanFord {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");


        v1.edges = List.of(new Edge(v3, 1), new Edge(v2, 2));
        v2.edges = List.of(new Edge(v3, -2));
        v3.edges = List.of(new Edge(v4, 1));
        v4.edges = List.of();


        List<Vertex> graph = List.of(v1, v2, v3, v4);
        bellmanFord(graph, v1);
    }

    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.dist = 0;
        int size = graph.size();
        // 1. 進行 頂點個數 -1 輪處理
        for (int i = 0; i < size - 1; i++) {
            // 2. 遍歷所有的邊
            for (Vertex s : graph) {
                for (Edge edge : s.edges) {
                    // 3. 處理每一條邊
                    Vertex e = edge.linked;
                    if (s.dist + edge.weight < e.dist && s.dist != Integer.MAX_VALUE) {
                        e.dist = s.dist + edge.weight;
                        e.prev = s;
                    }
                }
            }
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.dist);

        }
    }

}