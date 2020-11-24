package screen;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.GameCanvas;
import sample.Images;
import sample.Res;
import score.HighScore;

public class HighScoreScr {
    public static Scene getScene(Stage stage) {
        VBox menuOption1 = new VBox(10);
        menuOption1.setPrefWidth(100);
        menuOption1.setPrefHeight(35);
        menuOption1.setAlignment(Pos.CENTER);
        Label labelHigh = new Label(Res.menuMain[1]);
        labelHigh.setFont(Font.loadFont(HighScoreScr.class.getResource(Res.FONT).toString(), 60));
        labelHigh.setStyle("-fx-text-fill: #FF0000;");
        menuOption1.getChildren().add(labelHigh);

        GridPane gridPane = new GridPane();
        gridPane.setMaxSize(750, 150);
        gridPane.setPadding(new Insets(10));
        gridPane.setGridLinesVisible(true);

        Label[] lbl_tile = new Label[3];
        for (int i = 0; i < 3; i++) {
            lbl_tile[i] = new Label(Res.highScoreInfo[i]);
            lbl_tile[i].setFont(Font.loadFont(HighScoreScr.class.getResource(Res.FONT).toString(), 35));
            lbl_tile[i].setStyle("-fx-text-fill: #7FFF00;");
            gridPane.add(lbl_tile[i], i, 0);
            GridPane.setHalignment(lbl_tile[i], HPos.CENTER);
        }

        Label[][] lbl_High = new Label[5][3];

        for (int i = 0; i < 5; i++) {

            if (HighScore.gamers.size() > i) {
                lbl_High[i][0] = new Label(String.valueOf(i + 1));
                lbl_High[i][0].setFont(Font.loadFont(HighScoreScr.class.getResource(Res.FONT).toString(), 35));
                lbl_High[i][0].setStyle("-fx-text-fill: #7FFF00;");
                lbl_High[i][1] = new Label(HighScore.gamers.get(i).getName());
                lbl_High[i][1].setFont(Font.loadFont(HighScoreScr.class.getResource(Res.FONT).toString(), 35));
                lbl_High[i][1].setStyle("-fx-text-fill: #7FFF00;");
                lbl_High[i][2] = new Label(HighScore.gamers.get(i).getScore() + "");
                lbl_High[i][2].setFont(Font.loadFont(HighScoreScr.class.getResource(Res.FONT).toString(), 35));
                lbl_High[i][2].setStyle("-fx-text-fill: #7FFF00;");
            } else {
                lbl_High[i][0] = new Label("...");
                lbl_High[i][0].setFont(Font.loadFont(HighScoreScr.class.getResource(Res.FONT).toString(), 35));
                lbl_High[i][0].setStyle("-fx-text-fill: #7FFF00;");
                lbl_High[i][1] = new Label("...");
                lbl_High[i][1].setFont(Font.loadFont(HighScoreScr.class.getResource(Res.FONT).toString(), 35));
                lbl_High[i][1].setStyle("-fx-text-fill: #7FFF00;");
                lbl_High[i][2] = new Label("...");
                lbl_High[i][2].setFont(Font.loadFont(HighScoreScr.class.getResource(Res.FONT).toString(), 35));
                lbl_High[i][2].setStyle("-fx-text-fill: #7FFF00;");
            }
            GridPane.setHalignment(lbl_High[i][0], HPos.CENTER);
            GridPane.setHalignment(lbl_High[i][1], HPos.CENTER);
            GridPane.setHalignment(lbl_High[i][2], HPos.CENTER);
            gridPane.add(lbl_High[i][0], 0, i + 1);
            gridPane.add(lbl_High[i][1], 1, i + 1);
            gridPane.add(lbl_High[i][2], 2, i + 1);
        }

        Button backButton = new Button(Res.menuSetting[1]);
        backButton.setFont(Font.loadFont(HighScoreScr.class.getResource(Res.FONT).toString(), 35));
        backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
        backButton.setOnMouseEntered(t -> backButton.setStyle("-fx-background-color:#FFF5EE; -fx-text-fill: #7FFF00 "));
        backButton.setOnMouseExited(t -> backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00"));
        backButton.setTranslateX(435);
        backButton.setTranslateY(255);

        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent12 -> {
            stage.setScene(MainScr.getScene(stage));
        });

        menuOption1.getChildren().add(gridPane);
        StackPane stackPane1 = new StackPane();
        ImageView imageView2 = new ImageView(Images.img_BackGroundMain);
        imageView2.setPreserveRatio(true);
        imageView2.setFitHeight(GameCanvas.GAME_HEIGHT);
        stackPane1.getChildren().addAll(imageView2, menuOption1, backButton);
        Scene setWal = new Scene(stackPane1, GameCanvas.GAME_WIDTH, GameCanvas.GAME_HEIGHT);
        return setWal;
    }
}
