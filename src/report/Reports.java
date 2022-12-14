package report;

import main.Main;
import models.Transaction;
import utils.Constants;
import utils.Utilities;

import java.util.ArrayList;

import static data.DatabaseContract.*;
import static utils.Constants.PENSIVE_FACE_EMOJI;
import static utils.Constants.WRONG_EMOJI;
import static utils.Utilities.print;

public class Reports {

    // private constructor to prevent instantiation of this class from outside the class
    private Reports() {
    }

    public static void mainMenu() {
        String title = "Reports Menu";

        // Reports Menu message
        int input = Utilities.inputOptions(Constants.PLEASE_SELECT_THE_DESIRED_PROCESS, title, Constants.REPORTS_MENU_OPTIONS);

        switch (input) {
            case 0 ->
                // cash balance
                    cashBalance();
            case 1 ->
                // show all purchased items
                    showAllPurchasedItems();
            case 2 ->
                // show all sold items
                    showAllSoldItems();
            case 3 ->
                // show all my products
                    showAllMyProducts();
            case 4 ->
                // back
                    Utilities.mainMenu();
            default -> {
                // invalid input
                print("Invalid input " + WRONG_EMOJI, title, 0);
                mainMenu();
            }
        }
    }

    private static void cashBalance() {
        String title = "Cash Balance";

        // get the cash balance from the database
        double cashBalance = UserEntry.getUserById(Main.statement, Constants.CURRENT_USER_ID).getBalance();
        print("Your Cash Balance = " + cashBalance + "$", title, 1);

        // go back to the Reports Menu
        mainMenu();
    }

    private static void showAllPurchasedItems() {
        String title = "Show all Purchased items";

        // get all the purchased items from the database
        ArrayList<Transaction> transactions = TransactionEntry.getAllTransactionsByBuyerId(Main.statement, Constants.CURRENT_USER_ID);
        if (transactions.size() > 0) {
            // print the purchased items
            StringBuilder message = new StringBuilder();
            message.append("Purchased items:\n");
            for (Transaction transaction : transactions)
                message.append(transaction.toString()).append("\n");
            print(message.toString(), title, 1);

        } else {
            // no purchased items
            print("No Purchased Items " + PENSIVE_FACE_EMOJI, title, 1);
        }

        // go back to the Reports Menu
        mainMenu();
    }

    private static void showAllSoldItems() {
        String title = "Show all Sold items";

        // get all the sold items from the database
        ArrayList<Transaction> transactions = TransactionEntry.getAllTransactionsBySellerId(Main.statement, Constants.CURRENT_USER_ID);
        if (transactions.size() > 0) {
            // print the sold items
            StringBuilder message = new StringBuilder();
            message.append("Sold items:\n");
            for (Transaction transaction : transactions)
                message.append(transaction.toString()).append("\n");
            print(message.toString(), title, 1);

        } else {
            // no sold items
            print("No Sold Items " + PENSIVE_FACE_EMOJI, title, 1);
        }

        // go back to the Reports Menu
        mainMenu();
    }

    private static void showAllMyProducts() {
        String title = "Show all my Products";

        // get all the products from the database
        ArrayList<models.Product> products = ProductEntry.getProductsByUserId(Main.statement, Constants.CURRENT_USER_ID);
        if (products.size() > 0) {
            // print the products
            StringBuilder message = new StringBuilder();
            message.append("Products:\n");
            for (models.Product product : products)
                message.append(product.toString()).append("\n");
            print(message.toString(), title, 1);

        } else {
            // no products
            print("No Products " + PENSIVE_FACE_EMOJI, title, 1);
        }

        // go back to the Reports Menu
        mainMenu();
    }
}
