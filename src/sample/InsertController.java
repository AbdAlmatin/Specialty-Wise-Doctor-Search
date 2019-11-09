package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InsertController {

    AdminPanelController obj = new AdminPanelController();

    @FXML
    private Button insertDoctorID;

    @FXML
    void insertDoctor(ActionEvent event) throws NullPointerException {

    }

    @FXML
    void insertHospital(ActionEvent event) {

    }
}
