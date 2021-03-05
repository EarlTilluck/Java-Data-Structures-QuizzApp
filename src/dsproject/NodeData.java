package dsproject;

/**
 *
 * @author Khandine, Earl, Jared
 * 
 *  NodeData allows us to save either a true/false 
 *  question or a multiple choice question without 
 *  changing the code in the Qnode class.
 */
public class NodeData {
    TrueFalse tf;
    MultipleChoice mc;
    
    // constructors
    public NodeData(TrueFalse tf) { 
        this.tf = tf; 
    }
    public NodeData(MultipleChoice mc) { 
        this.mc = mc; 
    }
    
    // toString, this is used to get the results of the query
    @Override
    public String toString() {
        if (tf != null) {
            return tf.toString();
        } else if (mc != null) {
            return mc.toString();
        } 
        return null;
    }
    
}// end class
