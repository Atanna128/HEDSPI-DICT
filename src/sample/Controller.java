package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;

import java.io.FileNotFoundException;
import java.util.TreeMap;


public class Controller {
    public Dict dict = new Dict();
    public TextField inputText;
    public Button clickbutton;
    public TextArea outputText;
    public ImageView editbutton;
    public TreeView dictList;
        //using screencontroller to create multi screen . . .

    public void handleClose(ActionEvent ac){
        System.out.println("handleClose action");
    }

    public void edit(MouseEvent mouseEvent){
        if(outputText.isEditable()){
            outputText.setEditable(false);
        }else outputText.setEditable(true);
    }

    public void getDict(){
        try {
            TreeMap<String,String> dictionary = new TreeMap<>();
            Dict dict = new Dict();
            dict.read("C:\\Users\\buing\\IdeaProjects\\finalform\\src\\sample\\listDictionary\\textfield");
            System.out.println("done setupdict");
        }catch (FileNotFoundException e){
            System.out.println("File not found . . . ");
            e.printStackTrace();
        }
    }

    public void printOutput(){
        outputText.setText(inputText.getText());
    }

}
