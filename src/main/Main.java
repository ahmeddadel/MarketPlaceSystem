package main;

import data.Database;
import data.Fragmentation;
import transaction.Transactions;
import utils.Constants;

import java.sql.Connection;
import java.sql.Statement;

import static registration.Registrations.*;
import static utils.Constants.WRONG_EMOJI;
import static utils.Utilities.*;

public class Main {
    // create a connection to the database
    private static final Connection connection = Database.getInstance();

    // create a statement object to execute the sql queries
    public static final Statement statement = Database.getStatement(connection);

    public static void main(String[] args) {
        // start the application
        run();

        // close the connection to the database when the application is closed
        assert statement != null; // to remove the warning of statement being possibly null
        Database.closeStatement(statement);
        Database.closeConnection();
    }

    private static void run() {
        String title = "Welcome";

        // welcome message
        print(Constants.WELCOME_MESSAGE, title, 1);

        // home menu
        homeMenu();
    }

    public static void homeMenu() {
        String title = "Home Menu";

        int input = inputOptions(Constants.PLEASE_SELECT_THE_DESIRED_PROCESS, title, Constants.REGISTRATION_MENU_OPTIONS);

        switch (input) {
            case 0 ->
                // sign in
                    signIn();
            case 1 ->
                // sign up
                    signUp();
            case 2 ->
                // reset password
                    resetPassword();
            case 3 ->
                // transactions menu
                    Transactions.transactionMenu();
            case 4 ->
                // database fragmentation
                    Fragmentation.fragmentationMenu();
            case 5 ->
                // exit
                    exit();
            default -> {
                // invalid input
                print("Invalid input " + WRONG_EMOJI, title, 0);
                homeMenu();
            }
        }
    }

    private static void startOverAgain() {
        print("Start over again...", "Error", 2);
        run();
    }
}