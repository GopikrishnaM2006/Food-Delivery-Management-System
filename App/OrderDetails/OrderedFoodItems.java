package App.OrderDetails;

import java.io.Serializable;

import App.Food.FoodItem;
// Represents one ordered food item
public class OrderedFoodItems implements Serializable{
    private final static long serialVersionUID = 3L;
    int id;
    String name;
    double price;
    int quantiity;

    OrderedFoodItems(int id, String name, double price, int quantiity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantiity = quantiity;
    }

    OrderedFoodItems(FoodItem foodItem, int quantiity) {
        this.id = foodItem.id;
        this.name = foodItem.name;
        this.price = foodItem.price;
        this.quantiity = quantiity;
    }
}
