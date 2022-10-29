package nicolasyt.dvd;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import nicolasyt.dvd.M.DVDModele;
import nicolasyt.dvd.M.EcranModele;
import nicolasyt.dvd.V.DVDVue;
import nicolasyt.dvd.V.VueEcran;

import java.io.IOException;

public class ScreenSaverMain extends Application {

    public static int WIN_WIDTH = 1280;
    public static int WIN_HEIGHT = 720;
    public static int DVD_MOVE_CURR = 10;
    public static int timeOnScreen = 300;
    public static int DVD_MOVE_INITIAL = 10;
    public static final int DVD_WIDTH = 100;
    public static final int DVD_HEIGHT = 100;
    public static final int DVD_START_X = 0;
    public static final int DVD_START_Y = 0;
    public static final int DVD_STICK_MODIF = 10;

    public static final KeyCode INPUT_UP = KeyCode.UP;
    public static final KeyCode INPUT_DOWN = KeyCode.DOWN;
    public static final KeyCode INPUT_LEFT = KeyCode.LEFT;
    public static final KeyCode INPUT_RIGHT = KeyCode.RIGHT;

    public static final Color TEXT_COLOR = Color.GRAY;

    public static boolean cheatsRunning = false;

    public static String CHEAT_CODE_SPEED = "sugoma";
    public static String CHEAT_CODE_SMOL = "smol";
    public static String CURR_WRITTEN_CODE = "";
    public static final String INIT_TEXT = (INPUT_RIGHT + " & " + INPUT_UP + " to expand, " + INPUT_LEFT + " & " + INPUT_DOWN + " to shrink");

    @Override
    public void start(Stage stage) throws IOException {

        StackPane root = new StackPane();
        DVDModele dvd = new DVDModele(DVD_START_X, DVD_START_Y, DVD_WIDTH, DVD_HEIGHT);
        EcranModele ecran = new EcranModele(WIN_WIDTH, WIN_HEIGHT, dvd);
        DVDVue dvdVue = new DVDVue(dvd);
        VueEcran vueEcran = new VueEcran(dvdVue, ecran);
        Scene scene = new Scene(root, WIN_WIDTH, WIN_HEIGHT);
        root.getChildren().add(vueEcran);

        stage.setTitle("✨ DVD Screensaver | 🟪");

        Text text = new Text(INIT_TEXT + "\n\nby Nicolas R.");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFill(TEXT_COLOR);
        text.setStyle("-fx-font: 30 arial;");
        root.getChildren().add(text);
        int timeOnScreenInitial = timeOnScreen;

        AnimationTimer an = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (timeOnScreen > 0) {
                    timeOnScreen--;
                    text.setOpacity((float) 1 * timeOnScreen / timeOnScreenInitial);
                    if (timeOnScreen == 0) {
                        text.setOpacity(0);
                    }
                }
                vueEcran.moveDVD();
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
                    dvd.falsifyCornerTouch();
                    break;
                case TAB:
                    timeOnScreen = timeOnScreenInitial;
                    text.setText(INIT_TEXT);
            }
            stage.setTitle("✨ DVD Screensaver | 🟪 " + dvd.directX + "x" + dvd.directY);
            CURR_WRITTEN_CODE = CURR_WRITTEN_CODE + event.getText();
            if ((CURR_WRITTEN_CODE).toUpperCase().contains(CHEAT_CODE_SPEED.toUpperCase())) {
                DVD_MOVE_CURR = DVD_MOVE_CURR + 30;
                text.setText("SUGOMA CODE ACTIVATED :flushed:");
                text.setOpacity(1);
                CURR_WRITTEN_CODE = "";
                cheatsRunning = true;
                timeOnScreen = timeOnScreenInitial;
            }

            if ((CURR_WRITTEN_CODE).toUpperCase().contains(CHEAT_CODE_SMOL.toUpperCase())) {
                dvd.setDirectY(-dvd.directY / 2);
                dvd.setDirectX(-dvd.directX / 2);
                text.setText("wow you smol :rofl:");
                text.setOpacity(1);
                CURR_WRITTEN_CODE = "";
                cheatsRunning = true;
                timeOnScreen = timeOnScreenInitial;
            }

            if (CURR_WRITTEN_CODE.length() > 100) {
                CURR_WRITTEN_CODE = "";
            }
        });
        stage.getIcons().add(new Image("file:resources/images/logoApp.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}