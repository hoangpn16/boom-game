package screen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.*;

import java.util.Optional;

public class MainScr {
    public static Scene getScene(Stage stage) {
        Sounds.gameMusic.stop();
        ImageView imageView = new ImageView(Images.img_BackGroundMain);
        imageView.setPreserveRatio(true);
        VBox menuOption = new VBox(10);
        menuOption.setPrefWidth(100);
        menuOption.setPrefHeight(35);
        menuOption.setAlignment(Pos.CENTER);

        Label label = new Label("");
        ImageView logoView = new ImageView(Images.img_Logo);
        logoView.setFitHeight(200);
        logoView.setPreserveRatio(true);
        label.setGraphic(logoView);
        menuOption.getChildren().add(label);
        VBox.setMargin(label, new Insets(0, 0, 20, 0));

        Button[] button = new Button[Res.menuMain.length];
        for (int i = 0; i < button.length; i++) {
            button[i] = new Button(Res.menuMain[i]);
            button[i].setMinHeight(menuOption.getPrefHeight());
            button[i].setMinWidth(menuOption.getPrefWidth());
            button[i].setFont(Font.loadFont(MainScr.class.getResource(Res.FONT).toString(), 30));
            button[i].setStyle("-fx-background-color:transparent; -fx-text-fill: #f4c20d");
            int finalI = i;
            button[i].setOnMouseEntered(t -> button[finalI].setStyle("-fx-background-color:#e5f3ff; -fx-text-fill:#f4c20d "));

            int finalI1 = i;
            button[i].setOnMouseExited(t -> button[finalI1].setStyle("-fx-background-color:transparent; -fx-text-fill: #f4c20d"));
            menuOption.getChildren().add(button[i]);
        }

        button[0].setOnMouseClicked(event -> {
            stage.setScene(TutorialScr.getScene(stage));
        });

        button[1].setOnMouseClicked(event -> {
            stage.setScene(HighScoreScr.getScene(stage));
        });

        button[2].setOnMouseClicked(event -> {
            stage.setScene(SettingScr.getScene(stage));
        });

        button[3].setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, Res.notification_Exit,
                    ButtonType.NO, ButtonType.YES);
            alert.setHeaderText("");
            alert.setTitle("?");
            alert.initOwner(stage);
            Optional<ButtonType> type = alert.showAndWait();
            if (type.isPresent() && type.get() == ButtonType.YES) {
                GameCanvas.exit();
            }
        });

        Label version = new Label(GameCanvas.LINK);
        version.setFont(Font.loadFont(MainScr.class.getResource(Res.FONT).toString(), 16));
        version.setStyle("-fx-text-fill: #7FFF00;");
        version.setTranslateX(400);
        version.setTranslateY(-285);
        Label link = new Label("v" + GameCanvas.VERSION);
        link.setFont(Font.loadFont(MainScr.class.getResource(Res.FONT).toString(), 16));
        link.setStyle("-fx-text-fill: #7FFF00;");
        link.setTranslateX(480);
        link.setTranslateY(-260);

        Sounds.mainMusic.play();
        MediaView mediaView = new MediaView(Sounds.mainMusic);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mediaView, imageView, menuOption, link, version);
        Scene sceneMain = new Scene(stackPane, GameCanvas.GAME_WIDTH, GameCanvas.GAME_HEIGHT);
        return sceneMain;
    }
}
