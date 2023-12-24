package lk.ijse.GameCafe.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lk.ijse.GameCafe.dto.tm.CustomerTm;
import lk.ijse.GameCafe.dto.tm.PlayStationTm;
import lk.ijse.GameCafe.model.CustomerModel;
import lk.ijse.GameCafe.model.PlayStationModel;

import java.sql.SQLException;

public class PlayStationFormController {

    @FXML
    private JFXButton btnSave;

    @FXML
    private Pane pane;

    @FXML
    private TableView<String> tblPlayStation;

    @FXML
    private TextField txtPlayStationId;

    @FXML
    private TextField txtPlayStationNumber;

    @FXML
    private TextField txtSearchBar;

    @FXML
    private JFXComboBox<?> txtStatus;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {


            String id = txtPlayStationId.getText();
            PlayStationModel playStationModel = new PlayStationModel();

            try {
                boolean isDeleted = playStationModel.delePlayStation(id);

                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Play Station Deleted Successfully").show();
                    loadAllPlayStations();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            new Alert(Alert.AlertType.INFORMATION, "Please select a customer to delete. ");
    }

    private void loadAllPlayStations() {

    }

    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtPlayStationId.clear();
        txtPlayStationNumber.clear();
        txtStatus.setValue(null);
    }
    
    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
