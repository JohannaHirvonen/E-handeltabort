import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerHandler {
    private static final String FILENAME = "Customer.txt";
    public ArrayList<Customer> customerList;

    public CustomerHandler() {
        Utility.createTextFile(FILENAME);
        customerList = new ArrayList<>();
        readFromFile();
    }

    public void readFromFile() {
            try {
                Scanner scan = new Scanner(new File(FILENAME));
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] customerInfo = line.split(",");

                    Customer tempCustomer = new Customer(
                            customerInfo[0],
                            customerInfo[1]);
                    customerList.add(tempCustomer);
                }
            } catch (FileNotFoundException e) {
                System.out.println(" FEL!!! " + e.getMessage());
            }
    }

    public void addCustomer(Customer customer) {
        //TODO check if customer alreday exists
        customerList.add(customer);
        Utility.addItemToTextFile(customer.toFileString(), FILENAME);
    }

    public boolean authenticate(String username, String password) {
        for (Customer customer : customerList) {
            if (customer.getUserID().equals(username) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }
    public void printCustomerList() {

        if (this.customerList.isEmpty()) {
            System.out.println("No customers registered!");
        } else {
            int maxUserID = 50;
            int maxPassword = 50;


            String headline = "Id" + addWhiteSpace("User ID", maxUserID) + "| " +
                    addWhiteSpace("Password", maxPassword) + "| ";
            System.out.println(headline);

            System.out.println("-".repeat(headline.length()));

            for (int i = 0; i < this.customerList.size(); i++) {
                System.out.println(i + ": " +
                        addWhiteSpace(this.customerList.get(i).getUserID(), maxUserID) + "| " +
                        addWhiteSpace(this.customerList.get(i).getPassword() + "", maxPassword)+ "| ");

                if (i%2 == 1){
                    System.out.println("-".repeat(headline.length()));
                }
            }
        }
    }
    private String addWhiteSpace(String text, int maxAmount){
        if(text.length() > maxAmount){
            return text.substring(0, maxAmount - 3) + "...";
        }
        return text + " ".repeat(maxAmount - text.length());
    }

    public Customer getCustomer(int id) {
        return customerList.get(id);
    }
    public Customer getCustomer(String customerID){
        for (Customer customer : customerList) {
            if (customer.getUserID().equals(customerID)) {
                return customer;
            }
        }
        System.out.println("No customer found");
        return null;
    }
}