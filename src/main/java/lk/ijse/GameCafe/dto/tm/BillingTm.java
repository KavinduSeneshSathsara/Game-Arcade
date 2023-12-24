package lk.ijse.GameCafe.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillingTm {
    private String customerId;
  //  private String customerName;
    private String gameId;
    private String timePlayed;
    private String total;
}
