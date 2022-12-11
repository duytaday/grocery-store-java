package Item;


/**
 *
 * @author dongz
 */
public class Item
{
    private String id ,name, type;
    private int selprice, imprice, quantity;
    
    public Item(){}
    
    
    public Item(String id, String name, String type, int imprice, int selprice, int quantity)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imprice = imprice;
        this.selprice = selprice;
        this.quantity = quantity;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }

    public int getSelprice()
    {
        return selprice;
    }

    public void setSelprice(int selprice)
    {
        this.selprice = selprice;
    }

    public int getImprice()
    {
        return imprice;
    }

    public void setImprice(int imprice)
    {
        this.imprice = imprice;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
            
    
}
