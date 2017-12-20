package com.company;

import java.util.*;

class LinkStateAlgo {

    private static Graph graph;
    private static Set<Character> Uvertices;
    private static int currentDistanceVector[];
    private static Boolean VisitedTable[];
    private static Map<Character,Character> pathmap;
    private static Character source;
    private static HashMap<Character,Integer> map;

    LinkStateAlgo(Graph graph) {
        LinkStateAlgo.graph = graph;
    }

    void InitializeDijkistra(Character s) {
        Uvertices = new HashSet<>();
        pathmap = new HashMap<>();
        map = new HashMap<>();
        source = s;
        Uvertices = graph.getVerticeSet();
        currentDistanceVector = new int[Uvertices.size()];
        VisitedTable = new Boolean[Uvertices.size()];
        int i=0;
        for(Character character:Uvertices){
            map.put(character,i);
            i++;
        }
        for(int k=0;k<Uvertices.size();k++){
            if(k==map.get(source))
                currentDistanceVector[k]=0;
            else currentDistanceVector[k]=Integer.MAX_VALUE;
            VisitedTable[k] = false;
        }
        StartDijkstra();
    }

    private void StartDijkstra() {
        for(int i=0;i<Uvertices.size()-1;i++){
            int mini = -1;
            int min = Integer.MAX_VALUE;
            Character temp = null;
            for(int vertex=0;vertex<Uvertices.size();vertex++){
                if(!VisitedTable[vertex] && currentDistanceVector[vertex]<=min){
                    min = currentDistanceVector[vertex];
                    mini = vertex;
                }
            }
            if(mini==-1){
                System.out.println("Shouldn't have happened1");
                System.exit(0);
            }
            else{
                VisitedTable[mini] = true;
                temp = getCharacterMap(mini);
            }
            for(int vertex=0;vertex<Uvertices.size();vertex++){
                Character temp1 = getCharacterMap(vertex);
                if(temp == null){
                    System.out.println("Shouldn't have happened2");
                    System.exit(0);
                }
                if(!VisitedTable[vertex]
                        && graph.EdgeExistence(temp,temp1)
                        && currentDistanceVector[mini]!=Integer.MAX_VALUE
                        && currentDistanceVector[mini]+ graph.getEdgeWeight(temp,temp1) < currentDistanceVector[vertex]){
                    pathmap.put(getCharacterMap(vertex),getCharacterMap(mini));
                    currentDistanceVector[vertex] = currentDistanceVector[mini]+ graph.getEdgeWeight(temp,temp1);
                }
            }
            PrintShortestGraph();
        }
    }

    private void PrintShortestGraph() {
        System.out.println(source+"'s routing table\n");
        System.out.println("        Destination\t\t\t\tSource:::Interface");
        for(int i=0;i<Uvertices.size();i++){
            LinkedList<Character> path = new LinkedList<>();
            Character temp = getCharacterMap(i);
            path.add(temp);
            while(pathmap.get(temp)!=null){
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
            if(currentDistanceVector[i]!=Integer.MAX_VALUE){
                System.out.format("%15s%25d",Arrays.toString(path.toArray()),currentDistanceVector[i]);
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
