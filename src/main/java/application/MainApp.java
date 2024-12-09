package application;

import javafx.application.Application;
import javafx.stage.Stage;


import model.ImageFiles;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneCustom sceneCustom = new SceneCustom();

        primaryStage.setTitle("BKOLRanking");
        primaryStage.getIcons().add(ImageFiles.logo);
        primaryStage.setScene(sceneCustom.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
