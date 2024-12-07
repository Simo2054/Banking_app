package sechiule;

import pages.*;
import Card_types.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager
{
    private static DatabaseManager instance; // static instance
    private Connection connection;

    // private constructor to not allow instantiation from outside
    private DatabaseManager() throws Exception
    {
        String url = "jdbc:mariadb://<host>:<port>/<database_name>"; 
        // url specifies the JDBC connection string
        // jdbc:mariadb://<host>:<port>/<database_name>

        // credentials for logging into the database:
        String username = "username";
        String password = "password";

        // establish connection to the database
        this.connection = DriverManager.getConnection(url, username, password);

        // DriverManager.getConnection() - establishes a connection to the database 
        //                              using the provided url, username, password
        // Connection is an interface - defines methods for interacting with the database
        //                            - the actual behaviour is provided by the JDBC driver
    }

    // method to get a single instance
    // public to be available everywhere
    // static because the method belongs to the class itself, not to individual objects
    // synchronized because it ensures only one thread at a time can execute this method
    // used in a singleton to avoid creating multiple instances in multi-threading
    public static synchronized DatabaseManager getInstance() throws Exception
    {
        if(instance == null) // checks if singleton instance has already been created
        {
            instance = new DatabaseManager(); // will create an instance if there is none
        }
        return instance; // will return the existing one
    }

    // method to provide access to the database connection managed by the singleton
    public Connection getConnection()
    {
        return connection;
    }
    

    // create "users" table
    public void createUsersTable() throws Exception
    {
        String createUsersQuery = "CREATE TABLE IF NOT EXISTS users (" +
                                // create table named users if it doesn't already exist
                                  "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                                // ID is auto-incremented and primary key
                                  "email VARCHAR(255) NOT NULL UNIQUE, " +
                                // a string field email that must be unique
                                  "username VARCHAR(50) NOT NULL, " +
                                // a string field username
                                  "password VARCHAR(255) NOT NULL, " +
                                // a string field password
                                  "first_name VARCHAR(100) DEFAULT '', " +
                                  "last_name VARCHAR(50) DEFAULT '', " +
                                  "phone_number VARCHAR(15), " + 
                                  // can have null value, not required
                                  "country VARCHAR(50) DEFAULT '', " +
                                  "city VARCHAR(50) DEFAULT '', " +
                                  "county VARCHAR(50), " + 
                                  // can have null value, not required
                                  "street_name VARCHAR(100) DEFAULT '', " + 
                                  "home_number VARCHAR(10) DEFAULT '', " +
                                  "card_type VARCHAR(20) DEFAULT '', " + 
                                  "status ENUM('pending', 'completed') DEFAULT 'pending' NOT NULL" +
                                  // using predefined values - pending = data entry is still ongoing
                                  //                         - completed = the record is fully filled and ready for use
                                  ");";

        try( //Connection connection = getConnection();
        // establishes a connection to the database using the getConnection() method 
        // and assigns it to the 'connection' variable of type Connection
        // uses the existing connection
            Statement statement = connection.createStatement() )
        {
            // createStatement() - creates a Statement object for sending SQL statements to the DB

            statement.execute(createUsersQuery);
            // execute() executes the createTableQuery

            System.out.println("Table 'users' is ready! "); // debug helper :)
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

// NEEDS MORE WORK!
    // create "cards" table
    public void createCardsTable()
    {
        String createCardsQuery = "CREATE TABLE IF NOT EXISTS cards (" +
                                  "card_id INT AUTO_INCREMENT PRIMARY KEY, " +
                                  "user_id INT, " +
                                  "card_type VARCHAR(20) DEFAULT '', " +
                                  "iban VARCHAR(34) UNIQUE DEFAULT '', " +
                                  "currency VARCHAR(3) DEFAULT '', " +
                                  "card_number VARCHAR(17) UNIQUE DEFAULT '', " +
                                  "expiration_date VARCHAR(8) DEFAULT '', " + 
                                  "cvv CHAR(3) DEFAULT '', " +
                                  "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE" + 
                                  // user_id in cards must match an existing user_id in users (ensures referential integrity)
                                  // "ON DELETE CASCADE automatically deletes all cards when the associated user is deleted"
                                  ");";
        
        try ( //Connection connection = getConnection(); 
            Statement statement = connection.createStatement() )
        {
            statement.execute(createCardsQuery);
            System.out.println("Table 'cards' is ready! ");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    
    public void createTransactionsTable()
    {
        String createTransactionsQuery = "CREATE TABLE IF NOT EXISTS transactions (" + 
                                         "tsc_id INT AUTO_INCREMENT PRIMARY KEY, " +
                                         // transaction ID
                                         "card_id INT, " + 
                                         // card id to link each card to transactions
                                         "acc_sum INT DEFAULT 0, " + 
                                         // sum of money on the current card
                                         "tsc_title VARCHAR(50) DEFAULT '', " +
                                         // transaction title 
                                         "tsc_sum INT DEFAULT 0, " +
                                         // sum of money involved in the transaction
                                         "tsc_details VARCHAR(100) DEFAULT '', " +
                                         // transaction details
                                         "FOREIGN KEY (card_id) REFERENCES cards(card_id) ON DELETE CASCADE" +
                                         ");";
    
        try ( Statement statement = connection.createStatement() )
        {
            statement.execute(createTransactionsQuery);
            System.out.println("Table 'transactions' is ready! ");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    // Insert a new user into the 'users' table
    public void insertUserStep1(String email, String username, String password) throws Exception
    {
        String insertUserQuery = "INSERT INTO users (email, username, password) VALUES (?, ?, ?);";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery))
        {
        // prepareStatement() creates a PreparedStatement object for the insertUserQuery
        // safer than Statement because it prevents SQL injection

        // setString(index, value) sets the value of the placeholders (?) in the query 

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);

        // executeUpdate() - executes the SQL statement to insert data
            preparedStatement.executeUpdate();

        // debugging helpers: 
            System.out.println("Inserted a new user: " + email);
            System.out.println("with username: " + username);
            System.out.println("and password: " + password);
        } 
        catch (Exception e) 
        {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    // checks if a user with this email already exists
    public boolean userExists(String email) throws Exception
    {
        String checkQuery = "SELECT COUNT(*) FROM users WHERE email = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery))
        {
            preparedStatement.setString(1, email);

            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                return resultSet.next() && resultSet.getInt(1) > 0;
                // returns true if the user already exists in the database

                // resultSet.next() - moves the cursor to the next row in the result set
                
            }
        }
    }

    public void updateCardType(String email, String card_type) 
    {
        String updateQuery = "UPDATE users SET card_type = ? WHERE email = ?";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(updateQuery) ) 
        {
            preparedStatement.setString(1, card_type);
            preparedStatement.setString(2, email);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0)
            {
                System.out.println("Inserted a new card type: " + card_type);
            }
            else
            {
                System.out.println("no user found with email " + email);
            } 
                
        } 
        catch (Exception e) 
        {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void updateBkAccDataIdentity(String email, String first_name, String last_name, String phone_nr, String country)
    {
        String updateQuery = "UPDATE users SET first_name = ?, last_name = ?, phone_number = ?, country = ? WHERE email = ?";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(updateQuery) ) 
        {
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, phone_nr);
            preparedStatement.setString(4, country);
            preparedStatement.setString(5, email);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0)
            {
                System.out.println("updated identity data");
                System.out.println("user " + email + " is named " + first_name + " " + last_name);
                System.out.println("they live in: " + country + " and have the phone: " + phone_nr);
            }
            else
            {
                System.out.println("no user found with email " + email);
            } 
                
        } 
        catch (Exception e) 
        {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void updateBkAccAddress(String email, String city, String county, String street_name, String home_nr)
    {
        String updateQuery = "UPDATE users SET city = ?, county = ?, street_name = ?, home_number = ? WHERE email = ?";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(updateQuery) ) 
        {
            preparedStatement.setString(1, city);
            preparedStatement.setString(2, county);
            preparedStatement.setString(3, street_name);
            preparedStatement.setString(4, home_nr);
            preparedStatement.setString(5, email);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0)
            {
                System.out.println("updated address data");
                System.out.println("user: " + email + " lives in: " + city + " " + county);
                System.out.println("on " + street_name + " at " + home_nr);
            }
            else
            {
                System.out.println("no user found with email " + email);
            } 
                
        } 
        catch (Exception e) 
        {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void dataCompleted(String email)
    {
        String updateQuery = "UPDATE users SET status = 'completed' WHERE email = ?";
        
        try (//Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) 
        {
            preparedStatement.setString(1, email);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0)
            {
                System.out.println("data completed!!");
            }
            else
            {
                System.out.println("no user found with email " + email);
            } 
                
        } 
        catch (Exception e) 
        {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void insertCardInfo(int user_ID, String cardType, String IBAN, String currency, String card_nr, String exp_date, String cvv)
    {
        String insertCardQuery = "INSERT INTO cards (user_id, card_type, iban, currency, card_number, expiration_date, cvv) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(insertCardQuery) ) 
        {
            preparedStatement.setInt(1, user_ID);
            preparedStatement.setString(2, cardType);
            preparedStatement.setString(3, IBAN);
            preparedStatement.setString(4, currency);
            preparedStatement.setString(5, card_nr);
            preparedStatement.setString(6, exp_date);
            preparedStatement.setString(7, cvv);
            
            preparedStatement.executeUpdate();
            
            // debugging helpers:
            System.out.println("inserted a new " + cardType + " card!");
            System.out.println("IBAN: " + IBAN);
            System.out.println("card nr.: " + card_nr);
            System.out.println("CVV: " + cvv); 
            System.out.println("expiration date: " + exp_date);
        } 
        catch (Exception e) 
        {
            System.out.println("Error inserting card: " + e.getMessage());
        }
    }

    public int getUserID(String email)
    {
        String getterQuery = "SELECT user_id FROM users WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getterQuery))
        {
            preparedStatement.setString(1, email);

            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                if(resultSet.next())
                {
                    int id = resultSet.getInt("user_id");
                    System.out.println("ID-ul este: " + id);
                    return id;
                }
                else 
                {
                    System.out.println("no user found with the introduced email");
                    return 0;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Eroare: " + e.getMessage());
            return 0;
        }
    }

    public String getCardType(String email)
    {
        String getterQuery = "SELECT card_type FROM users WHERE email = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(getterQuery))
        {
            preparedStatement.setString(1, email);

            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                if(resultSet.next())
                {
                    String CT = resultSet.getString("card_type");
                    System.out.println("c. type is: " + CT);
                    return CT;
                }
                else 
                {
                    System.out.println("no user found with the introduced email");
                    return "";
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Eroare: " + e.getMessage());
            return "";
        }
    }

    public ArrayList<String> getIBANs()
    {
        String getQuery = "SELECT iban FROM cards";

        ArrayList<String> ibans = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            ResultSet resultSet = preparedStatement.executeQuery() )
        {
            while( resultSet.next() )
            {
                ibans.add( resultSet.getString("iban") );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return ibans;
    }

/* 
    // Retrieve all users from the 'users' table
    public void listUsers() {
        String selectAllQuery = "SELECT * FROM users";
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllQuery)) {

            System.out.println("Current users in the database:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                System.out.printf("ID: %d, Username: %s, Password: %s%n", id, user, pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete the 'users' table (optional for cleanup)
    public void deleteUsersTable() {
        String dropTableQuery = "DROP TABLE IF EXISTS users";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(dropTableQuery);
            System.out.println("Table 'users' has been deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
}
