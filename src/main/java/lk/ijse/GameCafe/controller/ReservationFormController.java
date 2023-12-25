package lk.ijse.GameCafe.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ReservationFormController {
    @FXML
    private Pane pane;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TableColumn<?, ?> colStationId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCustMail;

    @FXML
    private TextField txtCustName;

    @FXML
    private Text lblTime;

    @FXML
    private Text lblDate;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> cmbStartTime;

    @FXML
    private ComboBox<String> cmbEndTime;

    @FXML
    private ComboBox<String> cmbTimeZone;

    @FXML
    private ComboBox<?> cmbStation;

    public void initialize(){
        cmbStartTime.setDisable(true);
        cmbEndTime.setDisable(true);
        loadTimeZone();
    }

    private void loadTimeZone() {
        ObservableList<String> obList= FXCollections.observableArrayList();
        obList.add("AM");
        obList.add("PM");
        cmbTimeZone.setItems(obList);
    }

    @FXML
    void btnAddToListOnAction(ActionEvent event) {
//        String station=cmbStation.getValue();
    }

    @FXML
    void btnBookOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEndTimeOnAction(ActionEvent event) {
//        loadAll
    }

    @FXML
    void cmbStartTimeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTimeZoneOnAcion(ActionEvent event) {
        String value = cmbTimeZone.getValue();
        ObservableList<String> oblist=FXCollections.observableArrayList();
        if (value.equals("AM")){
            for (int i = 8; i < 13; i++) {
                oblist.add(i+".00");
                oblist.add(i+".30");
            }
        }else {
            for (int i = 1; i <6 ; i++) {
                oblist.add(i+".00");
                oblist.add(i+".30");
            }
        }
        cmbStartTime.setItems(oblist);
        cmbEndTime.setItems(oblist);
        cmbEndTime.setDisable(false);
        cmbStartTime.setDisable(false);
    }


}
