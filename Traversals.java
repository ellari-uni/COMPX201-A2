public class Traversals{
    public static void inOrder(Node head, String cat){
        //System.out.println(cat);
        if(cat == null){
            if(head.left != null) inOrder(head.left, cat);
            System.out.println(head.value);
            if(head.right!=null) inOrder(head.right, cat);
        }
        else{
            //System.out.println(head.value);
            if(ApplianceBST.compare(head.value.getCategory(), cat) == 0){
                if(head.left != null) inOrder(head.left, cat);
                System.out.println(head.value);
                if(head.right!=null) inOrder(head.right, cat);
            }
            else return;
        }
    }
    public static void inOrder(ApplianceBST t, String cat){
        
        inOrder(t.head, cat);
    }

    private static void preOrder(Node head){
        System.out.println("#####");
        System.out.println(head.value);
        System.out.println(head.left==null ? "null" : head.left.value);
        System.out.println(head.right==null ? "null" : head.right.value);
        System.out.println("#####");
        if(head.left != null) preOrder(head.left);
        if(head.right!=null) preOrder(head.right);
    }
    public static void preOrder(ApplianceBST t){
        preOrder(t.head);
    }    
}