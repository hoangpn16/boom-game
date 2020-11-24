package staticObject;

import javafx.scene.canvas.GraphicsContext;
import sample.GameCanvas;
import sample.Images;

public class Item extends StaticObject {
    private int type;
    public int timeHide;

    public Item() {

    }

    public Item(int x, int y, int type) {
        this.timeHide = 3000;
        this.x = x;
        this.y = y;
        this.type = type;
        this.height = 20;
        this.width = 20;
    }

    @Override
    public void paint(GraphicsContext g) {
        if (timeHide <= 0) {
            return;
        }
        if (this.type == 0) {
            if (GameCanvas.gameTicks % 100 < 50) {
                this.img = Images.img_item[0];
            } else {
                this.img = Images.img_item[1];
            }
            g.drawImage(this.img, this.x + 10, this.y + 10);
            timeHide--;
            return;
        }
        this.img = Images.img_item[2];
        g.drawImage(this.img, this.x + 10, this.y + 10);
        timeHide--;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTimeHide() {
        return timeHide;
    }

    public void setTimeHide(int timeHide) {
        this.timeHide = timeHide;
    }
}
