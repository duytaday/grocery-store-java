package Database;

import Staff.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Katecory
 */
public class DBStaffHandler
{
    private final DbConnector dbconnector;
    
    public DBStaffHandler()
    {
        dbconnector = new DbConnector();
        
    }
    
    //Get data from Database for Staff
    public StaffList getFullDataFromDBForStaffList() throws ClassNotFoundException, SQLException
    {
        this.dbconnector.connect();
        
        String statement = "Select * from grocery_store_project.staff order by stf_nam";
        Statement fullDataStmt = dbconnector.createStatement();
        ResultSet resultSet = fullDataStmt.executeQuery(statement);
        
        StaffList staffList = new StaffList();
        while(resultSet.next())
        {
            int intId = resultSet.getInt("stf_id");
            String name = resultSet.getNString("stf_nam");
            String role = resultSet.getNString("stf_role");
            String phonenum = resultSet.getNString("stf_phonenum");
            
            String id = Integer.toString(intId);
            
            staffList.Add(new Staff(id, name, role, phonenum));
        }
        
        this.dbconnector.disconnect();
        return staffList;
    }
    
    //Get data from Database for Item
    
    
    //Save data from StaffList to Database(add new and update version)
    public void insertIntoStaffListToDB(StaffList staffList) throws ClassNotFoundException, SQLException
    {
        this.dbconnector.connect();
        String statement = 
                "Insert Into grocery_store_project.staff "
                + "(stf_id, stf_nam, stf_role, stf_phonenum) "
                + "values "
                + "(?, ?, ?, ?) "
                + "on duplicate key update "
                + "stf_nam = ?, stf_role = ?, stf_phonenum = ?;";
        
        PreparedStatement insertOrUpdateStaffStmt = dbconnector.createPreparedStatement(statement);
        for (Staff staff : staffList.getStaffs()) 
        {
            int staffID_int = Integer.parseInt(staff.GetID());
            String staffName = staff.GetName();
            String staffRole = staff.GetRole();
            String staffPhoneNum = staff.GetPhoneNum();
            
            // For insert case
            insertOrUpdateStaffStmt.setInt(1, staffID_int);
            insertOrUpdateStaffStmt.setString(2, staffName);
            insertOrUpdateStaffStmt.setString(3, staffRole);
            insertOrUpdateStaffStmt.setString(4, staffPhoneNum);
            
            // For update case
            insertOrUpdateStaffStmt.setString(5, staffName);
            insertOrUpdateStaffStmt.setString(6, staffRole);
            insertOrUpdateStaffStmt.setString(7, staffPhoneNum);
            
            insertOrUpdateStaffStmt.execute();
        }
        dbconnector.disconnect();
    }
    
    //Save data from StaffList to Database(delete version)
    public void deleteStaffInDBFromStaffList(StaffList stafflist) throws ClassNotFoundException, SQLException
    {
        dbconnector.connect();
        String statement = "Delete from grocery_store_project.staff where stf_id not in (";
        
        //Get size of stafflist for ?
        int size = stafflist.getStaffs().size();
        
        //Create string hold ?
        String stringHoldQM = "";
        for(int i = 0;i < size - 1; i++)
        {
            stringHoldQM = stringHoldQM + "?,";
        }
        
        //update statement
        statement = statement + stringHoldQM + "?);";
        PreparedStatement deleteStaffInDB = dbconnector.createPreparedStatement(statement);
        
        //put id for ?
        int index = 1;
        for(Staff staff : stafflist.getStaffs()) 
        {
            int staff_id = Integer.parseInt(staff.GetID());
            deleteStaffInDB.setInt(index, staff_id);
            index++;
        }
        
        deleteStaffInDB.executeUpdate();
        this.dbconnector.disconnect();
    }
}
