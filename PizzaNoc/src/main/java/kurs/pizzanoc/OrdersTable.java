package kurs.pizzanoc;
//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class OrdersTable extends JPanel implements ActionListener {
    private JTable table;
    public TableModel model;

    public OrdersTable()
    {
        model = new TableModel();
        table = new JTable(model);

        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);

        table.setGridColor(Color.BLACK);
        table.setBackground(Color.WHITE);

        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);


        JScrollPane tableScrollPane = new JScrollPane(table);

        add(tableScrollPane);
    }

    void rep() {
        //OrdersTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }

    class TableModel extends AbstractTableModel {
        String[] columnNames = {"ID", "DATA", "STATUS", "FULLPRICE", "CARD?", "PHONE_NUMBER"};
        Object[][] data = {
                {"1", "02.02.2017", "przyjęte", "150", "TAK", "530204915"},
                {"2", "02.02.2017", "wysłane", "15", "TAK", "952204915"},
                {"3", "02.02.2017", "odmówione", "25", "NIE", "742204915"},
                {"4", "02.02.2017", "odebrane", "48", "TAK", "450204915"},
                {"5", "02.02.2017", "nieodebrane", "18", "NIE", "530134515"},
                {"2", "02.02.2017", "wysłane", "15", "TAK", "952204915"},
                {"3", "02.02.2017", "odmówione", "25", "NIE", "742204915"},
                {"4", "02.02.2017", "odebrane", "48", "TAK", "450204915"},
                {"2", "02.02.2017", "wysłane", "15", "TAK", "952204915"},
                {"3", "02.02.2017", "odmówione", "25", "NIE", "742204915"},
                {"4", "02.02.2017", "odebrane", "48", "TAK", "450204915"},
                {"2", "02.02.2017", "wysłane", "15", "TAK", "952204915"},
                {"3", "02.02.2017", "odmówione", "25", "NIE", "742204915"},
                {"4", "02.02.2017", "odebrane", "48", "TAK", "450204915"},
                {"2", "02.02.2017", "wysłane", "15", "TAK", "952204915"},
                {"3", "02.02.2017", "odmówione", "25", "NIE", "742204915"},
                {"4", "02.02.2017", "odebrane", "48", "TAK", "450204915"},
                {"2", "02.02.2017", "wysłane", "15", "TAK", "952204915"},
                {"3", "02.02.2017", "odmówione", "25", "NIE", "742204915"},
                {"4", "02.02.2017", "odebrane", "48", "TAK", "450204915"},
                {"2", "02.02.2017", "wysłane", "15", "TAK", "952204915"},
                {"3", "02.02.2017", "odmówione", "25", "NIE", "742204915"},
                {"4", "02.02.2017", "odebrane", "48", "TAK", "450204915"},
                {"2", "02.02.2017", "wysłane", "15", "TAK", "952204915"},
                {"3", "02.02.2017", "odmówione", "25", "NIE", "742204915"},
                {"4", "02.02.2017", "odebrane", "48", "TAK", "450204915"},
                {"2", "02.02.2017", "wysłane", "15", "TAK", "952204915"},
                {"3", "02.02.2017", "odmówione", "25", "NIE", "742204915"},
                {"4", "02.02.2017", "odebrane", "48", "TAK", "450204915"},
        };

        public void setData(Object[][] data) {
            this.data = data;
            fireTableDataChanged();
        }

        @Override
        public Class<?> getColumnClass(int column) {
            return super.getColumnClass(column);
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public Object getValueAt(int row, int column) {
            return data[row][column];
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 0;
        }

        @Override
        public void setValueAt(Object value, int row, int column) {
            data[row][column] = value;
        }
    }
}