package nicolasyt.dvd.M;

import nicolasyt.dvd.ScreenSaverMain;

public class EcranModele {
    public int width;
    public int height;
    public DVDModele dvd;

    public EcranModele(int width, int height, DVDModele dvd) {
        this.width = width;
        this.height = height;
        this.dvd = dvd;
        if(dvd.x <= 0 || dvd.x >= width || dvd.y <= 0 || dvd.y >= height){
            dvd.x = width/2;
            dvd.y = height/2;
        }
        if(dvd.directX + dvd.x >= width || dvd.directY + dvd.y >= height){
            dvd.directX = 10;
            dvd.directY = 10;
        }
    }

    public boolean moveDVDEcran(int distance) {
        boolean chgDirection = false;
        int countChgs = 0;
        if (dvd.x <= 0 || dvd.x >= width - dvd.directX) {
            dvd.changeDirection(1);
            chgDirection = true;
            countChgs++;
        }
        if (dvd.y <= 0 || dvd.y >= height - dvd.directY) {
            dvd.changeDirection(2);
            chgDirection = true;
            countChgs++;
        }
        if(countChgs == 2){
            dvd.changeCornerTouch();
        }
        dvd.move(distance);
        return chgDirection;
    }

    public void updateEcran(){
        this.width = ScreenSaverMain.WIN_WIDTH;
        this.height = ScreenSaverMain.WIN_HEIGHT;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DVDModele getdvd() {
        return dvd;
    }

}
