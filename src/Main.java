import javafx.application.Application;
import javafx.stage.Stage;
import sample.*;
import score.HighScore;
import screen.MainScr;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle(GameCanvas.GAME_NAME);
        stage.setScene(MainScr.getScene(stage));
        stage.getIcons().add(Images.img_icon);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        Main.loadData();
        launch(args);
    }

    public static void loadData() throws IOException {
        String appData = System.getProperty("user.home") + "\\AppData\\LocalLow";
        File folder = new File(appData, "\\BoomberMan");
        if (!folder.exists()) {
            folder.mkdir();
        }
        int language = Integer.parseInt(Rms.loadRMS("language"));
        if (language == 1) {
            GameCanvas.language = 1;
        }
        double loadVolume = Double.parseDouble(Rms.loadRMS("volume"));
        if (loadVolume == -1) {
            GameCanvas.volume = 0.3;
        } else {
            GameCanvas.volume = loadVolume;
        }
        Images.loadImages();
        Sounds.loadSound();
        HighScore.load();
    }
}
