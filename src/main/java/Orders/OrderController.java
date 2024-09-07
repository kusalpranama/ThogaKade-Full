package Orders;

import Util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController implements OrderService{
    @Override
    public ObservableList<Order> getAll() {
         ObservableList<Order> orderObservableList = FXCollections.observableArrayList();

         String SQL="SELECT * FROM orders";

        try {
           ResultSet resultSet= CrudUtil.execute(SQL);
           while (resultSet.next()){
               Order order=new Order(
                       resultSet.getString(1),
                       resultSet.getDate(2).toLocalDate(),
                       resultSet.getString(3)
               );
               orderObservableList.add(order);
           }
           return orderObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addOrder(Order order) {
        String SQL="INSERT INTO orders VALUES (?,?,?)";
        try {
           return CrudUtil.execute(SQL,
                    order.getOrderID(),
                    order.getOrderDate(),
                    order.getCustID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteOrder(String orderID) {
        String SQL="DELETE FROM orders WHERE OrderID=?";
        try {
            return CrudUtil.execute(SQL,
                    orderID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order searchOrder(String orderID) {
        String SQL="SELECT * FROM orders WHERE OrderID=?";

        try {
            ResultSet resultSet=CrudUtil.execute(SQL,orderID);
            while (resultSet.next()){
                return new Order(
                        resultSet.getString(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getString(3));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updateOrder(Order order) {
        String SQL="UPDATE orders SET OrderDate=?,CustID=? WHERE OrderID=? ";
        try {
          return   CrudUtil.execute(SQL,
                    order.getOrderDate(),
                    order.getCustID(),
                    order.getOrderID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
