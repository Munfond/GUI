package application;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.CsvFileNameReader;
import utils.ButtonCustom;
import utils.ResourcesPaths;
import view.ChartView;
import view.HomeView;
import view.SearchView;

public class SceneCustom {
    private Scene scene;
    public static final Image homeIcon = new Image(ResourcesPaths.pathOfHomeIcon);
    public static final  Image searchIcon = new Image(ResourcesPaths.pathOfSearchIcon);
    public static final  Image chartIcon = new Image(ResourcesPaths.pathOfChartIcon);
    public static final  Image menuIcon = new Image(ResourcesPaths.pathOfMenuIcon);

    public SceneCustom() {
        ArrayList<String> keywords = CsvFileNameReader.getFileName();
        SearchView searchView = new SearchView(keywords);
        ChartView chartView = new ChartView(keywords);
        HomeView homeView = new HomeView();




        // Tạo nút với icon và văn bản
        Button homeButton = ButtonCustom.createButtonWithIcon("Home", homeIcon);
        Button searchButton = ButtonCustom.createButtonWithIcon("Search Table", searchIcon);
        Button chartButton = ButtonCustom.createButtonWithIcon("KOL Chart", chartIcon);

        // Nút để mở rộng menu
        Button menuButton = ButtonCustom.createButtonWithIcon("Menu", menuIcon);

        // Tạo VBox chứa các nút
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.getChildren().add(menuButton); // Chỉ có menuButton khi khởi tạo

        menuButton.setOnAction(e -> {
            // Hiển thị các nút khi menuButton được nhấn
            if (buttonBox.getChildren().size() == 1) { // Nếu chỉ có menuButton
                buttonBox.getChildren().addAll(homeButton, searchButton, chartButton);
            } else { // Nếu các nút đã hiển thị, ẩn chúng đi
                buttonBox.getChildren().clear();
                buttonBox.getChildren().add(menuButton);
            }
        });

        // Tạo BorderPane và thêm menu vào phần trái
        BorderPane menuPane = new BorderPane();
        menuPane.setLeft(buttonBox);
        menuPane.setStyle("-fx-background-color: #A9A9A9; -fx-border-radius: 10; -fx-padding: 15;");

        // Tạo BorderPane chính
        BorderPane root = new BorderPane();
        root.setLeft(menuPane);
        root.setCenter(homeView.getRoot());

        // Set hành động cho các nút
        homeButton.setOnAction(e -> root.setCenter(homeView.getRoot()));
        searchButton.setOnAction(e -> root.setCenter(searchView.getRoot()));
        chartButton.setOnAction(e -> root.setCenter(chartView.getRoot()));

        // Cấu hình Scene và Stage
        this.scene = new Scene(root, 1000, 600);
        this.scene.getStylesheets().add(ResourcesPaths.pathOfStyleSheet);
    }

    public Scene getScene() {
        return scene;
    }

    
}
