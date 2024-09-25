class DebitCardType extends TypesOfCards
// each card type has its implementation in different classes (for code organization)
{
    public DebitCardType(MainFrame mainFrame)
    {
        super(mainFrame); // pass the MainFrame to the superclass 
        // for invoking the constructor of the parent class and accessing its variables

        // personalized titles and descriptions for each card type
        addTitle("Debit Card");
        addInstructions("this is a debit card ...");
    }

    // since the method is general for each type and it's abstract, it has its implementation in each card type class
    @Override
    public String getTypeName()
    {
        return "DebitCard";
    }
}