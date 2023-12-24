package lk.ijse.GameCafe.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ArcadeTm {
    private String gameId;
    private String gameName;
    private String price;
    private String time;
    private String date;

}
