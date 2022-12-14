package business;

import main.Main;
import models.Product;
import models.Transaction;
import models.User;
import utils.Constants;

import java.util.ArrayList;

import static data.DatabaseContract.*;
import static utils.Constants.CORRECT_EMOJI;
import static utils.Constants.WRONG_EMOJI;
import static utils.Utilities.*;

public class Business {

    // private constructor to prevent instantiation of this class from outside the class
    private Business() {
    }

    public static void sell() {
        String title = "Sell Menu";

        // Sell Menu message
        int input = inputOptions(Constants.PLEASE_SELECT_THE_DESIRED_PROCESS, title, Constants.SELL_MENU_OPTIONS);

        switch (input) {
            case 0 ->
                // add product
                    addProduct();
            case 1 ->
                // edit product
                    editProduct();
            case 2 ->
                // remove product
                    removeProduct();
            case 3 ->
                // back
                    mainMenu();
            default -> {
                // invalid input
                print("Invalid input " + WRONG_EMOJI, title, 0);
                sell();
            }
        }
    }

    // add product to the database
    private static void addProduct() {
        String title = "Add Product";

        // product attributes
        String name;
        String type;
        double price = -1;

        // get the user input
        name = input("Please enter the product name: ", title);
        type = input("Please enter the product type: ", title);
        try {
            price = Double.parseDouble(input("Please enter the product price: ", title));
        } catch (Exception e) {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            sell();
        }

        if (price < 0 || price > 1000000 || name.length() > 50 || type.length() > 50 || name.length() < 1 || type.length() < 1) {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            sell();
        }

        // create a new product object
        Product product = new Product(0, Constants.CURRENT_USER_ID, name, type, price);

        // check if the product already exists
        if (ProductEntry.getProductByName(Main.statement, name) == null) {
            // insert the product into the database
            ProductEntry.insertProduct(Main.statement, product);
            print("Product added successfully " + CORRECT_EMOJI, title, 1);
            sell();
        } else {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            addProduct();
        }
    }

    // edit product in the database
    private static void editProduct() {
        String title = "Edit Product";

        ArrayList<Product> products = ProductEntry.getProductsByUserId(Main.statement, Constants.CURRENT_USER_ID);

        // check if the user has any products
        if (products.size() == 0) {
            print("You don't have any products to edit " + WRONG_EMOJI, title, 0);
            sell();
        }

        StringBuilder message = new StringBuilder();
        message.append("Please enter the number of the desired product to be edited:\n");
        for (int i = 0; i < products.size(); i++)
            message.append(i + 1).append("- ").append(products.get(i).getName()).append("\n");

        // get the user input
        int input = -1;
        try {
            input = Integer.parseInt(input(message.toString(), title));
        } catch (Exception e) {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            sell();
        }

        // check if the input is valid
        if (input > 0 && input <= products.size()) {
            // get the product
            Product product = products.get(input - 1);

            // get the new product attributes
            String name = input("Please enter the new product name: ", title);
            String type = input("Please enter the new product type: ", title);

            double price = -1;
            try {
                price = Double.parseDouble(input("Please enter the new product price: ", title));
            } catch (Exception e) {
                print("Invalid input " + WRONG_EMOJI, title, 0);
                sell();
            }

            if (price < 0 || price > 1000000 || name.length() > 50 || type.length() > 50 || name.length() < 1 || type.length() < 1) {
                print("Invalid input " + WRONG_EMOJI, title, 0);
                sell();
            }

            product.setName(name);
            product.setType(type);
            product.setPrice(price);

            // update the product
            ProductEntry.updateProduct(Main.statement, product);
            print("Product updated successfully " + CORRECT_EMOJI, title, 1);
            sell();
        } else {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            editProduct();
        }
    }

    // remove product from the database
    private static void removeProduct() {
        String title = "Remove Product";

        ArrayList<Product> products = ProductEntry.getProductsByUserId(Main.statement, Constants.CURRENT_USER_ID);

        // check if the user has any products
        if (products.size() == 0) {
            print("You don't have any products to remove " + WRONG_EMOJI, title, 0);
            sell();
        }

        StringBuilder message = new StringBuilder();
        message.append("Please enter the number of the desired product to be removed:\n");
        for (int i = 0; i < products.size(); i++)
            message.append(i + 1).append("- ").append(products.get(i).getName()).append("\n");

        // get the user input
        int input = -1;
        try {
            input = Integer.parseInt(input(message.toString(), title));
        } catch (Exception e) {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            sell();
        }

        // check if the input is valid
        if (input > 0 && input <= products.size()) {
            // get the product
            Product product = products.get(input - 1);

            // remove the product
            ProductEntry.deleteProduct(Main.statement, product.getId());
            print("Product removed successfully " + CORRECT_EMOJI, title, 1);
            sell();
        } else {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            removeProduct();
        }
    }

