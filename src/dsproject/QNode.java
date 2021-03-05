package dsproject;

/**
 *
 * @author Khandine, Earl, Jared
 * 
 *  Question Node for binary tree.
 *  Stores values for the height that
 *  the node is at as well as a key
 *  value used to identify the node. 
 *
 *  Each node should contain a question
 *  and linked list of answers.
 */
public class QNode {
    
    public int num; // number of question
    public int height; // used for checking balance of bt
    
    // left and right nodes
    public QNode left;
    public QNode right;
    
    // question, with answers in it
    public NodeData nd;
 
    // construct a new node
    public QNode(int n, NodeData nd) {
        this.nd = nd;
        num = n;
        height = 1;
    }
    
    // this tostring, calls the nodedata's tostring
    @Override
    public String toString() {
        return nd.toString();
    }
}
