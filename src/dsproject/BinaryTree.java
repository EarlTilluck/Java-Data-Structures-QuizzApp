package dsproject;

/**
 *
 * @author Khandine, Earl, Jared
 * 
 *  This is an implementation of a binary tree, 
 *  with balanced insert. Balanced insert ensures that 
 *  the tree is either complete or near complete when items
 *  are entered into the structure. This avoids the potential
 *  downfall of the binary tree structure which is degenerate
 *  trees.
 */
public class BinaryTree {
    QNode root = null; 
    public int count = 0;
    
    /**
        find method, (not findOrInsert)
        we do not do or insert, since that is handled
        by the balanced insert method.
    
        balanced insert doesn't implement 'OrFind' behavior
        otherwise our search will implement re-balancing code 
        unnecessarily
    */
    public QNode find(int num) {
        
        // if no root, then complete fail
        if(root != null) {
        
            QNode curr = root;

            while (curr != null) {
                if(num < curr.num) {
                    // try left
                    curr = curr.left;

                } else if(num > curr.num) {
                    // try right
                    curr = curr.right;

                } else {
                    // if found then return the node
                    return curr;
                }
            }
        }
        
        // it was not found, or root was null (empty tree)
        return null;
    }
    
    /**
        Right rotate a subtree
        Node r = root of subtree
    
    This:
            r   
           / 
          x
           \
            y
    
    Becomes:
           x
            \
             r
            /
           y  
    */
    public QNode rightRotate(QNode r) {
        
        QNode x = r.left;
        QNode y = x.right;
 
        // perform rotation
        x.right = r;
        r.left = y;
 
        // Update heights
        r.height = max(height(r.left), height(r.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
 
        // return new root
        return x;
    }
    
    
    /**
        Left rotate a subtree of r
    
    This:
            r
             \
              y
             /
            x
        
    becomes:
            y
             \
              r
             /         
            x
    */
    public QNode leftRotate(QNode r) {
        QNode y = r.right;
        QNode x = y.left;
 
        // Perform rotation
        y.left = r;
        r.right = x;
 
        //  Update heights
        r.height = max(height(r.left), height(r.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
 
        // Return new root
        return y;
    }
    
    
    /**
        Balanced insert, this calls the insert method below
        and sets the node returned from it as the new root
    
        This is needed because the insert method is recursive
        and we only want to set the root when the the entire
        operation is complete and not when an inner call returns
        a node
    */
    public QNode balancedInsert(int num, NodeData nd) {
        this.root = this.insert(root, num, nd);
        this.count++;
        return root;
    }
    
    
    /**
        insert .. make sure the bt is balanced, 
        because the root always changes when the tree is re-balanced, 
        this method is designed to always return the new root of the
        binary tree,
    
        to find a node, we will have to implement a find (not findorinsert)
        method to retrieve the data.
    */
    private QNode insert(QNode node, int num, NodeData nd) {
 
        // if root node is null, create a new node and return it
        // it is set as the new root in balancedInsert method
        if (node == null){
            return (new QNode(num, nd));
        }
 
        // recursive method to insert the node as usual
        // duplicate numbers for questions are not allowed
        if (num < node.num){
            node.left = insert(node.left, num, nd);
        }
        else if (num > node.num){
            node.right = insert(node.right, num, nd);
        }
        else{
            // node is returned and nothing happens when the num
            // the same
            return node; 
        }
 
        // after recursive insert, then we are moving up
        // through all the ancestors we previously visited...
        
        // update the height of this ancestor node 
        node.height = 1 + max(height(node.left), height(node.right));
 
        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        
        // get the balance factor of this ancestor
        int balance = getBalance(node);
 
        // check to see if the node has become unbalanced
        // there are 4 possible cases of unbalanced 
        
        /* Left Left Case
        
                 z                   y 
                / \                /   \
               y   4              x       z
              / \          ->    /  \    / \ 
             x   3              1    2  3    4
            / \
           1   2

        */
        if (balance > 1 && num < node.left.num)
            return rightRotate(node);
 
        /*
            Right Right Case
        
            z                  y
           /  \              /   \ 
          1   y             z      x
              /  \     ->  / \    / \
             2   x        1   2  3   4
                 / \
                3   4
        */
        if (balance < -1 && num > node.right.num)
            return leftRotate(node);
 
        /* 
            Left Right Case
        
             z               z               x
            / \            /   \            /  \ 
           y   4          x     4         y      z
          / \      ->    /  \        ->  / \    / \
         1   x         y     3          1   2  3   4
             / \      / \
           2    3    1   2
        
        
        */
        if (balance > 1 && num > node.left.num) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
 
        /*
            Right Left Case
        
             z               z                 x
            / \             / \              /   \ 
           1   y           1   x            z      y
              / \      ->     /  \     ->  / \    / \
             x   4           2   y        1   2   3  4
            / \                 /  \
           2   3               3    4
        
        */
        if (balance < -1 && num < node.right.num) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
 
        // return node 
        return node;
    }
    
    
 
    /*
        find the max between two numbers
    */ 
    public int max(int a, int b) {
        if(a > b) {
            return a;
        }
        return b;
        // ignore equal case because it may never happen.
        // if it does, b is fine
    }
    
        
    /*
        get height of node, we need this method to avoid
        null pointer exceptions... 
        e.g. r.left.height in rightRotate method might be null
        height of null node is always 0
    */
    public int height(QNode n) {
        if (n == null){
            return 0;
        }
 
        return n.height;
    }
    
    
    /*
        get balance of node
    */
    public int getBalance(QNode n) {
        if (n == null){
            return 0;
        }
 
        return height(n.left) - height(n.right);
    }
    
        
    /*
        the following preorderTraversal methods are for 
        testing purposes and not used in the application
    */
    public void preOrderTraversal() {
        preOrder(root);
    }
    private void preOrder(QNode node) {
        if (node != null) {
            
            // print out the number -- change this to something else
            // if we need it
            System.out.print(node.num + ", ");
            
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    
}// end class