package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    public TextField inputText;
    public Button clickbutton;
    public TextArea outputText;

    public void handleClose(ActionEvent ac){
        System.out.println("handleClose action");
    }

    public void edit(MouseEvent mouseEvent){

    }

    public void printOutput(ActionEvent actionEvent){
        outputText.setText(inputText.getText());
    }

}
