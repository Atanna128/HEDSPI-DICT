package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addDictController implements Initializable {
    public TextField dictName;
    public Label warning;

    public void AddtoMainScene(ActionEvent event) throws IOException {
        String dictname = dictName.getText();
        check();

        backToMainScene(event);
    }

    private void check() {
    }

    public void backToMainScene(ActionEvent event) throws IOException {
        Parent addParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene addScene =new Scene(addParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

