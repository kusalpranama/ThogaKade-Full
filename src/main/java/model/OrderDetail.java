package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class OrderDetail {
    private String orderID;
    private String itemCode;
    private Integer orderQTY;
    private Integer discount;
}
