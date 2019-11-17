package sample;

import com.jfoenix.controls.JFXTextField;
import  javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InsertDoctorDataController implements Initializable {

    @FXML
    private AnchorPane Container;

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
    private ChoiceBox<String> District2;

    @FXML
    private TextField th1;

    @FXML
    private TextField th2;

    @FXML
    private JFXTextField personalCham1;

    @FXML
    private JFXTextField personalCham2;

    @FXML
    private TextField hospital;

    private Connection con;

    int IdOfDegree(String degreeName) throws SQLException {
        String subQuery = "select id from degrees where degree = ?";
        PreparedStatement preparedStatementSub = con.prepareStatement(subQuery);
        preparedStatementSub.setString(1, degreeName);
        ResultSet res  = preparedStatementSub.executeQuery();

        int degree_id = 0;
        while(res.next()) {
            degree_id = res.getInt("id");
            System.out.println(degree_id);
        }

        return  degree_id;
    }

    @FXML
    void InsertClicked(ActionEvent event) throws SQLException {
        boolean isFailed = false;
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/swds", "root", "amishipon");
        String query = " insert into doctors (reg_num, fname, lname, field_of_specialty)"
                + " values (?, ?, ?, ?)";

        // query for table doctors
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, regNum.getText());
        preparedStatement.setString(2, firstName.getText());
        preparedStatement.setString(3, lastName.getText());
        preparedStatement.setString(4, fieldOfSpecialty.getText());

        // query for table doctors_degrees
        String query1 = "insert into doctors_degrees(doctor_reg_num, degree_id) values(?, ?)";
        PreparedStatement preparedStatement1 = con.prepareStatement(query1);
        preparedStatement1.setString(1, regNum.getText());
        PreparedStatement preparedStatement2 = con.prepareStatement(query1);
        preparedStatement2.setString(1, regNum.getText());

        preparedStatement1.setString(2, String.valueOf(IdOfDegree(Degrees1.getValue())));

        // query for table personal_chamber_addrs
        String query3 = "INSERT INTO personal_chamber_addrs(doctor_reg_num, district, thana, location) values(?, ?, ?, ?)";
        PreparedStatement preparedStatement3 = con.prepareStatement(query3);
        preparedStatement3.setString(1, regNum.getText());

        // query for table personal_chamber_addrs 2nd
        String query4 = "INSERT INTO personal_chamber_addrs(doctor_reg_num, district, thana, location) values(?, ?, ?, ?)";
        PreparedStatement preparedStatement4 = con.prepareStatement(query4);
        preparedStatement4.setString(1, regNum.getText());

        // query for table work
        String query5 = "INSERT INTO work(doctor_reg_num, hospital_reg_num) values (?, ?)";
        PreparedStatement preparedStatement5 = con.prepareStatement(query4);
        preparedStatement5.setString(1, regNum.getText());

        // query for works for any hospital

        boolean runSecond = false, runThird = false, runFourth = false, runFifth = false;


        if (Degrees2.getValue() != null) {
            runSecond = true;
            preparedStatement2.setString(2, String.valueOf(IdOfDegree(Degrees2.getValue())));
        }

        // check any personal chamber address inserterd
        if (District1.getValue() != null) {
            runThird = true;
            preparedStatement3.setString(2, District1.getValue());
            preparedStatement3.setString(3, th1.getText());
            preparedStatement3.setString(4, personalCham1.getText());
        }

        // check second personal chamber address inserterd
        if (District2.getValue() != null) {
            runFourth = true;
            preparedStatement4.setString(2, District2.getValue());
            preparedStatement4.setString(3, th2.getText());
            preparedStatement4.setString(4, personalCham2.getText());
        }

        // check for works in any hospital
        if (hospital.getText() != null){
            runFifth = true;
            preparedStatement5.setString(2, String.valueOf(loadDatabase.hospitals_reg_num.get(hospital.getText())));
        }


        try{
            preparedStatement.execute();
            preparedStatement1.execute();
            if (runSecond)
                preparedStatement2.execute();
            if (runThird)
                preparedStatement3.execute();
            if (runFourth)
                preparedStatement4.execute();
            if (runFifth)
                preparedStatement5.execute();
        }
        catch (SQLException e){
            isFailed = true;
            System.out.println(e.getMessage());
            Toolkit.getDefaultToolkit().beep();
            Alert ErrorSql = new Alert(AlertType.ERROR);
            ErrorSql.setContentText(e.getMessage());
            ErrorSql.setHeaderText("Insertion Failed");
            ErrorSql.show();
        }

        if( !isFailed ){
            Toolkit.getDefaultToolkit().beep();
            Alert ErrorSql = new Alert(AlertType.INFORMATION);
            ErrorSql.setContentText("Data for '" + firstName.getText() + " " + lastName.getText() + "' Successfully inserted");
            ErrorSql.setHeaderText("Successfull");
            ErrorSql.show();

            // Clear the textfields
            firstName.clear();
            lastName.clear();
            regNum.clear();
            fieldOfSpecialty.clear();
            Degrees1.setValue("MBBS");
            Degrees2.getItems().clear();
        }

        con.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFields.bindAutoCompletion(th1, loadDatabase.thana);
        TextFields.bindAutoCompletion(th2, loadDatabase.thana);
        TextFields.bindAutoCompletion(hospital, loadDatabase.hospitals);

        Degrees1.setValue("MBBS");
        Degrees1.getItems().addAll("MBBS","Bachelor of Medicine, Bachelor of Surgery", "Bachelor of Surgery",  "Doctor of Medicine",  "Doctor of Osteopathic Medicine");
        Degrees2.getItems().addAll("FCPS", "MD", "MS","M.Phil", "M.MED");
        District1.setValue("Chittagong");
        District1.getItems().addAll(loadDatabase.districts);
        District2.setValue("Chittagong");
        District2.getItems().addAll(loadDatabase.districts);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
             }
        }

}
