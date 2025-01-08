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

    public ArrayList<String> IBANs;
    // a String ArrayList will be used to keep IBANs found in the database
    // it's utilitary and will be used to get the account number
    // of each IBAN to ensure each account number generated is unique

    private final String filePath = "user_files/country_codes.txt";
    // the file used to store the countries with specific country codes for each

    public IBAN_creator() throws IOException
    {
        loadCountries();
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
                    if(parts.length == 3)
                    {
                        String country = parts[0];
                        String country_code = parts[1];
                        countries.put(country, country_code);
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
            if(i.equals(country))
            {
                // return the coresponding value
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
    private boolean isUnique(String accNum)
    {   
        try 
        {
            IBANs = sechiule.DatabaseManager.getInstance().getIBANs();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

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

    // a String method that returns the created IBAN as a string 
    public String IBAN_composer(String country_chosen)
    {
        // 1. get the country code
        String countryCode = getCCode(country_chosen);

        // 2. get the bank identifier
        String bank_identifier = bankID(countryCode);

        // 3. get the account number
        String account_number = generateAccountNumber(12);
        // we are going with an account number of 12 characters
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

    // -----------------------------------------------------------------------------------------
    // these will be aspects of the card 

    // card number is usually of 16 digit length
    // issuer identification number (IIN) : 6 digits - mastercard starts with 5, Visa with 4
    // account number: 6 to 12 digits, depending on the lenghth of the card number
    // check digit - Luhn algorithm

    // iin: 5
    // accnr: 10
    // check digit: 1

    // method to calculate the check digit using Luhn algorithm
    private int calcCheckDigit(String numeric_cnr)
    {
        int sum = 0;
        boolean doubleDigit = true;

        // traverse the numebr from right to left
        for(int i = numeric_cnr.length() - 1; i >= 0; i--)
        {
            int digit = Character.getNumericValue(numeric_cnr.charAt(i));

            if(doubleDigit)
            {
                digit = digit * 2; // double the digit
                if(digit > 9)
                    digit = digit - 9; // substract 9 if the result is greater than 9
                    // equivalent to adding the two digits of the result
            }

            sum = sum + digit; // add the digit to the sum
            doubleDigit = !doubleDigit; // toggling the doubleDigit flag 
                                        //to only calculate every second digit
        }

        // how the check digit is calculated after all steps
        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit;
    }
    
    // method to generate the card number
    public String generateCardNumber(String country_chosen, String IBAN)
    {
        // 1. get the country code
        String countryCode = getCCode(country_chosen);

        // 2. get the bank identifier
        String bank_identifier = bankID(countryCode);

        // 3. place number 5 in front of the bank identifier to create IIN for the card
        // number 5 will be used because it's the standard for mastercard
        String IIN = "5" + bank_identifier;

        // 4. get 10 digits from the already generated account number
        String acc_nr = IBAN.substring(8, 18); // getting the account number calculated for the iban
        // we only get characters starting from index 8 (inclusive) to index 18 (exclusive)
        // only selecting 10 characters from the generated account number
        
        // 5. concatenate the digits obtained
        String concatenated = IIN + acc_nr;
        // we sould have a 15 digit string until now

        // 6. calculating the check digit using Luhn's algorithm
        int check_digit = calcCheckDigit(concatenated);
        // and convert to string
        String checkDigitString = Integer.toString(check_digit);

        // 7. assemble everything
        String CardNumber = concatenated + checkDigitString;
        return CardNumber;
    }
}
