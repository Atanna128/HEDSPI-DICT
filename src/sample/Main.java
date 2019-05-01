package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.TreeMap;

public class Main extends Application {
    @FXML
    public TreeView dictList;

    TreeMap<String,String> map = new TreeMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 960, 600);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(scene);
        primaryStage.show();
        setupDict();


    }

    public void setupDict(){
        try {
            Dict dict = new Dict();
            map = dict.read("C:\\Users\\buing\\IdeaProjects\\finalform\\src\\sample\\listDictionary\\textfield");
        }catch (FileNotFoundException e){
            System.out.println("File not found . . . ");
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
