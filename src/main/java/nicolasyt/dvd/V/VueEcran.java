package nicolasyt.dvd.V;

import javafx.scene.layout.Pane;
import nicolasyt.dvd.M.DVDModele;
import nicolasyt.dvd.M.EcranModele;

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

        if(ecran.moveDVDEcran(10)){
            dvdVue.changeColor();
        }
        dvdVue.updateVueDVD();
    }

}
