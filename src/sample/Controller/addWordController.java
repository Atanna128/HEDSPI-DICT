package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Model.Dict;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeMap;

public class addWordController implements Initializable {

    public TextField wordAdd;
    public TextArea meaningAdd;

    private TreeMap<String,String> dictionary;
    private String dictname;
    private Stage thisStage;
    private final Controller controller;

    public addWordController(Controller controller) {
        this.controller = controller;
        thisStage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Fxml/addWord.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Dictionary Application");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showStage() {
        thisStage.showAndWait();
    }

    public void AddtoMainScene(ActionEvent event) throws IOException {
        String wordadd = wordAdd.getText();
        String meaningadd = meaningAdd.getText();
        dictionary.put(wordadd,meaningadd);
        updateToFile();

        backToMainScene(event);
    }

    private void updateToFile() {
        try {
            String filename = controller.getDictname();
            FileWriter writer = new FileWriter(controller.getfinalpath("src/sample/listDictionary/" + filename));
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(" # " + dictname + " # "); // follow the format
            for (Map.Entry<String,String> entry: dictionary.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                buffer.write(" { " + key + " ; " + value + " } "); // follow the format
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void backToMainScene(ActionEvent event) throws IOException {
        Parent addParent = FXMLLoader.load(getClass().getResource("../Fxml/Main.fxml"));
        Scene addScene =new Scene(addParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Dict dict = new Dict();
            Scanner scanner = new Scanner(new File(controller.getfinalpath("src/sample/listDictionary/textfield")));
            dictionary = dict.read(new File(controller.getfinalpath("src/sample/listDictionary/textfield")));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
