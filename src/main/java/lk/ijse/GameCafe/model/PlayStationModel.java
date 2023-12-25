package lk.ijse.GameCafe.model;

import lk.ijse.GameCafe.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayStationModel {

    public boolean delePlayStation(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM play_station WHERE  play_station_id= ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1,id);

        return ps.executeUpdate() > 0;
    }
}
