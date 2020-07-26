package bfs;

public class Test {

    public static void main(String args[]){
        Node station1 = new Node("Westminster", null, null);
        Node station2 = new Node("Waterloo", station1, null);
        Node station3 = new Node("Trafalgar Square", station1, station2);
        Node station4 = new Node("Canary Wharf", station2, station3);
        Node station5 = new Node("London Bridge", station4, station3);
        Node station6 = new Node("Tottenham Court Road", station5, station4);

        BreadthFirstSearch bfs = new BreadthFirstSearch();

        System.out.println(bfs.compute(station6,station1));
        
        Node n6 = new Node("F", null, null);
        Node n5 = new Node("E", n6, null);
        Node n4 = new Node("D", null, null);
        Node n3 = new Node("C", n4, n5);
        Node n2 = new Node("B", null, null);
        Node n1 = new Node("A", n2, n3);
        
        System.out.println(bfs.compute(n1,n6));
    }
}
