import java.io.BufferedReader;
import java.io.FileReader;

public class Main{
    public static void main(String[] args) {
        ApplianceBST aBst = new ApplianceBST();

        //try (BufferedReader br = new BufferedReader(new FileReader("appliances.csv"))) {
        try (BufferedReader br = new BufferedReader(new FileReader("appliances.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Appliance Name")) continue;
                String[] lineElements = line.split(",");
                aBst.insert(new Appliance(lineElements[0], lineElements[1], Float.parseFloat(lineElements[2])));
                System.out.println(line);
            }
            /*
             * Created Traversal method to view the tree in order, as the StrBSTPrinter was unreadable
             */
            Traversals.inOrder(aBst);
            System.out.println();
        }catch (Exception e){
            System.err.println(e.getClass());
            System.err.println(e.getMessage());
        }
    }
}