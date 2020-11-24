package screen;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.*;

import java.util.Optional;

public class SettingScr {

    public static Scene getScene(Stage stage) {
        Slider volumeSlider = new Slider();
        volumeSlider.setValue(Sounds.mainMusic.getVolume() * 100);
        volumeSlider.setPrefWidth(500);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.setTranslateX(175);
        volumeSlider.setTranslateY(-130);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setStyle("-fx-background-color: yellow");
        volumeSlider.valueProperty().addListener(observable -> {
            Sounds.mainMusic.setVolume(volumeSlider.getValue() / 100);
            GameCanvas.volume = volumeSlider.getValue() / 100;
        });


        Label label1 = new Label(Res.textSetting[0]);
        label1.setFont(Font.loadFont(SettingScr.class.getResource(Res.FONT).toString(), 40));
        label1.setStyle("-fx-text-fill: #7FFF00;");
        label1.setScaleX(1);
        label1.setScaleY(2);
        label1.setTranslateX(-300);
        label1.setTranslateY(-140);

        Label label2 = new Label(Res.textSetting[1]);
        label2.setFont(Font.loadFont(SettingScr.class.getResource(Res.FONT).toString(), 40));
        label2.setStyle("-fx-text-fill: #7FFF00;");
        label2.setScaleX(1);
        label2.setScaleY(2);
        label2.setTranslateX(-300);
        label2.setTranslateY(45);

        ComboBox<String> language = new ComboBox<>();
        if (GameCanvas.language == 0) {
            language.getItems().addAll("English", "Vietnamese");
            language.setValue("English");
        } else {
            language.getItems().addAll("Tiếng Việt", "Tiếng Anh");
            language.setValue("Tiếng Việt");
        }
        language.setMaxSize(500, 40);
        language.getEditor().setFont(Font.loadFont(SettingScr.class.getResource(Res.FONT).toString(), 30));
        language.setStyle("-fx-font-size: 20pt;");
        language.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        final ListCell<String> cell = new ListCell<String>() {
                            {
                                super.setPrefWidth(100);
                            }

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);
                                    setFont(Font.loadFont(SettingScr.class.getResource(Res.FONT).toString(), 30));
                                    setStyle("-fx-text-fill: #7FFF00;");
                                } else {
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                });
        language.setTranslateX(175);
        language.setTranslateY(50);

        Button saveButton = new Button(Res.menuSetting[0]);
        saveButton.setFont(Font.loadFont(SettingScr.class.getResource(Res.FONT).toString(), 35));
        saveButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
        saveButton.setOnMouseEntered(t -> saveButton.setStyle("-fx-background-color:#FFF5EE; -fx-text-fill: #7FFF00 "));
        saveButton.setOnMouseExited(t -> saveButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00"));
        saveButton.setTranslateX(280);
        saveButton.setTranslateY(255);
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent12 -> {
            int index = 0;
            if (language.getSelectionModel().getSelectedItem().contains("V")) {
                index = 1;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, Res.notification_Save,
                    ButtonType.NO, ButtonType.YES);
            alert.setHeaderText("");
            alert.setTitle("?");
            alert.initOwner(stage);
            Optional<ButtonType> type = alert.showAndWait();
            if (type.isPresent() && type.get() == ButtonType.YES) {
                if (index != GameCanvas.language) {
                    GameCanvas.language = index;
                    Rms.saveRMS("language", GameCanvas.language + "");
                }
                Rms.saveRMS("volume", GameCanvas.volume + "");
                GameCanvas.exit();
            }
        });

        Button backButton = new Button(Res.menuSetting[1]);
        backButton.setFont(Font.loadFont(SettingScr.class.getResource(Res.FONT).toString(), 35));
        backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
        backButton.setOnMouseEntered(t -> backButton.setStyle("-fx-background-color:#FFF5EE; -fx-text-fill: #7FFF00 "));
        backButton.setOnMouseExited(t -> backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00"));
        backButton.setTranslateX(435);
        backButton.setTranslateY(255);

        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent12 -> {
            stage.setScene(MainScr.getScene(stage));
        });

        StackPane stackPane = new StackPane();
        ImageView imageView1 = new ImageView(Images.img_BackGroundMain);
        imageView1.setPreserveRatio(true);
        imageView1.setFitHeight(GameCanvas.GAME_HEIGHT);
        stackPane.getChildren().addAll(imageView1, label1, label2, language, volumeSlider, saveButton, backButton);
        Scene setWal = new Scene(stackPane, GameCanvas.GAME_WIDTH, GameCanvas.GAME_HEIGHT);
        return setWal;
    }

}
