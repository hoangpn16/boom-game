package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Rms {
    public static String appData = System.getProperty("user.home") + "\\AppData\\LocalLow\\BoomberMan\\";

    public static void saveRMS(String filePath, String data) {
        try {
            File file = new File(appData + filePath);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileWriter fw = new FileWriter(file);
                fw.write(data);
                fw.close();
            } catch (IOException ex) {

            }

        } catch (Exception e) {

        }
    }

    public static String loadRMS(String filePath) {
        String data = "-1";
        try {
            File file = new File(appData + filePath);
            if (!file.exists()) {
                Rms.saveRMS(filePath, "0");
                return data;
            }

            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    data = line;
                    break;
                }
                fr.close();
                br.close();
            } catch (Exception ex) {

            }
        } catch (Exception e) {
        }
        return data;
    }

    public static boolean[][] readMap(int xChar) {
        boolean[][] array = new boolean[15][15];
        for (int i = 0; i < 15; i++) {
            Arrays.fill(array[i], Boolean.FALSE);
        }

        try {

            String text = Rms.class.getResource(Res.FILE_MAP).toString();
            FileReader fr = new FileReader(text.substring(6, text.length()));
            BufferedReader br = new BufferedReader(fr);
            String line;
            int n = 0;
            while ((line = br.readLine()) != null) {
                if (n >= 1 && n < 16) {
                    for (int j = 1; j < line.length() - 1; j = j + 3) {
                        int num = j / 3;
                        if (line.codePointAt(j) == xChar && !array[num][n - 1]) {
                            array[num][n - 1] = true;
                        }
                    }
                }
                n++;
                if (n >= 16) {
                    break;
                }
            }
            fr.close();
            br.close();


            //File f = new File(Rms.class.getResource(Res.FILE_MAP).toString());
//            List<String> line = Files.readAllLines(Paths.get(Rms.class.getResource(Res.FILE_MAP).toString()));
//            for (int i = 1; i < 16; i++) {
//                String text = line.get(i);
//                for (int j = 1; j < text.length() - 1; j = j + 3) {
//                    int num = j / 3;
//                    if (text.codePointAt(j) == xChar && !array[num][i - 1]) {
//                        array[num][i - 1] = true;
//                    }
//                }
//            }
        } catch (Exception ex) {
        }
        return array;
    }
}
