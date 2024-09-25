package managers;

import pages.*;
import Card_types.*;

import java.io.*;
import java.util.*;

public class UserManager
{
    public Map<String, User> userMap = new HashMap<>();
    // a Map is used to store user information
    // the key is the user’s email (String), and the value is a User object 
    // that contains the user’s details (email, name, password)

    private final String filePath = "user_files/user_credentials.txt";
    // the file in which we will store user credentials

    private final String RememberCredPath = "user_files/stored_credentials.txt";
    // the file in which we will store the user credentials for them to not need to
    // authenticate each time the app opens

    public UserManager() throws IOException 
    {
        loadUsers(); // Load users from file on initialization
    }

    // add a new user
    public void addUser(String email, String name, String password) throws IOException
    {
        userMap.put(email, new User(email, name, password));//store User object in the map
        saveUsers(); // save users to file
    }

    // Method to authenticate users
    public boolean authenticate(String email, String password) 
    {
        User user = userMap.get(email); // Get the User object by email
        if (user != null) 
        {
            System.out.println("User found: " + user.getEmail());
            System.out.println("Stored password: " + user.getPassword());
            System.out.println("Input password: " + password);
            System.out.println("result: " + (user.getPassword()).equals(password));
            return user.getPassword().equals(password);
        }
        else
        {
            System.out.println("User not found with email: " + email);
            return false;
        }
    }

    public boolean checkExistentAcc(String email) 
    //checking if an account already exists (by email adress)
    {
        User user = userMap.get(email); // Get the User object by email
        if(user != null)
        {
            System.out.println("User found: " + user.getEmail());
            System.out.println("User introduced: " + email);
            System.out.println("result: " + (user.getEmail()).equals(email));
            return user.getEmail().equals(email);
        }
        else
        {
            System.out.println("User not found with email " + email);
            return false;
        }
    }

    // Method to load users from the file into the map
    private void loadUsers() throws IOException 
    {
        File file = new File(filePath);
        if (file.exists()) 
        {
            // using BufferedReader because we are reading a line at a time
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = reader.readLine()) != null) // reading line by line
                {
                    String[] parts = line.split(",");// is splits each line into parts using split(",")
                    if (parts.length == 3) // Only consider valid entries with email and password
                    {   
                        String email = parts[0];
                        String name = parts[1];
                        String password = parts[2];
                        userMap.put(email, new User(email, name, password)); // Store email, name, password in map
                    }
                }
            }
        }
    }

    // Method to save the current map of users to the file
    private void saveUsers() throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for (User user : userMap.values()) 
            // for each user in the map
            {
                writer.write(user.getEmail() + "," + user.getName() + "," + user.getPassword());
                // writes user details(email, name, password) in a single line using a format
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------

    // Method to write user credentials into a file if user wants to be
    public void RememberUser(String email, String password)
    {
        User user = userMap.get(email);// get the User object by email
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RememberCredPath)))
        {
            writer.write(email + "," + user.getName() + "," + password);
            // writes user details(email, name, password) in a single line using a format
            // writing will be done in the file that holds credentials for remembering 
            writer.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

