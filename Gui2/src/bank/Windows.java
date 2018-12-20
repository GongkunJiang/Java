package bank;

class Windows {
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int x, y, state;

    Windows(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }
}
