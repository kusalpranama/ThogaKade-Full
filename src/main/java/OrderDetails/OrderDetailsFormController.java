package OrderDetails;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetail;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsFormController implements Initializable {
    OrderDetailsService service=new OrderDetailsController();

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colOrderQTY;

    @FXML
    private TableView<OrderDetail> tblOrderDetail;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtOrderQTY;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
    colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
    colOrderQTY.setCellValueFactory(new PropertyValueFactory<>("orderQTY"));
    colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

    tblOrderDetail.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
        setTextToValues(newValue);

    }));
        loadTable();
    }

    private void setTextToValues(OrderDetail newValue) {
        if (null!=newValue){
            txtOrderID.setText(newValue.getOrderID());
            txtItemCode.setText(newValue.getItemCode());
            txtOrderQTY.setText(String.valueOf(newValue.getOrderQTY()));
            txtDiscount.setText(String.valueOf(newValue.getDiscount()));
        }
    }

    private void loadTable(){
         ObservableList<OrderDetail> all = service.getAll();
         tblOrderDetail.setItems(all);
    }
    @FXML
    void btnAddOrderDetailOnAction(ActionEvent event) {
        OrderDetail orderDetail=new OrderDetail(
                txtOrderID.getText(),
                txtItemCode.getText(),
                Integer.parseInt(txtOrderQTY.getText()),
                Integer.parseInt(txtDiscount.getText())
        );
        if (service.addOrderDetail(orderDetail)){
            new Alert(Alert.AlertType.INFORMATION,"Order Detail Added !😍").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Order Detail Not Added ! 🤦‍♂️").show();
        }
        loadTable();
    }

    @FXML
    void btnDeleteOrderDetailOnAction(ActionEvent event) {
        if (service.deleteOrderDetail(txtOrderID.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Order Detail Deleted !💀").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Order Detail Not Deleted ! 🤦‍♂️").show();
        }
        loadTable();
    }

    @FXML
    void btnSearchOrderDetailOnAction(ActionEvent event) {
        OrderDetail orderDetail=service.searchOrderDetail(txtOrderID.getText());
        setTextToValues(orderDetail);
    }

    @FXML
    void btnUpdateOrderDetailOnAction(ActionEvent event) {
        OrderDetail orderDetail=new OrderDetail(
                txtOrderID.getText(),
                txtItemCode.getText(),
                Integer.parseInt(txtOrderQTY.getText()),
                Integer.parseInt(txtDiscount.getText())
        );
        if (service.updateOrderDetail(orderDetail)){
            new Alert(Alert.AlertType.INFORMATION,"Order Detail Updated !🥰").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Order Detail Not Updated ! 🤦‍♂️").show();
        }
        loadTable();
    }


}
