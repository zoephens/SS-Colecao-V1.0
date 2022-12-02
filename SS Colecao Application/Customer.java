public class Customer {
    private String fName;
    private String lName;
    private String id;
    private String contact; //Format: ###-###-####
    private String address;
    private String email;

    //Constructor
    //(fname, lname, id, contact, add, email)
    public Customer(String fName, String lName, String id, String contact, String address, String email){
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

	public String getID(){
		return id;
	}

    public String getContact(){
        return contact;
    }

    public String getAddress(){
        return address;
    }

    public String getEmail(){
        return email;
    }

    public String toString(){
        return getID() + ", " + getFName() + ", " + getLName() + ", " + getContact() + ", " + getEmail() + ", " + getAddress();
    }
}
