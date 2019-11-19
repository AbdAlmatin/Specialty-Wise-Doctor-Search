package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.TextFields;

import javax.print.Doc;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SearchByDoctorConroller implements Initializable {

    Connection con;

    @FXML
    private JFXTextField DoctorName;


    @FXML
    void KeyRealeased(KeyEvent event) {

    }

    @FXML
    void searchClicked(ActionEvent event) throws SQLException {

        int registrationsNumb = 0;

        try{
            registrationsNumb = loadDatabase.doctor_reg_num.get(DoctorName.getText());
        }
        catch (NullPointerException e){
            System.out.println("painai bhai");

            Toolkit.getDefaultToolkit().beep();
            Alert.AlertType alertAlertType;
            Alert noData = new Alert(AlertType.ERROR);
            noData.setHeaderText("Sorry");
            noData.setContentText("No data is found for the name your inserted");
            noData.show();
            return;
        }

        System.out.println(registrationsNumb);

        String query = "select field_of_specialty from doctors where reg_num = " + registrationsNumb;
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery(query);



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFields.bindAutoCompletion(DoctorName, loadDatabase.doctors);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/swds", "root", "amishipon");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
