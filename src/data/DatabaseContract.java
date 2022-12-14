package data;

import models.Product;
import models.Transaction;
import models.User;
import utils.Constants;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static utils.Constants.WRONG_EMOJI;
import static utils.Utilities.print;

public class DatabaseContract {

    // private constructor to prevent instantiation of this class from outside the class
    private DatabaseContract() {
    }

    // database table names
    public static final String DATABASE_NAME = "marketplace";
    public static final String PORT_NUMBER = "3306";

    // database url, username and password
    public static final String URL = "jdbc:mariadb://localhost:" + PORT_NUMBER + "/" + DATABASE_NAME;
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "123456";

    // database fragments
    public static final class DatabaseFragmentation {

        // drop database if exists
        private static void dropDatabase(Statement statement, String databaseName) {
            try {
                statement.executeUpdate("DROP DATABASE IF EXISTS " + databaseName);
                System.out.println("Dropped database successfully");
            } catch (Exception e) {
                System.out.println("Error dropping database\n" + e.getMessage());
            }
        }

        // drop table if exists
        private static void dropTable(Statement statement, String tableName) {
            try {
                statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);
                System.out.println("Dropped table successfully");
            } catch (Exception e) {
                System.out.println("Error dropping table\n" + e.getMessage());
            }
        }

        // vertical fragmentation
        public static void createDatabaseVertical(Statement stmt) {
            String title = "Creating database for vertical fragmentation";
            String databaseName = DATABASE_NAME + "_vertical_fragmentation";
            dropDatabase(stmt, databaseName);

            // create database for vertical fragmentation
            try {
                String stmt_create = "CREATE DATABASE " + databaseName;
                stmt.executeUpdate(stmt_create);
                print("Database created successfully " + Constants.CORRECT_EMOJI, title, 1);
            } catch (Exception e) {
                System.out.println("Error while creating database for vertical fragmentation: " + e.getMessage() + WRONG_EMOJI);
                throw new RuntimeException("Error creating database\n" + e.getMessage());

            }
        }

        // create user_info table for vertical fragmentation database
        public static void createUserInfoTableVertical(Statement stmt) {
            String title = "Creating user_info table for vertical fragmentation database";
            String databaseName = DATABASE_NAME + "_vertical_fragmentation";
            String tableName = "user_info";
            dropTable(stmt, tableName);

            try {
                String stmt_create = "CREATE TABLE " + databaseName + "." + tableName + " AS \n" +
                        "SELECT id , name , email , password , location \n" +
                        "FROM " + DATABASE_NAME + "." + UserEntry.TABLE_NAME + ";";
                stmt.executeUpdate(stmt_create);
                print("Table created successfully " + Constants.CORRECT_EMOJI, title, 1);
            } catch (Exception e) {
                System.out.println("Error while creating user_info table for vertical fragmentation database: " + e.getMessage() + WRONG_EMOJI);
                throw new RuntimeException("Error creating table\n" + e.getMessage());
            }
        }

        // create user_balance table for vertical fragmentation database
        public static void createUserBalanceTableVertical(Statement stmt) {
            String title = "Creating user_balance table for vertical fragmentation database";
            String databaseName = DATABASE_NAME + "_vertical_fragmentation";
            String tableName = "user_balance";
            dropTable(stmt, tableName);

            try {
                String stmt_create = "CREATE TABLE " + databaseName + "." + tableName + " AS \n" +
                        "SELECT id , balance \n" +
                        "FROM " + DATABASE_NAME + "." + UserEntry.TABLE_NAME + ";";
                stmt.executeUpdate(stmt_create);
                print("Table created successfully " + Constants.CORRECT_EMOJI, title, 1);
            } catch (Exception e) {
                System.out.println("Error while creating user_balance table for vertical fragmentation database: " + e.getMessage() + WRONG_EMOJI);
                throw new RuntimeException("Error creating table\n" + e.getMessage());
            }
        }

        // ================================================= //

        // horizontal fragmentation
        public static void createDatabaseHorizontal(Statement stmt) {
            String title = "Creating database for horizontal fragmentation";
            String databaseName = DATABASE_NAME + "_horizontal_fragmentation";
            dropDatabase(stmt, databaseName);

            // create database for horizontal fragmentation
            try {
                String stmt_create = "CREATE DATABASE " + databaseName;
                stmt.executeUpdate(stmt_create);
                print("Database created successfully " + Constants.CORRECT_EMOJI, title, 1);
            } catch (Exception e) {
                System.out.println("Error while creating database for horizontal fragmentation: " + e.getMessage() + WRONG_EMOJI);
                throw new RuntimeException("Error creating database\n" + e.getMessage());
            }
        }

