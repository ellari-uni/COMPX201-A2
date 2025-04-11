public class PrintMethods {
    
    public static void inOrder(Node head){
        if (head.left != null) inOrder(head.left);
        System.out.println(head.value);
        if (head.right != null) inOrder(head.right);
    }

    public static void inOrder(ApplianceBST t, String category) {
        inOrderWithCategory(t.head, category);
    }

    private static void inOrderWithCategory(Node head, String category){
        if (head == null) return;
        inOrderWithCategory(head.left, category);
        if (head.value.getCategory().equals(category)) {
            System.out.println(head.value);
        }
        inOrderWithCategory(head.right, category);
    }

    public static void inOrder(ApplianceBST t, float minPrice, float maxPrice) {
        inOrderWithPriceRange(t.head, minPrice, maxPrice);
    }

    private static void inOrderWithPriceRange(Node head, float minPrice, float maxPrice){
        if (head == null) return;
        inOrderWithPriceRange(head.left, minPrice, maxPrice);
        if (head.value.getPrice() >= minPrice && head.value.getPrice() <= maxPrice) {
            System.out.println(head.value);
        }
        inOrderWithPriceRange(head.right, minPrice, maxPrice);
    }

    public static void inOrder(ApplianceBST t, String category, float minPrice, float maxPrice) {
        inOrderWithCategoryAndPriceRange(t.head, category, minPrice, maxPrice);
    }

    private static void inOrderWithCategoryAndPriceRange(Node head, String category, float minPrice, float maxPrice){
        if (head == null) return;
        inOrderWithCategoryAndPriceRange(head.left, category, minPrice, maxPrice);
        if (head.value.getCategory().equals(category) && head.value.getPrice() >= minPrice && head.value.getPrice() <= maxPrice) {
            System.out.println(head.value);
        }
        inOrderWithCategoryAndPriceRange(head.right, category, minPrice, maxPrice);
    }

    public static void printCategory(ApplianceBST t, String category){
        System.out.println("Appliances in Category: " + category);
        inOrderWithCategory(t.head, category);
    }

    public static void printCategoryWithPriceRange(ApplianceBST t, String category, float min, float max){
        System.out.println("Appliances in Category: " + category + " with price range: " + min + " - " + max);
        inOrderWithCategoryAndPriceRange(t.head, category, min, max);
    }

    public static void printCategoryAbovePrice(ApplianceBST t, String category, float min){
        System.out.println("Appliances in Category: " + category + " with price above " + min);
        inOrderWithPriceRangeAbove(t.head, category, min);
    }

    private static void inOrderWithPriceRangeAbove(Node head, String category, float minPrice){
        if (head == null) return;
        inOrderWithPriceRangeAbove(head.left, category, minPrice);
        if (head.value.getCategory().equals(category) && head.value.getPrice() > minPrice) {
            System.out.println(head.value);
        }
        inOrderWithPriceRangeAbove(head.right, category, minPrice);
    }

    public static void printCategoryBelowPrice(ApplianceBST t, String category, float max){
        System.out.println("Appliances in Category: " + category + " with price below " + max);
        inOrderWithPriceRangeBelow(t.head, category, max);
    }

    private static void inOrderWithPriceRangeBelow(Node head, String category, float maxPrice){
        if (head == null) return;
        inOrderWithPriceRangeBelow(head.left, category, maxPrice);
        if (head.value.getCategory().equals(category) && head.value.getPrice() < maxPrice) {
            System.out.println(head.value);
        }
        inOrderWithPriceRangeBelow(head.right, category, maxPrice);
    }
}