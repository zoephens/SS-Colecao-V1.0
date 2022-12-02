import java.io.File;

public class Coupon{
    //Attributes
    private String name;
    private String description;
    private String type;
    private Double value;

    //Constructors
    public Coupon(String name, Double value){
        this.name = name;
        this.value = value;
        this.type = "Discount";
        this.description = "Coupon to reduce overall order cost.";
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getType(){
        return type;
    }

    public Double getValue(){
        return value;
    }

    //This function takes a list of orders and a specific order
    //It will take the customer ID from the order and scan the
    //order list to count the amount of times an ID shows up more than 1 time
    public void check_eligibility(File olist, Order o){
        //take order and retrieve the ID field
        
    }

}