package data;

import data.DatabaseContract.DatabaseFragmentation;
import main.Main;
import utils.Constants;

import static utils.Constants.WRONG_EMOJI;
import static utils.Utilities.inputOptions;
import static utils.Utilities.print;

public class Fragmentation {

    // private constructor to prevent instantiation of this class from outside the class
    private Fragmentation() {
    }

    public static void fragmentationMenu() {
        String title = "Fragmentation Menu";
        int input = inputOptions(Constants.PLEASE_SELECT_THE_DESIRED_PROCESS, title, Constants.FRAGMENTATION_MENU_OPTIONS);

        switch (input) {
            case 0 -> {
                // vertical fragmentation
                DatabaseFragmentation.createDatabaseVertical(Main.statement);
                verticalFragmentation();
            }
            case 1 -> {
                // horizontal fragmentation
                DatabaseFragmentation.createDatabaseHorizontal(Main.statement);
                horizontalFragmentation();
            }
            case 2 ->
                // back
                    Main.homeMenu();
            default -> {
                // invalid input
                print("Invalid input " + WRONG_EMOJI, title, 0);
                fragmentationMenu();
            }
        }

    }

    private static void verticalFragmentation() {
        String title = "Vertical Fragmentation";
        int input = inputOptions(Constants.PLEASE_SELECT_THE_DESIRED_PROCESS, title, Constants.VERTICAL_FRAGMENTATION_OPTIONS);

        switch (input) {
            case 0 -> {
                // create user-info table
                DatabaseFragmentation.createUserInfoTableVertical(Main.statement);
                verticalFragmentation();
            }
            case 1 -> {
                // create user-balance table
                DatabaseFragmentation.createUserBalanceTableVertical(Main.statement);
                verticalFragmentation();
            }
            case 2 ->
                // back
                    fragmentationMenu();
            default -> {
                // invalid input
                print("Invalid input " + WRONG_EMOJI, title, 0);
                verticalFragmentation();
            }
        }
    }

    private static void horizontalFragmentation() {
        String title = "Horizontal Fragmentation";
        int input = inputOptions(Constants.PLEASE_SELECT_THE_DESIRED_PROCESS, title, Constants.HORIZONTAL_FRAGMENTATION_OPTIONS);

        switch (input) {
            case 0 -> {
                // create location-usa table
                DatabaseFragmentation.createLocationUSATableHorizontal(Main.statement);
                horizontalFragmentation();
            }
            case 1 -> {
                // create location-uk table
                DatabaseFragmentation.createLocationUKTableHorizontal(Main.statement);
                horizontalFragmentation();
            }
            case 2 -> {
                // create location-egy table
                DatabaseFragmentation.createLocationEGYTableHorizontal(Main.statement);
                horizontalFragmentation();
            }
            case 3 ->
                // back
                    fragmentationMenu();
            default -> {
                // invalid input
                print("Invalid input " + WRONG_EMOJI, title, 0);
                horizontalFragmentation();
            }
        }
    }
}
