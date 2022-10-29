package nicolasyt.dvd;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nicolasyt.dvd.M.DVDModele;
import nicolasyt.dvd.M.EcranModele;
import nicolasyt.dvd.V.DVDVue;
import nicolasyt.dvd.V.VueEcran;

import java.io.IOException;

public class ScreenSaverMain extends Application {

    public static int WIN_WIDTH = 1280;
    public static int WIN_HEIGHT = 720;
    public static final int DVD_WIDTH = 100;
    public static final int DVD_HEIGHT = 100;
    public static final int DVD_START_X = 0;
    public static final int DVD_START_Y = 0;
    public static int DVD_MOVE_INITIAL = 10;
    public static int DVD_MOVE_CURR = 10;
    public static final int DVD_STICK_MODIF = 30;
    public static String CHEAT_CODE_SPEED = "sugoma";
    public static String CHEAT_CODE_SMOL = "smol";
    public static String CURR_WRITTEN_CODE = "";

    @Override
    public void start(Stage stage) throws IOException {

        DVDModele dvd = new DVDModele(DVD_START_X, DVD_START_Y, DVD_WIDTH, DVD_HEIGHT);
        EcranModele ecran = new EcranModele(WIN_WIDTH, WIN_HEIGHT, dvd);
        DVDVue dvdVue = new DVDVue(dvd);
        VueEcran root = new VueEcran(dvdVue, ecran);
        Scene scene = new Scene(root, WIN_WIDTH, WIN_HEIGHT);
        stage.setTitle("DVD Screensaver 🟪");
        stage.getIcons().add(new Image("file:resources/images/logoApp.png"));
        stage.setScene(scene);
        stage.show();

        Text text = new Text(":");
        text.setOpacity(0);
        text.setStyle("-fx-font: 30 arial;");
        text.setX(WIN_WIDTH / 2 - 200);
        text.setY(WIN_HEIGHT / 2);
        root.getChildren().add(text);

        AnimationTimer an = new AnimationTimer() {
            @Override
            public void handle(long now) {
                root.moveDVD();
                WIN_WIDTH = (int) scene.getWidth();
                WIN_HEIGHT = (int) scene.getHeight();
                ecran.updateEcran();
            }
        };
        an.start();

        //button pressed
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    dvd.setDirectX(-DVD_STICK_MODIF);
                    break;
                case RIGHT:
                    dvd.setDirectX(DVD_STICK_MODIF);
                    break;
                case UP:
                    dvd.setDirectY(DVD_STICK_MODIF);
                    break;
                case DOWN:
                    dvd.setDirectY(-DVD_STICK_MODIF);
                    break;
                case ESCAPE:
                    DVD_MOVE_CURR = DVD_MOVE_INITIAL;
                    break;
            }
            CURR_WRITTEN_CODE = CURR_WRITTEN_CODE + event.getText();
            if ((CURR_WRITTEN_CODE).toUpperCase().contains(CHEAT_CODE_SPEED.toUpperCase())) {
                DVD_MOVE_CURR = DVD_MOVE_CURR + 30;
                text.setFill(Color.GRAY);
                text.setText("SUGOMA CODE ACTIVATED :flushed:");
                text.setOpacity(1);
                CURR_WRITTEN_CODE = "";
            }

            if ((CURR_WRITTEN_CODE).toUpperCase().contains(CHEAT_CODE_SMOL.toUpperCase())) {
                dvd.setDirectY(-dvd.directY / 2);
                dvd.setDirectX(-dvd.directX / 2);
                text.setFill(Color.GRAY);
                text.setText("wow you smol :rofl:");
                text.setOpacity(1);
                CURR_WRITTEN_CODE = "";
            }

            if(CURR_WRITTEN_CODE.length() > 100){
                CURR_WRITTEN_CODE = "";
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}