public class Traversals{
    public static void inOrder(Node head){
        if(head.left != null) inOrder(head.left);
        System.out.print(head.value.getName() + ", ");
        if(head.right!=null) inOrder(head.right);
    }
    public static void inOrder(ApplianceBST t){
        inOrder(t.head);
    }
}