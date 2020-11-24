package staticObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.Images;
import screen.GameScr;

import java.util.Arrays;

public class Bomb extends StaticObject {
    private int timeBang;
    private int dame;

    public Bomb() {

    }

    public Bomb(int x, int y, Image image, int dame) {
        this.x = 40 * ((int) (x + 20) / 40) + 5;
        this.y = 40 * ((int) (y + 20) / 40) + 5;
        this.img = image;
        this.timeBang = 4;
        this.dame = dame;
    }

    @Override
    public void paint(GraphicsContext g) {
        g.drawImage(this.img, x, y);
        if (timeBang < 2) {
            paintBang(g);
        }

    }

    public void paintBang(GraphicsContext g) {
        this.setImg(Images.img_bang[0]);
        boolean[] arr = isImpactRock();
        if (arr[0]) {
            g.drawImage(Images.img_bang[1], x + 25, y - 5);
        }
        if (arr[1]) {
            g.drawImage(Images.img_bang[2], x - 40, y - 5);
        }
        if (arr[2]) {
            g.drawImage(Images.img_bang[3], x - 5, y - 40);
        }
        if (arr[3]) {
            g.drawImage(Images.img_bang[4], x, y + 20);
        }

    }

    public int getTimeBang() {
        return timeBang;
    }

    public void setTimeBang(int timeBang) {
        this.timeBang = timeBang;
    }

    private boolean[] isImpactRock() {
        boolean[] array = new boolean[4];
        Arrays.fill(array, Boolean.TRUE);
        if (this.x - 5 <= 0) {
            array[1] = false;
        }
        if (this.x + 35 >= 600) {
            array[0] = false;
        }
        if (this.y - 5 <= 0) {
            array[2] = false;
        }
        if (this.y + 35 >= 600) {
            array[3] = false;
        }
        int x = this.x - 5;
        int y = this.y - 5;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (GameScr.arrayRock[i][j]) {
                    int cx = 40 * i;
                    int cy = 40 * j;
                    if (cx + 40 == x && y == cy) {
                        array[1] = false;
                    }
                    if (cx - 40 == x && y == cy) {
                        array[0] = false;
                    }
                    if (cx == x && cy + 40 == y) {
                        array[2] = false;
                    }
                    if (cx == x && cy - 40 == y) {
                        array[3] = false;
                    }
                }
            }
        }
        return array;
    }

    public int getDame() {
        return dame;
    }

    public void setDame(int dame) {
        this.dame = dame;
    }
}
