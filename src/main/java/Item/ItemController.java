package Item;

import Util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemService {

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM item";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5));
                itemObservableList.add(item);
            }
            return itemObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addItem(Item item) {
        String SQL = "INSERT INTO item VALUES (?,?,?,?,?)";

        try {
            return CrudUtil.execute(SQL,
                    item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrize(),
                    item.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteItem(String itemCode) {
        String SQL = "DELETE FROM item WHERE ItemCode=?";

        try {
            return CrudUtil.execute(SQL, itemCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item searchItem(String itemCode) {
        String SQL = "SELECT * FROM item WHERE ItemCode=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,itemCode);
            while (resultSet.next()) {
                return new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                );


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updateItem(Item item) {
        String SQL="UPDATE item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?";
        try {
           return CrudUtil.execute(SQL,
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrize(),
                    item.getQtyOnHand(),
                    item.getItemCode());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
