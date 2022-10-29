package nicolasyt.dvd.V;

import javafx.scene.layout.Pane;
import nicolasyt.dvd.M.EcranModele;
import nicolasyt.dvd.ScreenSaverMain;

public class VueEcran extends Pane {

    public DVDVue dvdVue;
    public EcranModele ecran;

    public VueEcran(DVDVue dvdVue, EcranModele ecran) {
        this.setStyle("-fx-background-color: #000000");
        this.dvdVue = dvdVue;
        this.ecran = ecran;
        this.getChildren().add(dvdVue.getRectangleDVD());
    }

    public void moveDVD() {

        if(ecran.moveDVDEcran(ScreenSaverMain.DVD_MOVE_CURR)){
            dvdVue.changeColor();
        }
        dvdVue.updateVueDVD();
    }
}
