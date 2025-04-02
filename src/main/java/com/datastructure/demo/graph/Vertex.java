package com.datastructure.demo.graph;

import java.util.List;
import java.util.Objects;

// 頂點
public class Vertex {
    String name;
    List<Edge> edges;

    boolean visited; // 是否被訪問過
    int inDegree; // 入度
    int status; // 狀態 0 - 未訪問 1 - 訪問中 2- 訪問過，用在topological排序

    int dist = INF; // 距離
    static final Integer INF = Integer.MAX_VALUE;
    Vertex prev = null; // 紀錄重何而來

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public static void main(String[] args) {
        /**
         *   ↗  → b ↘
         * a          d
         *   ↘  → c ↗
         **/
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.edges = List.of(new Edge(b),new Edge(c));
        b.edges = List.of(new Edge(d));
        c.edges = List.of(new Edge(d));
        d.edges = List.of();


    }
}
