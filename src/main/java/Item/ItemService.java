package Item;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemService {

    ObservableList<Item> getAll();

    boolean addItem(Item item);

    boolean deleteItem(String itemCode);

    Item searchItem(String itemCode);

    boolean updateItem(Item item);
}
