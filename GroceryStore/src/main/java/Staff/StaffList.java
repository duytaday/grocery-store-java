package Staff;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lance
 * @Editor Katecory
 */
public class StaffList
{
    private final ArrayList<Staff> staffList = new ArrayList<>();
    
    public void Add(Staff staff)
    {
        staffList.add(staff);
    }
    
    public  void updateStaff (Staff staff){
        Staff existedStaff = FindByID(staff.GetID());
        existedStaff.SetName(staff.GetName());
        existedStaff.SetRole(staff.GetRole());
        existedStaff.SetPhoneNum(staff.GetPhoneNum());
    }
    
    public Staff FindByID (String staffID)
    {
        for (Staff staff : staffList) 
        {
            if(staff.GetID().equals(staffID))
            {
                return staff;
            }
        }
        return null;
    }
    
    public boolean DeleteByID (String staffID)
    {
        for (Staff staff : staffList)
        {
            if(staff.GetID().equals(staffID)){
                staffList.remove(staff);
                return true;
            }
        }
        return false;
    }
    
    public List<Staff> getStaffs()
    {
        return staffList;
    }
}