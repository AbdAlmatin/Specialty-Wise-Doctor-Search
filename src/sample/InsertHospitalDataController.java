package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertHospitalDataController implements Initializable {

    @FXML
    private JFXTextField regNum;

    @FXML
    private JFXTextField Name;

    @FXML
    private ChoiceBox<String> District;

    @FXML
    private ChoiceBox<String> thana;

    @FXML
    private JFXTextField hospitalLoc;

    @FXML
    void InsertClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        District.setValue("Chittagong");
        District.getItems().setAll(loadDatabase.districts);
    }
}
