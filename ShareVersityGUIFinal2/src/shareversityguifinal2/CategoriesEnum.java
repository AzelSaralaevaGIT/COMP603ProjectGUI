/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

//Enum's with Categories for each Company available on the Share Market
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
