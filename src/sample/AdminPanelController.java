package sample;

import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {

    @FXML
    public AnchorPane RightSideBar;

    public  void AddChildren(Node child){
        RightSideBar.getChildren().clear();
        RightSideBar.getChildren().add(child);
    }

    @FXML
    void DeleteClicked(MouseEvent event) {

        Parent DeleteWindow = null;
        try{
            DeleteWindow = (Parent) FXMLLoader.load(getClass().getResource("Delete.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddChildren(DeleteWindow);
    }

    @FXML
    void InsertDoctorClicked(MouseEvent event) {
        Parent InsertWindow = null;
        try{
            InsertWindow = (Parent) FXMLLoader.load(getClass().getResource("InsertDoctorData.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddChildren(InsertWindow);
    }

    @FXML
    void InsertHopitalClicked(MouseEvent event) {
        Parent InsertWindow = null;
        try{
            InsertWindow = (Parent) FXMLLoader.load(getClass().getResource("InsertHospitalData.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddChildren(InsertWindow);
    }

    @FXML
    void UpdateClicked(MouseEvent event) {
        Parent UpdateWindow = null;
        try{
            UpdateWindow = (Parent) FXMLLoader.load(getClass().getResource("Update.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddChildren(UpdateWindow);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
