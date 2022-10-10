import java.awt.font.TextHitInfo;

public class OrderItems {
    int orderId;
    String orderType;
    int menuItem1;
    int menuItem2;
    int menuItem3;
    int menuItem4;
    int menuItem5;
    int menuItem6;

    public OrderItems(int orderId, String orderType, int menuItem1, int menuItem2, int menuItem3, int menuItem4, int menuItem5, int menuItem6)
    {
        this.orderId = orderId;
        this.orderType = orderType;
        this.menuItem1 = menuItem1;
        this.menuItem2 = menuItem2;
        this.menuItem3 = menuItem3;
        this.menuItem4 = menuItem4;
        this.menuItem5 = menuItem5;
        this.menuItem6 = menuItem6;
    }
}
