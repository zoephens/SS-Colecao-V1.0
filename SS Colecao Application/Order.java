import java.util.List;

public class Order {
    //attributes
    private Customer customer;
    private int item_count;
    private CourierMode deliveryMode;
    private int ticket_num;
    private List<String[]> order_details;
    private OrderStatus status;

    //Constructor
    public Order(Customer customer, int item_count, CourierMode delMode, int ticket_num, OrderStatus status, List<String[]> details){
        this.customer = customer;
        this.item_count = item_count;
        this.status = status;
        this.deliveryMode = delMode;
        this.ticket_num = ticket_num; //Might generate a ticket num each time an instance of order is called
        this.order_details = details;
    }

    public String getOwner(){
        return customer.getFullName();
    }

    public String getCustomerID(){
        return customer.getID();
    }

    public String getCustomerName(){
        return customer.getFullName();
    }

    public int getTicketNum(){
        return ticket_num;
    }

    public int getItemCount(){
        return item_count;
    }

    public String getMode(){
        return deliveryMode.name();
    }

    public String getStatus(){
        return status.name();
    }

    public String sgetOrderDetails(){
        String[] leng = order_details.get(0);
        String[] i = leng[0].split(", ");
        String type = i[0];
        String color = i[1];
        String size = i[2];

        String str = type + ", " + color + ", " + size;
        return str;
    }

    public void getPrice(){
        
    }
    
   // public Size getSize(Order o){
      //  return o.getOrderDetails();
   // }

    public double getOrderPrice(){
        //order_details
        return 0.0;
    }

    //Output: 1 - 620148438, Jane Doe, 2, [['bikini_bottom', 'red', 'M']], Pending
    public String toString(){
        return "" + getTicketNum() + ", " + getCustomerID() + ", " + getOwner() + ", " + getItemCount() + ", " + sgetOrderDetails() + ", " + getStatus();
    }

}
