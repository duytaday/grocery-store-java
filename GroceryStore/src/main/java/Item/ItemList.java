package Item;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dongz
 * @Editor Katecory
 */
public class ItemList 
{
    ArrayList<Item> itemList = new ArrayList<>();
    
    public void add(Item item)
    {
        itemList.add(item);
    }
    
    public Item FindByID (String itemID)
    {
        for (Item item : itemList) 
        {
            if(item.getId().equals(itemID))
            {
                return item;
            }
        }
        return null;
    }
    
    public void updateItem(Item item)
    {
        Item existedItem = FindByID(item.getId());
        existedItem.setName(item.getName());
        existedItem.setType(item.getType());
        existedItem.setImprice(item.getImprice());
        existedItem.setSelprice(item.getSelprice());
        existedItem.setQuantity(item.getQuantity());
    }
    
    public boolean DeleteByID (String itemID)
    {
        for (Item item : itemList)
        {
            if(item.getId().equals(itemID))
            {
                itemList.remove(item);
                return true;
            }
        }
        return false;
    }
    
    public List<Item> getItem()
    {
        return itemList;
    }
}
