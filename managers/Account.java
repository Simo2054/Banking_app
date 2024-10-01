package managers;

import pages.*;
import Card_types.*;

public class Account
{
    private String email;
    private String fs_name;
    private String ls_name;
    private String tel_nr;
    private String country;
    private String city;
    private String county;
    private String street_nm;
    private String home_nr;
    
    public Account(String email, String fs_name, String ls_name, String tel_nr, String country, 
                    String city, String county, String street_nm, String home_nr)
    {
        this.email = email;
        this.fs_name = fs_name;
        this.ls_name = ls_name;
        this.tel_nr = tel_nr;
        this.country = country;
        this.city = city;
        this.county = county;
        this.street_nm = street_nm;
        this.home_nr = home_nr;
    }

    public String getEmail()
    {
        return email;
    }
    public String getFsNm()
    {
        return fs_name;
    }
    public String getLsNm()
    {
        return ls_name;
    }
    public String getTelNr()
    {
        return tel_nr;
    }
    public String getCountry()
    {
        return country;
    }
    public String getCity()
    {
        return city;
    }
    public String getCounty()
    {
        return county;
    }
    public String getStreetName()
    {
        return street_nm;
    }
    public String getHomeNr()
    {
        return home_nr;
    }
}