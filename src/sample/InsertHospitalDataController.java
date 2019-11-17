package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InsertHospitalDataController implements Initializable {

    Connection con;

    @FXML
    private JFXTextField regNum;

    @FXML
    private JFXTextField Name;

    @FXML
    private ChoiceBox<String> District;

    @FXML
    private TextField thana;

    @FXML
    private JFXTextField hospitalLoc;

    @FXML
    void InsertClicked(ActionEvent event) throws SQLException {

        if(regNum.getText() == null || Name.getText() == null || District.getValue() == null || hospitalLoc.getText() == null) {
            Toolkit.getDefaultToolkit().beep();
            Alert Error = new Alert(Alert.AlertType.ERROR);
            Error.setContentText("Please fill all the fields");
            Error.setHeaderText("insertin Failed");
            Error.show();
            return;
        }


        String query = "insert into hospitals(reg_num, name, district, thana, location) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement1 = con.prepareStatement(query);
        preparedStatement1.setString(1, regNum.getText());
        preparedStatement1.setString(2, Name.getText());
        preparedStatement1.setString(3, District.getValue());
        preparedStatement1.setString(4, thana.getText());
        preparedStatement1.setString(5, hospitalLoc.getText());

        boolean isFailed = false;

        try{
            preparedStatement1.execute();
        }
        catch (SQLException e) {
            isFailed = true;
            Toolkit.getDefaultToolkit().beep();
            Alert ErrorSql = new Alert(Alert.AlertType.ERROR);
            ErrorSql.setContentText(e.getMessage());
            ErrorSql.setHeaderText("insertin Failed");
            ErrorSql.show();
        }

        if( !isFailed ) {
            Toolkit.getDefaultToolkit().beep();
            Alert Inserted = new Alert(Alert.AlertType.INFORMATION);
            Inserted.setContentText("Data for '" + Name.getText() + "' successfully added");
            Inserted.setHeaderText("Successfully Inserted");
            Inserted.show();

            // clear the input fields
            Name.setText(null);
            regNum.setText(null);
            District.setValue(null);
            thana.setText(null);
            hospitalLoc.setText(null);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        District.setValue("Chittagong");
        District.getItems().setAll(loadDatabase.districts);
        TextFields.bindAutoCompletion(thana, loadDatabase.thana);

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
