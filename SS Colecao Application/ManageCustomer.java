import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.channels.NetworkChannel;

public class ManageCustomer {

    private ArrayList<Customer> clst;

    public ManageCustomer(){
        this.clst = new ArrayList<Customer>(load_customers("database/customers.txt"));
    }

    //Function to search for a customer
    public Customer findCustomer(String iD){
        Customer cust = null;
        int j = 0;
        while(clst.size()>j){
            cust = clst.get(j);
            if(cust.getID().equals(iD))
                return cust;
            j++;
            cust = null;
        }   
        return cust;
    }
    
    //Function to create an array list of customers from a file
    //(String fName, String lName, int id, int contact, String address, String email)
    //620148438, Tiffany, Parkinson, 8763409204, tiffanyparkinson18@gmail.com, 101 Wallpark Street
    public ArrayList<Customer> load_customers(String cfile){
        Scanner sscan = null;
        ArrayList<Customer> clist = new ArrayList<Customer>();
        
        try{
            sscan  = new Scanner(new File(cfile));
            while(sscan.hasNext())
            {
                String [] nextLine = sscan.nextLine().split(", ");

                String id = nextLine[0];
                String fname = nextLine[1];
                String lname = nextLine[2];
                String contact = nextLine[3];
                String email = nextLine[4];
                String add = nextLine[5];
                
                //(fname, lname, id, contact, add, email)
                Customer cust = new Customer(fname, lname, id, contact, add, email);
                System.out.println(id);
                clist.add(cust);
            }
            sscan.close();
        }
        catch(IOException e)
        {}
        return clist; 
    }
}
