package src.Cashier;

import src.Order;
import src.OrderItems;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

class ReceiptTableModel extends AbstractTableModel {
    private final String[] cols = {"Item", "Price"};
    public Vector<OrderItems> data = new Vector<>();


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

//        @Override
//        public void setValueAt(Object value, int row, int col) {
//            data[row][col] = value;
//            fireTableCellUpdated(row,col);
//        }

    public void addItems(Vector<OrderItems> items) {
        // AAAHHHHHHH
        System.out.println(items);
        data.addAll(items);
        fireTableDataChanged();
    }

    public Order toOrder() {
        // Converts data into an Order
        return null;
    }

    public void clear() {
        data.clear();
        fireTableDataChanged();
    }
}
