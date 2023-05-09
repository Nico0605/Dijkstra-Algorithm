import java.util.*;

public class Node implements Comparable<Node> {
    private String label;
    private int weight = Integer.MAX_VALUE;
    private TreeMap<Node, Integer> links;
    private Node prev = null;
    private boolean visited = false;

    public Node(String label) {
        this.label = label;
        links = new TreeMap<>((a, b) -> a.label.compareTo(b.label));
    }

    public void link(Node node2, int weight) {
        links.put(node2, weight);
        node2.links.put(this, weight);
    }

    public int weightTo(Node node) {
        return weight + links.get(node);
    }

    public String getPath() {
        String weight = "";
        if (this.weight < Integer.MAX_VALUE)
            weight += this.weight;
        else
            weight = "inf";
        String out = "(" + label + "-" + weight + ")";
        if (prev != null)
            out = prev.getPath() + "-" + prev.links.get(this) + "->" + out;
        return out;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getLabel() {
        return label;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public TreeMap<Node, Integer> getLinks() {
        return links;
    }

    public int compareTo(Node other) {
        return Integer.compare(weight, other.weight);
    }

    public static List<Node> Dijkstra(Node start, Node end) {
        start.setWeight(0);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(start);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            current.setVisited(true);

            if (current.equals(end)) {
                break;
            }

            //Map it's similar to treeMap but the main difference is treeMap order the key and value in a tree
            for (Map.Entry<Node, Integer> entry : current.getLinks().entrySet()){
                Node neighbor = entry.getKey();//return node

                if (!neighbor.isVisited()) {
                    int distance = current.weightTo(neighbor);

                    if (distance < neighbor.getWeight()) {
                        neighbor.setWeight(distance);
                        neighbor.setPrev(current);
                        pq.offer(neighbor);
                    }
                }
            }
        }

        ArrayList<Node> path = new ArrayList<>();
        for (Node node = end; node != null; node = node.getPrev()) {
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }
}
