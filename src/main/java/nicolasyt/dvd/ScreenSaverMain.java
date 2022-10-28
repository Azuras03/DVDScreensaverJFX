package nicolasyt.dvd;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nicolasyt.dvd.M.DVDModele;
import nicolasyt.dvd.M.EcranModele;
import nicolasyt.dvd.V.DVDVue;
import nicolasyt.dvd.V.VueEcran;

import java.io.IOException;

public class ScreenSaverMain extends Application {

    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;
    public static final int DVD_WIDTH = 100;
    public static final int DVD_HEIGHT = 100;
    public static final int DVD_START_X = 0;
    public static final int DVD_START_Y = 0;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ScreenSaverMain.class.getResource("hello-view.fxml"));
        DVDModele dvd = new DVDModele(DVD_START_X, DVD_START_Y, DVD_WIDTH, DVD_HEIGHT);
        EcranModele ecran = new EcranModele(WIN_WIDTH, WIN_HEIGHT, dvd);
        DVDVue dvdVue = new DVDVue(dvd);
        VueEcran root = new VueEcran(dvdVue, ecran);
        Scene scene = new Scene(root, WIN_WIDTH, WIN_HEIGHT);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        AnimationTimer an = new AnimationTimer() {
            @Override
            public void handle(long now) {
                root.moveDVD();
            }
        };
        an.start();

    }

    public static void main(String[] args) {
        launch();
    }
}