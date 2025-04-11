public class ApplianceBST {
    Node head;

    public ApplianceBST(){
        head = null;
    }
    public ApplianceBST(Node n){
        head = n;
    }
    
    public void insert(Appliance n){
        head = insertIntoSubtree(n, head);
    }
    protected Node insertIntoSubtree(Appliance n, Node cRoot){
        if(cRoot == null) return new Node(n);
        else{
            switch(compare(n, cRoot.value)){
                case 1:
                    cRoot.left = insertIntoSubtree(n, cRoot.left);
                    break;
                case -1:
                    cRoot.right = insertIntoSubtree(n, cRoot.right);
                    break;
                case -2, -3:
                    


            }
        }
        
        
        /*
        else if (compare(n, cRoot.value) == -1) {
            cRoot.left = insertIntoSubtree(n, cRoot.left);
        }
        else if (compare(n, cRoot.value) == 1) {
            cRoot.right = insertIntoSubtree(n, cRoot.right);
        }
        return cRoot;
        */
    }

    public void remove(Appliance a){
        if(!search(a)) System.err.println("Tree does not contain element:\n"+a);
        else{
            Node[] searchRes = find(a);
            Node nodeRem = searchRes[0];
            Node prevNode = searchRes[1];
            int checkChildren = nodeRem.identifyWhichNull();

            if(nodeRem.isLeaf()){
                if (prevNode.value.compareTo(nodeRem.value) == -1) prevNode.right = null;
                else prevNode.left = null;
            }
            else if(checkChildren>0){
                switch(checkChildren){
                    case 1:
                        if (prevNode.value.compareTo(nodeRem.value) == -1) prevNode.right = nodeRem.right;
                        else prevNode.left = nodeRem.right;
                        break;
                    case 2:
                        if (prevNode.value.compareTo(nodeRem.value) == -1) prevNode.right = nodeRem.left;
                        else prevNode.left = nodeRem.left;
                        break;
                }
            }
            else if(checkChildren==0){
                Node[] greatestResults = findNextGreatest(nodeRem.right, prevNode);
                Node nGNode = greatestResults[0];
                Node nGParent = greatestResults[1];
                if (prevNode.value.compareTo(nodeRem.value) == -1) prevNode.right = nodeRem.right;
                else prevNode.left = nodeRem.right;


                if (nGParent.value.compareTo(nGNode.value) == -1) nGParent.right = nGNode.right;
                else nGParent.left = nGNode.left;
            }
            System.out.println("Successfully removed " + a);
            

            
        }

    }
    
    public boolean search(Appliance a){
        return searchSubtree(a, head);
    }
    private boolean searchSubtree(Appliance a, Node cRoot){
        if (cRoot == null) return false;
        else if (cRoot.value.compareTo(a) == 0) return true;
        else if (a.compareTo(cRoot.value) == -1) return searchSubtree(a, cRoot.left);
        else if (a.compareTo(cRoot.value) == 1) return searchSubtree(a, cRoot.right);
        return false;
    }

    public void printCategory(String c){
        Node start = findCat(c);
        if (start == null) System.out.println("\nNo objects of specified category \"" + c + "\" in the tree.");
        else{
            Traversals.inOrder(start, c);
        }


        // Node startNode = searchUpToCat(c, head);
        // System.out.println(startNode.value + " <- searchRoot");
        // // Traversals.preOrder(startNode);
        // System.out.println();
        // inOrderCat(startNode, c);
    }
    private Node findCat(String c){
        Node current = head;
        while(compare(c, current.value.getCategory()) != 0){
            System.out.println("Current value cat: " + current.value.getCategory());
            System.out.println("Searching value cat: " + c);
            System.out.println("Comparison 1 results: " + compare(c, current.value.getCategory()));
            System.out.println();
            if(compare(c, current.value.getCategory()) == -1){
                if (current.left == null) return null;
                else current = current.left;
            }
            else if(compare(c, current.value.getCategory()) == 1){
                if (current.right == null) return null;
                else current = current.right;
            }
        }

        System.out.println("reached");
        if (compare(current.value, head.value) == 0 && compare(c, current.value.getCategory()) != 0) return null;
        else return current;
    }



    private void inOrderCat(Node start, String c){
        inOrderCatR(start, c);
    }
    private void inOrderCatR(Node n, String c){
        if(compare(n.value.getCategory(), c) != 0) return;
        else{
            System.out.println("#### " + n.value + " ####");
            System.out.println("#### " + (n.left == null ? "null" : n.left.value) + " ####");
            System.out.println("#### " + (n.right == null ? "null" : n.right.value) + " ####");
            
        }
        if(n.left!=null) inOrderCatR(n.left, c);
        if(compare(n.value.getCategory(), c) == 0) System.out.println(n.value);
        if(n.right!=null) inOrderCatR(n.right, c);
        /*
        if ((n.left != null && compare(n.left.value.getCategory(), c) == 0)
         || 
        (n.right!=null && compare(n.right.value.getCategory(), c) == 0))

        {
            if(n.left != null) inOrderCatR(n.left,c);
            if (compare(n.value.getCategory(),c)==0)System.out.println(n.value);
            if(n.right!=null) inOrderCatR(n.right,c);
        }
        else{
            return;
        }
        */
    }
    private Node searchUpToCat(String cat, Node root){
        try{
            if(compare(root.value.getCategory(), cat) == 0) return root;
            else if ((compare(root.value.getCategory(), cat) == 1)) {
                //
                if (root.left != null) return searchUpToCat(cat, root.left);
                else return root;
            }
            else if ((compare(root.value.getCategory(), cat) == -1)){
                if (root.right != null) return searchUpToCat(cat, root.right);
                else return root;
            }
            else{
                System.out.println("test");
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage()+"\nNode cat: " + " Search cat: " + cat);
            return null;
        }
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
        Traversals.inOrder(head, null);
    }
    public static int compare(Appliance ap1, Appliance ap2){
        int comparison;
        if((comparison = compare(ap1.getCategory(), ap2.getCategory())) != 0){
            return comparison;
        }
        else if((ap1.getPrice() != ap2.getPrice())){
            return (ap1.getPrice() < ap2.getPrice()) ? -3 : 3;
        }
        else if((comparison = compare(ap1.getName(), ap2.getName())) != 0){
            return comparison*2;
        }
        return 0;
    }
    public static int compare(String str1, String str2){
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
    public Node getMinimum(){
        if(head.left != null) return getMinimumR(head.left);
        return head;
    }
    private Node getMinimumR(Node root){
        if(root.left != null) return getMinimumR(root.left);
        return root;
    }
    public Node getMaximum(){
        if (head.right != null) return getMaximumR(head.right);
        return head;
    }
    private Node getMaximumR(Node root){
        if (root.right != null) return getMaximumR(root.right);
        return root;
    }

    public int getHeight(){
        if(head == null) return 0;
        return getHeightR(head);
    }
    private int getHeightR(Node root){
        if(root == null) return -1;
        
        int leftHeight = getHeightR(root.left);
        int rightHeight = getHeightR(root.right);

        if (leftHeight > rightHeight) return leftHeight + 1;
        else return rightHeight + 1;
    }
}
