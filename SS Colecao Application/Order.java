import java.util.List;

public class Order {
    //attributes
    private Customer customer;
    private int item_count;
    private CourierMode deliveryMode;
    private int ticket_num;
    private List<List<String>> order_details;
    private OrderStatus status;

    //Constructor
    public Order(Customer customer, int item_count, CourierMode delMode, int ticket_num, OrderStatus status, List<List<String>> details){
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

    public int getCustomerID(){
        //return customer.getID();
        return 6200;
    }

    public String getCustomerName(){
        //return customer.getID();
        return "Maya May";
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
        return ""+order_details+"";
    }

    public void getPrice(){
        for(List<String> ord: order_details){
            int i = 0;
            for(String s: ord){
                if(i==0){
                    StockType t = StockType.valueOf(s);
                    i++;
                }
                if(i==1){
                    ItemColor c = ItemColor.valueOf(s);
                    i++;
                }
                if(i==2){
                    Size si = Size.valueOf(s);
                }
                else{
                    break;
                }
            }
        }
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
        return "" + getTicketNum() + " - " + getCustomerID() + ", " + getOwner() + ", " + getItemCount() + ", " + sgetOrderDetails() + ", " + getStatus();
    }

}
