package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree2 {

    Node root;

    public void add(int value) {
       root = addRecursive(root,value);
    }

    private Node addRecursive(Node current, int value) {
    	if (current == null) {
    		return new Node(value);
    	}
    	if (value < current.value) {
    		current.left = addRecursive(current.left, value);
    	} else {
    		current.right = addRecursive(current.right, value);
    	}
    	return current;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        return getSizeRecursive(root);
    }

    private int getSizeRecursive(Node current) {
    	if (current == null) {
    		return 0;
    	} else {
    		return getSizeRecursive(current.left) + 1 + getSizeRecursive(current.right);
    	}
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
       if(current == null) {
    	   return false;
       }
       if (value == current.value) {
    	   return true;
       }
       return current.value < value ? containsNodeRecursive(current.left,value) : containsNodeRecursive(current.right,value);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
       if (current == null) {
    	   return null;
       }
       if (value == current.value) {
    	   //no child
    	   if (current.left == null && current.right == null) {
    		   return null;
    	   }
    	   //one child
    	   if (current.right != null) {
    		   return current.left;
    	   }
    	   if (current.left != null) {
    		   return current.right;
    	   }
    	   //two childs
    	   int smallestValue = findSmallestValue(current.right);
    	   current.value = smallestValue;
    	   current.right = deleteRecursive(current.right, smallestValue);
    	   return current;
       }
       if (value < current.value) {
    	   current.left = deleteRecursive(current.left, value);
    	   return current;
       } else {
    	   current.right = deleteRecursive(current.right, value);
    	   return current;
       }
    }

    private int findSmallestValue(Node current) {
        return current.left == null ? current.value : findSmallestValue(current.left);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.value);
        }
    }

    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            Node node = queue.remove();

            System.out.print(" " + node.value);

            if (node.left != null) {
            	queue.add(node.left);
            }

            if (node.left != null) {
            	queue.add(node.right);
            }
        }
    }

    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }
}
