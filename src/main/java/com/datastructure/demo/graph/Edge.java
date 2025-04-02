package com.datastructure.demo.graph;

// 邊
public class Edge {

    Vertex linked;
    int weight;

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }

    public Edge(Vertex linked) {
        this(linked, 1);
    }

}
