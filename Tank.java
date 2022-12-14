public class Tank {
    private int x;
    private int y;
    private int direction = 0;// 0123上右下左
    private int speed = 2;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveUp() {
        if (y <= 0) {
            return;
        } else {
            y -= speed;
        }

    }

    public void moveRight() {
        if (x >= 930) {
            return;
        } else {
            x += speed;
        }

    }

    public void moveDown() {
        if (y >= 650) {
            return;
        } else {
            y += speed;
        }

    }

    public void moveLeft() {
        if (x <= 10) {
            return;
        } else {
            x -= speed;
        }

    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
