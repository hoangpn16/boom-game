package sample;

import javafx.scene.image.Image;

public class Images {
    public static Image img_icon;
    public static Image img_Logo;
    public static Image img_exit;
    public static Image img_BackGroundMain;
    public static Image[] img_move;
    public static Image[] img_Bomber;
    public static Image[] img_skill;
    public static Image[] img_box;
    public static Image img_bomb;
    public static Image[] img_bang;
    public static Image[] img_mob;
    public static Image[] img_attack;
    public static Image[] img_item;


    public static void loadImages() {
        Images.img_BackGroundMain = new Image(Images.class.getResource(Res.IMAGE_MAIN_BGR).toExternalForm());
        Images.img_Logo = new Image(Images.class.getResource(Res.IMAGE_LOGO).toExternalForm());
        Images.img_exit = new Image(Images.class.getResource(Res.IMAGE_EXIT).toExternalForm());
        Images.img_icon = new Image(Images.class.getResource(Res.IMAGE_ICON).toExternalForm());
        Images.img_attack = new Image[5];
        for (int i = 0; i < 5; i++) {
            Images.img_attack[i] = new Image(Images.class.getResource(Res.IMAGE_ATTACK[i]).toExternalForm());
        }
        Images.img_item = new Image[3];
        for (int i = 0; i < 3; i++) {
            Images.img_item[i] = new Image(Images.class.getResource(Res.IMAGE_ITEM[i]).toExternalForm());
        }
        Images.img_move = new Image[4];
        for (int i = 0; i < 4; i++) {
            Images.img_move[i] = new Image(Images.class.getResource(Res.IMAGE_MOVE[i]).toExternalForm());
        }
        Images.img_Bomber = new Image[7];
        for (int i = 0; i < 7; i++) {
            Images.img_Bomber[i] = new Image(Images.class.getResource(Res.IMAGE_BOMBER[i]).toExternalForm());
        }
        Images.img_bang = new Image[5];
        for (int i = 0; i < 5; i++) {
            Images.img_bang[i] = new Image(Images.class.getResource(Res.IMAGE_BANG[i]).toExternalForm());
        }
        Images.img_skill = new Image[5];
        for (int i = 0; i < 5; i++) {
            Images.img_skill[i] = new Image(Images.class.getResource(Res.IMAGE_SKILL[i]).toExternalForm());
        }
        Images.img_box = new Image[2];
        for (int i = 0; i < 2; i++) {
            Images.img_box[i] = new Image(Images.class.getResource(Res.IMAGE_BOX[i]).toExternalForm());
        }
        Images.img_bomb = new Image(Images.class.getResource(Res.IMAGE_BOMB).toExternalForm());
        Images.img_mob = new Image[5];
        for (int i = 0; i < 5; i++) {
            Images.img_mob[i] = new Image(Images.class.getResource(Res.IMAGE_MOB[i]).toExternalForm());
        }
    }
}
