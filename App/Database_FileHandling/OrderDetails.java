package App.Database_FileHandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
// Class responsible for storing OrderRecords (FileHandling)

import App.OrderDetails.OrderRecords;

public class OrderDetails {
    public void serialize(LinkedList<OrderRecords> orderRecords){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("OrderDetails.dat"))) {
            out.writeObject(orderRecords);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public LinkedList<OrderRecords> deserialize(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("OrderDetails.dat"))) {
            return (LinkedList<OrderRecords>) in.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new LinkedList<>();
    }
}
