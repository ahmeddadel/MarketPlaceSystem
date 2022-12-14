package transaction;

import main.Main;
import models.Transaction;
import models.User;
import utils.Constants;

import java.util.ArrayList;
import java.util.Formatter;

import static data.DatabaseContract.*;
import static utils.Constants.WRONG_EMOJI;
import static utils.Utilities.inputOptions;
import static utils.Utilities.print;

public class Transactions {

    // private constructor to prevent instantiation of this class from outside the class
    private Transactions() {
    }

    public static void transactionMenu() {
        String title = "Transactions Menu";

        // Transactions Menu message
        int input = inputOptions(Constants.PLEASE_SELECT_THE_DESIRED_PROCESS, title, Constants.TRANSACTION_MENU_OPTIONS);

        switch (input) {
            case 0 ->
                // show all users
                    showAllUsers();
            case 1 ->
                // show all products
                    showAllProducts();

            case 2 ->
                // show all transactions
                    showAllTransactions();

            case 3 ->
                // back
                    Main.homeMenu();

            default -> {
                // invalid input
                print("Invalid input " + WRONG_EMOJI, title, 0);
                transactionMenu();
            }
        }
    }

    private static void showAllUsers() {
        String title = "Show all users";
        String tab = "    ";

        // get all the users from the database
        ArrayList<User> users = UserEntry.getAllUsers(Main.statement);

        // print the users in a table format with the following columns (id, name, email, phone, location)
        Formatter formatter = new Formatter();
        formatter.format("Total users: %d%n%n", users.size());
        formatter.format("%-5s %-20s %-50s %-20s", "ID", "Name", tab + "Email", tab + "Location");
        formatter.format("%n");
        for (User user : users) {
            formatter.format("%-5s %-20s %-50s %-20s", user.getId(), user.getName(), user.getEmail(), user.getLocation());
            formatter.format("%n");
        }
        print(formatter.toString(), title, 1);
        formatter.close();

        // go back to the Transactions Menu
        transactionMenu();
    }

    private static void showAllProducts() {
        String title = "Show all products";
        String tab = "    ";

        // get all the products from the database
        ArrayList<models.Product> products = ProductEntry.getAllProducts(Main.statement);

        // print the products in a table format with the following columns (id, name, type, price, seller id)
        Formatter formatter = new Formatter();
        formatter.format("Total users: %d%n%n", products.size());
        formatter.format("%-5s %-20s %-20s %-20s %-20s", "ID", "Name", tab + "Type", tab + "Price", tab + "Seller ID");
        formatter.format("%n");
        for (models.Product product : products) {
            formatter.format("%-5s %-20s %-20s %-20s %-20s", product.getId(), product.getName(), product.getType(), product.getPrice(), product.getUserId());
            formatter.format("%n");
        }
        print(formatter.toString(), title, 1);
        formatter.close();

        // go back to the Transactions Menu
        transactionMenu();
    }

    private static void showAllTransactions() {
        String title = "Show all transactions";
        String tab = "    ";

        // get all the transactions from the database
        ArrayList<Transaction> transactions = TransactionEntry.getAllTransactions(Main.statement);

        // print the transactions in a table format with the following columns (id, product id, buyer id, seller id, date)
        Formatter formatter = new Formatter();
        formatter.format("Total transactions: %d%n%n", transactions.size());
        formatter.format("%-5s %-20s %-20s %-20s %-20s", "ID", "Product ID", "Buyer ID", tab + "Seller ID", tab + "Description");
        formatter.format("%n");
        for (Transaction transaction : transactions) {
            formatter.format("%-5s %-20s %-20s %-20s %-20s", transaction.getId(), tab + tab + transaction.getProductId(), tab + tab + tab + tab + transaction.getBuyerId(), tab + tab + tab + tab + tab + tab + transaction.getSellerId(), tab + tab + tab + tab + transaction.getDescription());
            formatter.format("%n");
        }
        print(formatter.toString(), title, 1);
        formatter.close();

        // go back to the Transactions Menu
        transactionMenu();
    }
}
