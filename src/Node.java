import java.util.Vector;

public class Node{
    private static Vector<Node> nodes;
    private Vector<Link> links;
    private boolean check;
    private String name;

    //all node set to 0 (0 represent an ipotecala infinity -> without the point of start this is set to 0)
    private int weightNode;

    Node(String name){
        this.name = name;
        check = false;
        nodes.add(this);//add to vector nodes a new node
        links = new Vector<>();//initialize vector links for than create in the future a Link between 2 nodes
    }

    public Vector<Link> getLinks() {
        return links;
    }

    public void setLinks(Vector<Link> links) {
        this.links = links;
    }

    private void setWeightNodes(){
        for (Node n: nodes) {
            n.weightNode = 0;
        }
    }

    public void roadTo(Node destination){
        setWeightNodes();

        Vector<Link> linksCopy = getAllLinks();

        //for first node
        for (int i = 0; i < links.size(); i++) {
            //if the first Link is bigger than second I exchange than. order
            if(links.get(i).getWeight() > links.get(i + 1).getWeight()){
                links.remove(i);
                links.add(links.get(i));
                i--;
            }
        }
    }

    private void searchPath(){

    }

    public Vector<Link> getAllLinks(){
        Vector<Link> tmpLink = new Vector<>();
        for (Link l: links) {
            tmpLink.add(l);
        }
        return tmpLink;
    }

}
