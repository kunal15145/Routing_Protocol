package com.company;

class Edge {
    private char source;
    private char destination;
    private int weight;
    Edge(char s,char d,int w){
        this.source = s;
        this.destination = d;
        this.weight = w;
    }
    char getSource(){
        return this.source;
    }
    char getDestination(){
        return this.destination;
    }
    int getWeight(){
        return this.weight;
    }
    void setWeight(int weight) {
        this.weight = weight;
    }
}
