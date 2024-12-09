package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.ResourcesPaths;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class HomeView {

    private VBox root;
    

    public HomeView() {
        Label title = new Label("BKOLRanking");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.DARKRED);

        Label subtitle = new Label("Having a relatively statistics and analysis about  the popularity of Blockchain KOLs");
        subtitle.setFont(Font.font("Arial", 16));

        VBox titleBox = new VBox(10, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        // Các ô thông tin
        HBox infoBox = new HBox(20);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setPadding(new Insets(20));

        infoBox.getChildren().addAll(
                createInfoCard("19,000+", "PageRank - Graph Nodes"),
                createInfoCard("300+", "KOLs"),
                createInfoCard("15,000+", "Followers"),
                createInfoCard("30,000+", "Tweets")
        );

        // Phần Challenge Problem
        Label challengeTitle = new Label("Techniques");
        challengeTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        Label challengeContent = new Label("Selenium Automation, Page - Rank Algorithm, JavaFX");
        challengeContent.setFont(Font.font("Arial", 14));

        VBox challengeBox = new VBox(10, challengeTitle, challengeContent);
        challengeBox.setPadding(new Insets(20));

        // Phần từ khóa phổ biến
        Label keywordsTitle = new Label("HashTags");
        keywordsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        Label keywordsContent = new Label("Blockchain, Web3, Crypto,...");
        keywordsContent.setWrapText(true);
        keywordsContent.setFont(Font.font("Arial", 14));

        VBox keywordsBox = new VBox(10, keywordsTitle, keywordsContent);
        keywordsBox.setPadding(new Insets(20));

        // Bố cục chính
        HBox bottomBox = new HBox(20, challengeBox, keywordsBox);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(20));

        root = new VBox(20, titleBox, infoBox, bottomBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        BackgroundImage backgroundImage = new BackgroundImage(
        new Image(ResourcesPaths.pathOfHomeBackground),
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,
        new BackgroundSize(100, 100, true, true, true, false) 
        );

        root.setBackground(new Background(backgroundImage));

    }

    private VBox createInfoCard(String value, String label) {
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        valueLabel.setTextFill(Color.DARKBLUE);

        Label textLabel = new Label(label);
        textLabel.setFont(Font.font("Arial", 14));

        VBox card = new VBox(5, valueLabel, textLabel);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-color: lightgray; -fx-border-width: 2px; -fx-background-color: #f8f8f8;");
        return card;
    }

    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }

    
}
