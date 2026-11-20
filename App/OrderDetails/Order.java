package App.OrderDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import App.Database_FileHandling.OrderDetails;
// Contains Order methods and count
public class Order{
    public static int orderCounter = 0;
    public Order(){
        orderCounter++;
    }
    // Create Order records
    public static LinkedList<OrderRecords> orderRecords = new LinkedList<>();
    public void CreateOrderRecord(int customerId, OrderList orderList){
        orderRecords.add(new OrderRecords(customerId, orderCounter, orderList.CustomerOrderList));
    }
    public void storeOrderRecord(){
        new OrderDetails().serialize(orderRecords);
    }
    // TODO: No Order ID is stored
    public void displayOrder(OrderList orderList){
        System.out.println("\n----------------- Order Details --------------------- ");
        System.out.println("Order No.: "+orderCounter);
        double total = 0;
        System.out.printf("%-5s %-25s %-10s %-4s %-10s%n","FID","Name","Price", "Qtn", "Price");
        for (int i = 0; i < orderList.CustomerOrderList.toArray().length; i++) {
            OrderedFoodItems ofi = orderList.CustomerOrderList.get(i);
            System.out.printf("%-5d %-25s %-10.2f %-4d %-10.2f%n",ofi.id, ofi.name, ofi.price, ofi.quantiity, (ofi.price*ofi.quantiity));
            total += (ofi.price * ofi.quantiity);
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-47s %-10.2f","Total",total);
    }
    //TODO: OrderRecords -> ordercounter
    public void displayOrder(ArrayList<OrderedFoodItems> customerOrderList){
        System.out.println("\n----------------- Order Details --------------------- ");
        System.out.println("Order No.: "+orderCounter);
        double total = 0;
        System.out.printf("%-5s %-25s %-10s %-4s %-10s%n","FID","Name","Price", "Qtn", "Price");
        for (int i = 0; i < customerOrderList.toArray().length; i++) {
            OrderedFoodItems ofi = customerOrderList.get(i);
            System.out.printf("%-5d %-25s %-10.2f %-4d %-10.2f%n",ofi.id, ofi.name, ofi.price, ofi.quantiity, (ofi.price*ofi.quantiity));
            total += (ofi.price * ofi.quantiity);
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-47s %-10.2f","Total",total);
    }
}
