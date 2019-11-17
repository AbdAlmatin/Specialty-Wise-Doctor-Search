package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private Label UsernameLabel;

    @FXML
    private Label adminLabel1;

    @FXML
    private Label adminLabel12;

    @FXML
    private Label adminLabel11;

    @FXML
    private Label adminLabel111;

    @FXML
    private AnchorPane RightSideBar;

    @FXML
    void logOutClicked(MouseEvent event) throws IOException {
        Parent SignUpWindowParent = FXMLLoader.load(getClass().getResource("LogInWindow.fxml"));
        Scene SignUpWindowScene = new Scene(SignUpWindowParent);
        Stage SignUpWindowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        SignUpWindowStage.setTitle("Login");
        SignUpWindowStage.setScene(SignUpWindowScene);
        SignUpWindowStage.show();
    }

    @FXML
    void searchByDoctorClicked(MouseEvent event) throws IOException {
        Parent par = FXMLLoader.load(getClass().getResource("SearchByDoctor.fxml"));
        RightSideBar.getChildren().clear();
        RightSideBar.getChildren().addAll(par);
    }


    @FXML
    void searchBySpecialtyClicked(MouseEvent event) throws IOException {
        Parent par = FXMLLoader.load(getClass().getResource("SearchBySpecialty.fxml"));
        RightSideBar.getChildren().clear();
        RightSideBar.getChildren().addAll(par);
    }

    @FXML
    void updateProfileClicked(MouseEvent event) {

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}