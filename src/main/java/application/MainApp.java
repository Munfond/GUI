package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import utils.ResourcesPaths;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneCustom sceneCustom = new SceneCustom();
        Image logo = new Image(ResourcesPaths.pathOfLogo);

        primaryStage.setTitle("BKOLRanking");
        primaryStage.getIcons().add(logo);
        primaryStage.setScene(sceneCustom.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
