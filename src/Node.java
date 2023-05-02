import java.util.Vector;

public class Node{
    private static Vector<Node> nodes = new Vector<>();
    private Vector<Link> links;
    private String name;

    //all node set to 0 (0 represent an ipotecala infinity -> without the point of start this is set to 0)
    private Integer weightNode;

    Node(String name){
        this.name = name;
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
            n.weightNode = null;
        }
    }

    public String goTo(Node n){
        Vector<Link> linksCopy = getAllLinks();
        Vector<Node> nodiGiaPercorsi = new Vector<>();
        Vector<Node> path = new Vector<>();
        Node selectedNode;
        Node position = n;
        path.add(position);
        String pathString = null;

        setWeightNodes();
        this.weightNode = 0;

        //for first node
        for (int i = 0; i < links.size() - 1; i++) {
            //if the first Link is bigger than second I exchange than. order
            if(links.get(i).getWeight() > links.get(i + 1).getWeight()){
                links.remove(i);
                links.add(links.get(i));
                i--;
            }
        }

        searchPath(n);

        pathString = this.name + "  ";

        while(position!=this){
            selectedNode = lightestNode(position, nodiGiaPercorsi);
            nodiGiaPercorsi.add(position);
            path.add(position);
            position = selectedNode;
        }
        
        for(int i = path.size() - 1; i > 0; i--){
            pathString += path.get(i).name + "  ";

        }
        return pathString;
    }

    private void searchPath(Node n){//n = destination
        if(this != n){//this = current node
            Node nTmp;
            Vector<Link> links = getAllLinks();
            Link linkTmp;

            //sort the vector links -> links is vector for the current node link with other node
            // (ex. a link with b, a link with c ecc. vector links contains this link)
            for(int i = 0; i < links.size() - 1; i++){
                if(links.get(i).getWeight() > links.get(i + 1).getWeight()){
                    linkTmp = links.get(i); //copy the biggest node
                    links.remove(i); // remove biggest node
                    links.add(linkTmp); // add biggest in last position

                    // control current node and following node, current node is not links.get(i) but is links.get(i + 1)
                    // because the current node (links.get(i)) when i have removed from vector the following node has taken its place
                    i--;
                }
            }

            for(Link l: links){
                nTmp = l.getFollowingNode(this);// take the order link (ex. home -1-> a  and home -2-> b, it has taken a link "a"
                                                // because "a" is first available node)
                if(nTmp.weightNode == null || nTmp.weightNode > this.weightNode + l.getWeight()){

                    nTmp.weightNode = this.weightNode + l.getWeight();//update weight
                    nTmp.searchPath(n);//it's a ricorsive method

                }
            }
        }
    }

    public Vector<Link> getAllLinks(){
        Vector<Link> tmpLink = new Vector<>();
        for (Link l: links) {
            tmpLink.add(l);
        }
        return tmpLink;
    }

    public static Node lightestNode(Node position, Vector<Node> nodesSeen){

        Node n = null;
        Node tmpNode;

        for(int i = 0; i < position.links.size(); i++){
            tmpNode = position.links.get(i).getFollowingNode(position);

            if((n == null || tmpNode.weightNode < n.weightNode) && !nodesSeen.contains(tmpNode)){
                n = tmpNode;

            }
        }

        return n;
    }

}
