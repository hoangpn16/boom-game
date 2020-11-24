package actor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import sample.*;
import screen.GameScr;
import staticObject.Bomb;
import staticObject.Item;

import java.util.ArrayList;
import java.util.List;


public class Bomber extends Actor {
    private Skill[] skills = new Skill[4];
    private int level = 1;
    private double exp = 0;
    private double lvExp = 100;
    private int lvHP = 100;
    private int lvMP = 80;
    private String name;
    private int score = 0;
    public int radiusTS = 30;

    public int gender = 0;

    public List<FlyText> vFlyText = new ArrayList<>();

    public Bomber() {

    }

    public Bomber(int x, int y, int speed, String name, int gender) {
        this.HP = 100;
        this.MP = 80;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.img = Images.img_Bomber[gender * 2 + RIGHT];
        this.name = name;
        this.width = this.getImg().getWidth(); //40
        this.height = this.getImg().getHeight(); //40
        this.dead = false;
        this.dame = 100;
        this.gender = gender;
        this.initSkill();
    }

    @Override
    public void move(int x, int y) {
        if (!this.canMove(x, y)) {
            return;
        }
        this.setX(x);
        this.setY(y);
    }

    public boolean canMove(int x, int y) {
        if (speed < 0 || this.dead) {
            return false;
        }
        if (x < 0 || x > 600 - this.getWidth() || y < -10 || y > 600 - this.getHeight()) {
            return false;
        }
        Rectangle me =
                new Rectangle(x + 10, y + 10, this.getWidth() - 20, this.getHeight() - 20);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (GameScr.arrayRock[i][j] && me.intersects(i * 40 + 10, j * 40, 20, 30)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean pickItem(Item item) {
        Rectangle me =
                new Rectangle(x, y - 10, this.getWidth(), this.getHeight() - 10);
        if (me.intersects(item.getX(), item.getY(), item.getWidth(), item.getHeight())) {
            if (item.getType() == 0) {
                this.lvHP += 10;
                this.HP += (int) this.lvHP * 50 / 100;
                if (this.HP > this.lvHP) {
                    this.HP = this.lvHP;
                }
            } else {
                this.dame += 5;
            }
            Sounds.pickItem.stop();
            Sounds.pickItem.play();
            item.setTimeHide(0);
            return true;
        }
        return false;
    }

    public boolean isDie() {
        return this.HP <= 0;
    }

    public boolean bangDie(Bomb bomb) {
        if (this.getSkills()[2].isUsing()) {
            return false;
        }
        if (bomb.getTimeBang() != 1 && bomb.getTimeBang() != 0) {
            return false;
        }
        int cx = bomb.getX() - 5;
        int cy = bomb.getY() - 5;
        int x = this.x;
        int y = this.y;
        boolean flag = false;
        if (y == cy && x > cx - 70 && x < cx + 70) {
            flag = true;
        }
        if (x == cx && y > cy - 80 && y < cy + 70) {
            flag = true;
        }
        if (flag) {
            FlyText flyText =
                    new FlyText("99999HP", this.getX() - 20, this.getY() - 1, 1, Color.YELLOW, 30);
            this.vFlyText.add(flyText);
            return true;
        }
        return false;
    }

    public boolean mobAttack(Mob mob) {
        if (this.getSkills()[2].isUsing()) {
            return false;
        }
        Rectangle me =
                new Rectangle(x - 1, y - 1, this.getWidth() - 2, this.getHeight() - 2);
        if (me.intersects(mob.getX(), mob.getY(), mob.getWidth(), mob.getHeight() - 10)) {
            FlyText flyText =
                    new FlyText(mob.getDame() + "HP", this.getX(), this.getY() - 10, 1, Color.YELLOW, 30);
            this.vFlyText.add(flyText);
            return true;
        }
        return false;
    }

    private void initSkill() {
        for (int i = 0; i < skills.length; i++) {
            skills[i] = new Skill(Images.img_skill[i]);
            skills[i].setName(Res.nameSkill[i]);
            skills[i].setInfo(Res.infoSkill[i]);
        }
        skills[0].setCoolDown(0.5);
        skills[0].setLearned(true);
        skills[0].setManaUse(20);

        skills[1].setCoolDown(0);

        skills[2].setCoolDown(30);

        skills[3].setCoolDown(90);
    }

    public void levelUp() {
        this.exp = 0;
        this.HP += 10;
        this.MP += 8;
        this.lvHP += 10;
        this.lvMP += 8;
        this.dame += 10;
        this.lvExp += 50;
        this.level++;

        if (this.level == 2) {
            skills[1].setCoolDown(0);
            skills[1].setLearned(true);
        }

        if (this.level == 3) {
            skills[2].setCoolDown(20);
            skills[2].setLearned(true);
            skills[2].setManaUse((int) this.lvMP * 40 / 100);
        }

        if (this.level == 4) {
            skills[3].setCoolDown(45);
            skills[3].setLearned(true);
            skills[3].setManaUse((int) this.lvMP * 55 / 100);
        }
    }

    private void paintSkill(GraphicsContext g) {
        int x = 630;
        int y = 280;
        for (int i = 0; i < skills.length; i++) {
            skills[i].paint(g, x, y);
            if (this.MP < skills[i].getManaUse()) {
                g.setFill(Color.rgb(0, 0, 255, 0.7));
                g.fillRect(x + 8, y + 8, 40, 40);
            }
            y += 80;
        }
    }

    private void paintInfo(GraphicsContext g) {
        g.setFont(Font.loadFont(Bomber.class.getResource(Res.FONT).toString(), 30));
        g.setFill(Color.RED);
        g.fillText(Res.name + this.getName(), 630, 35);
        g.setFont(Font.loadFont(Bomber.class.getResource(Res.FONT).toString(), 30));
        g.fillText(Res.score + this.getScore(), 630, 75);
    }

    private void paintHPvsMP(GraphicsContext g) {
        g.setFont(Font.loadFont(Bomber.class.getResource(Res.FONT).toString(), 20));
        g.setFill(Color.YELLOW);

        g.fillText("HP: ", 630, 135);
        g.fillText("MP: ", 630, 175);
        g.setFill(Color.rgb(205, 201, 201));
        g.fillRect(690, 115, 300, 25);
        g.setFill(Color.RED);
        double num = (double) this.getHP() / this.getLvHP() * 300;
        g.fillRect(691, 116, num - 2, 23);
        g.setFont(new Font(15));
        g.setFill(Color.YELLOW);
        g.fillText(this.getHP() + "/" + this.getLvHP(), 800, 133);

        g.setFill(Color.rgb(205, 201, 201));
        g.fillRect(690, 155, 270, 25);
        num = (double) this.getMP() / this.getLvMP() * 270;
        g.setFill(Color.BLUE);
        g.fillRect(691, 156, num - 1, 23);
        g.setFont(new Font(15));
        g.setFill(Color.YELLOW);
        g.fillText(this.getMP() + "/" + this.getLvMP(), 800, 173);
        if (this.MP < this.lvMP) {
            g.fillText("+5%/s", 910, 173);
        }
        if (this.level > 1 && this.HP < this.lvHP) {
            g.fillText("+10%/s", 938, 133);
        }

        if (this.level == 1 && this.HP < this.lvHP) {
            g.fillText("+1%/s", 943, 133);
        }

        g.setFont(Font.loadFont(Bomber.class.getResource(Res.FONT).toString(), 20));
        g.setFill(Color.YELLOW);
        g.fillText("Dame: " + this.getDame() + "   Level: " + this.level + this.getPercentLevel(), 630, 220);
    }

    private String getPercentLevel() {
        double num = (double) (exp / lvExp * 100);
        return "+" + String.format("%.2f", num) + "%";
    }

    @Override
    public void paint(GraphicsContext g) {
        this.paintInfo(g);
        this.paintHPvsMP(g);
        this.paintSkill(g);
        if (skills[3].isUsing() && skills[3].getTimeSkill() > 2) {
            this.paintSkill_4(g);
        }
        g.drawImage(this.getImg(), this.getX(), this.getY());

        if (skills[2].isUsing() && skills[2].getTimeSkill() > 0) {
            this.paintSkill_3(g);
        }
        for (int i = 0; i < this.vFlyText.size(); i++) {
            FlyText flyText = this.vFlyText.get(i);
            if (flyText.getType() == 1) {
                g.drawImage(Images.img_attack[(int) GameCanvas.gameTicks % 10 / 2], this.getX() + 5, this.getY() + 10);
            }
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

    private void paintSkill_3(GraphicsContext g) {
        int num = GameCanvas.gameTicks / 50;
        if (num % 3 == 0) {
            g.drawImage(new Image(Res.IMAGE_SKIll_3[0]), this.getX() - 3, this.getY() - 8);
            return;
        }
        if (num % 3 == 1) {
            g.drawImage(new Image(Res.IMAGE_SKIll_3[1]), this.getX() - 15, this.getY() - 13);
            return;
        }
        g.drawImage(new Image(Res.IMAGE_SKIll_3[2]), this.getX() - 15, this.getY() - 13);
    }

    private void paintSkill_4(GraphicsContext g) {
        int num = GameCanvas.gameTicks / 50;
        if (num % 3 == 0) {
            g.drawImage(new Image(Res.IMAGE_SKIll_4[0]), this.getX() - 3, this.getY() - 8);
            return;
        }
        if (num % 3 == 1) {
            g.drawImage(new Image(Res.IMAGE_SKIll_4[1]), this.getX() - 8, this.getY() - 13);
            return;
        }
        g.drawImage(new Image(Res.IMAGE_SKIll_4[2]), this.getX() - 15, this.getY() - 13);

    }

    @Override
    public void changeDirection(int direction) {
        if (speed < 0 || this.dead) {
            return;
        }
        switch (direction) {
            case LEFT:
                this.img = Images.img_Bomber[gender * 2 + LEFT];
                break;
            case RIGHT:
                this.img = Images.img_Bomber[gender * 2 + RIGHT];
                break;
            case UP:
                //this.img = Images.img_Bomber[UP];
                break;
            case DOWN:
                //this.img = Images.img_Bomber[DOWN];
                break;
        }
    }

    public void recuperateMP() {
        if (this.MP >= this.lvMP) {
            return;
        }
        int num = (int) this.lvMP * 5 / 100;
        this.MP += num;
        if (this.MP > this.lvMP) {
            this.MP = this.lvMP;
        }
    }

    public void recuperateHP() {
        if (this.HP >= this.lvHP) {
            return;
        }
        if (this.level == 1) {
            int num = (int) this.lvHP / 100;
            this.HP += num;
            if (this.HP > this.lvHP) {
                this.HP = this.lvHP;
            }
            return;
        }
        int num = (int) this.lvHP * 10 / 100;
        this.HP += num;
        if (this.HP > this.lvHP) {
            this.HP = this.lvHP;
        }
    }

    public Skill[] getSkills() {
        return skills;
    }


    public boolean canUseSkill(int index) {
        Skill skill = skills[index];
        if (!skill.isLearned()) {
            return false;
        }
        if (!skill.canUse()) {
            return false;
        }
        if (this.MP < skill.getManaUse()) {
            return false;
        }
        if (skill.isUsing() && index != 0) {
            return false;
        }
        return true;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public double getLvExp() {
        return lvExp;
    }

    public void setLvExp(double lvExp) {
        this.lvExp = lvExp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLvHP() {
        return lvHP;
    }

    public void setLvHP(int lvHP) {
        this.lvHP = lvHP;
    }

    public int getLvMP() {
        return lvMP;
    }

    public void setLvMP(int lvMP) {
        this.lvMP = lvMP;
    }
}
