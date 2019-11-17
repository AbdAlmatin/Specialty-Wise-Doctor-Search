package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {

    Connection con;

    @FXML
    private JFXTextField regNum;

    @FXML
    void DeleteButtonPressed(ActionEvent event) throws SQLException {
        String query = "Delete from doctors where reg_num = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, regNum.getText());

        Statement stm = con.createStatement();
        String query1 = "select * from doctors where reg_num = " + regNum.getText();
        ResultSet res = stm.executeQuery(query1);

        Toolkit.getDefaultToolkit().beep();
        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setHeaderText("Are you sure to Delete this data?");
        //confirmation.setContentText(res.getString("fname") + " " + res.getString("lname") + res.getString("reg_num") + " " + res.getString("field_of_specialty"));
        confirmation.setContentText("kirre delete korbi ken?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmation.getButtonTypes().setAll(yes, cancel);
        Optional< ButtonType > results = confirmation.showAndWait();

        boolean isFailed = false;

        if(results.get() == yes){
            try{
                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                isFailed = true;
                Toolkit.getDefaultToolkit().beep();
                Alert ErrorSql = new Alert(Alert.AlertType.ERROR);
                ErrorSql.setContentText(e.getMessage());
                ErrorSql.setHeaderText("Deletion Failed");
                ErrorSql.show();
            }

        }

        if( !isFailed ) {
            Toolkit.getDefaultToolkit().beep();
            Alert successful = new Alert(Alert.AlertType.INFORMATION);
            successful.setContentText("Data for Doctor successfully deleted");
            successful.setHeaderText("Successfull");
            successful.show();
            regNum.setText(null);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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