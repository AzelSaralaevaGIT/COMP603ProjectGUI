package shareversityguifinal2;

//Enum with Categories for each Company available on the Share Market
public enum CategoriesEnum
{
    TECHNOLOGY("Technology"),
    EDUCATION("Education"),
    AGRICULTURE("Agriculture"),
    MANUFACTURING("Manufacturing"),
    TRANSPORT("Transport"),
    HEALTHCARE("Healthcare"),
    MEDIA("Media"),
    FOOD("Food"),
    FINANCE("Finance and Banking"),
    TELECOMMUNICATION("Telecommunication");
    
    private String name;
    
    private CategoriesEnum(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
}
