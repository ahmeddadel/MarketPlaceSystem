package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static utils.Constants.CORRECT_EMOJI;
import static utils.Constants.WRONG_EMOJI;
import static utils.Utilities.print;

public class Database {

    // private constructor to prevent instantiation of this class from outside the class
    private Database() {
    }

    // Connection object to connect to the database
    private static Connection instance = null;

    // static method to create instance of Singleton data.Database class
    public static Connection getInstance() {
        // synchronized block to remove overhead of multiple threads trying to access the instance at the same time
        synchronized (Database.class) {
            if (instance == null)
                try {
                    instance = DriverManager.getConnection(DatabaseContract.URL, DatabaseContract.USER_NAME, DatabaseContract.PASSWORD);
                    print("Connected to the database successfully " + CORRECT_EMOJI, "Database Connection", 1);
                } catch (SQLException e) {
                    print("Error connecting to the database " + WRONG_EMOJI, "Database Connection", 0);
                    System.out.println("Error connecting to the database\n" + e.getMessage());
                }
            return instance;
        }
    }

    // static method to close the connection to the database
    public static void closeConnection() {
        try {
            instance.close();
        } catch (SQLException e) {
            print("Error closing the connection to the database\n" + e.getMessage(), "Error", 0);
            System.out.println("Error closing connection to the database\n" + e.getMessage());
        }
    }

    // static method to create a statement object to execute the sql queries
    public static Statement getStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            print("Error creating statement object\n" + e.getMessage(), "Error", 0);
            System.out.println("Error creating statement object\n" + e.getMessage());
        }
        return null;
    }

    // static method to close the statement object
    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            print("Error closing the statement object\n" + e.getMessage(), "Error", 0);
            System.out.println("Error closing statement object\n" + e.getMessage());
        }
    }
}
