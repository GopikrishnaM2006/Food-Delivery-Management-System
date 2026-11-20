package App.GraphNetwork;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Node {
    String place;
    int time;

    Node(String place, int time) {
        this.place = place;
        this.time = time;
    }
}
// Contains Algorithm to find the shortest path between points
public class Dijkstra {
    public int shortestTime(HashMap<String, HashMap<String, Integer>> network, String source, String destination){
        if (!(network.containsKey(source) && network.containsKey(destination))) {
            // TODO: throw new Exception()
        }
        HashMap<String, String> parent = new HashMap<>();
        HashMap<String, Integer> time = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));

        // TODO: No possible routes

        for (Map.Entry<String, HashMap<String, Integer>> connections : network.entrySet()) {
            if (connections.getKey().equals(source)) {
                time.put(source, 0);
            } else {
                time.put(connections.getKey(), Integer.MAX_VALUE);
            }
        }
        queue.offer(new Node(source, 0));
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if (n.time > time.get(n.place)) {
                continue;
            }
            HashMap<String, Integer> neighbours = network.get(n.place);
            for (Map.Entry<String, Integer> neighbour : neighbours.entrySet()) {
                if (n.time + neighbour.getValue() < time.get(neighbour.getKey())) {
                    queue.offer(new Node(neighbour.getKey(), n.time + neighbour.getValue()));
                    time.put(neighbour.getKey(), n.time + neighbour.getValue());
                    parent.put(neighbour.getKey(), n.place);
                }
            }
        }
        
        // if (time.get(destination) != Integer.MAX_VALUE) {
            return time.get(destination);
        // } else {
        //     // TODO
        //     throw new Exception("hi");
        // }
    }
}
