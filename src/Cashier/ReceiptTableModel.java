package src.Cashier;

import src.OrderItems;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

class ReceiptTableModel extends AbstractTableModel {
    private String[] cols = {"Item", "Price"};
    private Vector<OrderItems> data = new Vector<>();
//        private Object[][] data = {
//                {"Bowl with White Steamed Rice, Orange Chicken", "$8.50", false},
//                {"Medium Dr. Pepper", "$2.50", false},
//                {"Egg Rolls 6 piece", "$2.00", false},
//        };


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
    }
}
