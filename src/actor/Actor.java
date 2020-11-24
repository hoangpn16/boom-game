package actor;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public abstract class Actor {
    protected int x;
    protected int y;
    protected int HP;
    protected int MP;
    protected int speed;
    protected Image img;
    protected int status = 0;
    public static final int LEFT = 2;
    public static final int RIGHT = 1;
    public static final int UP = 3;
    public static final int DOWN = 4;
    public static final int CENTER = 0;
    protected double width;
    protected double height;
    protected boolean dead = false;
    protected int dame;


    public Actor() {

    }

    public abstract void paint(GraphicsContext g);

    public abstract void move(int x, int y);

    public abstract void changeDirection(int direction);

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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getDame() {
        return dame;
    }

    public void setDame(int dame) {
        this.dame = dame;
    }
}
