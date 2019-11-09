package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AdminLoginController {

    @FXML
    private Label StatusId;

    @FXML
    private JFXTextField UsernameId;

    @FXML
    private JFXPasswordField PasswordId;

    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException, ParseException {
        String Username = UsernameId.getText().toString();
        String Password = PasswordId.getText().toString();
        Object obj = (new JSONParser()).parse(new FileReader("AdminInfo.json"));
        JSONObject JObject = (JSONObject) obj;
        JSONArray Found= (JSONArray) JObject.get(Username);
        if(Found !=  null){
            System.out.println(Found.get(0));
            if(Password.equals(Found.get(0))){
                Parent n= FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
                Scene n1=new Scene(n);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setTitle("Admin Panel");
                window.setScene(n1);
                window.show();
            }

        }
    }


}