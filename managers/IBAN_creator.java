package managers;

import pages.*;
import Card_types.*;

import java.io.*;
import java.util.*;
import java.math.*;

public class IBAN_creator 
{
    public HashMap<String, String> countries = new HashMap<>();
    // a map to keep all the countries and their specific country code
    // the key will be the country name 
    // the values will be the country code

    public ArrayList<String> IBANs = new ArrayList<>();
    // a String ArrayList will be used to keep IBANs found in the database
    // it's utilitary and will be used to get the account number
    // of each IBAN to ensure each account number generated is unique

    private final String filePath = "user_files/country_codes.txt";
    // the file used to store the countries with specific country codes for each

    private final String cardInfoFilePath = "user_files/card_info.txt";
    // the file used to store the card information for each user

    public IBAN_creator() throws IOException
    {
        loadCountries();
        loadIBANs();
    }

    // method to load countries with their specific countyr code 
    // from file into the map
    private void loadCountries() throws IOException
    {
        File file = new File(filePath);
        if(file.exists())
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    String[] parts = line.split(",");
                    if(parts.length == 2)
                    {
                        String country = parts[0];
                        String country_code = parts[1];
                        countries.put(country, country_code);
                    }
                }
            }
        }
    }

    // method to load each IBAN found in the file into the ArrayList
    private void loadIBANs() throws IOException
    {
        File file = new File(cardInfoFilePath);
        if(file.exists())
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    String[] parts = line.split(",");
                    if(parts.length == 5)
                    {
                        String IBAN = parts[4];
                        IBANs.add(IBAN);
                    }
                }
            }
        }
    }
    
    // method to return the specific country code for the given country
    private String getCCode(String country)
    {
        // iterating through the map's keys to find the key that is the country introduced
        for(String i: countries.keySet())
        {
            if(i == country)
            {
                return countries.get(i);
            }
        }
        // if the country introduced by the user is not found in the list
        // an empty string is returned
        return "";
    }

    // method to get the bank identifier part of iban for each country
    // the bank identifier for each country will be the country code 
    // letters as ASCII values concatenated
    private String bankID(String CCode)
    {
        StringBuilder asciiString = new StringBuilder();

        // loop through each character in the country code
        for(int i = 0; i < CCode.length(); i++)
        {
            // get the ASCII value of the character and append it to the result
            int asciiValue = (int) CCode.charAt(i);
            asciiString.append(asciiValue);
        }
        
        // return the concatenated ASCII values as a string
        return asciiString.toString();
    }

    // method to generate a random account number of a specific length
    private String generateAccountNumber(int length)
    {
        //int length = 12; // length of account number
        Random random = new Random();

        StringBuilder accNr = new StringBuilder();

        for(int i = 0; i < length; i++)
        {
            // generate random digits from 0 to 9
            int digit = random.nextInt(10);
            accNr.append(digit);
        }

        return accNr.toString();
    }

    // method to ensure unicity of account number
    // checks if the given account number
    private boolean isUnique(String accNum)
    {   
        // iterating through the ArrayList containing the IBANs from the file
        for(String i: IBANs)
        {
            // since the values from the ArrayList are entire IBANs, 
            // we only take in consideration the account number, which starts from index 8
            String accNrSubString = i.substring(8);
            // checking if the substrings are equal
            if(accNum.equals(accNrSubString))
            {
                // value is not unique
                return false;
            }
        }
        return true; // account number is unique since there were no matches found
    }

    // method to convert letters to numbers 
    // (A = 10, B = 11, ... , Z = 35)
    // will be used to calculate the check digits
    private String convertLettersToNumbers(String input)
    {
        StringBuilder numericString = new StringBuilder();

        for(char ch: input.toCharArray())
        {
            if(Character.isLetter(ch))
            {
                int numericValue = Character.toUpperCase(ch) - 'A' + 10;
                numericString.append(numericValue);
            }
            else
            {
                numericString.append(ch);
            }          
        }
        return numericString.toString();
    }

    // method to calculate IBAN check digits using modulo 97 rule
    private int calculateCheckDigits(String numericIBAN)
    {
        // conversion from string to biginteger
        BigInteger ibanNumber = new BigInteger(numericIBAN);

        // calculus:
        // basically ibanNumber % 97 :)
        BigInteger mod97 = ibanNumber.mod(BigInteger.valueOf(97));

        // returning the actual check digits calculated as an int value
        return 98 - mod97.intValue();
    }

    // maybe make this a String method and return IBAN as a string :)
    public String IBAN_composer(String country_chosen)
    {
        // 1. get the country code
        String countryCode = getCCode(country_chosen);

        // 2. get the bank identifier
        String bank_identifier = bankID(countryCode);

        // 3. get the account number
        String account_number = generateAccountNumber(12);
        // we are going with an account number of 12 cahracters
        // as a standard for IBAN format

        // checking if the generated account number is unique
        while(!isUnique(account_number))
        // value is unique -> true (no need to regenerate)
        // value is not unique -> false (a need to regenerate)
        // now we negate :)
        {
            // regenerate
            account_number = generateAccountNumber(12);
        }

        // 4. combining the bank identifier and the account number
        String BIdAccNr = bank_identifier + account_number;

        // 5. moving the country code and "00" (provisory check digits) 
        // to the end
        String rearranged = BIdAccNr + countryCode + "00";

        // 6. converting the letters to numbers
        String numbericIBAN = convertLettersToNumbers(rearranged);

        // 7. calculating the check digits using the modulo 97 rule
        int check_digits = calculateCheckDigits(numbericIBAN);
        String checkDigitsString = Integer.toString(check_digits);

        // 8. assemble everything
        String IBAN = countryCode + checkDigitsString + bank_identifier + account_number;

        // and return the result
        return IBAN;
    }


}
