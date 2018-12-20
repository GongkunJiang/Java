package bank;

import java.awt.*;

class Custome {
    private Color fillcolor;
    private int x1, x2, y1, y2,No;


    Custome(Color cyan, int x1, int y1, int No, int x2, int y2) {
        this.fillcolor = cyan;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.No = No;

    }

    public Color getFillcolor() {
        return fillcolor;
    }

    public void setFillcolor(Color fillcolor) {
        this.fillcolor = fillcolor;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setNo(int no) {
        No = no;
    }

    public int getNo() {
        return No;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
