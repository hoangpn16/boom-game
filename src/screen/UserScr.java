package screen;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.GameCanvas;
import sample.Images;
import sample.Res;

public class UserScr {
    public static Scene getScene(Stage stage) {
        Label labelName = new Label(Res.name);
        labelName.setFont(Font.loadFont(UserScr.class.getResource(Res.FONT).toString(), 30));
        labelName.setStyle("-fx-text-fill: #7FFF00;");

        labelName.setTranslateY(-170);

        TextField textField = new TextField();
        textField.setPromptText("name...");
        textField.setFont(Font.loadFont(UserScr.class.getResource(Res.FONT).toString(), 25));
        textField.setStyle("-fx-text-fill: #7FFF00;");
        textField.setMaxWidth(500);
        textField.setMaxHeight(20);
        textField.setTranslateY(-80);

        Button backButton = new Button(Res.menuSetting[1]);
        backButton.setFont(Font.loadFont(UserScr.class.getResource(Res.FONT).toString(), 35));
        backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
        backButton.setOnMouseEntered(t -> backButton.setStyle("-fx-background-color:#FFF5EE; -fx-text-fill: #7FFF00 "));
        backButton.setOnMouseExited(t -> backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00"));
        backButton.setTranslateX(435);
        backButton.setTranslateY(255);

        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent12 -> {
            stage.setScene(MainScr.getScene(stage));
        });

        StackPane stackPane3 = new StackPane();
        ImageView imageView4 = new ImageView(Images.img_BackGroundMain);
        imageView4.setPreserveRatio(true);
        imageView4.setFitHeight(GameCanvas.GAME_HEIGHT);

        stackPane3.getChildren().addAll(imageView4, textField, labelName, backButton);

        ImageView[] imageView = new ImageView[3];
        Button[] stackPanes = new Button[3];

        for (int i = 0; i < 3; i++) {
            imageView[i] = new ImageView(Images.img_Bomber[i * 2 + 1]);
            stackPanes[i] = new Button();
            int finalI = i;
            stackPanes[i].setStyle("-fx-background-color:transparent");
            imageView[i].setFitWidth(100);
            imageView[i].setFitHeight(100);
            stackPanes[i].setGraphic(imageView[i]);
            //stackPanes[i].getChildren().addAll(imageView[i]);
            stackPanes[i].setMinSize(120, 150);
            stackPanes[i].setMaxSize(120, 150);

            stackPanes[i].setOnMouseEntered(t -> stackPanes[finalI].setStyle("-fx-background-color:#FFF5EE; -fx-background-radius:20"));
            stackPanes[i].setOnMouseExited(t -> stackPanes[finalI].setStyle("-fx-background-color:transparent;"));
            stackPanes[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!textField.getText().equals("")) {
                        stackPanes[finalI].setStyle("-fx-background-color:yellow");
                        GameScr gameScr = new GameScr();
                        gameScr.initGameScr();
                        gameScr.createChar(textField.getText(), finalI);
                        stage.setScene(gameScr.getScene(stage));
                    }
                }
            });
            stackPane3.getChildren().addAll(stackPanes[i]);
        }
        stackPanes[0].setTranslateX(-180);
        stackPanes[0].setTranslateY(80);


        stackPanes[1].setTranslateY(80);


        stackPanes[2].setTranslateX(180);
        stackPanes[2].setTranslateY(80);


        Scene setWal = new Scene(stackPane3, GameCanvas.GAME_WIDTH, GameCanvas.GAME_HEIGHT);
        return setWal;
    }


}
