package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderID;
    private LocalDate orderDate;
    private String CustID;
}
