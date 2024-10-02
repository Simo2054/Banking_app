package managers;

import pages.*;
import Card_types.*;

import java.io.*;
import java.util.*;

public class AdressManager 
{
    // Nested map structure: Country -> City -> List of Counties
    private Map<String, Map<String, List<String> > > addressData = new HashMap<>();
    // outer Map's key is String (country name)
    // the value is another Map
    // inner Map's key is String (city name)
    // the value is List<String> that contains the counties/districts for that city

    private final String filePath = "user_files/locations_available.txt";
    // the file with all countries, cities and counties/districts available

    // Constructor that takes the file path and loads data from it
    public AdressManager() 
    {
        try 
        {
            loadDataFromFile(); // method to load data from file on initialization
        } 
        catch (IOException e) 
        {
            System.out.println("Error trying to read from file!");
            e.printStackTrace();
        }
    }

    // Method to load data from the file
    private void loadDataFromFile() throws IOException 
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) 
        {
            // Split each line into country, city, county
            String[] parts = line.split(":");
            if (parts.length == 3) // only consider valid the lines with country, city, county
            {
                String country = parts[0];
                String city = parts[1];
                String county = parts[2];

                // Add to the address data map
                addAddress(country, city, county);
            }
        }

        reader.close(); // closing the file after reading all the lines to free up system resources
    }

    // Utility method to add data to the map
    private void addAddress(String country, String city, String county) 
    {
        addressData.computeIfAbsent(country, countryKey -> new HashMap<>()) // checks if the country already exists in the outer map
                                                                // if not, creates a new exntry for the country with an empty city map
                    .computeIfAbsent(city, cityKey -> new ArrayList<>()) // checks if the city already exists for the given country
                                                                // if not, it creates a new entry for the city with an empty county map
                    .add(county); // adds the county to the list of counties for the given city

    // key is a placeholder variable representing the key being checked in the map
    // and lambda expression defines the action to take if the key is absent 
    }

    // Method to get all countries (returns a list of all countries as an array)
    public String[] getCountries() 
    {
        List<String> countriesList = new ArrayList<>(addressData.keySet());
        // adressData.keySet() retrieves all the keys (countries) from the adressData map
        // afterwards, it converts the set of countries into a list before returning it
        return countriesList.toArray(new String[0]);
        // then we convert the list to an array using toArray method
        // using the argument "new String[0]" to specify the type of array
        // java will allocate the right size for the array
    }

    // Method to get cities by country (returns a list of all cities for the given country)
    public String[] getCitiesByCountry(String country) 
    {
        Map<String, List<String> > citiesMap = addressData.get(country);
        // adressData.get(country) retrieves the map of cities for the given country

        if (citiesMap == null) 
        {
            return new String[0]; // return an empty array if the country doesn't exist
        }
        List<String> citiesList = new ArrayList<>(citiesMap.keySet());
        return citiesList.toArray(new String[0]); 
    }

    // Method to get districts by country and city
    public String[] getDistrictsByCity(String country, String city) 
    {
        Map<String, List<String>> citiesMap = addressData.get(country);

        if (citiesMap == null) 
        {
            return new String[0]; // return an empty array if the country doesn't exist
        }
        List<String> districtsList = citiesMap.get(city);
        // retrieves the list of districts for the specified city
        if (districtsList == null) 
        {
            return new String[0]; // returns an empty array if the city doesn't exist
        }
        return districtsList.toArray(new String[0]); // converts list to array
    }
}
