package App.OrderDetails;

import java.util.ArrayList;

import App.Validation;
import App.CustomExceptions.CustomExceptions;
import App.Food.FoodItem;
import App.Food.TheIndianRestaurant;

// Stores Ordered food item after deserialising order (user's order)
public class OrderList {
    // void createIntoOrder(String s) {

    // }
    // Stores the ordered food items in a list
    public ArrayList<OrderedFoodItems> CustomerOrderList = new ArrayList<>();

    public void deserializeOrder(String order)
            throws CustomExceptions.InvalidOrderFormatException, CustomExceptions.InvalidFoodItemException {
        CustomExceptions customerExceptions = new CustomExceptions();
        Validation validation = new Validation();
        if (!validation.validateOrderFormat(order)) {
            // excepton
            throw customerExceptions.new InvalidOrderFormatException("Invalid Order Format");
        }

        for (int i = 0; i < order.length(); i++) {
            if (order.charAt(i) == '{' || order.charAt(i) == '}' || order.charAt(i) == ',') {
                continue;
            }
            // {{,},{,}}
            FoodItem foodItem = null;
            int foodId = Integer.parseInt(order.substring(i, order.indexOf(',', i)));
            for (int j = 0; j < TheIndianRestaurant.TheIndianRestaurantMenu.size(); j++) {
                if (TheIndianRestaurant.TheIndianRestaurantMenu.get(j).id == foodId) {
                    foodItem = TheIndianRestaurant.TheIndianRestaurantMenu.get(j);
                    break;
                }
            }
            // Add validation
            if (foodItem == null) {
                throw customerExceptions.new InvalidFoodItemException("Food ID: " + foodId + " doesn't exists!");
            }
            CustomerOrderList.add(new OrderedFoodItems(foodItem,
                    Integer.parseInt(order.substring(order.indexOf(',', i) + 1, order.indexOf('}', i)))));
            i = order.indexOf('}', i);

        }
    }
}
