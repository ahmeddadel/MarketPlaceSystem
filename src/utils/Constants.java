package utils;

import static utils.Utilities.emoji;

public class Constants {

    // private constructor to prevent instantiation of this class from outside the class
    private Constants() {
    }

    public static int CURRENT_USER_ID;
    public static final String WELCOME_MESSAGE = emoji(0x1F603) + " WELCOME TO OUR MARKETPLACE " + emoji(0x1F603) + "\n\nTeam Members:-\n1- Ahmed Adel 188928\n2- Ayman Ashraf 184682\n3- Omar Ahmed  185381\n4- Mohamed Gamal 182678";
    public static final String PLEASE_SELECT_THE_DESIRED_PROCESS = "Please select the desired process";
    public static final String[] REGISTRATION_MENU_OPTIONS = {"Sign-In", "Sign-Up", "Reset Password", "All Transactions", "Database Fragmentation !!", "Exit"};
    public static final String[] MAIN_MENU_OPTIONS = {"Sell", "Buy", "Add Cash", "Reports", "Sign-Out"};
    public static final String[] SELL_MENU_OPTIONS = {"Add Product", "Edit Product", "Remove Product", "  <-- Back"};
    public static final String[] TRANSACTION_MENU_OPTIONS = {"Show all users", "Show all products", "Show all transactions", "  <-- Back"};
    public static final String NO_ENOUGH_CASH_MESSAGE = "Would you like to add money to your balance?";
    public static final String[] NO_ENOUGH_CASH_OPTIONS = {"Yes", "No"};
    public static final String[] REPORTS_MENU_OPTIONS = {"Cash Balance", "Show all Purchased items", "Show all Sold items", "Show all my Products", "  <-- Back"};
    public static final String[] FRAGMENTATION_MENU_OPTIONS = {"Vertical Fragmentation", "Horizontal Fragmentation", "  <-- Back"};
    public static final String[] VERTICAL_FRAGMENTATION_OPTIONS = {"Create User-Info table", "Create User-Balance table", "  <-- Back"};
    public static final String[] HORIZONTAL_FRAGMENTATION_OPTIONS = {"Create Location-USA table", "Create Location-UK table", "Create Location-EGY table", "  <-- Back"};
    public static final String EXIT_MESSAGE = "Thank you for using our marketplace " + emoji(0x2764);

    public static final String WRONG_EMOJI = emoji(0x274C);
    public static final String CORRECT_EMOJI = emoji(0x2705);
    public static final String PENSIVE_FACE_EMOJI = emoji(0x1F614);
    public static final String EXCLAMATION_EMOJI = emoji(0x2757);
}