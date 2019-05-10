package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {
    @FXML
    public TextField inputText;
    public TextArea outputText;
    public ImageView editbutton;
    public Menu openrecent;
    public ListView dictList;
    public Button add;
    public ImageView deletebutton;

    public  TreeMap<String,String> dictionary;
    public String dictname;

    //done
    public void addWordScene(ActionEvent event) throws IOException {
        Parent addParent = FXMLLoader.load(getClass().getResource("addWord.fxml"));
        Scene addScene =new Scene(addParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.setWidth(960);
        window.setHeight(600);
        window.show();
    }

    //done
    public void addDictScene(ActionEvent event) throws IOException {
        Parent addParent = FXMLLoader.load(getClass().getResource("addDict.fxml"));
        Scene addScene =new Scene(addParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.setWidth(960);
        window.setHeight(600);
        window.show();
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

    //done
    public void delete(MouseEvent event){
        String word;
        word = " " + inputText.getText();
        dictionary.remove(word);
        outputText.clear();
        inputText.clear();
        updateListView();
        updateToFile();

    }

    //done
    private void updateListView() {
        dictList.getItems().clear();
        for (Map.Entry<String,String> entry: dictionary.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            dictList.getItems().add(key);
        }
    }

    //done
    public void editMeaning(){
        String word;
        String meaning;
        word = " " + inputText.getText();
        meaning = outputText.getText();
        dictionary.replace(word,meaning);
        updateToFile();
    }

    //done
    private void updateToFile() {
        try {
            FileWriter writer = new FileWriter(getfinalpath("src/sample/listDictionary/textfield"));
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

    //done // find the meaning and show it on right textarea
    public void searching(Event event){
        String input = inputText.getText();
        String meaning;
        meaning = getMeaning(input);
        outputText.setEditable(true);
        outputText.setText(meaning);
        outputText.setEditable(false);
        autocomplete(input);
    }

    //done
    private void autocomplete(String word) {
        String recentword = " " + word + "(.*)";
        dictList.getItems().clear();
        for (Map.Entry<String,String> entry: dictionary.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.matches(recentword)) {
                dictList.getItems().add(key);
            }
        }
    }

    //done
    public String getMeaning(String word){
        String notfound = "We are still finding for you . . . . \nDont give up on us :)";
        word = " " + word;
        Set<String> keys = dictionary.keySet();
        for (String key : keys ){
            if (key.equals(word)) return dictionary.get(key);
        }
        return notfound;
    }


    // still working on it, but have no idea :(
    // choicebox / choicedialog ?
    public void openFile(Event event){
        String filename = "da";
        System.out.println("select file : " + filename );
    }

    //done
    //list all file in folder listDictionary
    public void listFile(File dir) {
        int i = 0;
        String getname;
        File[] files = dir.listFiles();
        for (File file: files) {
            getname = file.getName();
            openrecent.getItems().add(new MenuItem(getname));
            i++;
        }
    }

    public String getfinalpath(String getfile) {
        File file = new File(getfile);
        String pathname = file.getAbsolutePath();

        String out = "";
        for (int i = 0 ; i < getfile.length() ; i++){
            if (getfile.charAt(i) ==  '\\'){
                out = out + "/";
            }else out = out + getfile.charAt(i);
        }
        return  out;
    }

    //initialize
    public void getDict(){
        try {
            Dict dict = new Dict();
            Scanner scanner = new Scanner(new File(getfinalpath("src/sample/listDictionary/textfield")));
            dictionary = dict.read(new File(getfinalpath("src/sample/listDictionary/textfield")));
            dictname = dict.getdictname(scanner);
            for (Map.Entry<String,String> entry: dictionary.entrySet()) {
                String key   = entry.getKey();
                String value = entry.getValue();
                dictList.getItems().add(key);
//                content.add(key);
            }

        }catch (FileNotFoundException e){
            System.out.println("Error in getdict(). File not found . . . ");
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getDict();
        listFile(new File(getfinalpath("src/sample/listDictionary/")));

    }

}
