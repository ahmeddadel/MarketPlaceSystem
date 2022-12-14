package utils;

import business.Business;
import data.Database;
import models.User;
import report.Reports;

import javax.swing.*;
import java.util.regex.Pattern;

import static main.Main.homeMenu;
import static main.Main.statement;
import static utils.Constants.EXCLAMATION_EMOJI;
import static utils.Constants.WRONG_EMOJI;

public class Utilities {
    // private constructor to prevent instantiation of this class from outside the class
    private Utilities() {
    }

    public static boolean validateUserRegistration(User user) {
        String title = "Validation";

        // user attributes
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        double balance = user.getBalance();
        String location = user.getLocation();

        if (name == null || name.isEmpty()) {
            Utilities.print("Name is required " + EXCLAMATION_EMOJI, title, 2);
            return false;
        }
        if (email == null || email.isEmpty()) {
            Utilities.print("Email is required " + EXCLAMATION_EMOJI, title, 2);
            return false;
        }
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            Utilities.print("Invalid email " + WRONG_EMOJI, title, 0);
            return false;
        }
        if (password == null || password.isEmpty()) {
            Utilities.print("Password is required " + EXCLAMATION_EMOJI, title, 2);
            return false;
        }
        if (password.length() < 6) {
            Utilities.print("Password must be at least 6 characters " + EXCLAMATION_EMOJI, title, 2);
            return false;
        }
        if (balance < 0) {
            Utilities.print("Balance must be greater than or equal to 0 " + EXCLAMATION_EMOJI, title, 2);
            return false;
        }
        if (location == null || location.isEmpty()) {
            Utilities.print("Location is required " + EXCLAMATION_EMOJI, title, 2);
            return false;
        }
        return true;
    }

    public static void print(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(new JButton(), message, title, messageType);
    }

    public static String input(String message, String title) {
        return JOptionPane.showInputDialog(new JButton(), message, title, JOptionPane.QUESTION_MESSAGE);
    }

    public static int inputOptions(String message, String title, String[] options) {
        return JOptionPane.showOptionDialog(new JButton(), message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    public static void mainMenu() {
        String title = "Main Menu";

        // Main Menu message
        int input = Utilities.inputOptions(Constants.PLEASE_SELECT_THE_DESIRED_PROCESS, title, Constants.MAIN_MENU_OPTIONS);

        switch (input) {
            case 0 ->
                // sell
                    Business.sell();
            case 1 ->
                // buy
                    Business.buy();
            case 2 ->
                // add cash
                    Business.addCash();
            case 3 ->
                // reports
                    Reports.mainMenu();
            case 4 ->
                // sign out
                    homeMenu();
            default -> {
                // invalid input
                Utilities.print("Invalid input " + WRONG_EMOJI, title, 0);
                mainMenu();
            }
        }
    }

    public static void exit() {
        String title = "Exit";

        Utilities.print(Constants.EXIT_MESSAGE, title, 1);

        // close the connection to the database when the application is closed
        assert statement != null; // to remove the warning of statement being possibly null
        Database.closeStatement(statement);
        Database.closeConnection();

        System.exit(0);
    }

    public static String emoji(int unicode) {
        return new String(Character.toChars(unicode));
    }
}