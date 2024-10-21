package kafka.Billing.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Integer orderId;
    private Integer customerId;
    private Float totalAmount;
    private String status;

}
