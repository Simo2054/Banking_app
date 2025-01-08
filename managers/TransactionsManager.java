package managers;

import pages.*;
import Card_types.*;

import java.io.*;
import java.util.*;


public class TransactionsManager 
{
    private List<Product> productList = new ArrayList<>();

    public void addTransaction(String name, double price, String description)
    {
        Product product = new Product(name, price, description);
        productList.add(product);
    }

    public List<Product> getTransactions()
    {
        return new ArrayList<>(productList);
        // returns a copy of the productList 
        // to ensure the original list cannot be modified
    }
}
