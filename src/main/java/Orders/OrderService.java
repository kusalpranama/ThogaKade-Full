package Orders;

import javafx.collections.ObservableList;
import model.Order;

public interface OrderService {
    ObservableList<Order> getAll();

    boolean addOrder(Order order);

    boolean deleteOrder(String orderID);

    Order searchOrder(String orderID);

    boolean updateOrder(Order order);
}
