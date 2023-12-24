package lk.ijse.GameCafe.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentTm {
    private String orderId;
    private String paymentId;
    private String date;
    private String time;
    private String amount;
}
