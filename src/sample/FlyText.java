package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FlyText {
    private String info;
    private int x;
    public int y;
    private int type;
    private Color color;
    public double timeHide;

    public FlyText() {

    }

    public FlyText(String info, int x, int y, int type, Color color, double timeHide) {
        this.info = info;
        this.x = x;
        this.y = y;
        this.type = type;
        this.color = color;
        this.timeHide = timeHide;
    }

    public void paint(GraphicsContext g) {
        g.setFill(this.color);
        g.fillText( (this.type == 0 ? "+" : "-") + this.info, this.x, this.y);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getTimeHide() {
        return timeHide;
    }

    public void setTimeHide(double timeHide) {
        this.timeHide = timeHide;
    }
}