    public static void buy() {
        String title = "Buy Menu";

        ArrayList<Product> products = ProductEntry.getAllProductsExceptUserId(Main.statement, Constants.CURRENT_USER_ID);
        StringBuilder message = new StringBuilder();
        message.append("Please enter the number of the desired product to be bought:\n");
        message.append("Your current balance is ").append(UserEntry.getUserById(Main.statement, Constants.CURRENT_USER_ID).getBalance()).append("$\n");
        message.append("0-  <-- Back\n");
        for (int i = 0; i < products.size(); i++)
            message.append(i + 1).append("- ").append(products.get(i).getName()).append(" = ").append(products.get(i).getPrice()).append("$").append("\n");

        // get the user input
        int input = -1;
        try {
            input = Integer.parseInt(input(message.toString(), title));
        } catch (Exception e) {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            buy();
        }

        // check if the input is valid
        if (input > 0 && input <= products.size()) {
            // get the product
            Product product = products.get(input - 1);

            // get the product price
            double price = product.getPrice();

            // get the user balance
            double balance = UserEntry.getUserById(Main.statement, Constants.CURRENT_USER_ID).getBalance();

            // check if the user has enough balance
            if (balance >= price) {
                // insert the transaction into the database
                Transaction transaction = new Transaction(0, product.getType() + " Purchase", product.getUserId(), Constants.CURRENT_USER_ID, product.getId());
                TransactionEntry.insertTransaction(Main.statement, transaction);

                // update the buyer balance
                balance = balance - price;
                User buyer = UserEntry.getUserById(Main.statement, Constants.CURRENT_USER_ID);
                buyer.setBalance(balance);
                UserEntry.updateUser(Main.statement, buyer);

                // update the seller balance
                User seller = UserEntry.getUserById(Main.statement, product.getUserId());
                seller.setBalance(seller.getBalance() + price);
                UserEntry.updateUser(Main.statement, seller);

                // update the product owner
                product.setUserId(Constants.CURRENT_USER_ID);
                ProductEntry.updateProduct(Main.statement, product);

                print("Product bought successfully " + CORRECT_EMOJI, title, 1);
                buy();
            } else {
                print("You don't have enough balance " + emoji(0x1F61E) + "Your current balance is " + balance + "$", title, 0);
                noEnoughBalance();
            }
        } else if (input == 0) {
            mainMenu();
        } else {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            buy();
        }
    }

    private static void noEnoughBalance() {
        String title = "No Enough Balance";
        int input = inputOptions(Constants.NO_ENOUGH_CASH_MESSAGE, title, Constants.NO_ENOUGH_CASH_OPTIONS);
        switch (input) {
            case 0 -> {
                // add money to the balance
                double balance = UserEntry.getUserById(Main.statement, Constants.CURRENT_USER_ID).getBalance();
                double money = Double.parseDouble(input("Please enter the amount of money you want to add: ", title));
                balance = balance + money;
                User user = UserEntry.getUserById(Main.statement, Constants.CURRENT_USER_ID);
                user.setBalance(balance);
                UserEntry.updateUser(Main.statement, user);
                print("Money added successfully " + CORRECT_EMOJI, title, 1);
                buy();
            }
            case 1 -> buy();
            default -> {
                // invalid input
                print("Invalid input " + WRONG_EMOJI, title, 0);
                noEnoughBalance();
            }
        }
    }

    public static void addCash() {
        String title = "Add Cash";

        // get the user input
        double input = -1;
        try {
            input = Double.parseDouble(input("Please enter the amount of money you want to add: ", title));
        } catch (Exception e) {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            mainMenu();
        }

        if (input <= 0) {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            mainMenu();
        }

        // get the user
        User user = UserEntry.getUserById(Main.statement, Constants.CURRENT_USER_ID);
        user.setBalance(user.getBalance() + input);

        // update the user
        UserEntry.updateUser(Main.statement, user);
        print("Cash added successfully " + CORRECT_EMOJI, title, 1);
        mainMenu();

    }
}