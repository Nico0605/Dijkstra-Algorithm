public class TestDijkstraAlgorithm {
    public static void main(String[] args) {
        Node home = new Node("home");
        Node a = new Node("a");
        Node b = new Node("b");
        Node office = new Node("office");

        new Link(home, a,1);
        new Link(home, b, 2);
        new Link(a,office, 2);
        new Link(b, office, 1);

        System.out.println(home.goTo(office));

    }
}
