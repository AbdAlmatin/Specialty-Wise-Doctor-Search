package sample;

import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.geom.transform.BaseTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertDoctorDataController implements Initializable {

    @FXML
    private JFXTextField regNum;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField fieldOfSpecialty;

    @FXML
    private ChoiceBox<String> Degrees1;

    @FXML
    private ChoiceBox<String> Degrees2;

    public InsertDoctorDataController() {
    }

    @FXML
    void InsertClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Degrees1.setValue("MBBS");
        Degrees1.getItems().addAll("MBBS","Bachelor of Medicine, Bachelor of Surgery", "Bachelor of Surgery",  "Doctor of Medicine",  "Doctor of Osteopathic Medicine");
        Degrees2.getItems().addAll("FCPS", "MD", "MS","M.Phil", "M.MED");
    }
}
