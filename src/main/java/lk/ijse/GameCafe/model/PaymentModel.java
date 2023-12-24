package lk.ijse.GameCafe.model;

import lk.ijse.GameCafe.db.DbConnection;
import lk.ijse.GameCafe.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentModel {

    public boolean SavePayment(PaymentDto dto) throws SQLException {
        
        Connection connection = DbConnection.getInstance().getConnection();
        
        String sql = "INSERT INTO  Payment VALUES(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1,dto.getPaymentId());
        ps.setString(2,dto.getOrderId());
        ps.setString(3,dto.getDate());
        ps.setString(4,dto.getTime());
        ps.setString(5, dto.getAmount());

        int i = ps.executeUpdate();
        return i> 0;
    }

    public List<PaymentDto> getAllPayments() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM payment";

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        List<PaymentDto> list = new ArrayList<>();

        while (resultSet.next()){
            PaymentDto dto = new PaymentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));

            list.add(dto);


        }

        return list;

    }

    public boolean updatePayment(PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE Payment SET  order_id=?,payment_date=?,payment_time=?,amount=? WHERE payment_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1,dto.getOrderId());
        ps.setString(2,dto.getDate());
        ps.setString(3,dto.getTime());
        ps.setString(4,dto.getAmount());
        ps.setString(5,dto.getPaymentId());


        return ps.executeUpdate()>0;
    }

    public PaymentDto searchModel(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM payment WHERE payment_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);

        ResultSet resultSet = ps.executeQuery();

        PaymentDto dto = null;

        if (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String orderId = resultSet.getString(2);
            String date = resultSet.getString(3);
            String time = resultSet.getString(4);
            String amount = resultSet.getString(5);

            dto = new PaymentDto(paymentId, orderId, date, time, amount);
        }
        return dto;
    }

    public boolean deletePayments(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM payment WHERE payment_id=?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,id);

        return ps.executeUpdate()>0;
    }
}