        // create location_USA table for horizontal fragmentation database
        public static void createLocationUSATableHorizontal(Statement stmt) {
            String title = "Creating location_USA table for horizontal fragmentation database";
            String databaseName = DATABASE_NAME + "_horizontal_fragmentation";
            String tableName = "location_USA";
            String location = "USA";
            dropTable(stmt, tableName);

            try {
                String stmt_create = "CREATE TABLE " + databaseName + "." + tableName + " AS \n" +
                        "SELECT id , name , email , password , balance , location \n" +
                        "FROM " + DATABASE_NAME + "." + UserEntry.TABLE_NAME + " \n" +
                        "WHERE location = " + "'" + location + "';";
                stmt.executeUpdate(stmt_create);
                print("Table created successfully " + Constants.CORRECT_EMOJI, title, 1);
            } catch (Exception e) {
                System.out.println("Error while creating location_USA table for horizontal fragmentation database: " + e.getMessage() + WRONG_EMOJI);
                throw new RuntimeException("Error creating table\n" + e.getMessage());
            }
        }

        // create location_UK table for horizontal fragmentation database
        public static void createLocationUKTableHorizontal(Statement stmt) {
            String title = "Creating location_UK table for horizontal fragmentation database";
            String databaseName = DATABASE_NAME + "_horizontal_fragmentation";
            String tableName = "location_UK";
            String location = "UK";
            dropTable(stmt, tableName);

            try {
                String stmt_create = "CREATE TABLE " + databaseName + "." + tableName + " AS \n" +
                        "SELECT id , name , email , password , balance , location \n" +
                        "FROM " + DATABASE_NAME + "." + UserEntry.TABLE_NAME + " \n" +
                        "WHERE location = " + "'" + location + "';";
                stmt.executeUpdate(stmt_create);
                print("Table created successfully " + Constants.CORRECT_EMOJI, title, 1);
            } catch (Exception e) {
                System.out.println("Error while creating location_UK table for horizontal fragmentation database: " + e.getMessage() + WRONG_EMOJI);
                throw new RuntimeException("Error creating table\n" + e.getMessage());
            }
        }

