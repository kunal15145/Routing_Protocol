package com.company;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main_2015145 {

    private static Graph graph = new Graph(new ArrayList<>(),new ArrayList<>());

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("1. Add Edge");
        System.out.println("2. Remove Edge");
        System.out.println("3. Update Weight");
        System.out.println("Format: -  QB1 ");
        System.out.println();
        MakeGraph();
        graph.createUniqueVerticesSet();
        LinkStateAlgo linkStateAlgo = new LinkStateAlgo(graph);
        DVAlgo dvAlgo = new DVAlgo(graph);
        if(choice==1){
            System.out.println("Running Dijkstra\n");
            for(Character character:graph.getVerticeSet()){
                linkStateAlgo.InitializeDijkistra(character);
            }
        }
        else if(choice==2){
            System.out.println("Running Bellman-Ford\n");
            for(Character character:graph.getVerticeSet()) {
                dvAlgo.StartBelmanFord(character);
            }
        }
        while(true){
            String temp = scanner.nextLine();
            if(temp.equals("end"))
                break;
            else if(temp.length()==5){
                if (Character.getNumericValue(temp.charAt(0)) == 1) {
                    graph.addEdge(temp.charAt(2), temp.charAt(3), Character.getNumericValue(temp.charAt(4)));
                    graph.addEdge(temp.charAt(3), temp.charAt(2), Character.getNumericValue(temp.charAt(4)));
                }
                else if(Character.getNumericValue(temp.charAt(0))==2) {
                    if(!graph.removeEdge(temp.charAt(2), temp.charAt(3)))
                        System.out.println("No Such Edge to remove");
                }
                else if(Character.getNumericValue(temp.charAt(0))==3){
                    if(!graph.UpdateGraph(temp.charAt(2),temp.charAt(3),Character.getNumericValue(temp.charAt(4))))
                        System.out.println("No Such Edge to Update");
                }
                if(choice==1){
                    for(Character character:graph.getVerticeSet()){
                        linkStateAlgo.InitializeDijkistra(character);
                    }
                }
                else if(choice==2){
                    for(Character character:graph.getVerticeSet()) {
                        dvAlgo.StartBelmanFord(character);
                    }
                }
            }
        }
    }

//    private static void printEdges() {
//        for(Edge edge:graph.getEdges()) {
//            System.out.print(edge.getSource());
//            System.out.print(edge.getDestination());
//            System.out.print(edge.getWeight());
//            System.out.println();
//        }
//    }

    private static void MakeGraph() throws IOException {
        Path filepath = Paths.get("input.txt");
        Scanner scanner = new Scanner(filepath);
        while(true) {
            String s = scanner.next();
            if (s.equals("#")) {
                break;
            }
            String d = scanner.next();
            if (d.equals("#")) {
                break;
            }
            String w = scanner.next();
            if (w.equals("#")) {
                break;
            }
            graph.addVertex(s.charAt(0));
            graph.addVertex(d.charAt(0));
            graph.addEdge(s.charAt(0), d.charAt(0), Integer.parseInt(w));
            graph.addEdge(d.charAt(0), s.charAt(0), Integer.parseInt(w));
        }
    }
}