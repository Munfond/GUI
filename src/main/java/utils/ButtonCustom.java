package utils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ButtonCustom {
    public static Button createButtonWithIcon(String text, Image iconImage) {
        // Tạo HBox chứa văn bản và icon
        HBox hbox = new HBox();
        hbox.setSpacing(10); // Khoảng cách giữa văn bản và icon
    
        // Tạo Label cho văn bản
        Label textLabel = new Label(text);
        textLabel.setStyle("-fx-text-fill: black; -fx-font-size: 14px;"); // Thiết lập màu và kích thước font
    
        // Tạo ImageView cho icon
        ImageView iconView = new ImageView(iconImage);
        iconView.setFitWidth(20); // Đặt kích thước icon
        iconView.setFitHeight(20);
    
        // Thêm văn bản và icon vào HBox
        hbox.getChildren().addAll(iconView, textLabel);
    
        // Tạo nút và thêm HBox vào làm nội dung
        Button button = new Button();
        button.setGraphic(hbox); // Đặt HBox làm nội dung của nút
        button.setStyle("-fx-background-color: transparent; -fx-cursor: hand;"); // Nền trong suốt và trỏ chuột kiểu "hand"
    
        // Đặt kích thước cố định cho nút nếu cần
        button.setPrefWidth(200);
    
        return button;
    }
    
}
