package managers;

public class User 
{
    private String email;
    private String password;
    private String first_name;
    private String last_name;

    public User (String mail, String pass, String fnm, String lnm)
    {
        this.email = mail;
        this.password = pass;
        this.first_name = fnm;
        this.last_name = lnm;
    }

    // getters

    public String getEmail()
    {
        return email;
    }

    public String getPass()
    {
        return password;
    }

    public String getFnm()
    {
        return first_name;
    }

    public String getLnm()
    {
        return last_name;
    }
    
}
