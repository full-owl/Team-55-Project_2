package src.Cashier;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceiptView extends JSplitPane {
    private JButton cancelButton;
    private JButton checkoutButton;
//    private JButton cashButton;
//    private JButton cardButton;
    private JTable table;

    public ReceiptView(ReceiptTableModel model) {
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500,70));
        table.setFillsViewportHeight(true);

        var scrollPane = new JScrollPane(table);


        // Checkout view
        var checkoutView = new JPanel();
        checkoutView.setLayout(new GridLayout(2,2,5,5));

        var subtotal = new JLabel("Subtotal: 0.0");
        model.subtotalLabel = subtotal;
        checkoutView.add(subtotal);
        var total = new JLabel("Total: 0.0");
        model.totalLabel = total;
        checkoutView.add(total);

        var cancelAction = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // System.out.println("Cancel");
                ReceiptTableModel model = (ReceiptTableModel) table.getModel();
                model.clear();
            }
        };
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(cancelAction);
        checkoutView.add(cancelButton);

        var checkoutAction = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // System.out.println("Check out");
                ReceiptTableModel model = (ReceiptTableModel) table.getModel();
                jdbcpostgreSQL.insertOrder(model.data);

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
