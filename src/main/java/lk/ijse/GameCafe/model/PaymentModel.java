package lk.ijse.GameCafe.model;

import lk.ijse.GameCafe.db.DbConnection;
import lk.ijse.GameCafe.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    public boolean savePayment(PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance( ).getConnection( );

        String sql = "insert into payment value (?,?,?,?,?)";

        PreparedStatement ps = connection.prepareStatement( sql );

        ps.setString( 1, dto.getPaymentId());
        ps.setString( 2, dto.getBookingId() );
        ps.setDate( 3, dto.getDate() );
        ps.setTime( 4, dto.getTime() );
        ps.setDouble( 5, dto.getAmount() );

        return ps.executeUpdate() != 0;
    }

    public boolean updatePayment(PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance( ).getConnection( );

        String sql = "update payment set booking_id=?, amount=? where payment_id=?";

        PreparedStatement ps = connection.prepareStatement( sql );

        ps.setString( 1, dto.getBookingId());
        ps.setDouble( 2, dto.getAmount());
        ps.setString( 3, dto.getPaymentId() );

        return ps.executeUpdate() != 0;
    }

    public String generateNextId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM payment ORDER BY payment_id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        String  current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return splitId(current);
        }
        return splitId(null);

    }

    private String splitId(String current) {
        if (current != null) {
            String[] split = current.split("P");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 >= id && id > 0) return "P00" + id;
            else if (99 >= id && id > 9) return "P0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "P001";
    }
}
