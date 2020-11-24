package staticObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class StaticObject {
    protected int x;
    protected int y;
    protected Image img;

    protected double width;
    protected double height;

    public StaticObject(){

    }

    public abstract void paint(GraphicsContext g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
