package Customer;

import Util.CrudUtil;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController implements CustomerService {


    @Override
    public ObservableList<Customer> getAll() {

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM customer";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customerObservableList.add(customer);
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean addCustomer(Customer customer) {
        String SQL = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,
                    customer.getCustId(),
                    customer.getCustTitle(),
                    customer.getCustName(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getCustAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteCustomer(String custId) {
        String SQL = "DELETE FROM customer WHERE CustID=?";
        try {
            return CrudUtil.execute(SQL, custId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Customer searchCustomer(String custId) {
        String SQL = "SELECT * FROM customer WHERE CustID=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL, custId);
            while (resultSet.next()) {
                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String SQL = "UPDATE customer SET CustTitle = ?, CustName = ?,  DOB = ?, salary = ?,CustAddress = ?, City = ?, Province = ?, PostalCode = ? WHERE CustID = ? ";

        try {
            return CrudUtil.execute(SQL,
                    customer.getCustTitle(),
                    customer.getCustName(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getCustAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode(),
                    customer.getCustId()
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

