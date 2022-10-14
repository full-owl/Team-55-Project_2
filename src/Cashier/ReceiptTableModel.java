package src.Cashier;

import src.Order;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

class ReceiptTableModel extends AbstractTableModel {
    private final String[] cols = {"Item", "Price"};
    public Vector<OrderItem> data = new Vector<>();
    public double price = 0.0;


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) {
            return data.get(row).toString();
        } else if (col == 1) {
            return data.get(row).getPrice();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // price is not editable
    }

    public void addItems(Vector<OrderItem> items) {
        System.out.println(items);
        data.addAll(items);
        for(var item: items) {
            price += item.getPrice();
        }
        fireTableDataChanged();
    }

    public void clear() {
        data.clear();
        fireTableDataChanged();
    }
}
