package src.Cashier;

import javax.swing.*;
import java.awt.*;

public class ReceiptView extends JSplitPane {
    private JButton cancelButton;
    private JButton cashButton;
    private JButton cardButton;

    public ReceiptView(ReceiptTableModel model) {
        var table = new JTable(model);
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

    public static void main(String[] args) {
        var frame = new JFrame();
        var cv = new ReceiptView(new ReceiptTableModel());
        frame.add(cv);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
