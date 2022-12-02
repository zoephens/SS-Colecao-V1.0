import java.util.*;
import java.io.File;
import java.io.IOException;

public class ManageCustomer {

    public ManageCustomer(){

    }

    //Function to search for a customer
    
    //Function to create an array list of customers from a file
    private ArrayList<Customer> load_customers(String cfile){
        Scanner sscan = null;
        ArrayList<Customer> studList = new ArrayList<Customer>();
        
        try{
            sscan  = new Scanner(new File(cfile));
            while(sscan.hasNext())
            {
                String [] nextLine = sscan.nextLine().split(" ");
                
                
            }
            sscan.close();
        }
        catch(IOException e)
        {}
        return studList; 
    }
}
