package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;



public class Controller implements Initializable {
    @FXML
    public TextField inputText;
    public Button clickbutton;
    public TextArea outputText;
    public ImageView editbutton;
    public Menu openrecent;
    public ListView dictList;
    public Button add;

    public  TreeMap<String,String> dictionary;


    //using screencontroller to create multi screen . . .

    // working on it
    public void addWord(ActionEvent event){
        TextInputDialog demo = new TextInputDialog();
        demo.setHeaderText("New word");
        demo.setContentText("Word");

    }

    // demo addword
    // demo sucessfully xD
    public void demo(ActionEvent event){
        dictionary.put(" demohere", "lua dao day dung tin");
        dictList.getItems().clear();
        for (Map.Entry<String,String> entry: dictionary.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            dictList.getItems().add(key);
        }
    }

    //done
    // set editable for the word's meaning
    public void edit(MouseEvent mouseEvent){
        if(outputText.isEditable()){
            outputText.setEditable(false);
            editMeaning();
        }else {
            outputText.setEditable(true);
        }
    }

    //  cần mở file ra 1 lần nữa để replace ??
    //  still working on it
    public void editMeaning(){
        String word;
        String meaning;
        word = " " + inputText.getText();
        meaning = outputText.getText();
        dictionary.replace(word,meaning);
        System.out.println("word " + word + " has changed meaning to " + meaning);
    }

    //done // find the meaning and show it on right textarea
    public void searching(ActionEvent event){
        String input = inputText.getText();
        String meaning;
        meaning = getMeaning(input);
        outputText.setEditable(true);
        outputText.setText(meaning);
        outputText.setEditable(false);
    }

    //done
    public String getMeaning(String word){
        String notfound = "Word not found!";
        word = " " + word;
        Set<String> keys = dictionary.keySet();
        for (String key : keys ){
            if (key.equals(word)) return dictionary.get(key);
        }
        return notfound;
    }

//    initialize
    public void getDict(){
        try {
            Dict dict = new Dict();
            dictionary = dict.read("C:\\Users\\buing\\IdeaProjects\\finalform\\src\\sample\\listDictionary\\textfield");

            for (Map.Entry<String,String> entry: dictionary.entrySet()) {
                String key   = entry.getKey();
                String value = entry.getValue();
                dictList.getItems().add(key);
//                content.add(key);
            }

        }catch (FileNotFoundException e){
            System.out.println("File not found . . . ");
            e.printStackTrace();
        }
    }


    // still working on it, but have no idea :(
    // choicebox / choicedialog ?
    public void openFile(Event event){
        String filename = "da";
        System.out.println("select file : " + filename );
    }
    //list file
    private void listFile(File dir) {
        int i = 0;
        String getname;
        File[] files = dir.listFiles();
        for (File file: files) {
            getname = file.getName();
            openrecent.getItems().add(new MenuItem(getname));
            i++;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getDict();
        listFile(new File("C:\\Users\\buing\\IdeaProjects\\finalform\\src\\sample\\listDictionary"));

    }
}
