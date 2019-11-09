/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.jws.soap.SOAPBinding;

public class LogInWindowController {

    @FXML
    private Label StatusId;

    @FXML
    private Label CreateNewAccountLabel;


    @FXML
    private JFXPasswordField PasswordId;

    @FXML
    private JFXTextField UsernameId;
    
    private boolean LoggedIn = false;
    private Scanner me; 
    
    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException, ParseException {
        String Username = UsernameId.getText().toString();
        String Password = PasswordId.getText().toString();
        Object obj = (new JSONParser()).parse(new FileReader("userinformation.json"));
        JSONObject JObject = (JSONObject) obj;
        JSONArray Found= (JSONArray) JObject.get(Username);
        if(Found !=  null){
            if(Password.equals(Found.get(0))){
                Parent n= FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                Scene n1=new Scene(n);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setTitle("Specialty Wise Doctor Search");
                window.setScene(n1);
                window.show();
            }
        }
    }
    

    @FXML
    void CreateNewAccountPressed(MouseEvent event) throws IOException {
        Parent SignUpWindowParent = FXMLLoader.load(getClass().getResource("SignupWindow.fxml"));
        Scene SignUpWindowScene = new Scene(SignUpWindowParent);
        Stage SignUpWindowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        SignUpWindowStage.setTitle("Sign Up");
        SignUpWindowStage.setScene(SignUpWindowScene);
        SignUpWindowStage.show();
    }

    @FXML
    void AdminLoginClicked(ActionEvent event) throws IOException {
        Parent SignUpWindowParent = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Scene SignUpWindowScene = new Scene(SignUpWindowParent);
        Stage SignUpWindowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        SignUpWindowStage.setTitle("Admin Login");
        SignUpWindowStage.setScene(SignUpWindowScene);
        SignUpWindowStage.show();
    }

}
