package Database;

import Item.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dongz
 * editor Katecory
 */
public class DBItemHandler
{
    private final DbConnector dbconnector;
    
    public DBItemHandler()
    {
        dbconnector = new DbConnector();
        
    }
    
    //Get daata from Database for item
    public ItemList getFullDataFromDBForItemList() throws ClassNotFoundException, SQLException
    {
        this.dbconnector.connect();
        String statement = "Select * from grocery_store_project.item order by item_type;";
        Statement fullDataStmt = dbconnector.createStatement();
        ResultSet resultSet = fullDataStmt.executeQuery(statement);
        
        ItemList itemList = new ItemList();
        while (resultSet.next())
                {
                    String id  = resultSet.getNString("item_id");
                    String name = resultSet.getNString("item_nam");
                    String type = resultSet.getNString("item_type");
                    int imprice = resultSet.getInt("item_imprice");
                    int selprice = resultSet.getInt("item_selprice");
                    int quantity = resultSet.getInt("item_quantity");
                    
                    itemList.add(new Item(id, name, type, imprice, selprice, quantity));
                }
        
        this.dbconnector.disconnect();
        return itemList;
    }
    
    //Save data from ItemList to Database(add new and update version)
    public void insertIntoItemListToDB(ItemList itemList) throws ClassNotFoundException, SQLException
    {
        this.dbconnector.connect();
        String statement = 
                "Insert Into grocery_store_project.item "
                + "(item_id, item_nam, item_type, item_imprice, item_selprice, item_quantity) "
                + "values "
                + "(?, ?, ?, ?, ?, ?) "
                + "on duplicate key update "
                + "item_nam = ?, item_type = ?, item_imprice = ?, item_selprice = ?, item_quantity = ?;";
        
        PreparedStatement insertOrUpdateStaffStmt = dbconnector.createPreparedStatement(statement);
        for (Item item : itemList.getItem()) 
        {
            String itemID = item.getId();
            String itemName = item.getName();
            String itemType = item.getType();
            int itemImprice = item.getImprice();
            int itemSelprice = item.getSelprice();
            int itemQuantity = item.getQuantity();
            
            // For insert case
            insertOrUpdateStaffStmt.setString(1, itemID);
            insertOrUpdateStaffStmt.setString(2, itemName);
            insertOrUpdateStaffStmt.setString(3, itemType);
            insertOrUpdateStaffStmt.setInt(4, itemImprice);
            insertOrUpdateStaffStmt.setInt(5, itemSelprice);
            insertOrUpdateStaffStmt.setInt(6, itemQuantity);
            
            // For update case
            insertOrUpdateStaffStmt.setString(7, itemName);
            insertOrUpdateStaffStmt.setString(8, itemType);
            insertOrUpdateStaffStmt.setInt(9, itemImprice);
            insertOrUpdateStaffStmt.setInt(10, itemSelprice);
            insertOrUpdateStaffStmt.setInt(11, itemQuantity);
            
            insertOrUpdateStaffStmt.execute();
        }
        dbconnector.disconnect();
    }
    
    //Save data from ItemList to Database(delete version)
    public void deleteItemInDBFromItemList(ItemList itemlist) throws ClassNotFoundException, SQLException
    {
        dbconnector.connect();
        String statement = "Delete from grocery_store_project.item where item_id not in (";
        
        //Get size of itemlist for ?
        int size = itemlist.getItem().size();
        
        //Create string hold ?
        String stringHoldQM = "";
        for(int i = 0;i < size - 1; i++)
        {
            stringHoldQM = stringHoldQM + "?,";
        }
        
        //update statement
        statement = statement + stringHoldQM + "?);";
        PreparedStatement deleteItemInDB = dbconnector.createPreparedStatement(statement);
        
        //put id for ?
        int index = 1;
        for(Item item : itemlist.getItem()) 
        {
            deleteItemInDB.setString(index, item.getId());
            index++;
        }
        
        deleteItemInDB.executeUpdate();
        this.dbconnector.disconnect();
    }
    
}
