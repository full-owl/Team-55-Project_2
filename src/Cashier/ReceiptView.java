package src.Cashier;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GUI that displays the items that are currently being ordered
 * It has a table that shows the items and their prices as well as a section to either cancel or checkout the order
 */
public class ReceiptView extends JSplitPane {
    private final JButton cancelButton;
    private final JButton checkoutButton;

    private JFrame confirm;
    //    private JButton cashButton;
//    private JButton cardButton;
    private final JTable table;

    public ReceiptView(ReceiptTableModel model) {
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        var scrollPane = new JScrollPane(table);


        // Checkout view
        var checkoutView = new JPanel();
        checkoutView.setLayout(new GridLayout(2, 2, 5, 5));

        var subtotal = new JLabel("Subtotal: 0.0");
        model.subtotalLabel = subtotal;
        checkoutView.add(subtotal);
        var total = new JLabel("Total: 0.0");
        model.totalLabel = total;
        checkoutView.add(total);

        var cancelAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // System.out.println("Cancel");
                ReceiptTableModel model = (ReceiptTableModel) table.getModel();
                var a = JOptionPane.showConfirmDialog(confirm, "Do you want to cancel this order?", "Cancel Order", JOptionPane.YES_NO_OPTION);
                System.out.println(a);
                if (a == JOptionPane.YES_OPTION) {
                    model.clear();
                }
            }
        };
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(cancelAction);
        checkoutView.add(cancelButton);

        var checkoutAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // System.out.println("Check out");
                ReceiptTableModel model = (ReceiptTableModel) table.getModel();
                jdbcpostgreSQL.insertOrder(model.data);

                JOptionPane.showInternalMessageDialog(confirm, "Order completed", "Order", JOptionPane.PLAIN_MESSAGE);
                model.clear();
            }
        };
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(checkoutAction);
        checkoutView.add(checkoutButton);
//        cashButton = new JButton("Cash");
//        checkoutView.add(cashButton);
//        cardButton = new JButton("Card");
//        checkoutView.add(cardButton);

        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setLeftComponent(scrollPane);
        setRightComponent(checkoutView);
        setDividerLocation(0.9);
        setResizeWeight(0.9);
    }

    public static void main(String[] args) {
        var frame = new JFrame();
        var cv = new ReceiptView(new ReceiptTableModel());
        frame.add(cv);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
