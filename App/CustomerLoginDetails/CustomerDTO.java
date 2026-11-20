package App.CustomerLoginDetails;
// CustomerDTO object to prevent unwanted access to Customer Object
public class CustomerDTO {
    String name;
    String username;
    String password;

    public CustomerDTO(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
