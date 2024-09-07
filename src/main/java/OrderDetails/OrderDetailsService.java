package OrderDetails;

import javafx.collections.ObservableList;
import model.OrderDetail;

public interface OrderDetailsService {
        ObservableList<OrderDetail> getAll();

        boolean addOrderDetail(OrderDetail orderDetail);

        boolean deleteOrderDetail(String orderID);

        OrderDetail searchOrderDetail(String orderID);

        boolean updateOrderDetail(OrderDetail orderDetail);
}
