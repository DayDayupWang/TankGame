public class MyTank extends Tank {
    Shot shot = null;

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
        new Thread(shot).start();
    }

}
