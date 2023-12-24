package lk.ijse.GameCafe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.GameCafe.model.CustomerModel;

import java.io.IOException;

public class MainFormController {

    @FXML
    private AnchorPane rootMain;

    @FXML
    private AnchorPane rootMainPane;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) rootMain.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    public void HomeOnMouseClick(MouseEvent mouseEvent) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/view/Dashboard_form.fxml"));
        rootMainPane.getChildren().add(newLoadedPane);
    }

    public void PaymentOnMouseClick(MouseEvent mouseEvent) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/view/payments_form.fxml"));
        rootMainPane.getChildren().add(newLoadedPane);
    }

    public void OrderOnMouseClick(MouseEvent mouseEvent) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/view/Reservation_form.fxml"));
        rootMainPane.getChildren().add(newLoadedPane);
    }

    public void CustomerOnMouseClick(MouseEvent mouseEvent) throws IOException {

        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        rootMainPane.getChildren().add(newLoadedPane);

    }

    public void SupplierOnMouseClick(MouseEvent mouseEvent) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/view/playStation_form.fxml"));
        rootMainPane.getChildren().add(newLoadedPane);
    }

    public void EmployeeOnMouseClick(MouseEvent mouseEvent) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/view/Employee_form.fxml"));
        rootMainPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void GameOnMouseClick(MouseEvent event) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/view/Arcade_form.fxml"));
        rootMainPane.getChildren().add(newLoadedPane);
    }
}