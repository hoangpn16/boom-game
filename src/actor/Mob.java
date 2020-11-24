package actor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import sample.FlyText;
import sample.Images;
import screen.GameScr;
import staticObject.Bomb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mob extends Actor {

    private int HPFull;

    private int direction;

    public List<FlyText> vFlyText = new ArrayList<>();

    public Mob() {

    }

    public Mob(Image image, int x, int y, int HPFull) {
        this.img = image;
        this.x = x;
        this.y = y;
        this.speed = 1;
        this.width = this.getImg().getWidth();
        this.height = this.getImg().getHeight();
        this.HPFull = HPFull;
        this.HP = this.HPFull;
        this.dame = 50;
        this.dead = false;
        this.direction = 1;
    }

    @Override
    public void paint(GraphicsContext g) {
        g.drawImage(this.img, this.x, this.y);
        this.paintHP(g);
        for (int i = 0; i < this.vFlyText.size(); i++) {
            FlyText flyText = this.vFlyText.get(i);
            if (flyText.timeHide > 0) {
                flyText.paint(g);
                if (flyText.timeHide % 10 == 0) {
                    flyText.y--;
                }
                flyText.timeHide--;
            } else {
                this.vFlyText.remove(i);
                i--;
            }
        }
    }

    @Override
    public void move(int x, int y) {
        if (!this.canMove(x, y)) {
            return;
        }
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void changeDirection(int direction) {
        if (speed < 0 || this.dead) {
            return;
        }
        switch (direction) {
            case LEFT:
                this.img = Images.img_mob[LEFT];
                break;
            case RIGHT:
                this.img = Images.img_mob[RIGHT];
                break;
            case UP:
                this.img = Images.img_mob[UP];
                break;
            case DOWN:
                this.img = Images.img_mob[DOWN];
                break;
        }
    }

    public boolean canMove(int x, int y) {
        if (speed < 0 || this.dead) {
            return false;
        }
        if (x < 0 || x > 600 - this.getWidth() || y < 0 || y > 600 - this.getHeight()) {
            return false;
        }
        Rectangle me =
                new Rectangle(x + 1, y + 1, this.getWidth() - 2, this.getHeight() - 2);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (GameScr.arrayRock[i][j] && me.intersects(i * 40, j * 40, 40, 40)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void autoMove() {
        if (this.direction == 1 && this.canMove(this.getX() + this.getSpeed(), this.getY())) {
            if (this.canMove(this.getX(), this.getY() + this.getSpeed()) && new Random().nextBoolean()) {
                this.changeDirection(Mob.DOWN);
                this.direction = 4;
                this.move(this.getX(), this.getY() + this.getSpeed());
                return;
            }
            if (this.canMove(this.getX(), this.getY() - this.getSpeed()) && new Random().nextBoolean()) {
                this.changeDirection(Mob.UP);
                this.move(this.getX(), this.getY() - this.getSpeed());
                this.direction = 3;
                return;
            }
            this.changeDirection(Mob.RIGHT);
            this.move(this.getX() + this.getSpeed(), this.getY());
            return;
        }
        if (this.direction == 4 && this.canMove(this.getX(), this.getY() + this.getSpeed())) {
            if (this.canMove(this.getX() + this.getSpeed(), this.getY()) && new Random().nextBoolean()) {
                this.changeDirection(Mob.RIGHT);
                this.move(this.getX() + this.getSpeed(), this.getY());
                this.direction = 1;
                return;
            }
            this.changeDirection(Mob.DOWN);
            this.move(this.getX(), this.getY() + this.getSpeed());
            return;
        }
        if (this.direction == 2 && this.canMove(this.getX() - this.getSpeed(), this.getY())) {
            if (this.canMove(this.getX(), this.getY() - this.getSpeed()) && new Random().nextBoolean()) {
                this.changeDirection(Mob.UP);
                this.direction = 3;
                this.move(this.getX(), this.getY() - this.getSpeed());
                return;
            }
            if (this.canMove(this.getX(), this.getY() + this.getSpeed()) && new Random().nextBoolean()) {
                this.changeDirection(Mob.DOWN);
                this.move(this.getX(), this.getY() + this.getSpeed());
                this.direction = 4;
                return;
            }
            this.changeDirection(Mob.LEFT);
            this.move(this.getX() - this.getSpeed(), this.getY());
            return;
        }
        if (this.direction == 3 && this.canMove(this.getX(), this.getY() - this.getSpeed())) {
            this.changeDirection(Mob.UP);
            this.move(this.getX(), this.getY() - this.getSpeed());
            return;
        }
        if (this.canMove(this.getX() + this.getSpeed(), this.getY())) {
            this.changeDirection(Mob.RIGHT);
            this.move(this.getX() + this.getSpeed(), this.getY());
            this.direction = 1;
            return;
        }
        if (this.canMove(this.getX(), this.getY() + this.getSpeed())) {
            this.changeDirection(Mob.DOWN);
            this.move(this.getX(), this.getY() + this.getSpeed());
            this.direction = 4;
            return;
        }
        if (this.canMove(this.getX() - this.getSpeed(), this.getY())) {
            this.changeDirection(Mob.LEFT);
            this.move(this.getX() - this.getSpeed(), this.getY());
            this.direction = 2;
            return;
        }
        if (this.canMove(this.getX(), this.getY() - this.getSpeed())) {
            this.changeDirection(Mob.UP);
            this.move(this.getX(), this.getY() - this.getSpeed());
            this.direction = 3;
            return;
        }
    }

    public boolean bangInjured(Bomb bomb) {
        if (bomb.getTimeBang() != 1 && bomb.getTimeBang() != 0) {
            return false;
        }
        int cx = bomb.getX() - 5;
        int cy = bomb.getY() - 5;
        int x = this.x;
        int y = this.y;
        boolean flag = false;
        if (y == cy && x > cx - 80 && x < cx + 80) {
            flag = true;
        }
        if (x == cx && y > cy - 80 && y < cy + 80) {
            flag = true;
        }
        Rectangle mobR =
                new Rectangle(x -1, y - 11, this.getWidth() -2, this.getHeight() - 12);
        if(mobR.intersects(cx,cy -40,40,120)){
            flag = true;
        }
        if(mobR.intersects(cx- 40, cy ,120,40)){
            flag = true;
        }

        if (flag) {
            FlyText flyText =
                    new FlyText(bomb.getDame() + "HP", this.getX(), this.getY() - 10, 1, Color.YELLOW, 50);
            this.vFlyText.add(flyText);
            return true;
        }
        return false;
    }

    public void paintHP(GraphicsContext g) {
        double num = (double) this.getHP() / this.getHPFull() * 38;
        g.setFill(Color.web("#BBBBBB"));
        g.fillRect(this.getX(), this.getY() - 10, 40, 5);
        g.setFill(Color.RED);
        g.fillRect(this.getX() + 1, this.getY() - 9, num, 3);
        g.setFill(Color.YELLOW);
        g.setFont(Font.font(10));
        g.fillText(this.getHP() + "/" + this.getHPFull(), this.getX() + 2, this.getY() - 12);

    }

    public void update() {
        this.HP += 20;
        this.HPFull += 20;
        this.dame += 10;
    }

    public int getHPFull() {
        return HPFull;
    }

    public void setHPFull(int HPFull) {
        this.HPFull = HPFull;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
