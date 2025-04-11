public class ApplianceBST {
    Node head;

    public ApplianceBST(){
        head = null;
    }
    public ApplianceBST(Node n){
        head = n;
    }
    
    
    public void insert(Appliance a) {
        head = insertRecursive(head, new Node(a));
    }
    

    private Node insertRecursive(Node current, Node newNode) {
        if (current == null) return newNode;
    
        int cmp = compare(newNode.value, current.value);
    
        if (cmp < 0) {
            current.left = insertRecursive(current.left, newNode);
        } else if (cmp > 0) {
            current.right = insertRecursive(current.right, newNode);
        } else {
            System.err.println("Duplicate not inserted: \n" + newNode.value + "\n");
        }
    
        return current;
    }
    

    public void remove(Appliance a) {
        head = removeR(head, a);
    }

    
    private Node removeR(Node current, Appliance a) {
        if (current == null) return null;
    
        int cmp = compare(a, current.value);
    
        if (cmp < 0) {
            current.left = removeR(current.left, a);
        } else if (cmp > 0) {
            current.right = removeR(current.right, a);
        } else {
            // Node found
            if (current.left == null && current.right == null) {
                return null;
            } else if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } else {
                Node minNode = getMinimumNode(current.right);
                current.value = minNode.value;
                current.right = removeR(current.right, minNode.value);
            }
        }
    
        return current;
    }
    

    public boolean search(Appliance a) {
        return searchR(a, head);
    }
    

    private boolean searchR(Appliance a, Node current) {
        if (current == null) return false;
    
        int cmp = compare(a, current.value);
        if (cmp == 0) return true;
        else if (cmp < 0) return searchR(a, current.left);
        else return searchR(a, current.right);
    }
    

    public Node[] find(Appliance a){
        return findR(a, head, null);
    }
    
    
    public Node[] findNextGreatest(Node cRoot, Node prev){
        if (cRoot.left == null) return new Node[]{cRoot, prev};
        else return findNextGreatest(cRoot.left, cRoot);
    }
    
    
    private Node[] findR(Appliance a, Node cRoot, Node prev){
        if (cRoot == null) return new Node[]{cRoot, prev};
        else if (cRoot.value.compareTo(a) == 0) return new Node[]{cRoot, prev};
        else if (a.compareTo(cRoot.value) == -1) return findR(a, cRoot.left, cRoot);
        else if (a.compareTo(cRoot.value) == 1) return findR(a, cRoot.right, cRoot);
        return new Node[] {cRoot, prev};
    }
    
    
    public void print(){
        PrintMethods.inOrder(head);
    }
    
    
    private int compare(Appliance ap1, Appliance ap2){
        int comparison;
        if((comparison = compare(ap1.getCategory(), ap2.getCategory())) != 0){
            return comparison;
        }
        else if((ap1.getPrice() != ap2.getPrice())){
            return (ap1.getPrice() < ap2.getPrice()) ? -1 : 1;
        }
        else if((comparison = compare(ap1.getName(), ap2.getName())) != 0){
            return comparison;
        }
        return 0;
    }
    
    
    private int compare(String str1, String str2){
        //Iterate through strings
        //Iteration limit is the maximum index of the smallest string (if equal, string 2)
        for(int i = 0; i < ((str1.length() > str2.length()) ? str2.length()-1 : str2.length()-1); i++){
            //Cast both characters into their ASCII codes for easy comparison
            int char1 = (int)str1.charAt(i);
            int char2 = (int)str2.charAt(i);
            //"if at the end of the loop AND if the string lengths are unequal"
            //End of the loop is variable, depending on whether str1 or str2 is longer, so a ternary makes sense here
            if(i == ((str1.length() > str2.length()) ? str2.length()-1 : str2.length()-1) && str1.length() != str2.length()){
                //This means the longer string comes after the shorter string, so return appropriate value
                return (str1.length() > str2.length()) ? 1 : -1; 
            }
            //If the characters are the same, then continue to the next character
            if(char1 == char2) continue;
            
            //If character in string 1 comes before character in string 2, return -1, else return 1
            if(char1 < char2) return -1;
            else return 1;
        }
        //If nothing has matched, it means the strings are perfectly equal
        return 0;
    }
    
    
    public Node getMinimum() {
        return getMinimumNode(head);
    }
    
    
    private Node getMinimumNode(Node node) {
        if (node == null || node.left == null) return node;
        return getMinimumNode(node.left);
    }
    
    
    public Node getMaximum() {
        return getMaximumNode(head);
    }
    
    
    private Node getMaximumNode(Node node) {
        if (node == null || node.right == null) return node;
        return getMaximumNode(node.right);
    }
    
    
    public int getHeight() {
        return getHeightR(head);
    }
    
    
    private int getHeightR(Node node) {
        if (node == null) return -1;
    
        int leftHeight = getHeightR(node.left);
        int rightHeight = getHeightR(node.right);
    
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
}
