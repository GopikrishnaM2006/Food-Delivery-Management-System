package App.OrderDetails;

import java.io.Serializable;
import java.util.ArrayList;
// Represents One Complete Order
public class OrderRecords implements Serializable{
    private final static long serialVersionUID = 2L;
    int customerId;
    int orderId;
    public ArrayList<OrderedFoodItems> orderList;

    public OrderRecords(int customerId, int orderId, ArrayList<OrderedFoodItems> orderList) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderList = orderList;
    }
}
