package Customer;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustAddress;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colCustName;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker dateDob;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtCustAddress;

    @FXML
    private JFXTextField txtCustID;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
         Customer customer = new Customer(
                 txtCustID.getText(),
                 cmbTitle.getValue().toString(),
                 txtCustName.getText(),
                 dateDob.getValue(),
                 Double.parseDouble(txtSalary.getText()),
                 txtCustAddress.getText(),
                 txtCity.getText(),
                 txtProvince.getText(),
                 txtPostalCode.getText()
         );
        if (service.addCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added !😍").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Not Added !!😢").show();
        }
        loadTable();
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {

        if(service.deleteCustomer(txtCustID.getText())){
        new Alert(Alert.AlertType.INFORMATION,"Customer Deleted !!😍").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer not Deleted !!💀").show();
        }
        loadTable();
    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {

         Customer customer = service.searchCustomer(txtCustID.getText());
         setTextToValues(customer);
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
            Customer customer=new Customer(
                    txtCustID.getText(),
                    cmbTitle.getValue().toString(),
                    txtCustName.getText(),
                    dateDob.getValue(),
                    Double.parseDouble(txtSalary.getText()),
                    txtCustAddress.getText(),
                    txtCity.getText(),
                    txtProvince.getText(),
                    txtPostalCode.getText()
            );
            if (service.updateCustomer(customer)){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated 😍").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer not Updated 💀").show();
            }
            loadTable();
    }
    CustomerService service=new CustomerController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustID.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));


        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr");
        titles.add("Miss");
        cmbTitle.setItems(titles);
        tblCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            setTextToValues(newValue);
        } ));
        loadTable();
    }

    private void loadTable() {
         ObservableList<Customer> customerObservableList = service.getAll();
        tblCustomers.setItems(customerObservableList);

    }
    private void setTextToValues(Customer newValue){
       if(null!=newValue) {
            txtCustID.setText(newValue.getCustId());
            txtCustName.setText(newValue.getCustName());
            txtSalary.setText(String.valueOf(newValue.getSalary()));
            cmbTitle.setValue(newValue.getCustTitle());
            txtCustAddress.setText(newValue.getCustAddress());
            dateDob.setValue(newValue.getDob());
            txtCity.setText(newValue.getCity());
            txtProvince.setText(newValue.getProvince());
            txtPostalCode.setText(newValue.getPostalCode());


        }
    }
}
