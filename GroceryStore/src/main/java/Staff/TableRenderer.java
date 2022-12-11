package Staff;

import java.util.Collection;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lance
 * @editor Katecory
 */
public class TableRenderer
{
    public void renderToTable(DefaultTableModel tableModel, Collection<Staff> staffCollection)
    {
        tableModel.setRowCount(0);
        
        for (Staff staff : staffCollection)
        {
            Object[] row = new Object[]
            {
                staff.GetID(), staff.GetName(), staff.GetRole(), staff.GetPhoneNum()
            };
            tableModel.addRow(row);
        }
        tableModel.fireTableDataChanged();
    }
}
