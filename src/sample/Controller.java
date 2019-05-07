package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;

import java.io.*;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {
    @FXML
    public TextField inputText;
    public Button clickbutton;
    public TextArea outputText;
    public ImageView editbutton;
    public Menu openrecent;
    public ListView dictList;
    public Button add;
    public ImageView deletebutton;

    public  TreeMap<String,String> dictionary;
    public String dictname;


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
        // đúng ra là phải dùng ObservableList để setItem cho ListView, do hàm getItem.add chỉ là copy dữ liệu
        // nếu dùng ObservableList thì chỉ cần addItem là ListView sẽ tự update khi OL thay đổi
        updateListView();
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

    //working on it
    public void delete(MouseEvent event){
        String word;
        word = " " + inputText.getText();
        dictionary.remove(word);
        updateListView();
        updateToFile();

    }

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
            String filename = "C:\\Users\\buing\\IdeaProjects\\finalform\\src\\sample\\listDictionary\\textfield";
            FileWriter writer = new FileWriter(filename);
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

    //initialize
    public void getDict(){
        try {
            File file = new File("sample/listDictionary/textfield");

            String filename = "C:\\Users\\buing\\IdeaProjects\\finalform\\src\\sample\\listDictionary\\textfield";
            Dict dict = new Dict();
            Scanner scanner = new Scanner(new File(filename));
            dictionary = dict.read(filename);
            dictname = dict.getdictname(scanner);
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

    //done
    //list all file in folder listDictionary
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

    private String makechange(String getfile) {
        String out = "";
        for (int i = 0 ; i < getfile.length() ; i++){
            if (getfile.charAt(i) ==  '\\'){
                out = out + "/";
            }else out = out + getfile.charAt(i);
        }
        return  out;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getDict();
        File file = new File("src/sample/listDictionary/");
        String getfile = file.getAbsolutePath();
        getfile = makechange(getfile);
        listFile(new File(getfile));


    }


}
