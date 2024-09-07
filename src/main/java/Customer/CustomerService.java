package Customer;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerService {
    ObservableList<Customer> getAll();

    boolean addCustomer(Customer customer);

    boolean deleteCustomer(String custId);

    Customer searchCustomer(String custId);

    boolean updateCustomer(Customer customer);
}
