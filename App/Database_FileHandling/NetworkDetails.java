package App.Database_FileHandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
// Class responsible for storing Network (Address paths) (FileHandling)

public class NetworkDetails {
    public void serialization(HashMap<String, HashMap<String,Integer>> network){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("NetworkData.dat"))) {
            out.writeObject(network);
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @SuppressWarnings ("unchecked")
    public HashMap<String, HashMap<String,Integer>> deserialization(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("NetworkData.dat"))) {
            return (HashMap<String, HashMap<String,Integer>>) in.readObject();
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new HashMap<>();
    }
}
