package actor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.Images;
import sample.Res;
import screen.MainScr;

public class Skill {
    private boolean isLearned;

    private long timeUse = System.currentTimeMillis() - 50000;

    private int type = 0;

    private int x;

    private int y;

    private String name;

    private String info;

    private double coolDown = 20;

    private Image img;

    private double width;

    private double height;

    private int manaUse;

    private boolean canUse = true;

    private boolean isUsing = false;

    private int timeSkill;

    public Skill() {

    }

    public Skill(Image image) {
        this.img = image;
        this.width = 40;
        this.height = 40;

    }

    public void paint(GraphicsContext g, int x, int y) {
        g.drawImage(Images.img_skill[4], x, y);
        g.drawImage(this.img, x + 8, y + 8);

        if (this.isLearned) {
            g.setFont(Font.loadFont(Skill.class.getResource(Res.FONT).toString(), 25));
            g.setFill(Color.web("#00FF00"));
            g.fillText(this.name, x + 70, y + 20);

            g.setFont(Font.loadFont(Skill.class.getResource(Res.FONT).toString(), 15));
            g.fillText(this.info, x + 70, y + 50);
            long num = System.currentTimeMillis();
            double num2 = (double) ((num - this.timeUse) / 1000);
            if (num2 < this.coolDown) {
                double h1 = this.getHeight() * num2 / this.coolDown;
                double h = this.getHeight() - h1;

                g.setFill(Color.rgb(205, 201, 201, 0.7));
                g.fillRect(x + 8, y + 8 + h1, this.getWidth(), h);
            }
        } else {
            g.setFill(Color.rgb(205, 201, 201,0.7));
            g.fillRect(x + 8, y + 8, this.getWidth(), this.getHeight());

            g.setFont(Font.loadFont(Skill.class.getResource(Res.FONT).toString(), 25));
            g.setFill(Color.web("#BBBBBB"));
            g.fillText(this.name, x + 70, y + 20);

            g.setFont(Font.loadFont(Skill.class.getResource(Res.FONT).toString(), 15));
            g.fillText(this.info, x + 70, y + 50);
        }

    }

    public boolean canUse() {
        double check = (double) (System.currentTimeMillis() - this.timeUse) / 1000;
        if (check < this.coolDown) {
            return false;
        }
        return true;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    public long getTimeUse() {
        return timeUse;
    }

    public void setTimeUse(long timeUse) {
        this.timeUse = timeUse;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(double coolDown) {
        this.coolDown = coolDown;
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

    public int getManaUse() {
        return manaUse;
    }

    public void setManaUse(int manaUse) {
        this.manaUse = manaUse;
    }

    public boolean isCanUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    public boolean isUsing() {
        return isUsing;
    }

    public void setUsing(boolean using) {
        isUsing = using;
    }

    public int getTimeSkill() {
        return timeSkill;
    }

    public void setTimeSkill(int timeSkill) {
        this.timeSkill = timeSkill;
    }
}
