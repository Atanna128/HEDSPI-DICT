package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;

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
    public TreeView<String> dictList = new TreeView<>();
    //using screencontroller to create multi screen . . .

    public void edit(MouseEvent mouseEvent){
        if(outputText.isEditable()){
            outputText.setEditable(false);
            System.out.println("Defination now can not edit anymore!");
        }else {
            outputText.setEditable(true);
            System.out.println("Defination now can edit again!");
        }
    }

    public void searching(ActionEvent event){
        System.out.println("Searching for the meaning of the word . . .");
    }

    public TreeItem getTreeViewItem(TreeItem<String> item , String value){
        if (item != null && item.getValue().equals(value))
            return  item;

        for (TreeItem<String> child : item.getChildren()){
            TreeItem<String> s=getTreeViewItem(child, value);
            if(s!=null)
                return s;

        }
        return null;
    }

//
//    public void getmeaning(ActionEvent event){
//        TreeItem<String> abc = new TreeItem<>();
//        TreeItem<String> selected = new TreeItem<>();
//        String search = inputText.getText();
//        selected = dictList.getModelItem
//        abc = getTreeViewItem()
//    }

    public void showTreeView(ActionEvent event){
        TreeItem<String> root = new TreeItem<>("Root Node");

        dictList.setRoot(root);
        dictList.setShowRoot(false);
        root.setExpanded(true);
        root.getChildren().addAll(
                new TreeItem<>("Item 1"),
                new TreeItem<>("Item 2"),
                new TreeItem<>("Item 3")
        );
    }

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
            System.out.println("test = " + test);


        }catch (FileNotFoundException e){
            System.out.println("File not found . . . ");
            e.printStackTrace();
        }
    }

    public void printOutput(){
        outputText.setText(inputText.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getDict();
    }
}
