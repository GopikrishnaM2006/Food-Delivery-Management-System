package App;

import java.util.Scanner;

import App.CustomExceptions.CustomExceptions;
import App.CustomExceptions.CustomExceptions.InvalidFoodItemException;
import App.CustomExceptions.CustomExceptions.InvalidOrderFormatException;
import App.CustomerLoginDetails.Customer;
import App.CustomerLoginDetails.CustomerDTO;
import App.Food.FoodItem;
import App.Food.TheIndianRestaurant;
import App.GraphNetwork.Dijkstra;
import App.GraphNetwork.Network;
import App.LoginPage.Login;
import App.OrderDetails.Order;
import App.OrderDetails.OrderList;
import App.OrderDetails.OrderRecords;
import App.Database_FileHandling.CustomerDetails;
import App.Database_FileHandling.NetworkDetails;
import App.Database_FileHandling.OrderDetails;

public class Menu {
    Login login = new Login();
    Scanner sc = new Scanner(System.in);
    CustomExceptions customExceptions = new CustomExceptions();
    CustomerDetails customerDetails = new CustomerDetails();

    Menu() {
        loginMenu();
        // show();
        // String Username = loginMenu();
        // foodMenu();
        // deliveryMenu("khushleen", 1);
        // TODO: ADD options to place order, view past orders, profile -> profile can be
        // changed
    }

