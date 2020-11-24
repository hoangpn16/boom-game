package score;

import sample.Rms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScore {
    public static List<Gamer> gamers = new ArrayList<>();

    public static void addGamer(Gamer gamer) {
        gamers.add(gamer);
        Collections.sort(gamers);
        while (gamers.size() > 5) {
            gamers.remove(gamers.size() - 1);
        }
    }

    public static void load() {
        try {
            File file = new File(Rms.appData + "highScore");
            if (!file.exists()) {
                Rms.saveRMS("highScore", "");
                return;
            }
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] text = line.split("\\|");
                    gamers.add(new Gamer(text[0], Integer.parseInt(text[1])));
                }
                fr.close();
                br.close();
            } catch (Exception ex) {

            }
        } catch (Exception e) {

        }
        Collections.sort(gamers);
    }

    public static String getString(List<Gamer> gamers) {
        StringBuilder text = new StringBuilder();
        for (Gamer gamer : gamers) {
            text.append(gamer.getName()).append("|").append(gamer.getScore()).append("\n");
        }
        return text.toString();
    }
}
