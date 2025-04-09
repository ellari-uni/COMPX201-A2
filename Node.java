public class Node {
    Appliance value;
    Node left;
    Node right;

    public Node(Appliance value){
        this.value = value;
        left = null;
        right = null;
    }

    public boolean isLeaf(){
        return left == null && right == null;
    }
    /**
     * Inform which of the children are null
     * @return 1 -> left node null, 2 -> right node null, 0 -> None null, -1 -> leaf
     */
    public int identifyWhichNull(){
        if(!isLeaf()){
            if(!(left == null) && !(right == null)) return 0; //If both occupied, then none are null
            else if(left== null) return 1; 
            else if (right == null) return 2; 
        }
        else {
            return -1;
        }
        return -9999;
    }
}
