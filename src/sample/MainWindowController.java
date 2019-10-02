/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXComboBox;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author admat
 */
public class MainWindowController implements Initializable {

    @FXML
    private TextField DoctorNameId;
    @FXML
    private JFXComboBox<String> ComboVoxId;

    ObservableList<String> MyComboVOxList = FXCollections.observableArrayList("Male", "Female");
    @FXML
    private TextField DoctorNameId1;
    @FXML
    private TextField DoctorNameId11;
    @FXML
    private TextArea ShowResults;
    @FXML
    private TextField SpecialtyId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboVoxId.setItems(MyComboVOxList);
    }

    @FXML
    private void GoForSearchByName(ActionEvent event) throws FileNotFoundException, IOException, ParseException {
      //  ShowResults.clear();
        String one;

        one = DoctorNameId.getText();
        // System.out.println(one);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("test_load_copy_jsonarray.json"));

        JSONArray jsonArray = (JSONArray) obj;
        List <String> stor = new ArrayList<>();
        String mainPerson = "Matching rate is very low";
        double prev = 0.0;
        int cnt = 0;
        for(Object o: jsonArray){
            JSONObject tmpObj = (JSONObject) o;
            Object name = tmpObj.keySet();
            //   System.out.println(tmpObj);
            cnt++;
            String nam = name.toString().replaceAll("[\\[\\],]","");
            // ShowResults.appendText(nam + "\n");
            if(SimilarityTestAlgo.similarity(nam, one) > .8){
                if(SimilarityTestAlgo.similarity(nam, one) > prev) mainPerson = nam + "\nDetails:\n\n" + tmpObj.get(nam) + "\n\n\n";
                prev = SimilarityTestAlgo.similarity(nam, one);
                stor.add(nam + "\nDetails:\n\n" + tmpObj.get(nam) + "\n\n\n");
            }
        }
        System.out.println(cnt);
//        ShowResults.appendText("Select any one them:\n");
   //     ShowResults.appendText(mainPerson);
        for(String x: stor){
            if(mainPerson != x)
               // ShowResults.appendText(x + "\n");
            System.out.println(x);
        }


    }

    @FXML
    private void GoForSearchBySpecialty(ActionEvent event) throws FileNotFoundException, IOException, ParseException {
        ShowResults.clear();
        String one;

        one = SpecialtyId.getText();
        // System.out.println(one);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("DoctorsRawInfo.json"));

        JSONArray jsonArray = (JSONArray) obj;
        List <String> stor = new ArrayList<>();
        String mainPerson = "Matching rate is very low";
        double prev = 0.0;
        for(Object o: jsonArray){
            JSONObject tmpObj = (JSONObject) o;
            Object name = tmpObj.keySet();

            String nam = name.toString().replaceAll("[\\[\\],]","");
            //    ShowResults.appendText(nam + "\n");


            if(SimilarityTestAlgo.similarity(nam, one) > 0.7){
                if(prev <  SimilarityTestAlgo.similarity(nam, one)) mainPerson = nam + "\nDetails:\n\n" + tmpObj.get(nam) + "\n\n\n";
                prev = SimilarityTestAlgo.similarity(nam, one);
                stor.add(nam + "\nDetails:\n\n" + tmpObj.get(nam) + "\n\n\n");
            }
        }
        //  ShowResults.appendText("Select any one them:\n");
        ShowResults.appendText(mainPerson);
        for(String x: stor){

            if(x != mainPerson) ShowResults.appendText(x + "\n");
            //System.out.println(x);
        }

    }

}

