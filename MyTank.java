import java.util.Vector;

public class MyTank extends Tank {
    Shot shot = null;
    // 发射多颗子弹的集合
    Vector<Shot> shots = new Vector<Shot>();

    public MyTank(int x, int y) {
        super(x, y);

    }

    public void shotEnemy() {
        switch (getDirection()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0, 5);
                break;
            case 1:
                shot = new Shot(getX() + 50, getY() + 30, 1, 5);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2, 5);
                break;
            case 3:
                shot = new Shot(getX() - 10, getY() + 30, 3, 5);
                break;

        }
        shots.add(shot);
        new Thread(shot).start();
    }

}
