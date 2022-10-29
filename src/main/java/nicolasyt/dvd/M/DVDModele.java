package nicolasyt.dvd.M;

import nicolasyt.dvd.ScreenSaverMain;

public class DVDModele {
    public int x;
    public int y;
    public int directX;
    public int directY;
    public String direction;
    public boolean cornerTouch;

    public DVDModele(int x, int y, int dx, int dy) {
        if (dx <= 0) {
            dx = 10;
        }
        if (dy <= 0) {
            dy = 10;
        }
        this.x = x;
        this.y = y;
        this.directX = dx;
        this.directY = dy;
        this.direction = "DB";
        this.cornerTouch = false;
    }

    public void move(int distance) {
        switch (direction) {
            case "DB":
                x += distance;
                y -= distance;
                break;
            case "GB":
                x -= distance;
                y -= distance;
                break;
            case "GH":
                x -= distance;
                y += distance;
                break;
            case "DH":
                x += distance;
                y += distance;
                break;
        }
    }

    public void changeDirection(int selector) {
        switch (selector) {
            case 1:
                switch (direction) {
                    case "DB":
                        direction = "GB";
                        break;
                    case "GB":
                        direction = "DB";
                        break;
                    case "GH":
                        direction = "DH";
                        break;
                    case "DH":
                        direction = "GH";
                        break;
                }
                break;
            case 2:
                switch (direction) {
                    case "DB":
                        direction = "DH";
                        break;
                    case "GB":
                        direction = "GH";
                        break;
                    case "GH":
                        direction = "GB";
                        break;
                    case "DH":
                        direction = "DB";
                        break;
                }
                break;
        }
    }

    public void changeCornerTouch() {
        cornerTouch = !cornerTouch;
    }

    public void falsifyCornerTouch() {
        cornerTouch = false;
    }

    public void verifyCornerTouch() {
        cornerTouch = true;
    }

    public void setDirectX(int directXPlus) {
        if (x > 0 && x + directX + directXPlus < ScreenSaverMain.WIN_WIDTH) {
            if (directX + directXPlus > 1) {
                this.directX += directXPlus;
            }
        }
    }

    public void setDirectY(int directYPlus) {
        if (y > 0 && y + directY + directYPlus < ScreenSaverMain.WIN_HEIGHT) {
            if (directY + directYPlus > 1) {
                this.directY += directYPlus;
            }
        }
    }
}
