package lk.ijse.GameCafe.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.GameCafe.db.DbConnection;
import lk.ijse.GameCafe.dto.BookingDto;
import lk.ijse.GameCafe.dto.CustomerDto;
import lk.ijse.GameCafe.dto.PaymentDto;
import lk.ijse.GameCafe.model.BookingModel;
import lk.ijse.GameCafe.model.CustomerModel;
import lk.ijse.GameCafe.model.PaymentModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class PaymentsFormController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private TextField txtBookingId;

    @FXML
    private TextField txtSearchBar;

    @FXML
    private Text lblDate;

    @FXML
    private Text lblTime;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colBookingId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtCustomer;

    @FXML
    private Text lblPaymentID;

    @FXML
    private JFXButton btnPay;

    PaymentModel paymentModel = new PaymentModel();
    BookingModel bookingModel = new BookingModel();
    CustomerModel customerModel = new CustomerModel();

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtBookingId.clear();
        txtCustomer.clear();
        txtAmount.clear();
    }

    @FXML
    void btnPayOnAction(ActionEvent event) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance( ).getConnection( );
            connection.setAutoCommit( false );

            boolean savePayment = paymentModel.savePayment( new PaymentDto(
                    lblPaymentID.getText( ),
                    txtBookingId.getText( ),
                    Date.valueOf( LocalDate.now( ) ),
                    Time.valueOf( LocalTime.now( ) ),
                    Double.parseDouble( txtAmount.getText( ) )
            ) );

            if ( savePayment ) {
                boolean isUpdated = bookingModel.updateStatus( txtBookingId.getText( ) );

                if ( isUpdated ) {
                    connection.commit();
                    new Alert( Alert.AlertType.CONFIRMATION, "Payment Saved" ).show();
                    setPaymentId();
                } else {
                    new Alert( Alert.AlertType.ERROR , "Something Went Wrong" ).show();
                }
            } else {
                new Alert( Alert.AlertType.ERROR , "Something Went Wrong" ).show();
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit( true );
        }
    }

    @FXML
    void txtBookingIdOnAction(ActionEvent event) {
        try {
            BookingDto bookingData = bookingModel.getBookingData( txtBookingId.getText() );

            if ( bookingData != null && bookingData.getStatus().equals( "Not Paid" ) ) {

                CustomerDto dto = customerModel.SearchModel( bookingData.getCus_id() );
                txtCustomer.setText( dto.getCusName() );
                txtAmount.setText( String.valueOf( bookingData.getTotal() ) );
                btnPay.setDisable( false );
            } else {
                new Alert( Alert.AlertType.ERROR, "Already Paid !" ).show();
                btnPay.setDisable( true );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    private void time() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {

            lblTime.setText(timeFormat.format(new java.util.Date()));
            lblDate.setText(dateFormat.format(new java.util.Date()));
        }), new KeyFrame( Duration.seconds(1)));
        timeline.setCycleCount( Animation.INDEFINITE);

        timeline.play();
    }

    public void setPaymentId() {
        try {
            lblPaymentID.setText( paymentModel.generateNextId() );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       setPaymentId();
       time();
    }
}
