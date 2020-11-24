package staticObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rock extends StaticObject {

    public Rock() {

    }

    public Rock(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.img = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    @Override
    public void paint(GraphicsContext g) {
        g.drawImage(this.img, this.x, this.y);
    }
}
