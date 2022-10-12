package src.Cashier;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

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
    }

    public static void main(String[] args) {
        var frame = new JFrame();
        var cv = new ReceiptView();
        frame.add(cv);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
//// GUI initializer generated by IntelliJ IDEA GUI Designer
//// >>> IMPORTANT!! <<<
//// DO NOT EDIT OR ADD ANY CODE HERE!
//        $$$setupUI$$$();
//    }
//
//    /**
//     * Method generated by IntelliJ IDEA GUI Designer
//     * >>> IMPORTANT!! <<<
//     * DO NOT edit this method OR call it in your code!
//     *
//     * @noinspection ALL
//     */
//    private void $$$setupUI$$$() {
//        createUIComponents();
//        panel1 = new JPanel();
//        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
//        splitPane = new JSplitPane();
//        splitPane.setOrientation(0);
//        panel1.add(splitPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
//        final JPanel panel2 = new JPanel();
//        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), 5, 5, true, true));
//        splitPane.setRightComponent(panel2);
//        panel2.setBorder(BorderFactory.createTitledBorder(null, "Checkout", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
//        cancelButton = new JButton();
//        cancelButton.setText("Cancel");
//        panel2.add(cancelButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        cashButton = new JButton();
//        cashButton.setText("Cash");
//        panel2.add(cashButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        cardButton = new JButton();
//        cardButton.setText("Card");
//        panel2.add(cardButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        final JScrollPane scrollPane1 = new JScrollPane();
//        splitPane.setLeftComponent(scrollPane1);
//        scrollPane1.setViewportView(recieptTable);
//    }
//
//    /**
//     * @noinspection ALL
//     */
//    public JComponent $$$getRootComponent$$$() {
//        return panel1;
//    }
//
//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }
}