    // Shows the login menu
    void loginMenu() {
        while (true) {

            System.out.println("\n**** Welcome ****");
            System.out.print("New (N) or Existing (E) Customer: ");
            char choice = sc.next().toLowerCase().charAt(0);
            Customer.customerList = customerDetails.deserialization();
            Customer.refreshCredentials();
            // TODO: Hide Password?
            try {
                // Existing Customer
                if (choice == 'e') {
                    System.out.println("\nNice to have you back!");
                    System.out.print("Enter Username: ");
                    String username = sc.next().toLowerCase().trim();
                    System.out.print("Enter Password: ");
                    String password = sc.next();
                    if (login.correctCredentials(username, password)) {
                        System.out.println("Entering the app...");
                        foodMenu(username);
                        // return username;
                        break;
                    } else {
                        throw customExceptions.new InvalidCredentialsException("Invalid Username or Password");
                    }
                } else if (choice == 'n') {
                    // New Customer
                    System.out.println("\nEnter your details: ");
                    System.out.print("Enter name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    String username, password;
                    Validation validation = new Validation();
                    while (true) {
                        try {
                            System.out.print("Enter Username: ");
                            username = sc.nextLine().toLowerCase().trim();
                            if (!validation.validateUsername(username)) {
                                throw customExceptions.new InvalidUsernameException("Username already taken");
                            }
                            else if(validation.checkNullValue(username)){
                                throw customExceptions.new NullUsernameException("Username cannot be null");
                            } 
                            else if(validation.checkSpaces(username)){
                                throw customExceptions.new UnwantedSpacesException("Username cannot contain spaces");
                            }
                            else {
                                break;
                            }
                        } 
                        catch (CustomExceptions.InvalidUsernameException iue) {
                            System.out.println(iue.getLocalizedMessage());
                        }
                        catch (CustomExceptions.NullUsernameException nue) {
                            System.out.println(nue.getLocalizedMessage());
                        }
                        catch (CustomExceptions.UnwantedSpacesException use) {
                            System.out.println(use.getLocalizedMessage());
                        }
                    }
                    while (true) {
                        try {
                            System.out.print("Enter Password: ");
                            password = sc.next();
                            if (validation.validatePasswordLength(password)) {
                                throw customExceptions.new InvalidLengthException("Password length must be atleast 8");
                            } else if (!validation.validatePassword(password)) {
                                throw customExceptions.new InvalidPasswordException(
                                        "Password must contain a number, an uppercase and a lowercase alphabet");
                            }
                            break;
                        } catch (CustomExceptions.InvalidLengthException ile) {
                            System.out.println(ile.getLocalizedMessage());
                        } catch (CustomExceptions.InvalidPasswordException ipe) {
                            System.out.println(ipe.getLocalizedMessage());
                        }
                    }
                    CustomerDTO customerdto = new CustomerDTO(name, username, password);
                    Customer customer = new Customer(customerdto);
                    Customer.add(customer);
                    System.out.println("Added succesfully");
                } else {
                    System.out.println("Enter a valid operation!");
                }
            } catch (CustomExceptions.InvalidCredentialsException ice) {
                System.out.println(ice.getLocalizedMessage());
            }
        }
    }
    // shows food menu
    void foodMenu(String username) {
        System.out.println("The Indian Restaurant ~~~~");
        System.out.println(" --------------- MENU --------------- ");
        System.out.printf("%-5s %-25s %-6s%n", "No.", "Food Item", "Price");
        TheIndianRestaurant theIndianRestaurant = new TheIndianRestaurant();
        theIndianRestaurant.createPredefinedMenu();
        theIndianRestaurant.addFoodItem(new FoodItem(111, "Hot Chocolate", 119));
        TheIndianRestaurant.TheIndianRestaurantMenu.forEach(foodItem -> {
            System.out.printf("%-5d %-25s %6s%n", foodItem.id, foodItem.name, "₹" + foodItem.price);
        });
        // Another method
        // for(int i=0; i<TheIndianRestaurant.TheIndianRestaurantMenu.size(); i++){
        // FoodItem foodItem = TheIndianRestaurant.TheIndianRestaurantMenu.get(i);
        // System.out.printf("%-5d %-25s
        // ₹%6.2f%n",foodItem.id,foodItem.name,foodItem.price);
        // }

        //

        // takes customer order
        while (true) {
            System.out.println("\nFollow the given format: {{ID,Qnt},{ID,Qnt}}");
            System.out.print("Enter your order: ");
            String CustomerOrder = sc.next();
            sc.nextLine();
            System.out.println();
            try {
                Order.orderRecords = new OrderDetails().deserialize();
                OrderList orderList = new OrderList();
                orderList.deserializeOrder(CustomerOrder);
                Order order = new Order();
                Customer customer = Customer.getCustomerByUsername(username);
                order.CreateOrderRecord(customer.getCustomerId(), orderList);
                order.storeOrderRecord();
                order.displayOrder(orderList);
                System.out.println();
                deliveryMenu(username, Order.orderCounter);
                // TODO: Store Order
                break;
            } catch (InvalidFoodItemException ifie) {
                System.out.println(ifie.getMessage());
            } catch (InvalidOrderFormatException iofe) {
                System.out.println(iofe.getMessage());
            }
        }

    }

    // Show deleivery details
    void deliveryMenu(String username, int orderId) {
        // TODO remove this
        Customer.customerList = customerDetails.deserialization();

        NetworkDetails networkDetails = new NetworkDetails();
        Network.network = networkDetails.deserialization();
        System.out.println("Deliverying Details: ");
        Customer customer = Customer.getCustomerByUsername(username);
        System.out.println("\nCustomer Name: " + customer.getCustomerName());
        // String n = null;
        if (customer.getCustomerAddress() == null || customer.getCustomerAddress() == "") {
            System.out.println("No stored address");
            System.out.println("Add address to proceed!");
            Network network = new Network();
            network.displayNode();
            // sc.nextLine();
            System.out.println("\nEnter Address to deliver: ");
            String address = sc.nextLine().trim().toLowerCase();
            // sc.next();
            if (address.equals("other")) {
                // sc.next();
                System.out.print("Place: ");
                String newPlace = sc.nextLine();
                // sc.next();
                System.out.print("Nearest place: ");
                String adjacentPlace = sc.nextLine();
                // sc.next();
                System.out.println("Time taken from Nearest place (in mins): ");
                int time = sc.nextInt();
                network.addRoute(newPlace, adjacentPlace, time);
                networkDetails.serialization(Network.network);
                customer.addAddress(newPlace);
            }
            else{
                customer.addAddress(address);
            }
            System.out.println("Added Successfully!");
            new CustomerDetails().serialization(Customer.customerList);

            // TODO: Line change like input through js
        }
        System.out.println("Deliverying to: " + customer.getCustomerAddress());
        // try{
        // TODO: Static Linkedlist and all try multiple times
        // TODO: Time for food making
            System.out.println("Estimated Time: "+ (40+new Dijkstra().shortestTime(Network.network,"hotel", customer.getCustomerAddress()))+"mins");
            System.out.println("Time Distribution: "+"Preparation: 40mins  Delivery: "+new Dijkstra().shortestTime(Network.network,"hotel", customer.getCustomerAddress())+"mins");
        // }catch(Exception e){
        //     System.out.println(e.getMessage());
        // }
        // TODO: Add option to change delivery address

    }

    // Testing Network
    void show(){
        Network.network = new NetworkDetails().deserialization();
        Network n = new Network();
        n.displayNode();
        System.out.println(new Dijkstra().shortestTime(Network.network, "hotel", "nerul"));
    // Order o =new Order();
    // Order.orderRecords = new OrderDetails().deserialize();
    // for(OrderRecords or :Order.orderRecords){
    // o.displayOrder(or.orderList);
    // // TODO: ID's should be in stored
    // }

    }
}
