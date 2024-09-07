package Item;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    ItemService service=new ItemController();

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrize;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSixe;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtUnitPrize;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrize.setCellValueFactory(new PropertyValueFactory<>("unitPrize"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblItem.getSelectionModel().selectedItemProperty().addListener(((observableValue,oldValue , newValue) -> {
            setTextToValue(newValue);
        }));
        loadTable();
    }
    private void loadTable(){
        ObservableList<Item> all = service.getAll();
        tblItem.setItems(all);

    }
    public void setTextToValue(Item newValue){
        if (null!=newValue){
            txtItemCode.setText(newValue.getItemCode());
            txtDescription.setText(newValue.getDescription());
            txtPackSixe.setText(newValue.getPackSize());
            txtUnitPrize.setText(String.valueOf(newValue.getUnitPrize()));
            txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
        }

    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        Item item=new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSixe.getText(),
                Double.parseDouble(txtUnitPrize.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );
        if (service.addItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Item Added😍").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Item Not Added 💀").show();
        }
        loadTable();
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {

        if (service.deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Item Deleted !😶‍🌫️").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Item Not Deleted !🤦‍♂️").show();
        }
        loadTable();
    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        Item item =service.searchItem(txtItemCode.getText());
        setTextToValue(item);
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        Item item=new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSixe.getText(),
                Double.parseDouble(txtUnitPrize.getText()),
                Integer.parseInt(txtQtyOnHand.getText())

        );
        if (service.updateItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Item Updated !😶‍🌫️").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Item not Updated !🤦‍♂️").show();
        }
        loadTable();
    }


}
