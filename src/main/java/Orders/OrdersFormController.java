package Orders;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private TableView<Order> tblOrder;

    @FXML
    private JFXTextField txtCustID;

    @FXML
    private JFXTextField txtOrderID;
        OrderService service=new OrderController();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("CustID"));

        tblOrder.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextTo(newValue);
        }));
        loadTable();
    }

    private void setTextTo(Order newValue) {
        if (null!=newValue){
            txtCustID.setText(newValue.getCustID());
            txtOrderID.setText(newValue.getOrderID());
            dateDOB.setValue(newValue.getOrderDate());
        }
    }

    private void loadTable(){
         ObservableList<Order> all = service.getAll();
         tblOrder.setItems(all);
    }
    @FXML
    void btnAddOrderOnAction(ActionEvent event) {

        Order order=new Order(
                txtOrderID.getText(),
                dateDOB.getValue(),
                txtCustID.getText()
        );
        if (service.addOrder(order)){
            new Alert(Alert.AlertType.INFORMATION,"Order Added !🥰👌").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Order Not Added !🤦‍♂️🥶").show();
        }
        loadTable();
    }

    @FXML
    void btnDeleteOrderOnAction(ActionEvent event) {
        if (service.deleteOrder(txtOrderID.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Order Deleted !🥰👌").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Order Not Deleted !🤦‍♂️🥶").show();

        }
        loadTable();
    }

    @FXML
    void btnSearchOrderOnAction(ActionEvent event) {
       Order order=service.searchOrder(txtOrderID.getText());
       setTextTo(order);
    }

    @FXML
    void btnUpdateOrderOnAction(ActionEvent event) {
        Order order=new Order(
                txtOrderID.getText(),
                dateDOB.getValue(),
                txtCustID.getText()
        );
        if (service.updateOrder(order)){
            new Alert(Alert.AlertType.INFORMATION,"Order Updated !🥰👌").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Order Not Updated !🤦‍♂️🥶").show();
        }
        loadTable();
    }


}
