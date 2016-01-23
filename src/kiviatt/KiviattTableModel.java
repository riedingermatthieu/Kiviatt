/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiviatt;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author riedinma
 */
public class KiviattTableModel implements TableModel {

    public List<KiviattAxe> listAxes;

    @Override
    public int getRowCount() {
        return 4;
    }

    @Override
    public int getColumnCount() {
        return listAxes.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return listAxes.get(columnIndex).getNom();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (rowIndex) {
            case 0:
                return listAxes.get(columnIndex).getNom();
            case 1:
                return listAxes.get(columnIndex).getValeur();
            case 2:
                return listAxes.get(columnIndex).getvMin();
            case 3:
                return listAxes.get(columnIndex).getvMax();
            default:
                System.out.println("rowNumber invalid " + rowIndex);
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex
    ) {
        
    }

    @Override
    public void addTableModelListener(TableModelListener l
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener l
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
