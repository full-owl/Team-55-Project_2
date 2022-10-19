package src.Cashier;

import javax.swing.*;

/**
 * Combines the MenuItemsView and ReceiptView into one Pane
 * with MenuItems on the right and Receipt on the left
 */
public class CashierView extends JSplitPane {

    private final MenuItemsView itemsView;
    private final ReceiptView receiptView;

    public CashierView() {
        super();
        itemsView = new MenuItemsView();

        var receiptModel = new ReceiptTableModel();
        receiptView = new ReceiptView(receiptModel);
        itemsView.receiptModel = receiptModel;
        setLeftComponent(itemsView);
        setRightComponent(receiptView);

        setDividerLocation(0.90);
        setResizeWeight(0.9);
    }

    // for manager
    public void startCashierView() {
        var frame = new JFrame();
        var cv = new CashierView();
        frame.add(cv);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
