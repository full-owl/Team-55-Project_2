package Cashier;

import javax.swing.*;

public class CashierView extends JSplitPane {

    private MenuItemsView itemsView;
    private ReceiptView receiptView;

    public CashierView() {
        super();
        itemsView = new MenuItemsView();
        receiptView = new ReceiptView();

        setLeftComponent(itemsView);
        setRightComponent(receiptView);

        setDividerLocation(0.90);
        setResizeWeight(0.9);
    }

    public static void main(String[] args) {
        var frame = new JFrame();
        var cv = new CashierView();
        frame.add(cv);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
