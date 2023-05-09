public class TestDijkstra {
    public static void main(String[] args) {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");

        a.link(b, 2);
        a.link(c, 4);
        b.link(c, 1);
        b.link(d, 7);
        c.link(d, 3);
        c.link(e, 6);
        d.link(f, 1);
        e.link(f, 8);

        Node[] nodes = {a, b, c, d, e, f};
        Node.Dijkstra(a,f);

        for (Node n : nodes) {
            System.out.println("Shortest path to node " + n.getLabel() + ": " + n.getPath());
        }
    }
}
