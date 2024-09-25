class PrepaidCardType extends TypesOfCards
{
    public PrepaidCardType(MainFrame mainFrame)
    {
        super(mainFrame);
        addTitle("Prepaid Card");
        addInstructions("Prepaid cards are loaded with money in advance, and you can only spend the amount available on the card.\n" +
        "> Purpose: Useful for controlling spending, budgeting, or for people who don't have a bank account.\n" + 
        "> Key features: \n" + 
        " - Not linked to a bank account.\n" + 
        " - No borrowing or credit involved.\n" +
        " - Can be used for purchases and ATM withdrawals.\n");
    }

    @Override
    public String getTypeName()
    {
        return "PrepaidCard";
    }
}