// used in TransactionsManager

package managers;

public class Product 
{
    private String tname;
    private double tprice;
    private String tdescription;

    public Product (String name, double price, String description)
    {
        this.tname = name;
        this.tprice = price;
        this.tdescription = description;
    }

    // getters
    public String getName() 
    {
        return tname;
    }

    public double getPrice()
    {
        return tprice;
    } 

    public String getDescription()
    {
        return tdescription;
    }
}
