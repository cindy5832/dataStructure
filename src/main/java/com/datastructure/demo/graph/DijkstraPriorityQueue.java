package com.datastructure.demo.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraPriorityQueue {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6);
        dijkstra(graph, v1);
    }

    private static void dijkstra(List<Vertex> graph, Vertex source) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist));
        source.dist = 0;

        for (Vertex v : graph) {
            queue.offer(v);
        }

        while (!queue.isEmpty()) {
            // 3. 選取當前頂點
            Vertex curr = queue.peek();
            // 4. 更新當前頂點鄰居距離
            if (!curr.visited) {
                updateNeighbourDist(curr, queue);
                curr.visited = true;
            }
            // 5. 移除當前頂點
            queue.poll();
            curr.visited = true;
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " dist: " + vertex.dist + " " + (vertex.prev != null ? vertex.prev.name : "null"));
        }
    }

    private static void updateNeighbourDist(Vertex curr, PriorityQueue<Vertex> queue) {
        for (Edge edge : curr.edges) {
            Vertex edges = edge.linked;
            Vertex n = edge.linked;
            if (!n.visited) {
                int dist = curr.dist + edge.weight;
                if (dist < n.dist) {
                    n.dist = dist;
                    n.prev = curr;
                    queue.offer(n);
                }
            }
        }
    }

}