        // create location_EGY table for horizontal fragmentation database
        public static void createLocationEGYTableHorizontal(Statement stmt) {
            String title = "Creating location_EGY table for horizontal fragmentation database";
            String databaseName = DATABASE_NAME + "_horizontal_fragmentation";
            String tableName = "location_EGY";
            String location = "EGY";
            dropTable(stmt, tableName);

            try {
                String stmt_create = "CREATE TABLE " + databaseName + "." + tableName + " AS \n" +
                        "SELECT id , name , email , password , balance , location \n" +
                        "FROM " + DATABASE_NAME + "." + UserEntry.TABLE_NAME + " \n" +
                        "WHERE location = " + "'" + location + "';";
                stmt.executeUpdate(stmt_create);
                print("Table created successfully " + Constants.CORRECT_EMOJI, title, 1);
            } catch (Exception e) {
                System.out.println("Error while creating location_EGY table for horizontal fragmentation database: " + e.getMessage() + WRONG_EMOJI);
                throw new RuntimeException("Error creating table\n" + e.getMessage());
            }
        }
    }

    // user table and columns names
    public static final class UserEntry {
        public static final String TABLE_NAME = "user";

        public static final String COLUMN_USER_ID = "id";
        public static final String COLUMN_USER_NAME = "name";
        public static final String COLUMN_USER_EMAIL = "email";
        public static final String COLUMN_USER_PASSWORD = "password";
        public static final String COLUMN_USER_BALANCE = "balance";
        public static final String COLUMN_USER_LOCATION = "location";

        // get all users from the database
        public static ArrayList<User> getAllUsers(Statement stmt) {

            // query to get all users
            String getAllUsers = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + ";";

            // create an array list to store the users
            ArrayList<User> users = new ArrayList<>();

            try {
                ResultSet resultSet = stmt.executeQuery(getAllUsers);
                // check if the result set is empty
                if (!resultSet.next())
                    print("No users found " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a user object
                        User user = new User(
                                resultSet.getInt(COLUMN_USER_ID),
                                resultSet.getString(COLUMN_USER_NAME),
                                resultSet.getString(COLUMN_USER_EMAIL),
                                resultSet.getString(COLUMN_USER_PASSWORD),
                                resultSet.getDouble(COLUMN_USER_BALANCE),
                                resultSet.getString(COLUMN_USER_LOCATION)
                        );
                        // add the user to the array list
                        users.add(user);
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return users;
        }

        // get a user by id
        public static User getUserById(Statement stmt, int id) {

            // query to get a user by id
            String getUserById = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_USER_ID + " = " + id + ";";

            // create a user object
            User user = null;

            try {
                ResultSet resultSet = stmt.executeQuery(getUserById);
                // check if the result set is empty
                if (!resultSet.next())
                    print("No user with id " + id + " " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a user object
                        user = new User(
                                resultSet.getInt(COLUMN_USER_ID),
                                resultSet.getString(COLUMN_USER_NAME),
                                resultSet.getString(COLUMN_USER_EMAIL),
                                resultSet.getString(COLUMN_USER_PASSWORD),
                                resultSet.getDouble(COLUMN_USER_BALANCE),
                                resultSet.getString(COLUMN_USER_LOCATION)
                        );
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return user;
        }

        // get a user by email
        public static User getUserByEmail(Statement stmt, String email) {

            // query to get a user by email
            String getUserByEmail = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_USER_EMAIL + " = '" + email + "';";

            // create a user object
            User user = null;

            try {
                ResultSet resultSet = stmt.executeQuery(getUserByEmail);
                if (!resultSet.next())
                    print("No user with email " + email + " " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a user object
                        user = new User(
                                resultSet.getInt(COLUMN_USER_ID),
                                resultSet.getString(COLUMN_USER_NAME),
                                resultSet.getString(COLUMN_USER_EMAIL),
                                resultSet.getString(COLUMN_USER_PASSWORD),
                                resultSet.getDouble(COLUMN_USER_BALANCE),
                                resultSet.getString(COLUMN_USER_LOCATION)
                        );
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return user;
        }

        // insert a user into the database
        public static void insertUser(Statement stmt, User user) {

            // query to insert a user
            String insertUser = "INSERT INTO " + DATABASE_NAME + "." + TABLE_NAME + " VALUES (" +
                    user.getId() + ", '" +
                    user.getName() + "', '" +
                    user.getEmail() + "', '" +
                    user.getPassword() + "', " +
                    user.getBalance() + ", '" +
                    user.getLocation() + "');";

            try {
                stmt.executeUpdate(insertUser);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // update a user in the database
        public static void updateUser(Statement stmt, User user) {

            // query to update a user
            String updateUser = "UPDATE " + DATABASE_NAME + "." + TABLE_NAME + " SET " +
                    COLUMN_USER_NAME + " = '" + user.getName() + "', " +
                    COLUMN_USER_EMAIL + " = '" + user.getEmail() + "', " +
                    COLUMN_USER_PASSWORD + " = '" + user.getPassword() + "', " +
                    COLUMN_USER_BALANCE + " = " + user.getBalance() + ", " +
                    COLUMN_USER_LOCATION + " = '" + user.getLocation() + "' " +
                    "WHERE " + COLUMN_USER_ID + " = " + user.getId() + ";";

            try {
                stmt.executeUpdate(updateUser);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete a user from the database
        public static void deleteUser(Statement stmt, int id) {

            // query to delete a user
            String deleteUser = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_USER_ID + " = " + id + ";";

            try {
                stmt.executeUpdate(deleteUser);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete all users from the database
        public static void deleteAllUsers(Statement stmt) {

            // query to delete all users
            String deleteAllUsers = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + ";";

            try {
                stmt.executeUpdate(deleteAllUsers);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // get the number of users in the database
        public static int getNumberOfUsers(Statement stmt) {

            int numberOfUsers = getAllUsers(stmt).size();

            if (numberOfUsers == 0)
                print("No users in the database " + WRONG_EMOJI, TABLE_NAME, 0);

            return numberOfUsers;
        }
    }


    // product table and columns names
    public static final class ProductEntry {
        public static final String TABLE_NAME = "product";

        public static final String COLUMN_PRODUCT_ID = "id";
        public static final String COLUMN_PRODUCT_USER_ID = "userId";
        public static final String COLUMN_PRODUCT_NAME = "name";
        public static final String COLUMN_PRODUCT_TYPE = "type";
        public static final String COLUMN_PRODUCT_PRICE = "price";

        // get all products from the database
        public static ArrayList<Product> getAllProducts(Statement stmt) {

            // query to get all products
            String getAllProducts = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + ";";

            // create an array list of products
            ArrayList<Product> products = new ArrayList<>();

            try {
                ResultSet resultSet = stmt.executeQuery(getAllProducts);
                if (!resultSet.next())
                    print("No products in the database " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a product object
                        Product product = new Product(
                                resultSet.getInt(COLUMN_PRODUCT_ID),
                                resultSet.getInt(COLUMN_PRODUCT_USER_ID),
                                resultSet.getString(COLUMN_PRODUCT_NAME),
                                resultSet.getString(COLUMN_PRODUCT_TYPE),
                                resultSet.getDouble(COLUMN_PRODUCT_PRICE)
                        );
                        // add the product to the array list
                        products.add(product);
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return products;
        }

        // get a product by id
        public static Product getProductById(Statement stmt, int id) {

            // query to get a product by id
            String getProductById = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_ID + " = " + id + ";";

            // create a product object
            Product product = null;

            try {
                ResultSet resultSet = stmt.executeQuery(getProductById);
                if (!resultSet.next())
                    print("No product with id " + id + " " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a product object
                        product = new Product(
                                resultSet.getInt(COLUMN_PRODUCT_ID),
                                resultSet.getInt(COLUMN_PRODUCT_USER_ID),
                                resultSet.getString(COLUMN_PRODUCT_NAME),
                                resultSet.getString(COLUMN_PRODUCT_TYPE),
                                resultSet.getDouble(COLUMN_PRODUCT_PRICE)
                        );
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return product;
        }

        // get a product by name
        public static Product getProductByName(Statement stmt, String name) {

            // query to get a product by name
            String getProductByName = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_NAME + " = '" + name + "';";

            // create a product object
            Product product = null;

            try {
                ResultSet resultSet = stmt.executeQuery(getProductByName);
                if (!resultSet.next())
                    print("No product with name " + name + " " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a product object
                        product = new Product(
                                resultSet.getInt(COLUMN_PRODUCT_ID),
                                resultSet.getInt(COLUMN_PRODUCT_USER_ID),
                                resultSet.getString(COLUMN_PRODUCT_NAME),
                                resultSet.getString(COLUMN_PRODUCT_TYPE),
                                resultSet.getDouble(COLUMN_PRODUCT_PRICE)
                        );
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return product;
        }

        // get a product by user id
        public static ArrayList<Product> getProductsByUserId(Statement stmt, int userId) {

            // query to get a product by user id
            String getProductsByUserId = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_USER_ID + " = " + userId + ";";

            // create an array list of products
            ArrayList<Product> products = new ArrayList<>();

            try {
                ResultSet resultSet = stmt.executeQuery(getProductsByUserId);
                if (!resultSet.next())
                    print("No products with user id " + userId + " " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a product object
                        Product product = new Product(
                                resultSet.getInt(COLUMN_PRODUCT_ID),
                                resultSet.getInt(COLUMN_PRODUCT_USER_ID),
                                resultSet.getString(COLUMN_PRODUCT_NAME),
                                resultSet.getString(COLUMN_PRODUCT_TYPE),
                                resultSet.getDouble(COLUMN_PRODUCT_PRICE)
                        );
                        // add the product to the array list
                        products.add(product);
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return products;
        }

        // get all products except the ones with the given user id
        public static ArrayList<Product> getAllProductsExceptUserId(Statement stmt, int userId) {

            // query to get all products except the ones with the given user id
            String getAllProductsExceptUserId = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_USER_ID + " != " + userId + ";";

            // create an array list of products
            ArrayList<Product> products = new ArrayList<>();

            try {
                ResultSet resultSet = stmt.executeQuery(getAllProductsExceptUserId);
                if (!resultSet.next())
                    print("No products with user id " + userId + " " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a product object
                        Product product = new Product(
                                resultSet.getInt(COLUMN_PRODUCT_ID),
                                resultSet.getInt(COLUMN_PRODUCT_USER_ID),
                                resultSet.getString(COLUMN_PRODUCT_NAME),
                                resultSet.getString(COLUMN_PRODUCT_TYPE),
                                resultSet.getDouble(COLUMN_PRODUCT_PRICE)
                        );
                        // add the product to the array list
                        products.add(product);
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return products;
        }

        // insert a product into the database
        public static void insertProduct(Statement stmt, Product product) {

            // query to insert a product
            String insertProduct = "INSERT INTO " + DATABASE_NAME + "." + TABLE_NAME + " (" +
                    COLUMN_PRODUCT_USER_ID + ", " +
                    COLUMN_PRODUCT_NAME + ", " +
                    COLUMN_PRODUCT_TYPE + ", " +
                    COLUMN_PRODUCT_PRICE + ") VALUES (" +
                    product.getUserId() + ", '" +
                    product.getName() + "', '" +
                    product.getType() + "', " +
                    product.getPrice() + ");";

            try {
                stmt.executeUpdate(insertProduct);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // update a product in the database
        public static void updateProduct(Statement stmt, Product product) {

            // query to update a product
            String updateUser = "UPDATE " + DATABASE_NAME + "." + TABLE_NAME + " SET " +
                    COLUMN_PRODUCT_USER_ID + " = " + product.getUserId() + ", " +
                    COLUMN_PRODUCT_NAME + " = '" + product.getName() + "', " +
                    COLUMN_PRODUCT_TYPE + " = '" + product.getType() + "', " +
                    COLUMN_PRODUCT_PRICE + " = " + product.getPrice() + " " +
                    "WHERE " + COLUMN_PRODUCT_ID + " = " + product.getId() + ";";

            try {
                stmt.executeUpdate(updateUser);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete a product from the database
        public static void deleteProduct(Statement stmt, int id) {

            // query to delete a product
            String deleteProduct = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_ID + " = " + id + ";";

            try {
                stmt.executeUpdate(deleteProduct);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete all products from the database
        public static void deleteAllProducts(Statement stmt) {

            // query to delete all products
            String deleteAllProducts = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + ";";

            try {
                stmt.executeUpdate(deleteAllProducts);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete all products from the database by user id
        public static void deleteAllProductsByUserId(Statement stmt, int userId) {

            // query to delete all products by user id
            String deleteAllProductsByUserId = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_USER_ID + " = " + userId + ";";

            try {
                stmt.executeUpdate(deleteAllProductsByUserId);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // get the number of products in the database
        public static int getNumberOfProducts(Statement stmt) {

            // query to get the number of products
            String getNumberOfProducts = "SELECT COUNT(*) FROM " + DATABASE_NAME + "." + TABLE_NAME + ";";

            // create a variable to store the number of products
            int numberOfProducts = 0;

            try {
                ResultSet resultSet = stmt.executeQuery(getNumberOfProducts);
                if (resultSet.next())
                    numberOfProducts = resultSet.getInt(1);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return numberOfProducts;
        }

    }

    // transaction table and columns names
    public static final class TransactionEntry {
        public static final String TABLE_NAME = "transaction";

        public static final String COLUMN_TRANSACTION_ID = "id";
        public static final String COLUMN_TRANSACTION_DESCRIPTION = "description";
        public static final String COLUMN_TRANSACTION_SELLER_ID = "sellerId";
        public static final String COLUMN_TRANSACTION_BUYER_ID = "buyerId";
        public static final String COLUMN_TRANSACTION_PRODUCT_ID = "productId";

        // get all transactions from the database
        public static ArrayList<Transaction> getAllTransactions(Statement stmt) {

            // query to get all transactions
            String getAllTransactions = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + ";";

            // create an array list of transactions
            ArrayList<Transaction> transactions = new ArrayList<>();

            try {
                ResultSet resultSet = stmt.executeQuery(getAllTransactions);
                if (!resultSet.next())
                    print("No transactions " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a transaction object
                        Transaction transaction = new Transaction(
                                resultSet.getInt(COLUMN_TRANSACTION_ID),
                                resultSet.getString(COLUMN_TRANSACTION_DESCRIPTION),
                                resultSet.getInt(COLUMN_TRANSACTION_SELLER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_BUYER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_PRODUCT_ID)
                        );
                        // add the transaction to the array list
                        transactions.add(transaction);
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return transactions;
        }

        // get a transaction from the database by id
        public static Transaction getTransactionById(Statement stmt, int id) {

            // query to get a transaction by id
            String getTransactionById = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_ID + " = " + id + ";";

            // create a transaction object
            Transaction transaction = null;

            try {
                ResultSet resultSet = stmt.executeQuery(getTransactionById);
                if (!resultSet.next())
                    print("No transaction found " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a transaction object
                        transaction = new Transaction(
                                resultSet.getInt(COLUMN_TRANSACTION_ID),
                                resultSet.getString(COLUMN_TRANSACTION_DESCRIPTION),
                                resultSet.getInt(COLUMN_TRANSACTION_SELLER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_BUYER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_PRODUCT_ID)
                        );
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return transaction;
        }

        // get all transactions from the database by seller id
        public static ArrayList<Transaction> getAllTransactionsBySellerId(Statement stmt, int sellerId) {

            // query to get all transactions by seller id
            String getAllTransactionsBySellerId = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_SELLER_ID + " = " + sellerId + ";";

            // create an array list of transactions
            ArrayList<Transaction> transactions = new ArrayList<>();

            try {
                ResultSet resultSet = stmt.executeQuery(getAllTransactionsBySellerId);
                if (!resultSet.next())
                    print("No transactions found " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a transaction object
                        Transaction transaction = new Transaction(
                                resultSet.getInt(COLUMN_TRANSACTION_ID),
                                resultSet.getString(COLUMN_TRANSACTION_DESCRIPTION),
                                resultSet.getInt(COLUMN_TRANSACTION_SELLER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_BUYER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_PRODUCT_ID)
                        );
                        // add the transaction to the array list
                        transactions.add(transaction);
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return transactions;
        }

        // get all transactions from the database by buyer id
        public static ArrayList<Transaction> getAllTransactionsByBuyerId(Statement stmt, int buyerId) {

            // query to get all transactions by buyer id
            String getAllTransactionsByBuyerId = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_BUYER_ID + " = " + buyerId + ";";

            // create an array list of transactions
            ArrayList<Transaction> transactions = new ArrayList<>();

            try {
                ResultSet resultSet = stmt.executeQuery(getAllTransactionsByBuyerId);
                if (!resultSet.next())
                    print("No transactions found " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a transaction object
                        Transaction transaction = new Transaction(
                                resultSet.getInt(COLUMN_TRANSACTION_ID),
                                resultSet.getString(COLUMN_TRANSACTION_DESCRIPTION),
                                resultSet.getInt(COLUMN_TRANSACTION_SELLER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_BUYER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_PRODUCT_ID)
                        );
                        // add the transaction to the array list
                        transactions.add(transaction);
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return transactions;
        }

        // get all transactions from the database by product id
        public static ArrayList<Transaction> getAllTransactionsByProductId(Statement stmt, int productId) {

            // query to get all transactions by product id
            String getAllTransactionsByProductId = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_PRODUCT_ID + " = " + productId + ";";

            // create an array list of transactions
            ArrayList<Transaction> transactions = new ArrayList<>();

            try {
                ResultSet resultSet = stmt.executeQuery(getAllTransactionsByProductId);
                if (!resultSet.next())
                    print("No transactions found " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a transaction object
                        Transaction transaction = new Transaction(
                                resultSet.getInt(COLUMN_TRANSACTION_ID),
                                resultSet.getString(COLUMN_TRANSACTION_DESCRIPTION),
                                resultSet.getInt(COLUMN_TRANSACTION_SELLER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_BUYER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_PRODUCT_ID)
                        );
                        // add the transaction to the array list
                        transactions.add(transaction);
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return transactions;
        }

        // get all transactions from the database by seller id and buyer id
        public static ArrayList<Transaction> getAllTransactionsBySellerIdAndBuyerId(Statement stmt, int sellerId, int buyerId) {

            // query to get all transactions by seller id and buyer id
            String getAllTransactionsBySellerIdAndBuyerId = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_SELLER_ID + " = " + sellerId + " AND " + COLUMN_TRANSACTION_BUYER_ID + " = " + buyerId + ";";

            // create an array list of transactions
            ArrayList<Transaction> transactions = new ArrayList<>();

            try {
                ResultSet resultSet = stmt.executeQuery(getAllTransactionsBySellerIdAndBuyerId);
                if (!resultSet.next())
                    print("No transactions found " + WRONG_EMOJI, TABLE_NAME, 0);
                else
                    do {
                        // create a transaction object
                        Transaction transaction = new Transaction(
                                resultSet.getInt(COLUMN_TRANSACTION_ID),
                                resultSet.getString(COLUMN_TRANSACTION_DESCRIPTION),
                                resultSet.getInt(COLUMN_TRANSACTION_SELLER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_BUYER_ID),
                                resultSet.getInt(COLUMN_TRANSACTION_PRODUCT_ID)
                        );
                        // add the transaction to the array list
                        transactions.add(transaction);
                    } while (resultSet.next());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return transactions;
        }

        // insert a transaction into the database
        public static void insertTransaction(Statement stmt, Transaction transaction) {

            // query to insert a transaction
            String insertTransaction = "INSERT INTO " + DATABASE_NAME + "." + TABLE_NAME + " (" + COLUMN_TRANSACTION_DESCRIPTION + ", " + COLUMN_TRANSACTION_SELLER_ID + ", " + COLUMN_TRANSACTION_BUYER_ID + ", " + COLUMN_TRANSACTION_PRODUCT_ID + ") VALUES ('" + transaction.getDescription() + "', " + transaction.getSellerId() + ", " + transaction.getBuyerId() + ", " + transaction.getProductId() + ");";

            try {
                stmt.executeUpdate(insertTransaction);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // update a transaction in the database
        public static void updateTransaction(Statement stmt, Transaction transaction) {

            // query to update a transaction
            String updateTransaction = "UPDATE " + DATABASE_NAME + "." + TABLE_NAME + " SET " + COLUMN_TRANSACTION_DESCRIPTION + " = '" + transaction.getDescription() + "', " + COLUMN_TRANSACTION_SELLER_ID + " = " + transaction.getSellerId() + ", " + COLUMN_TRANSACTION_BUYER_ID + " = " + transaction.getBuyerId() + ", " + COLUMN_TRANSACTION_PRODUCT_ID + " = " + transaction.getProductId() + " WHERE " + COLUMN_TRANSACTION_ID + " = " + transaction.getId() + ";";

            try {
                stmt.executeUpdate(updateTransaction);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete a transaction from the database
        public static void deleteTransaction(Statement stmt, int transactionId) {

            // query to delete a transaction
            String deleteTransaction = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_ID + " = " + transactionId + ";";

            try {
                stmt.executeUpdate(deleteTransaction);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete all transactions from the database
        public static void deleteAllTransactions(Statement stmt) {

            // query to delete all transactions
            String deleteAllTransactions = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + ";";

            try {
                stmt.executeUpdate(deleteAllTransactions);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete all transactions from the database by seller id
        public static void deleteAllTransactionsBySellerId(Statement stmt, int sellerId) {

            // query to delete all transactions by seller id
            String deleteAllTransactionsBySellerId = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_SELLER_ID + " = " + sellerId + ";";

            try {
                stmt.executeUpdate(deleteAllTransactionsBySellerId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete all transactions from the database by buyer id
        public static void deleteAllTransactionsByBuyerId(Statement stmt, int buyerId) {

            // query to delete all transactions by buyer id
            String deleteAllTransactionsByBuyerId = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_BUYER_ID + " = " + buyerId + ";";

            try {
                stmt.executeUpdate(deleteAllTransactionsByBuyerId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete all transactions from the database by product id
        public static void deleteAllTransactionsByProductId(Statement stmt, int productId) {

            // query to delete all transactions by product id
            String deleteAllTransactionsByProductId = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_PRODUCT_ID + " = " + productId + ";";

            try {
                stmt.executeUpdate(deleteAllTransactionsByProductId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // delete all transactions from the database by seller id and buyer id
        public static void deleteAllTransactionsBySellerIdAndBuyerId(Statement stmt, int sellerId, int buyerId) {

            // query to delete all transactions by seller id and buyer id
            String deleteAllTransactionsBySellerIdAndBuyerId = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE " + COLUMN_TRANSACTION_SELLER_ID + " = " + sellerId + " AND " + COLUMN_TRANSACTION_BUYER_ID + " = " + buyerId + ";";

            try {
                stmt.executeUpdate(deleteAllTransactionsBySellerIdAndBuyerId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // get the number of transactions in the database
        public static int getNumberOfTransactions(Statement stmt) {

            // query to get the number of transactions
            String getNumberOfTransactions = "SELECT COUNT(*) FROM " + DATABASE_NAME + "." + TABLE_NAME + ";";

            try {
                ResultSet resultSet = stmt.executeQuery(getNumberOfTransactions);
                resultSet.next();
                return resultSet.getInt(1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}