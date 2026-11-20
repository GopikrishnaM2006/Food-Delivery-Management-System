package App.Database_FileHandling;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.io.IOException;
import java.io.ObjectInputStream;
// Class responsible for storing CustomerList (FileHandling)
import App.CustomerLoginDetails.Customer;
public class CustomerDetails{
    public void serialization(LinkedList<Customer> customerList){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CustomerData.dat"))) {
            out.writeObject(customerList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public LinkedList<Customer> deserialization(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("CustomerData.dat"))) {
            return (LinkedList<Customer>) in.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new LinkedList<>();
    }
    public static void main(String[] args) {
        
    }
}