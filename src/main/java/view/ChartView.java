package view;

import java.util.ArrayList;

import application.SceneCustom;
import controller.ChartController;
import javafx.collections.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.*;

public class ChartView {

    private final BorderPane root;
    private final BarChart<String, Number> barChart;
    private final ComboBox<String> keywordComboBox;
    private final ChartController chartController;

    public ChartView(ArrayList<String> keywords) {
        // Khởi tạo ChartController
        chartController = new ChartController();

        // Root layout
        root = new BorderPane();

        // Header
        VBox header = new VBox();
        header.setStyle("-fx-padding: 10; -fx-alignment: center;");
        javafx.scene.control.Label headerLabel = new javafx.scene.control.Label("KOL Chart");
        headerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        header.getChildren().add(headerLabel);

        // Keyword ComboBox
        keywordComboBox = new ComboBox<>();
        keywordComboBox.setItems(FXCollections.observableArrayList(keywords));
        keywordComboBox.setPromptText("Select a keyword");
        keywordComboBox.setStyle("-fx-background-color: #A9A9A9");

        // Load Chart Button
        Button loadChartButton = new Button();
        ImageView imageView = new ImageView(SceneCustom.chartIcon);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        loadChartButton.setGraphic(imageView);

        // Search bar
        HBox searchBar = new HBox(10, keywordComboBox, loadChartButton);
        searchBar.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Bar chart setup
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Username");
        yAxis.setLabel("Ranking Points");
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Top 10 KOLs Ranking");

        // Layout setup
        VBox layout = new VBox(10, header, searchBar, barChart);
        layout.setStyle("-fx-padding: 20;");
        root.setCenter(layout);
    }

    public BorderPane getRoot() {
        return root;
    }

    private void loadChart(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            AlertUtils.showAlert("Error", "Please select a keyword.", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Lấy dữ liệu từ ChartController
            ObservableList<XYChart.Data<String, Number>> chartData = chartController.getChartData(keyword);

            // Xóa dữ liệu cũ và thêm dữ liệu mới vào biểu đồ
            barChart.getData().clear();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Top 10 for " + keyword);
            series.getData().addAll(chartData);
            barChart.getData().add(series);

            // Điều chỉnh giao diện biểu đồ
            CategoryAxis xAxis = (CategoryAxis) barChart.getXAxis();
            xAxis.setTickLabelRotation(45);
            barChart.setBarGap(5);
            barChart.setCategoryGap(30);

        } catch (Exception ex) {
            AlertUtils.showAlert("Error", "Failed to load chart data: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
