package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/main.fxml"));
        Scene scene = new Scene(root, 960, 600);
        scene.getStylesheets().add(getClass().getResource("/sample/css/app.css").toExternalForm());
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
