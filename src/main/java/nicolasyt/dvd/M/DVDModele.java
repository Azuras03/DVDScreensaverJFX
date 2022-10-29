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
            case "DB" -> {
                x += distance;
                y -= distance;
            }
            case "GB" -> {
                x -= distance;
                y -= distance;
            }
            case "GH" -> {
                x -= distance;
                y += distance;
            }
            case "DH" -> {
                x += distance;
                y += distance;
            }
        }
    }

    public void changeDirection(int selector) {
        switch (selector) {
            case 1 -> {
                switch (direction) {
                    case "DB" -> direction = "GB";
                    case "GB" -> direction = "DB";
                    case "GH" -> direction = "DH";
                    case "DH" -> direction = "GH";
                }
            }
            case 2 -> {
                switch (direction) {
                    case "DB" -> direction = "DH";
                    case "GB" -> direction = "GH";
                    case "GH" -> direction = "GB";
                    case "DH" -> direction = "DB";
                }
            }
        }
    }

    public void changeCornerTouch() {
        cornerTouch = !cornerTouch;
    }

    public void setDirectX(int directXPlus) {
        if (x > 0 && x + directX + directXPlus < ScreenSaverMain.WIN_WIDTH) {
            if (directX + directXPlus > 0) {
                this.directX += directXPlus;
            }
        }
    }

    public void setDirectY(int directYPlus) {
        if (y > 0 && y + directY + directYPlus < ScreenSaverMain.WIN_HEIGHT) {
            if (directY + directYPlus > 0) {
                this.directY += directYPlus;
            }
        }
    }
}
