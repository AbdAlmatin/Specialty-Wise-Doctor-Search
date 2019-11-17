package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyEvent;

public class UpdateController {

    @FXML
    private JFXTextField regNum;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField fieldOfSpecialty;

    @FXML
    private ChoiceBox<?> Degrees1;

    @FXML
    private ChoiceBox<?> Degrees2;

    @FXML
    private ChoiceBox<?> District1;

    @FXML
    private ChoiceBox<?> thana1;

    @FXML
    private JFXTextField personalCham1;

    @FXML
    private ChoiceBox<?> District2;

    @FXML
    private ChoiceBox<?> thana2;

    @FXML
    private JFXTextField personalCham2;

    @FXML
    void InsertClicked(ActionEvent event) {

    }

    @FXML
    void KeyPressed(KeyEvent event) {

    }

}
