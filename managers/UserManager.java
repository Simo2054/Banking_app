package managers;

import pages.*;
import Card_types.*;
import sechiule.*;

import java.io.*;
import java.util.*;

public class UserManager
{
    public Map<String, String> ccMap = new HashMap<>();
    // a map that will store information about countries and the currency used in those countries
    // the key will be the country
    // the value is the currency

    private final String ccFilePath = "user_files/country_codes.txt";
    // path to the file that will contain information about countries and currencies


    private final String RememberCredPath = "user_files/stored_credentials.txt";
    // the file in which we will store the user credentials for them to not need to
    // authenticate each time the app opens


    public UserManager() throws IOException 
    {
        loadCountries();
    }

    private void loadCountries() throws IOException
    {
        File file = new File(ccFilePath);
        if (file.exists()) 
        {
            // using BufferedReader because we are reading a line at a time
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = reader.readLine()) != null) // reading line by line
                {
                    String[] parts = line.split(",");// is splits each line into parts using split(",")
                    if (parts.length == 3) // Only consider valid entries with country, country code, currency
                    {   
                        String country = parts[0];
                        String currency = parts[2];
                        ccMap.put(country, currency);
                    }
                }
            }
        }
    }

    public String chooseCurrency(String chosen_country)
    {
        // iterating through the map's keys to find the key that is the country introduced
        for(String i: ccMap.keySet())
        {
            if(i.equals(chosen_country))
            {
                return ccMap.get(i);
            }
        }
        // if the introduced country is not found in the list
        // an empty string is returned
        return "";
    }

    //---------------------------------------------------------------------------

    // Method to write user credentials into a file if user wants to be remembered by the app
    public void RememberUser(String email, String password)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RememberCredPath)))
        {
            writer.write(email + "," + password);
            // writes user details(email, password) in a single line using a format
            // writing will be done in the file that holds credentials for remembering 
            writer.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


// NEEDS WORK HERE!!
// later used in LoginPage
    public boolean authenticate(String email, String password) throws Exception
    {
        String returnedPass = sechiule.DatabaseManager.getInstance().accountExists(email);

        if(returnedPass.equals(password))
        {
            System.out.println("correct acc");
            return true; 
        }
        else
        {
            System.out.println("wrong acc");
            return false;
        }
    }

// used in SignUpPage
    // checks if the introduced email adress contains a substring domain
    public boolean checkValidMail(String email)
    {
        if(email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@outlook.com"))
        {
            return true; // valid format
        }
        else
        {
            return false; // invalid format
        }
    }

}

