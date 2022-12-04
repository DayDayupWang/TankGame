//让shot继承runnable从而发射子弹
public class Shot implements Runnable {
    int x;
    int y;
    int direction = 0;
    int speed = 5;
    boolean isLive = true;

    public Shot(int x, int y, int direction, int speed) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true) {
            // 子弹休眠50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            switch (direction) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
                default:
                    break;
            }
            System.out.println("子弹在" + x + ", " + y + "位置移动");
            if (x < 0 || x > 1000 || y < 0 || y > 750) {
                isLive = false;
                break;
            }
        }
    }
}
