package sample.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.InitializeDict;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addDictController extends InitializeDict implements Initializable {
    public TextField dictName;
    public Label warning;
    public ChoiceBox choiceBox;

    private ArrayList<String> listname = new ArrayList<>();
    public void AddtoMainScene(ActionEvent event) throws IOException {
        if (!check(event)) {
            String dictname = dictName.getText();
            create(dictname);
            backToMainScene(event);
        }
    }

    private void create(String name) throws IOException{
        File file = new File(getfinalpath("src/sample/listDictionary/" + name));
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write("# " + name + " # ");
        fw.close();
    }

    public boolean check(Event event) {
        String dictname = dictName.getText();
        if (compareToChoiceBox(dictname)){
            warning.setVisible(true);
            return true;
        }else{
            warning.setVisible(false);
            return false;
        }
    }

    private boolean compareToChoiceBox(String dictname){
        for (String name : listname ){
            if(dictname.equals(name)){
                return true;
            }
        }
        return false;
    }

    public void backToMainScene(ActionEvent event) throws IOException {
        Parent addParent = FXMLLoader.load(getClass().getResource("../Fxml/Main.fxml"));
        Scene addScene =new Scene(addParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    @Override
    public void listFile(File dir) {
        String getname;
        File[] files = dir.listFiles();
        for (File file: files) {
            getname = file.getName();
            choiceBox.getItems().add(getname);
            listname.add(getname);
        }
    }

    @Override
    public String dictOrder() {
        String abc = null;
        return abc;
    }

    @Override
    public void updateDictOrder(String name) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listFile(new File(getfinalpath("src/sample/listDictionary/")));
    }
}

