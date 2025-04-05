import java.text.NumberFormat;
import java.util.Locale;

public class Appliance {
    private String category;
    private float price;
    private String name;

    public Appliance(String name, String category, float price){
        this.name = name;
        this.category = category;
        this.price = price;
    }


    public String getCategory(){
        return category;
    }
    public String getName(){
        return name;
    }
    public float getPrice(){
        return price;
    }
    public String toString(){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String outString = "";
        outString+=String.format("%-" + 15 + "s", name) + " | ";
        outString+=String.format("%-" + 15 + "s", category)+ " | ";
        outString+=currencyFormat.format(price);
        return outString;
    }
    public int compareTo(Appliance ap){
        int comparison;
        if((comparison = category.compareTo(ap.category))!=0){
            return comparison;
        } else if (price != ap.getPrice()){
            comparison = (price < ap.getPrice()) ? -1 : 1;
            return comparison;
        } else {
            return name.compareTo(ap.getName());
        }
    }
}
