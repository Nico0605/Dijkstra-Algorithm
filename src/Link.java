public class Link {
    private int weight;
    private Node node1, node2;

    Link(Node node1, Node node2, int weight){
        if(node1 != null && node2 != null){
            this.node1 = node1;
            this.node2 = node2;

            this.weight = weight;

            node1.getLinks().add(this);
            node2.getLinks().add(this);
        }
    }

    public Node getFollowingNode(Node node){
        if(node1 == node)
            return node2;

        if(node2 == node)
            return node1;

        return null;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getNode1() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }
}
