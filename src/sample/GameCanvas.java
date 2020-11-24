package sample;

import javafx.application.Platform;

public class GameCanvas {
    public static final String GAME_NAME = "Bomber Man";
    public static final int GAME_WIDTH = 1024;
    public static final int GAME_HEIGHT = 600;
    public static double volume = 3;
    public static int language = 0;
    public static final String VERSION = "0.0.1";
    public static final String LINK = "https://uet.vnu.edu.vn/";
    public static int gameTicks;

    public static void exit(){
        Platform.exit();
    }
}
