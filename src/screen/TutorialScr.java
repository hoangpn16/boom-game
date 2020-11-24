package screen;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.GameCanvas;
import sample.Images;
import sample.Res;

public class TutorialScr {
    public static Scene getScene(Stage stage) {
        VBox menuOption2 = new VBox(10);
        menuOption2.setPrefWidth(100);
        menuOption2.setPrefHeight(35);
        menuOption2.setAlignment(Pos.CENTER);
        Label labelHDSD = new Label(Res.tutorial);
        labelHDSD.setFont(Font.loadFont(TutorialScr.class.getResource(Res.FONT).toString(), 60));
        labelHDSD.setStyle("-fx-text-fill: #FF0000;");
        menuOption2.getChildren().add(labelHDSD);
        VBox.setMargin(labelHDSD, new Insets(0, 0, 20, 0));

        GridPane gridpane = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints(150);
        ColumnConstraints col2 = new ColumnConstraints(350);
        gridpane.getColumnConstraints().addAll(col1, col2);
        gridpane.setMaxSize(500, 150);
        gridpane.setPadding(new Insets(10));
        gridpane.setVgap(10);

        Label[][] labels = new Label[5][2];

        for (int i = 0; i < 4; i++) {
            labels[i][0] = new Label("");
            ImageView logoView1 = new ImageView(Images.img_move[i]);
            logoView1.setFitHeight(50);
            logoView1.setPreserveRatio(true);
            labels[i][0].setGraphic(logoView1);
            GridPane.setHalignment(labels[i][0], HPos.CENTER);
            gridpane.add(labels[i][0], 0, i);

            labels[i][1] = new Label(Res.textMove[i]);
            labels[i][1].setFont(Font.loadFont(TutorialScr.class.getResource(Res.FONT).toString(), 30));
            labels[i][1].setStyle("-fx-text-fill: #7FFF00;");
            GridPane.setHalignment(labels[i][1], HPos.CENTER);
            gridpane.add(labels[i][1], 1, i);
        }

        labels[4][0] = new Label(Res.skillTutorial);
        GridPane.setHalignment(labels[4][0], HPos.CENTER);
        labels[4][0].setFont(Font.loadFont(TutorialScr.class.getResource(Res.FONT).toString(), 30));
        labels[4][0].setStyle("-fx-text-fill: #7FFF00;");
        gridpane.add(labels[4][0], 0, 4);

        labels[4][1] = new Label("Q,W,E,R");
        labels[4][1].setFont(Font.loadFont(TutorialScr.class.getResource(Res.FONT).toString(), 30));
        labels[4][1].setStyle("-fx-text-fill: #7FFF00;");
        GridPane.setHalignment(labels[4][1], HPos.CENTER);
        gridpane.add(labels[4][1], 1, 4);
        menuOption2.getChildren().add(gridpane);

        Button OKButton = new Button("OK");
        OKButton.setFont(Font.loadFont(TutorialScr.class.getResource(Res.FONT).toString(), 35));
        OKButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
        OKButton.setOnMouseEntered(t -> OKButton.setStyle("-fx-background-color:#FFF5EE; -fx-text-fill: #7FFF00 "));
        OKButton.setOnMouseExited(t -> OKButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00"));
        OKButton.setTranslateX(455);
        OKButton.setTranslateY(255);

        OKButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent1 -> {
            stage.setScene(UserScr.getScene(stage));
        });

        Button backButton = new Button(Res.menuSetting[1]);
        backButton.setFont(Font.loadFont(TutorialScr.class.getResource(Res.FONT).toString(), 35));
        backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
        backButton.setOnMouseEntered(t -> backButton.setStyle("-fx-background-color:#FFF5EE; -fx-text-fill: #7FFF00 "));
        backButton.setOnMouseExited(t -> backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00"));
        backButton.setTranslateX(-435);
        backButton.setTranslateY(255);

        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent12 -> {
            stage.setScene(MainScr.getScene(stage));
        });

        StackPane stackPane2 = new StackPane();
        ImageView imageView3 = new ImageView(Images.img_BackGroundMain);
        imageView3.setPreserveRatio(true);
        imageView3.setFitHeight(GameCanvas.GAME_HEIGHT);
        stackPane2.getChildren().addAll(imageView3, menuOption2, backButton, OKButton);
        Scene setWal = new Scene(stackPane2, GameCanvas.GAME_WIDTH, GameCanvas.GAME_HEIGHT);
        return setWal;
    }

}
