package App.CustomerLoginDetails;

import java.io.Serializable;
import java.util.LinkedList;

import App.Database_FileHandling.CustomerDetails;
import App.LoginPage.Login;

// Custom object representing Customer 
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int customerCounter = 1;
    private int id;
    private String name;
    private String username;
    private String password;
    private String address = null;
    // TODO: It should be private too

    // Stores Customer Data
    public static LinkedList<Customer> customerList = new LinkedList<>();

    public Customer(CustomerDTO customer){
        this.id = customerCounter++;
        this.name = customer.name;
        this.username = customer.username;
        this.password = customer.password;
    }

    public static void add(Customer customer){
        addToCustomerList(customer);
        addToCredentials(customer);
        new CustomerDetails().serialization(customerList);
    }
    public static void refreshCredentials(){
        for (Customer customer : customerList) {
            addToCredentials(customer);
        }
    }
    static void addToCredentials(Customer customer){
        Login.credentials.put(customer.username, customer.password);
    }
    static void addToCustomerList(Customer customer){
        customerList.add(customer);
    }
    public static Customer getCustomerByUsername(String username){
        for(int i=0; i< customerList.size(); i++){
            if(customerList.get(i).username.equals(username)){
                return customerList.get(i);
            }   
        }
        return null;
    }
    public String getCustomerName(){
        return this.name;
    }
    public int getCustomerId(){
        return this.id;
    }
    public String getCustomerAddress(){
        // if(this.address == null){
        //     return "NA";
        // }
        // else{
            return this.address;
        // }
    }
    public void addAddress(String address){
        this.address = address;
    }
    // Add to linkedlist and file (handling)
}
