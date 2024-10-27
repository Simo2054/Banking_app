package managers;

import pages.*;
import Card_types.*;

public class AccSum 
{
    private String IBAN;
    private String first_name;
    private String last_name;
    private int sum;
    private String currency;

    public AccSum(String IBAN, String fs_name, String ls_name,
                int sum, String currency)
    {
        this.IBAN = IBAN;
        this.first_name = fs_name;
        this.last_name = ls_name;
        this.sum = sum;
        this.currency = currency;
    }

    public String getIban()
    {
        return IBAN;
    }
    public String getFsNm()
    {
        return first_name;
    }
    public String getLsNm()
    {
        return last_name;
    }
    public int getSum()
    {
        return sum;
    }
    public String getCurrency()
    {
        return currency;
    }
}
