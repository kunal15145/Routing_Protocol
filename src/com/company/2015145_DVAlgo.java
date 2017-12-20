package com.company;

import java.util.*;

class DVAlgo {

    private static Graph graph;
    private static List<Edge> edges;
    private static int distanceVector[];
    private static int numberOfVertices;
    private static int numberOfEdges;
    private static HashMap<Character,Integer> map;
    private static HashMap<Character,Character> pathmap;
    private static Character source;

    DVAlgo(Graph graph) {
        DVAlgo.graph = graph;
    }

    void StartBelmanFord(Character source){
        DVAlgo.source = source;
        Set<Character> verticesCopy = graph.getVerticeSet();
        edges = graph.getEdges();
        distanceVector = new int[verticesCopy.size()];
        pathmap = new HashMap<>();
        numberOfVertices = verticesCopy.size();
        numberOfEdges = graph.getEdges().size();
        map = new HashMap<>();
        int i=0;
        for(Character character: verticesCopy){
            map.put(character,i);
            i++;
        }
        for(int j = 0; j< verticesCopy.size(); j++){
            if(j==map.get(source))
                distanceVector[j]=0;
            else distanceVector[j]=Integer.MAX_VALUE;
        }
        Initialize();
    }

    private void Initialize() {
        for(int i=1;i<=numberOfVertices-1;i++){
            for(int j=0;j<numberOfEdges;j++) {
                int temp1 = map.get(edges.get(j).getSource());
                int temp2 = map.get(edges.get(j).getDestination());
                if (distanceVector[temp1] != Integer.MAX_VALUE
                        && distanceVector[temp1] + edges.get(j).getWeight() < distanceVector[temp2]) {
                    pathmap.put(getCharacterMap(temp2),getCharacterMap(temp1));
                    distanceVector[temp2] = distanceVector[temp1] + edges.get(j).getWeight();
                    PrintGraph();
                }
            }
        }
    }

    private void PrintGraph() {
        System.out.println(source + "'s routing table\n");
        System.out.println("        Destination\t\t\t\tSource:::Interface");
        for (int i = 0; i < numberOfVertices; ++i) {
            LinkedList<Character> path = new LinkedList<>();
            Character temp = getCharacterMap(i);
            path.add(temp);
            while(pathmap.get(getCharacterMap(i))!=null){
                if(temp==source){
                    break;
                }
                temp = pathmap.get(temp);
                if(temp==source){
                    path.add(source);
                    break;
                }
                path.add(temp);
            }
            Collections.reverse(path);
//            if(path.size()==1){
//                if(path.get(0)!=source) {
//                    System.out.format("%15s%33s", path.getFirst(), "Path Not Found");
//                    System.out.println();
//                }
//            }
//            else {
//                System.out.format("%15s%23s:::%s",path.getLast(),path.getFirst(),path.get(1));
//                System.out.println();
//            }
            if(distanceVector[i]!=Integer.MAX_VALUE){
                System.out.format("%15s%25d",Arrays.toString(path.toArray()),distanceVector[i]);
                System.out.println();
            }
            else {
                System.out.format("%15s%25s",Arrays.toString(path.toArray()),"Inf");
                System.out.println();
            }
        }
        System.out.println();
    }

    private Character getCharacterMap(int mini) {
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            if(entry.getValue()==mini)
                return entry.getKey();
        }
        return null;
    }

}
