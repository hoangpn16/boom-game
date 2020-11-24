package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Sounds {

    public static MediaPlayer mainMusic;

    public static MediaPlayer gameMusic;

    public static MediaPlayer sound_TS;

    public static MediaPlayer bomb_bang;

    public static MediaPlayer bomb_new;

    public static MediaPlayer pickItem;

    public static MediaPlayer mobAttack;

    private static MediaPlayer getMusic(String url) {
        MediaPlayer mediaPlayer = new MediaPlayer(
                new Media(Sounds.class.getResource(url).toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(mediaPlayer.getVolume() * GameCanvas.volume);
        return mediaPlayer;
    }

    private static MediaPlayer getMusicOneShot(String url) {
        MediaPlayer mediaPlayer = new MediaPlayer(
                new Media(Sounds.class.getResource(url).toString()));
        mediaPlayer.setVolume(mediaPlayer.getVolume() * GameCanvas.volume);
        return mediaPlayer;
    }

    public static void loadSound() {
        Sounds.gameMusic = getMusic(Res.MUSIC_GAME);
        Sounds.mainMusic = getMusic(Res.MUSIC_MAIN);
        Sounds.sound_TS = getMusicOneShot(Res.MUSIC_TS);
        Sounds.bomb_bang = getMusicOneShot(Res.MUSIC_BANG);
        Sounds.bomb_new = getMusicOneShot(Res.MUSIC_NewBOM);
        Sounds.pickItem = getMusicOneShot(Res.MUSIC_PICK);
        Sounds.mobAttack = getMusicOneShot(Res.MUSIC_ATTACK);
        if (GameCanvas.volume != 0) {
            Sounds.pickItem.setVolume(1);
            Sounds.mobAttack.setVolume(1);
        }
    }
}
