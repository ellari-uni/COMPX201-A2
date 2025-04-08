public class ApplianceBST {
    Node head;

    public ApplianceBST(){
        head = null;
    }

    public void insert(Appliance a){
        if (head == null) head = new Node(a);

        else if (compare(a, head.value) == 0) System.err.println("Cannot add duplicate items to the BST. Ignoring element:\n"+a+"\n\n");
        else head = insertIntoSubtree(a, head);
    }
    private Node insertIntoSubtree(Appliance a, Node cRoot){
        
        if (cRoot == null) return new Node(a);
        else if (compare(a, cRoot.value) == -1) cRoot.left = insertIntoSubtree(a, cRoot.left);
        else if (compare(a, cRoot.value) == 1) cRoot.right = insertIntoSubtree(a, cRoot.right);
        return cRoot;

        /*
        if (cRoot == null) return new Node(a);
        else if (a.compareTo(cRoot.value) == -1) cRoot.left = insertIntoSubtree(a, cRoot.left);
        else if (a.compareTo(cRoot.value) == 1) cRoot.right = insertIntoSubtree(a, cRoot.right);
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
    public void printTree(){
        System.out.println("Tree: ");
        StrBSTPrinter.printNode(head);
    }
    public void printInOrder(){
        printInOrderR(head);
    }
    public void printInOrderR(Node cRoot){
        if (cRoot == null) return;
        printInOrderR(cRoot.left);
        System.out.println(" " + cRoot.value + " ");
        printInOrderR(cRoot.right);
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
}
