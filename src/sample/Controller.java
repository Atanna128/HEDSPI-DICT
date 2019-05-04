package sample;

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
import java.util.TreeMap;



public class Controller implements Initializable {
    @FXML
    public TextField inputText;
    public Button clickbutton;
    public TextArea outputText;
    public ImageView editbutton;

    public Menu openrecent;
    public ListView dictList;


    //using screencontroller to create multi screen . . .

    //done
    public void edit(MouseEvent mouseEvent){
        if(outputText.isEditable()){
            outputText.setEditable(false);
            System.out.println("Defination now can not edit anymore!");
        }else {
            outputText.setEditable(true);
            System.out.println("Defination now can edit again!");
        }
    }

    // đợi hàm getTreeViewItem làm xong đã rồi fix sau :3
    public void searching(ActionEvent event){
        System.out.println("Searching for the meaning of the word . . .");
        String searchword = inputText.getText();
        String meaning = "";
        meaning = getTreeViewItem(dictList.getRoot(),searchword).getValue();
        System.out.println(meaning);
        outputText.setText(meaning);
    }

    // get ready to be deleted  :))
    public TreeItem<String> getTreeViewItem(TreeItem<String> item , String value) throws NullPointerException{
        if (item != null && item.getValue().equals(value))
            return  item;

        for (TreeItem<String> child : item.getChildren()){
            TreeItem<String> s=getTreeViewItem(child, value);
            if(s!=null)
                return s;

        }
        TreeItem<String> abc = new TreeItem<>("cant find");
//        return null;
        return abc;
    }



//    initialize
    public void getDict(){
        try {


            TreeItem<String> root = new TreeItem<>("Root");
            root.setValue("List of word");
            dictList.setRoot(root);
            dictList.setShowRoot(false);
            root.setExpanded(true);

            TreeMap<String,String> dictionary;
            Dict dict = new Dict();
            dictionary = dict.read("C:\\Users\\buing\\IdeaProjects\\finalform\\src\\sample\\listDictionary\\textfield");

            for (Map.Entry<String,String> entry: dictionary.entrySet()) {
                String key   = entry.getKey();
                String value = entry.getValue();

                TreeItem<String> next = new TreeItem<>(key);
                TreeItem<String> next2 = new TreeItem<>(value);
                next.setExpanded(false);
                root.getChildren().add(next);
                next.getChildren().add(next2);
            }
            TreeItem<String> demo = new TreeItem<>();
            String test;
            test = root.getChildren().get(2).getValue();
            demo = root.getChildren().get(2);



        }catch (FileNotFoundException e){
            System.out.println("File not found . . . ");
            e.printStackTrace();
        }
    }


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
