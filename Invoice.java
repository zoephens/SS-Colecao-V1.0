import java.util.ArrayList;

public class Invoice {
    private  int orderNum;
    private int cost,quantity,unitPrice,customerIdNum;
    private String fName, lName, address,item,courier,date;
    

    public Invoice(int orderNum,String date,int customerIdNum,String fName, String lName,String address,int quantity, String item,int unitPrice, int cost,String courier)
    {
        this.orderNum=orderNum;
        this.date=date;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.quantity=quantity;
        this.item= item;
        this.unitPrice=unitPrice;
        this.cost=cost;
        this.courier= courier;

        
    }

    public String getfName()
    {
        return fName;
    }

    public String getlName()
    {
        return lName;
    }

    public String getaddress()
    {
        return address;
    }

    public String getitem()
    {
        return item;
    }

    public String getcourier(){
        return courier;
    }

    public int getorderNum(){
        return orderNum;
    }

    public String getdate(){
        return date;
    }

    public int getcost(){
        return cost;
    }

    public int getquantity(){
        return quantity;
    }

    public int getunitPrice(){
        return unitPrice;
    }

    public int getcustomerIdNum(){
        return customerIdNum;
    }



    public String toString()
    {
        return String.valueOf(getorderNum())+"\t"+getdate()+"\t"+String.valueOf(getcustomerIdNum())+"\t"+getfName()+"\t"+getlName()+"\t"+getaddress()+"\t"+String.valueOf(getquantity())+"\t"+getitem()+"\t"+String.valueOf(getunitPrice())+"\t"+String.valueOf(getcost())+"\t"+getcourier();
    }
}
