public class Traversals{
    public static void inOrder(Node head){
        if(head.left != null) inOrder(head.left);
        System.out.println(head.value);
        if(head.right!=null) inOrder(head.right);
    }
    public static void inOrder(ApplianceBST t){
        inOrder(t.head);
    }

    public static void preOrder(Node head){
        System.out.println(head.value);
        if(head.left != null) preOrder(head.left);
        if(head.right!=null) preOrder(head.right);
    }
    public static void preOrder(ApplianceBST t){
        preOrder(t.head);
    }
}