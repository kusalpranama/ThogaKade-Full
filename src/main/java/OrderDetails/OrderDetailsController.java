package OrderDetails;

import Util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsController implements OrderDetailsService{

    @Override
    public ObservableList<OrderDetail> getAll() {
         ObservableList<OrderDetail> orderDetailObservableList = FXCollections.observableArrayList();
         String SQL="SELECT * FROM orderdetail";

        try {
             ResultSet resultSet = CrudUtil.execute(SQL);
             while (resultSet.next()){
                 OrderDetail orderDetail=new OrderDetail(
                         resultSet.getString(1),
                         resultSet.getString(2),
                         resultSet.getInt(3),
                         resultSet.getInt(4));

                 orderDetailObservableList.add(orderDetail);
             }
             return orderDetailObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) {
        String SQL="INSERT INTO orderdetail VALUES (?,?,?,?)";

        try {
           return CrudUtil.execute(SQL,
                    orderDetail.getOrderID(),
                    orderDetail.getItemCode(),
                    orderDetail.getOrderQTY(),
                    orderDetail.getDiscount());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteOrderDetail(String orderID) {
        String SQL="DELETE FROM orderdetail WHERE OrderID=?";

        try {
            return CrudUtil.execute(SQL,orderID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public OrderDetail searchOrderDetail(String orderID) {
        String SQL="SELECT * FROM orderdetail WHERE OrderID=?";
        try {
           ResultSet resultSet= CrudUtil.execute(SQL,orderID);
           while (resultSet.next()){
              return new OrderDetail(
                       resultSet.getString(1),
                       resultSet.getString(2),
                       resultSet.getInt(3),
                       resultSet.getInt(4)
               );

           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        String SQL = "UPDATE orderdetail SET OrderQTY=?, Discount=? WHERE OrderID=? AND ItemCode=?";

        try {
            return CrudUtil.execute(SQL,
                    orderDetail.getOrderQTY(),
                    orderDetail.getDiscount(),
                    orderDetail.getOrderID(),
                    orderDetail.getItemCode()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
