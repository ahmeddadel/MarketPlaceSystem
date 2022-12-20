package registration;

import main.Main;
import models.User;
import utils.Constants;

import static data.DatabaseContract.UserEntry;
import static utils.Constants.CORRECT_EMOJI;
import static utils.Constants.WRONG_EMOJI;
import static utils.Utilities.*;

public class Registrations {

    // private constructor to prevent instantiation of this class from outside the class
    private Registrations() {
    }

    // sign-in an existing user
    public static void signIn() {
        String title = "Sign-In";

        String email = input("Please enter your email: ", title);
        String password = input("Please enter your password: ", title);

        // check if the user exists in the database
        User user = UserEntry.getUserByEmail(Main.statement, email);
        if (user != null) {
            // check if the password is correct
            if (user.getPassword().equals(password)) {
                print("Welcome " + user.getName() + " " + emoji(0x1F60A), title, 1);
                Constants.CURRENT_USER_ID = user.getId();
                mainMenu();
            } else {
                print("Invalid password " + WRONG_EMOJI, title, 0);
                Main.homeMenu();
            }
        } else {
            print("User does not exist " + WRONG_EMOJI, title, 0);
            Main.homeMenu();
        }
    }

    // sign-up a new user
    public static void signUp() {
        String title = "Sign Up";

        // user attributes
        String name;
        String email;
        String password;
        double balance;
        String location;

        // get the user input
        name = input("Please enter your name: ", title);
        email = input("Please enter your email: ", title);
        password = input("Please enter your password: ", title);

        try {
            balance = Double.parseDouble(input("Please enter your balance: ", title));
        } catch (NumberFormatException e) {
            print("Invalid input " + WRONG_EMOJI, title, 0);
            Main.homeMenu();
            return;
        }

        location = input("Please enter your location: ", title);

        // create a new user object
        User user = new User(0, name, email, password, balance, location);

        // validate the user input
        if (validateUserRegistration(user)) {
            // insert the user into the database
            UserEntry.insertUser(Main.statement, user);
            print("Signed Up Successfully " + emoji(0x1F60A), title, 1);
            signIn();
        } else {
            Main.homeMenu();
        }
    }

    public static void resetPassword() {
        String title = "Reset Password";

        String email = input("Please enter your email: ", title);

        // get the user from the database
        User user = UserEntry.getUserByEmail(Main.statement, email);

        // check if the user exists in the database
        if (user != null) {
            String password = input("Please enter your new password: ", title);
            user.setPassword(password);
            // update the user password in the database
            UserEntry.updateUser(Main.statement, user);
            print("Password updated successfully " + CORRECT_EMOJI, title, 1);
        } else {
            print("User does not exist " + WRONG_EMOJI, title, 0);
            Main.homeMenu();
        }
    }
}