package Item;

import java.util.Collection;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Katecory
 */
public class TableRenderer
{
    public void renderToTable(DefaultTableModel tableModel, Collection<Item> itemCollection)
    {
        tableModel.setRowCount(0);
        
        for (Item item : itemCollection)
        {
            Object[] row = new Object[]
            {
                item.getId(), item.getName(), item.getType(), item.getImprice(), item.getSelprice(), item.getQuantity()
            };
            tableModel.addRow(row);
        }
        tableModel.fireTableDataChanged();
    }
}