package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import model.CsvReader;
import model.KolRanking;
import utils.*;

import java.io.File;
import java.util.List;

public class SearchController {

    private final CsvReader csvReader;
    private final TableView<ObservableList<String>> tableView;
    private final ComboBox<String> searchBox;

    public SearchController(ComboBox<String> searchBox, TableView<ObservableList<String>> tableView) {
        this.csvReader = new CsvReader();
        this.searchBox = searchBox;
        this.tableView = tableView;

        
    }


    public void loadSearchResults() {
        String keyword = searchBox.getValue();
        if (keyword == null || keyword.isEmpty()) {
            AlertUtils.showError("Error", "Please select a keyword to search.");
            return;
        }

        clearTable();

        
        File csvFile = new File("src/main/resources/data", keyword + ".csv");
        if (csvFile.exists()) {
            loadCsvFile(csvFile);
        } else {
            AlertUtils.showError("Error", "File not found for keyword: " + keyword);
        }
    }


    private void clearTable() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
    }

    /**
     *
     *
     * @param file 
     */
    private void loadCsvFile(File file) {
        try {
            List<KolRanking> data = csvReader.readKolData(file.getName().replace(".csv", ""));
            populateTable(data);
        } catch (Exception e) {
            AlertUtils.showError("Error", "Failed to load CSV file: " + file.getName());
        }
    }

    /**
     * 
     *
     * @param data 
     */
    private void populateTable(List<KolRanking> data) {
        if (data.isEmpty()) {
            AlertUtils.showInfo("Info", "No data found.");
            return;
        }


        if (tableView.getColumns().isEmpty()) {
            TableColumn<ObservableList<String>, String> rankColumn = new TableColumn<>("Rank");
            rankColumn.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(0)));
            tableView.getColumns().add(rankColumn);

            TableColumn<ObservableList<String>, String> usernameColumn = new TableColumn<>("Username");
            usernameColumn.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(1)));
            usernameColumn.setCellFactory(col -> createClickableCell());
            tableView.getColumns().add(usernameColumn);

            TableColumn<ObservableList<String>, String> rankingColumn = new TableColumn<>("Ranking Points");
            rankingColumn.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(2)));
            tableView.getColumns().add(rankingColumn);
        }

        for (KolRanking kol : data) {
            ObservableList<String> row = FXCollections.observableArrayList(String.valueOf(kol.getRank()), kol.getUsername(), 
                    String.valueOf(kol.getRankingPoint()));
            tableView.getItems().add(row);
        }
    }

    /**
     * 
     *
     * @return 
     */
    private TableCell<ObservableList<String>, String> createClickableCell() {
        return new TableCell<>() {
            @Override
            protected void updateItem(String username, boolean empty) {
                super.updateItem(username, empty);
                if (empty || username == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(username);
                    setStyle("-fx-text-fill: blue; -fx-underline: true;");
                    setOnMouseClicked(event -> OpenLink.openLinkInBrowser("https://x.com/" + username));
                }
            }
        };
    }


}
