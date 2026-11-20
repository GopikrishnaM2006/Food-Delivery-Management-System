package App.Food;

import java.util.ArrayList;
// Represents a hotel named "TheIndianRestaurant"
public class TheIndianRestaurant {
    public static ArrayList<FoodItem> TheIndianRestaurantMenu = new ArrayList<>();
    public void addFoodItem(FoodItem foodItem){
        TheIndianRestaurantMenu.add(foodItem);
    }
    public void createPredefinedMenu(){
        TheIndianRestaurantMenu.add(new FoodItem(101, "Veg Burger", 99));
        TheIndianRestaurantMenu.add(new FoodItem(102, "Paneer Burger", 149));
        TheIndianRestaurantMenu.add(new FoodItem(103, "Margherita Pizza", 179));
        TheIndianRestaurantMenu.add(new FoodItem(104, "Farmhouse Pizza",249));
        TheIndianRestaurantMenu.add(new FoodItem(105, "Veg Sandwich", 89));
        TheIndianRestaurantMenu.add(new FoodItem(106, "Paneer Tikka Sandwich", 139));
        TheIndianRestaurantMenu.add(new FoodItem(107, "Masala Dosa", 99));
        TheIndianRestaurantMenu.add(new FoodItem(108, "Paneer Biryani", 199));
        TheIndianRestaurantMenu.add(new FoodItem(109, "French Fries", 79));
        TheIndianRestaurantMenu.add(new FoodItem(110, "Cold Coffee", 109));
    }
}
