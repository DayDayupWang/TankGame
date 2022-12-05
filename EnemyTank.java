import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    // private int direction = 2;// 0123上右下左
    Vector<Shot> shots = new Vector<Shot>();
    boolean isLive = true;
    private Vector<EnemyTank> enemyTanks;

    public EnemyTank(int x, int y) {
        super(x, y);

    }

    // 把MyPannel的成员Vector<EnemyTank> enemyTanks设置给EnemyTank的成员enemyTanks
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }



    // 编写判断当前敌人坦克是否和enemyTanks中其他坦克重叠或者碰撞
    public boolean isTouch() {
        switch (this.getDirection()) {

            case 0:// 自己是上下
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        switch (enemyTank.getDirection()) {
                            case 0:
                            case 2:// 敌人是上和下
                                if (this.getX() >= enemyTank.getX() - 40 && this.getX() <= enemyTank.getX() + 40
                                        && this.getY() >= enemyTank.getY() - 60
                                        && this.getY() <= enemyTank.getY() + 60) {
                                    return true;
                                }
                                break;
                            case 1:
                            case 3:// 敌人是右和左
                                if (this.getX() >= enemyTank.getX() - 40 && this.getX() <= enemyTank.getX() + 60 &&
                                        this.getY() >= enemyTank.getY() - 60 && this.getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }
                                break;

                        }

                    }
                }
                break;
            case 1:// 自己是右和左
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        switch (enemyTank.getDirection()) {
                            case 0:
                            case 2:// 敌人是上和下
                                if (enemyTank.getX() >= this.getX() - 40 && enemyTank.getX() <= this.getX() + 60 &&
                                        enemyTank.getY() >= this.getY() - 60 && enemyTank.getY() <= this.getY() + 40) {
                                    return true;
                                }
                                break;
                            case 1:
                            case 3:// 敌人是右和左
                                if (this.getX() >= enemyTank.getX() - 60 && this.getX() >= enemyTank.getX() + 60 &&
                                        this.getY() >= enemyTank.getY() - 40 && this.getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }
                                break;

                        }
                    }
                }

                break;

        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            // 根据坦克的方向继续移动
            switch (getDirection()) {
                case 0:

                    for (int i = 0; i < (int) (Math.random() * 10 + 30); i++) {
                        try {

                            moveUp();

                            Thread.sleep(50); // 休眠50ms
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                    }

                    break;
                case 1:
                    for (int i = 0; i < (int) (Math.random() * 10 + 30); i++) {
                        try {

                            moveRight();

                            Thread.sleep(50); // 休眠50ms
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                    }

                    break;
                case 2:
                    for (int i = 0; i < (int) (Math.random() * 10 + 30); i++) {
                        try {

                            moveDown();

                            Thread.sleep(50); // 休眠50ms
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                    }

                    break;
                case 3:
                    for (int i = 0; i < (int) (Math.random() * 10 + 30); i++) {
                        try {

                            moveLeft();

                            Thread.sleep(50); // 休眠50ms
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                    }

                    break;

            }

            // 随机改变坦克方向
            setDirection((int) (Math.random() * 4));
            if (!isLive) {
                break;// 退出线程
            }
        }

    }

}
