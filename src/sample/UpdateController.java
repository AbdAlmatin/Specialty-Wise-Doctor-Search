package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.TextFields;

import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {

    Connection con;

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

    @FXML
    private ChoiceBox<String> District1;

    @FXML
    private TextField thana1;

    @FXML
    private TextField thana2;

    @FXML
    private JFXTextField personalCham1;

    @FXML
    private ChoiceBox<String> District2;

    @FXML
    private JFXTextField personalCham2;


    @FXML
    private TextField hospital;

    @FXML
    void keyreleased(KeyEvent event) throws SQLException {

        firstName.setText(null);
        lastName.setText(null);
        fieldOfSpecialty.setText(null);
        District1.setValue(null);
        District2.setValue(null);
        thana1.setText(null);
        thana2.setText(null);
        hospital.setText(null);
        personalCham1.setText(null);
        personalCham2.setText(null);

        if(regNum.getText().equals("")){
            System.out.println(regNum.getText());
            return;
        }

        String query = "select * from doctors where reg_num = " + regNum.getText();
        String query1 = "select * from degrees where degrees.id in (select degree_id from doctors_degrees where doctor_reg_num = " + regNum.getText() + ")";
        String query3 = "select district, thana, location from personal_chamber_addrs where doctor_reg_num = " + regNum.getText();
        String query4 = "select hospital_reg_num from work where doctor_reg_num = " + regNum.getText();

        Statement stm = con.createStatement();
        Statement stm1 = con.createStatement();
        Statement stm2 = con.createStatement();
        Statement stm3 = con.createStatement();
        Statement stm4 = con.createStatement();
        System.out.println(query);
        ResultSet res = stm.executeQuery(query);

        ResultSet res2  = stm1.executeQuery(query1);
        ResultSet res3 = stm2.executeQuery(query3);
        ResultSet res4 = stm3.executeQuery(query4);

        if( res.next() == false ) {
            firstName.setText(null);
            lastName.setText(null);
            fieldOfSpecialty.setText(null);
            District1.setValue(null);
            District2.setValue(null);
            thana1.setText(null);
            thana2.setText(null);
            hospital.setText(null);
            personalCham1.setText(null);
            personalCham2.setText(null);
        } else {
            firstName.setText(res.getString("fname"));
            lastName.setText(res.getString("lname"));
            fieldOfSpecialty.setText(res.getString("field_of_specialty"));

            if(res2.next()) {
                Degrees1.setValue(res2.getString("degree"));
            }
            if(res2.next()){
                Degrees2.setValue(res2.getString("degree"));
                System.out.println(res2.getString("degree"));
            }

            if(res3.next() == true) {
                District1.setValue(res3.getString("district"));
                thana1.setText(res3.getString("thana"));
                personalCham1.setText(res3.getString("location"));
            }

            if(res3.next() == true) {
                District2.setValue(res3.getString("district"));
                thana2.setText(res3.getString("thana"));
                personalCham2.setText(res3.getString("location"));
            }

            if( res4.next() ){
                hospital.setText(res4.getString("hospital_reg_num"));
            }

        }

    }

    @FXML
    void KeyPressed(KeyEvent event) throws SQLException {

    }

    @FXML
    void updateClicked(ActionEvent event) throws SQLException {

        String del = "delete from personal_chamber_addrs where doctor_reg_num = " + regNum.getText();
        Statement stdel = con.createStatement();
        stdel.executeUpdate(del);




        String query1 = "insert into personal_chamber_addrs(doctor_reg_num, district,  thana, location) values (" + regNum.getText() + ", '" + District1.getValue() + "', '" + thana1.getText() + "', \"" + personalCham1.getText() + "\")";
        System.out.println(query1);
        Statement st1 = con.createStatement();
        st1.execute(query1);

        String query2 = "insert into personal_chamber_addrs(doctor_reg_num, district,  thana, location) values (" + regNum.getText() + ", '" + District2.getValue() + "', '" + thana2.getText() + "', \"" + personalCham2.getText() + "\")";

        Statement stm2 = con.createStatement();
        stm2.execute(query2);

        if(hospital.getText() != null) {
            if(hospital.getText() == null) return;
            String del1 = "delete from work where doctor_reg_num = " + regNum.getText();
            Statement stdel1 = con.createStatement();
            stdel1.executeUpdate(del1);
            System.out.println("*******************");
            String query3 = "insert into work(doctor_reg_num, hospital_reg_num) values (" + regNum.getText() + ", " + hospital.getText() + ")";
            Statement st4 = con.createStatement();
            st4.execute(query3);
        }

        Toolkit.getDefaultToolkit().beep();
        Alert.AlertType alertAlertType;
        Alert succes = new Alert(AlertType.INFORMATION);
        succes.setHeaderText("Update");
        succes.setContentText("Great! Data successfully updated");
        succes.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TextFields.bindAutoCompletion(thana1, loadDatabase.thana);
        TextFields.bindAutoCompletion(thana2, loadDatabase.thana);
        TextFields.bindAutoCompletion(hospital, loadDatabase.hospitals);

        Degrees1.setValue("MBBS");
        Degrees1.getItems().addAll("MBBS","Bachelor of Medicine, Bachelor of Surgery", "Bachelor of Surgery",  "Doctor of Medicine",  "Doctor of Osteopathic Medicine");
        Degrees2.getItems().addAll("FCPS", "MD", "MS","M.Phil", "M.MED");
        District1.getItems().addAll(loadDatabase.districts);
        District2.getItems().addAll(loadDatabase.districts);
        hospital.setText(null);

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
