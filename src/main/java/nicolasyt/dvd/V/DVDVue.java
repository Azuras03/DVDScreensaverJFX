package nicolasyt.dvd.V;

import javafx.scene.shape.Rectangle;
import nicolasyt.dvd.M.DVDModele;

public class DVDVue extends Rectangle{
    public DVDModele dvd;

    public DVDVue(DVDModele dvd) {
        this.dvd = dvd;
        this.setWidth(dvd.directX);
        this.setHeight(dvd.directY);
        this.setX(dvd.x);
        this.setY(dvd.y);
        this.setStyle("-fx-fill: #FF0000");
    }
    public Rectangle getRectangleDVD() {
        return this;
    }

    public void updateVueDVD() {
        this.setX(dvd.x);
        this.setY(dvd.y);
        this.setWidth(dvd.directX);
        this.setHeight(dvd.directY);
        if(dvd.cornerTouch){
            changeColor();
        }
    }

    public void changeColor() {
        this.setFill(javafx.scene.paint.Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
    }


}
