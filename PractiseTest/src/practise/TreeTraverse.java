package practise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import bfs.Node;

public class TreeTraverse {

    static class Node{
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
            this.visited = false;
        }
        int data;
        Node left;
        Node right;
        boolean visited;
        
        public String toString() {
        	return this.data + "";
        }
    }

    public static void main(String[] args) {
        //The tree:
        //   1
        //  / \
        // 7   9
        // \  / \
        //  8 2 3

        Node node1 = new Node(1);
        Node node7 = new Node(7);
        Node node9 = new Node(9);
        Node node8 = new Node(8);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.left = node7;
        node1.right = node9;
        node7.right = node8;
        node9.right = node3;
        node9.left = node2;
        System.out.println("BFS: ");
        System.out.println(breadthFirstSearch(node1));

    }

    private static List<Node> breadthFirstSearch(Node node){
    	List<Node> explored = new ArrayList<>();
    	Queue<Node> queue = new LinkedList<>();
    	queue.add(node);
        while(!queue.isEmpty()){
        	node = queue.remove();
            if(node.left != null){
            	queue.add(node.left);
            }
            if(node.right != null){
            	queue.add(node.right);
            }
            explored.add(node);
        }
        return explored;
    }

}