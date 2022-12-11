package Staff;

import java.io.Serializable;

/**
 *
 * @author Lance
 * @Editor Katecory
 */
public class Staff implements Serializable
{
    private String ID, Name, Role, PhoneNum;
    
    public Staff(){}
    
    public Staff (String ID, String Name, String Role, String PhoneNum)
    {
        this.ID = ID;
        this.Name = Name;
        this.Role = Role;
        this.PhoneNum = PhoneNum;
    }
    
    public String GetID()
    {
        return ID;
    }
    
    public String GetName()
    {
        return Name;
    }
    
    public String GetRole()
    {
        return Role;
    }
    
    public String GetPhoneNum()
    {
        return PhoneNum;
    }
    
    public void SetID(String ID)
    {
        this.ID = ID;
    }
    
    public void SetName(String Name)
    {
        this.Name = Name;
    }
    
    public void SetRole(String Role)
    {
        this.Role = Role;
    }
    
    public void SetPhoneNum(String PhoneNum)
    {
        this.PhoneNum = PhoneNum;
    }
}