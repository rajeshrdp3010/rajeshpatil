package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * basic breadth first search in java
 */
public class BreadthFirstSearch2 {


    public BreadthFirstSearch2(){
    	
    }

   
    public static List<Node> compute(Node start, Node end){
    	List<Node> explored = new ArrayList<Node>();
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(start);
    	
    	if (start.equals(end)) {
    		explored.add(start);
    		return explored;
    	}
    	
    	while(!queue.isEmpty()) {
    		Node node = queue.remove();
    		if (node.equals(end)) {
    			explored.add(node);
        		return explored;
    		}
    		if (node.leftChild != null) {
    			queue.add(node.leftChild);
    		}
    		if (node.rightChild != null) {
    			queue.add(node.rightChild);
    		}
    		explored.add(node);
    	}
    	return explored;
    }

}
