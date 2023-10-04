import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Customer {
    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    private String userID;
    private String password;

    private Order cart;



    public Customer(String userID, String password) {
        this.userID = userID;
        this.password = password;
        cart = new Order();
    }

    public Order getCart(){
        return cart;
    }

    public void addProductToCart(Product product){
        cart.addProduct(product);
    }

    public void removeProductFromCart(Product product){
        cart.removeProduct(product);
    }

    public void makePurchase(){
        cart.makePurchase();
//        TODO orderHandler.addOrderToList
    }
    public String toFileString() {
        return this.userID + "," + this.password + ",";
    }
}
