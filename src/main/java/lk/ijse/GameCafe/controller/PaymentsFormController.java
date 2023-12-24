package lk.ijse.GameCafe.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.GameCafe.dto.PaymentDto;
import lk.ijse.GameCafe.dto.tm.PaymentTm;
import lk.ijse.GameCafe.model.PaymentModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class PaymentsFormController {

    @FXML
    private TableColumn<?, ?> colAmountId;

    @FXML
    private TableColumn<?, ?> colDateId;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colTimeId;

    @FXML
    private Pane pane;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtSearchBar;

    @FXML
    private TextField txtTime;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtOrderId.clear();
        txtPaymentId.clear();
        txtDate.setValue(null);
        txtTime.clear();
        txtAmount.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtPaymentId.getText();

        PaymentModel model = new PaymentModel();

        try {
            boolean isDeleted = model.deletePayments(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Deleted Successfully").show();
                loadAllPayments();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        boolean isPaymentValidated = ValidatePayment();


        if (!isPaymentValidated){
            return;
        }

        String orderId = txtOrderId.getText();
        String payId = txtPaymentId.getText();
        String date = String.valueOf(txtDate.getValue());
        String time = txtTime.getText();
        String amount = txtAmount.getText();

        PaymentDto dto = new PaymentDto(orderId,payId,date,time,amount);

        PaymentModel paymentModel = new PaymentModel();
        try {
            boolean isSaved = paymentModel.SavePayment(dto);
            if(isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, " Payment Saved Successfully").show();
                loadAllPayments();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void initialize(){
        setCellValueFactory();
        loadAllPayments();
    }

    private void loadAllPayments() {
        PaymentModel model = new PaymentModel();

        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDto> list = model.getAllPayments();

            for (PaymentDto dto : list){
                PaymentTm  paymentTm = new PaymentTm(dto.getPaymentId(),
                        dto.getOrderId(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getAmount());

                obList.add(paymentTm);
            }
            tblPayment.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDateId.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTimeId.setCellValueFactory(new PropertyValueFactory<>("time"));
        colAmountId.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private boolean ValidatePayment() {
        String paymentIdText = txtPaymentId.getText();
        boolean isPaymentIdValidated = Pattern.matches("[P][0-9]{4}", paymentIdText);
        if (!isPaymentIdValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid Payment Id!").show();
            return false;
        }

        String orderIdText = txtOrderId.getText();
        boolean isOrderIdValidated = Pattern.matches("[O][0-9]{4}", orderIdText);
        if (!isOrderIdValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid Order Id!").show();
            return false;
        }

        String amountText = txtAmount.getText();
        boolean isAmountValidated = Pattern.matches("[0-9]+", amountText);
        if(!isAmountValidated){
            new Alert(Alert.AlertType.ERROR, "invalid Amount!").show();
            return false;

        }

        return true;

    }



    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtSearchBar.getText();
        var model = new PaymentModel();
        try {

            PaymentDto dto = model.searchModel(id);
            if (dto != null){
                fillField(dto);
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Payment not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void fillField(PaymentDto dto) {
        txtPaymentId.setText(dto.getPaymentId());
        txtOrderId.setText(dto.getOrderId());

        LocalDate dateValue = LocalDate.parse(dto.getDate());
        txtDate.setValue(dateValue);

        txtTime.setText(dto.getTime());
        txtAmount.setText(dto.getAmount());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String orderIdText = txtOrderId.getText();
        String paymentIdText = txtPaymentId.getText();
        LocalDate dateValue = txtDate.getValue(); // Use getValue() to get the selected date
        String dateText = (dateValue != null) ? dateValue.toString() : null; // Convert LocalDate to String
        String timeText = txtTime.getText();
        String amountText = txtAmount.getText();
        PaymentDto dto = new PaymentDto(orderIdText, paymentIdText, dateText, timeText, amountText);

        PaymentModel model = new PaymentModel();
        try {
            boolean isUpdated = model.updatePayment(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Updated Successfully").show();
                loadAllPayments();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


}
