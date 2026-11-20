package App.GraphNetwork;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
// Stores the address in graphical manner (network)
public class Network implements Serializable{
    private final static long serialVersionUID = 4L;
    public static HashMap<String, HashMap<String,Integer>> network = new HashMap<>();

    void createNode(String place, String adjacentPlace, int time){
        HashMap<String, Integer> connections = new HashMap<>();
        connections.put(adjacentPlace, time);
        network.put(place, connections);
        // addNode(place, adjacentPlace, time);
    }
    void addNode(String place, String adjacentPlace, int time){
        HashMap<String,Integer> connections = network.get(place);
        connections.put(adjacentPlace, time);
    }
    public void displayNode(){
        if(network.isEmpty()){
            network.put("hotel", new HashMap<String,Integer>());
        }
        for(Map.Entry<String, HashMap<String,Integer>> connections : network.entrySet()){
            System.out.printf("%-10s",connections.getKey());
        }
        System.out.print("other");
    }
    // TODO: Same track better time
    public void addRoute(String place, String adjacentPlace, int time){
        if(network.containsKey(place)){
            addNode(place, adjacentPlace, time);
        }else{
            createNode(place, adjacentPlace, time);
        }
        if(network.containsKey(adjacentPlace)){
            addNode(adjacentPlace, place, time);
        }
        else{
            createNode(adjacentPlace, place, time);
        }
    }
}
