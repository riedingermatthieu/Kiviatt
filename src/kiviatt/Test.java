/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiviatt;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author riedinma
 */
public class Test {

    public Test() {
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("V");
        tableModel.addColumn("Vmin");
        tableModel.addColumn("Vmax");
        
        tableModel.addRow(new Object[] {"axe1", 5, 1, 10});
        tableModel.addRow(new Object[] {"axe2", 50, 10, 100});
        tableModel.addRow(new Object[] {"axe3", 50, 2, 1000});
        tableModel.addRow(new Object[] {"axe4", 25, 2, 100});
        tableModel.addRow(new Object[] {"axe5", 58, 2, 100});
        tableModel.addRow(new Object[] {"axe6", 75, 2, 100});
        
        Kiviatt kiviatt = new Kiviatt(tableModel);
        
        JTable table = new JTable(tableModel);
        JPanel panel = new JPanel();
        
        panel.setLayout(new BorderLayout());
        
        panel.add(kiviatt, BorderLayout.CENTER);
        panel.add(table, BorderLayout.SOUTH);
        
        f.setContentPane(panel);
        f.pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Test();

    }

}
