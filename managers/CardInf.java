package managers;

import pages.*;
import Card_types.*;

public class CardInf 
{
    private String email;
    private String card_type;
    private String fs_name;
    private String ls_name;
    private String IBAN;
    private String card_nr;
    private String CVV;
    
    public CardInf(String email, String card_type,
                    String fs_name, String ls_name,
                    String IBAN, String card_nr, String CVV)
    {
        this.email = email;
        this.card_type = card_type;
        this.fs_name = fs_name;
        this.ls_name = ls_name;
        this.IBAN = IBAN;
        this.card_nr = card_nr;
        this.CVV = CVV;
    }

    public String getEmail()
    {
        return email;
    }

    public String getCardType()
    {
        return card_type;
    }

    public String getFsNm()
    {
        return fs_name;
    }

    public String getLsNm()
    {
        return ls_name;
    }

    public String getIban()
    {
        return IBAN;
    }

    public String getCardNr()
    {
        return card_nr;
    }

    public String getCVV()
    {
        return CVV;
    }
}
