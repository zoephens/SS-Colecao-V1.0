public class Customer {
    private String fName;
    private String lName;
    private int id;
    private int contact; //Format: ###-###-####
    private String address;
    private String email;

    //Constructor
    public Customer(String fName, String lName, int id, int contact, String address, String email){
        this.fName = fName;
		this.lName = lName;
		this.id = id;
        this.contact = contact;
        this.address = address;
        this.email = email;
    }

    public String getFName(){
		return fName;
	}

	public String getLName(){
		return lName;
	}

    public String getFullName(){
        return "" + getFName() + " " + getLName();
    }

	public int getID(){
		return id;
	}

    public int getContact(){
        return contact;
    }

    public String getAddress(){
        return address;
    }

    public String getEmail(){
        return email;
    }

    public String toString(){
        return ""+getID() + ", " + getFName() + ", " + getLName() + ", " + getContact() + ", " + getEmail() + ", " + getAddress();
    }
}
