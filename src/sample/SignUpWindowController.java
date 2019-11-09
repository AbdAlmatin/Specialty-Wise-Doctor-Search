/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.*;
import java.util.Formatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.WStringSeqHelper;

public class SignUpWindowController {

    @FXML
    private JFXTextField UsernameId;

    @FXML
    private ToolBar signupTollbar;

    @FXML
    private JFXPasswordField PasswordId;

    @FXML
    private JFXTextField Thana;

    @FXML
    private JFXTextField DistrictId;

    @FXML
    private JFXTextField EmailId;


    @FXML
    private Button myButton;

    @FXML
    void SingupButtonPressed(ActionEvent event) throws IOException, ParseException {
        /*
         * creating a new account for new user i.e., storing username and password
         */
        AddUserInformation(UsernameId.getText(), PasswordId.getText(), EmailId.getText(), DistrictId.getText(), Thana.getText());
        Parent root = FXMLLoader.load(getClass().getResource("PaymentMethod.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Payment Method");
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    private BufferedWriter UserData;
    
    private void AddUserInformation(String Username, String Password, String Email,String District, String Thana) throws IOException, FileNotFoundException, ParseException {
        Object JsonFileParser = new JSONParser().parse(new FileReader("userinformation.json"));
        JSONObject JObject = (JSONObject) JsonFileParser;
        JSONArray JsonArray = new JSONArray();
        JsonArray.add(0, Password);
        JsonArray.add(1, Email);
        JsonArray.add(2, District);
        JsonArray.add(3, Thana);

        JObject.put(Username, JsonArray);
        System.out.println(JObject);

        try(FileWriter JsonFW = new FileWriter("userinformation.json")){
            JsonFW.write(JObject.toString());
            JsonFW.flush();
        }
        catch (IOException e){

        }


    }


    @FXML
    void BackButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogInWindow.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Log in");

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void AdminLoginClicked(ActionEvent event) {

    }

}
