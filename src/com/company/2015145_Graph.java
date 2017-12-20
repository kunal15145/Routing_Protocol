package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Graph {

    private List<Character> vertices;
    private List<Edge> edges;
    private Set<Character> verticeU = new HashSet<>();

    Graph(List<Character> v, List<Edge> e) {
        this.vertices = v;
        this.edges = e;
    }

    public List<Character> getVertices() {
        return this.vertices;
    }

    public List<Edge> getEdges() {
        return this.edges;
    }

    void addVertex(char s) {
        vertices.add(s);
    }

    void addEdge(char s,char d,int w){
        edges.add(new Edge(s,d,w));
    }

    boolean removeEdge(char s,char d) {
        for (Edge edge : getEdges()) {
            if (edge.getSource() == s && edge.getDestination() == d) {
                getEdges().remove(edge);
                return true;
            }
        }
        return false;
    }

    boolean UpdateGraph(char s,char d,int w){
        for(Edge edge:getEdges()) {
            if (edge.getSource() == s && edge.getDestination() == d) {
                edge.setWeight(w);
                return true;
            }
        }
        return false;
    }

    boolean EdgeExistence(Character s, Character d){
        for(Edge edge:getEdges()){
            if(edge.getSource()==s && edge.getDestination()==d){
                return true;
            }
        }
        return false;
    }

    int getEdgeWeight(Character s,Character d){
        for(Edge edge:getEdges()){
            if(edge.getSource()==s && edge.getDestination()==d)
                return edge.getWeight();
        }
        return -1;
    }

    void createUniqueVerticesSet() {
        for(int i=0;i<getVertices().size();i++){
            verticeU.add(vertices.get(i));
        }
    }

    Set<Character> getVerticeSet(){
        return verticeU;
    }

}
