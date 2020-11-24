package screen;

import actor.Bomber;
import actor.Mob;
import actor.Skill;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import sample.*;
import score.Gamer;
import score.HighScore;
import staticObject.Bomb;
import staticObject.Item;
import staticObject.Rock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScr {
    public Bomber bomber;

    public Circle circle = new Circle(0);

    public List<Bomb> vBomb = new ArrayList<>();

    public List<Mob> vMob = new ArrayList<>();

    public List<Item> vItem = new ArrayList<>();

    public static boolean[][] arrayRock;

    public static boolean[][] arrayMob;

    public static boolean[][] arrayBomBer;

    public static boolean[][] arrayItem;

    public int HP_TS = 0;

    public boolean bomberDie = false;

    private int xMove;
    private int yMove;

    public Scene getScene(Stage stage) {
        Sounds.mainMusic.stop();
        Sounds.gameMusic.play();

        AnchorPane gameScr = new AnchorPane();
        gameScr.setMinSize(600, 600);
        gameScr.setMaxSize(600, 600);
        gameScr.setLayoutX(0);
        gameScr.setLayoutY(0);
        gameScr.setStyle("-fx-background-color:#777777");

        ImageView iView = new ImageView(Images.img_exit);
        iView.setFitWidth(30);
        iView.setFitHeight(30);
        Button stackPane = new Button();
        stackPane.setGraphic(iView);
        stackPane.setMaxSize(20, 20);
        stackPane.setStyle("-fx-background-color:transparent");
        stackPane.setOnMouseEntered(t -> stackPane.setStyle("-fx-background-color:#FFF5EE;  -fx-background-radius:20"));
        stackPane.setOnMouseExited(t -> stackPane.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00"));
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setScene(MainScr.getScene(stage));
            }
        });
        stackPane.setLayoutX(977);
        stackPane.setLayoutY(7);

        StackPane anDie = new StackPane();
        anDie.setMaxSize(200, 200);
        anDie.setMinSize(200, 200);
        anDie.setLayoutX(400);
        anDie.setLayoutY(200);
        anDie.setStyle("-fx-background-color:white;   -fx-background-radius:20");
        Label labelDie = new Label(Res.textDie);
        labelDie.setFont(Font.loadFont(GameScr.class.getResource(Res.FONT).toString(), 30));
        labelDie.setStyle("-fx-text-fill: #FF0000;");
        labelDie.setTranslateY(-35);

        Button btnOK = new Button("OK");
        btnOK.setFont(Font.loadFont(GameScr.class.getResource(Res.FONT).toString(), 35));
        btnOK.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00;   -fx-background-radius:20");
        btnOK.setOnMouseEntered(t ->
                btnOK.setStyle("-fx-background-color:#FFF5EE; -fx-text-fill: #7FFF00;   -fx-background-radius:20"));
        btnOK.setOnMouseExited(t -> btnOK.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00"));
        btnOK.setTranslateY(50);
        btnOK.setMaxWidth(200);


        anDie.getChildren().addAll(labelDie, btnOK);


        circle.setLayoutX(bomber.getX());
        circle.setLayoutY(bomber.getY());
        circle.setFill(Color.WHITE);

        Canvas canvas = new Canvas(GameCanvas.GAME_WIDTH, GameCanvas.GAME_HEIGHT);
        GraphicsContext g = canvas.getGraphicsContext2D();


        ImageView imageView = new ImageView(Images.img_BackGroundMain);
        imageView.setPreserveRatio(true);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(imageView, gameScr, circle, canvas, stackPane);
        Scene setWal = new Scene(anchorPane, GameCanvas.GAME_WIDTH, GameCanvas.GAME_HEIGHT);

        xMove = bomber.getX();
        yMove = bomber.getY();

        AnimationTimer timer = new AnimationTimer() {
            long currentTimeMillis = System.currentTimeMillis();
            long timeInjured = System.currentTimeMillis();

            @Override
            public void handle(long l) {
                long currentTime = System.currentTimeMillis();

                GameCanvas.gameTicks++;
//                if (bomber.getX() < xMove) {
//                    bomber.changeDirection(Bomber.RIGHT);
//                    bomber.move(bomber.getX() + 2, bomber.getY());
//                }
//                if (bomber.getX() > xMove) {
//                    bomber.changeDirection(Bomber.LEFT);
//                    bomber.move(bomber.getX() - 2, bomber.getY());
//                }
//                if (bomber.getY() > yMove) {
//                    bomber.changeDirection(Bomber.UP);
//                    bomber.move(bomber.getX(), bomber.getY() - 2);
//                }
//                if (bomber.getY() < yMove) {
//                    bomber.changeDirection(Bomber.DOWN);
//                    bomber.move(bomber.getX(), bomber.getY() + 2);
//                }

                if (GameCanvas.gameTicks % 1500 == 0) {
                    for (int i = 0; i < 15; i++) {
                        for (int j = 0; j < 15; j++) {
                            if (arrayMob[i][j]) {
                                int hp = ((int) GameCanvas.gameTicks / 1500) * 40 + 80;
                                Mob mob = new Mob(Images.img_mob[Mob.CENTER], 40 * i, 40 * j, hp);
                                vMob.add(mob);
                            }
                            if (arrayItem[i][j]) {
                                Item item = new Item(40 * i, 40 * j, new Random().nextInt(2));
                                vItem.add(item);
                            }
                        }
                    }

                    for (Mob mob : vMob
                    ) {
                        mob.update();
                    }
                }

                g.clearRect(0, 0, GameCanvas.GAME_WIDTH, GameCanvas.GAME_HEIGHT);

                paint(g);

                if (GameCanvas.gameTicks % 3 == 0) {
                    for (int i = 0; i < vMob.size(); i++) {
                        vMob.get(i).autoMove();
                    }
                }

                if (bomber.getExp() >= bomber.getLvExp()) {
                    double num = bomber.getExp() - bomber.getLvExp();
                    bomber.levelUp();
                    bomber.setExp(num);
                }

                if (System.currentTimeMillis() - timeInjured > 1000) {
                    for (int i = 0; i < vMob.size(); i++) {
                        if (bomber.mobAttack(vMob.get(i))) {
                            Sounds.mobAttack.stop();
                            Sounds.mobAttack.play();
                            bomber.setHP(bomber.getHP() - vMob.get(i).getDame());
                            if (bomber.getHP() < 0) {
                                bomber.setHP(0);
                            }
                        }
                    }
                    timeInjured = System.currentTimeMillis();
                }

                for (Item item : vItem) {
                    if (bomber.pickItem(item)) {
                        item.setTimeHide(0);
                    }
                }

                if (bomber.isDie() && !bomberDie) {
                    bomber.setDead(true);
                    bomber.setImg(Images.img_Bomber[0]);
                    anchorPane.getChildren().addAll(anDie);
                    bomberDie = true;
                }
                Skill[] skill = bomber.getSkills();
                if (currentTime - currentTimeMillis > 1000) {
                    if (!bomber.isDead()) {
                        bomber.recuperateMP();
                        bomber.recuperateHP();
                    }


                    for (int i = 0; i < skill.length; i++) {
                        int num = skill[i].getTimeSkill() - 1;
                        if (num < 0) {
                            skill[i].setUsing(false);
                            num = 0;
                        }
                        skill[i].setTimeSkill(num);

                    }

                    for (int i = 0; i < vBomb.size(); i++) {
                        Bomb bomb = vBomb.get(i);
                        if (bomb.getTimeBang() > 0) {
                            bomb.setTimeBang(bomb.getTimeBang() - 1);
                        }
                        if (bomb.getTimeBang() == 1) {
                            Sounds.bomb_bang.stop();
                            Sounds.bomb_bang.play();
                        }
                        if (bomb.getTimeBang() < 0) {
                            bomb.setTimeBang(0);
                        }
                    }

                    for (int i = 0; i < vMob.size(); i++) {
                        Mob mob = vMob.get(i);
                        if (!mob.isDead()) {
                            for (int j = 0; j < vBomb.size(); j++) {
                                Bomb bomb = vBomb.get(j);
                                if (mob.bangInjured(bomb) && bomb.getTimeBang() > 0) {
                                    int dame = bomb.getDame();
                                    if (mob.getHP() - dame > 0) {
                                        mob.setHP(mob.getHP() - dame);
                                    } else {
                                        mob.setHP(0);
                                        mob.setDead(true);

                                    }
                                }
                            }
                        }
                    }
                    currentTimeMillis = System.currentTimeMillis();
                }

                if (skill[3].isUsing() && skill[3].getTimeSkill() < 2) {
                    bomber.radiusTS += 10;
                    circle.setRadius(bomber.radiusTS);
                    if (bomber.radiusTS > GameCanvas.GAME_WIDTH + 100) {
                        bomber.setSpeed(10);
                        circle.setRadius(0);
                        Sounds.sound_TS.stop();
                        if (HP_TS > 0) {
                            for (int i = 0; i < vMob.size(); i++) {
                                Mob mob = vMob.get(i);
                                if (!mob.isDead() && mob.getHP() > HP_TS) {
                                    mob.setHP(mob.getHP() - HP_TS);
                                } else {
                                    mob.setDead(true);
                                }
                            }
                        }
                        HP_TS = 0;
                    }
                }

                for (int i = 0; i < vMob.size(); i++) {
                    Mob mob = vMob.get(i);
                    if (mob.isDead()) {
                        vMob.remove(i);
                        bomber.setExp(bomber.getExp() + 100);
                        bomber.setScore(bomber.getScore() + 5);
                        i--;
                    }
                }
            }
        };
        timer.start();

        setWal.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:
//                        if (bomber.canMove(xMove + bomber.getSpeed(), bomber.getY())) {
//                            xMove += bomber.getSpeed();
//                        }

                       bomber.changeDirection(Bomber.RIGHT);
                        bomber.move(bomber.getX() + bomber.getSpeed(), bomber.getY());
                        break;
                    case LEFT:
//                        if (bomber.canMove(xMove - bomber.getSpeed(), bomber.getY())) {
//                            xMove -= bomber.getSpeed();
//                        }
                        bomber.changeDirection(Bomber.LEFT);
                        bomber.move(bomber.getX() - bomber.getSpeed(), bomber.getY());
                        break;
                    case UP:
//                        if (bomber.canMove(bomber.getX(), yMove - bomber.getSpeed())) {
//                            yMove -= bomber.getSpeed();
//                        }
                        bomber.changeDirection(Bomber.UP);
                        bomber.move(bomber.getX(), bomber.getY() - bomber.getSpeed());
                        break;
                    case DOWN:
//                        if (bomber.canMove(bomber.getX(), yMove + bomber.getSpeed())) {
//                            yMove += bomber.getSpeed();
//                        }
                        bomber.changeDirection(Bomber.DOWN);
                        bomber.move(bomber.getX(), bomber.getY() + bomber.getSpeed());
                        break;
                    case Q:
                        doSelectSkill(0);
                        break;
                    case E:
                        doSelectSkill(2);
                        break;
                    case R:
                        doSelectSkill(3);
                        break;
                }
            }
        });

        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent12 -> {
            timer.stop();
            Gamer gamer = new Gamer(bomber.getName(), bomber.getScore());
            HighScore.addGamer(gamer);
            Rms.saveRMS("highScore", HighScore.getString(HighScore.gamers));
            stage.setScene(MainScr.getScene(stage));
        });
        return setWal;
    }

    public void paint(GraphicsContext g) {
        createMap(g);
        bomber.paint(g);

        for (int i = 0; i < vMob.size(); i++) {
            Mob mob = vMob.get(i);
            if (!mob.isDead()) {
                mob.paint(g);
            }
        }

        for (int i = 0; i < vItem.size(); i++) {
            Item item = vItem.get(i);
            if (item.timeHide > 0) {
                item.paint(g);
            } else {
                vItem.remove(i);
                i--;
            }
        }

        for (int i = 0; i < vBomb.size(); i++) {
            Bomb bomb = vBomb.get(i);
            if (bomb.getTimeBang() > 0) {
                vBomb.get(i).paint(g);
                if (!bomber.isDead() && bomber.bangDie(vBomb.get(i))) {
                    bomber.setDead(true);
                    bomber.setHP(0);
                }
            } else {
                vBomb.remove(i);
                i--;
            }
        }
    }

    public void initGameScr() {
        vItem.clear();
        vMob.clear();
        vBomb.clear();
        bomberDie = false;
        arrayRock = Rms.readMap(42);
        arrayMob = Rms.readMap(77);
        arrayBomBer = Rms.readMap(80);
        arrayItem = Rms.readMap(73);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (arrayMob[i][j]) {
                    Mob mob = new Mob(Images.img_mob[Mob.CENTER], 40 * i, 40 * j, 100);
                    vMob.add(mob);
                }
                if (arrayItem[i][j]) {
                    Item item = new Item(40 * i, 40 * j, new Random().nextInt(2));
                    vItem.add(item);
                }
            }
        }

    }

    public void doSelectSkill(int index) {
        if (!bomber.canUseSkill(index)) {
            return;
        }
        Skill skill = bomber.getSkills()[index];
        switch (index) {
            case 0:
                Sounds.bomb_new.stop();
                Sounds.bomb_new.play();
                skill.setUsing(true);
                skill.setTimeSkill(2);
                int dame = (int) bomber.getDame() * 110 / 100;
                Bomb bomb = new Bomb(bomber.getX(), bomber.getY(), Images.img_bomb, dame);
                vBomb.add(bomb);

                bomber.setMP(bomber.getMP() - skill.getManaUse());
                skill.setTimeUse(System.currentTimeMillis());
                break;
            case 1:
                System.out.println("skill2");
                skill.setTimeUse(System.currentTimeMillis());
                break;
            case 2:
                skill.setUsing(true);
                skill.setTimeSkill(7);
                bomber.setMP(bomber.getMP() - skill.getManaUse());
                skill.setTimeUse(System.currentTimeMillis());
                break;
            case 3:
                Sounds.sound_TS.stop();
                Sounds.sound_TS.play();
                skill.setUsing(true);
                skill.setTimeSkill(5);
                bomber.setMP(bomber.getMP() - skill.getManaUse());
                bomber.radiusTS = 0;
                circle.setRadius(0);
                circle.setLayoutX(bomber.getX() + 20);
                circle.setLayoutY(bomber.getY() + 10);
                HP_TS = bomber.getHP() * 80 / 100;
                bomber.setHP(bomber.getHP() - HP_TS);
                bomber.setSpeed(0);
                skill.setTimeUse(System.currentTimeMillis());
                break;

        }
    }

    public void createMap(GraphicsContext g) {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (arrayRock[i][j]) {
                    Rock rock = new Rock(i * 40, j * 40, Images.img_box[0]);
                    rock.paint(g);
                }
                if (arrayMob[i][j]) {
                    g.setFill(Color.RED);
                    g.fillRect(i * 40 + 15, j * 40 + 15, 10, 10);
                }
                if (arrayItem[i][j]) {
                    g.setFill(Color.BLUE);
                    g.fillRect(i * 40 + 15, j * 40 + 15, 10, 10);
                }
            }
        }
    }

    public void createChar(String name, int gender) {
        if (name.equals("")) {
            name = "Kerhoangde";
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (arrayBomBer[i][j]) {
                    bomber = new Bomber(40 * i, 40 * j, 10, name, gender);
                    return;
                }
            }
        }
    }

}
