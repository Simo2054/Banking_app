package managers;

import pages.*;
import Card_types.*;

import java.io.*;
import java.util.*;

public class BankAccount 
{
    public Map<String, AccSum> accMap = new HashMap<>();
    // a map is used to store information about bank account
    // the key will be the IBAN of the associated bank acc
    // the value is an AccSum object that contains
    // information: first name, last name, sum of money, currency

    public Map<String, String> ccMap = new HashMap<>();
    // a map that will store information about countries and the currency used in those countries
    // the key will be the country
    // the value is the currency

    private final String filePath = "user_files/bank_acc_sum.txt";
    // path to the file that will contain information about the bank acc

    private final String ccFilePath = "user_files/country_codes.txt";
    // path to the file that will contain information about countries and currencies

    public BankAccount() throws IOException
    {
        loadAccounts();
        loadCountries();
    }

    private void loadAccounts() throws IOException
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
                    if (parts.length == 5) // Only consider valid entries with IBAN, first name, last name, sum, currency
                    {   
                        String IBAN = parts[0];
                        String first_name = parts[1];
                        String last_name = parts[2];
                        String sum = parts[3];
                        String currency = parts[4];
                        int money = Integer.valueOf(sum);
                        accMap.put(IBAN, new AccSum(IBAN, first_name, last_name, money, currency));
                    }
                }
            }
        }
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

    
}
