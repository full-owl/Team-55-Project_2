package src.Cashier;

import src.OrderItems;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.Vector;

public class ReceiptView extends JSplitPane {
    private JButton cancelButton;
    private JButton cashButton;
    private JButton cardButton;

    public ReceiptView() {
        var table = new JTable(new ReceiptTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500,70));
        table.setFillsViewportHeight(true);

        var scrollPane = new JScrollPane(table);


        // Checkout view
        var checkoutView = new JPanel();
        checkoutView.setLayout(new GridLayout(0,3,5,5));
        cancelButton = new JButton("Cancel");
        checkoutView.add(cancelButton);
        cashButton = new JButton("Cash");
        checkoutView.add(cashButton);
        cardButton = new JButton("Card");
        checkoutView.add(cardButton);

        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setLeftComponent(scrollPane);
        setRightComponent(checkoutView);
        setDividerLocation(0.9);
        setResizeWeight(0.9);
    }

    class ReceiptTableModel extends AbstractTableModel {
        private String[] cols = {"Item", "Price", "Selected"};
        private Object[][] data = {
                {"Bowl with White Steamed Rice, Orange Chicken", "$8.50", false},
                {"Medium Dr. Pepper", "$2.50", false},
                {"Egg Rolls 6 piece", "$2.00", false},
        };

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0,columnIndex).getClass();
        }

        @Override
        public int getColumnCount() {
            return cols.length;
        }

        @Override
        public String getColumnName(int column) {
            return  cols[column];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != 1; // price is not editable
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row,col);
        }

        public void addItems(Vector<OrderItems> items) {
            // AAAHHHHHHH
        }
    }

    public static void main(String[] args) {
        var frame = new JFrame();
        var cv = new ReceiptView();
        frame.add(cv);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
