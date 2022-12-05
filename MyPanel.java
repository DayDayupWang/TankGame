import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//监听键盘事件，实现keylistener
public class MyPanel extends JPanel implements KeyListener, Runnable {
    // 定义我的坦克
    MyTank mytank = null;
    // 敌方坦克存放
    Vector<EnemyTank> eT = new Vector<>();
    // 定义一个存放Node对象的Vector，用于恢复敌人坦克坐标和方向
    Vector<Node> nodes = new Vector<Node>();

    // 敌人坦克数量
    int eTSzie = 3;

    public MyPanel(String key){
        // 把画板对象的敌方坦克设置给Recorder的敌方坦克们
        nodes = Recorder.getNodes();
        Recorder.setEnemyTanks(eT);
        // 初始化自己的坦克
        mytank = new MyTank(100, 100);
        switch (key) {
            case "1":
                // 初始化敌人坦克
                for (int i = 0; i < eTSzie; i++) {
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    enemyTank.setDirection(2);
                    // 启动敌人坦克线程，让它动起来
                    new Thread(enemyTank).start();
                    // 给敌方坦克加入了一个子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection(), 5);
                    enemyTank.shots.add(shot);
                    // 启动shot对象
                    new Thread(shot).start();
                    // 加入坦克集
                    eT.add(enemyTank);

                }
                break;
            case "2":
                // 初始化敌人坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    enemyTank.setDirection(node.getDirect());
                    // 启动敌人坦克线程，让它动起来
                    new Thread(enemyTank).start();
                    // 给敌方坦克加入了一个子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection(), 5);
                    enemyTank.shots.add(shot);
                    // 启动shot对象
                    new Thread(shot).start();
                    // 加入坦克集
                    eT.add(enemyTank);

                }
                break;

            default:
                System.out.println("输入有误");
                break;
        }

    }

    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 1);// 画出一个敌方坦克
        g.setColor(Color.BLACK);// 需要重新设置颜色
        // Recorder.getAllETankNum()
        g.drawString(Recorder.getAllETankNum() + "", 1080, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);// paint(g)函数会重绘图像，要加上super.paint(g)，表示在原来图像的基础上，再画图
        g.fillRect(0, 0, 1000, 750);// 填充矩形，默认黑色
        showInfo(g);
        // 画出坦克-封装方法
        drawTank(mytank.getX(), mytank.getY(), g, mytank.getDirection(), 0);

        // // 自己坦克子弹的绘图控制
        // if (mytank.shot != null && mytank.shot.isLive == true) {
        // g.fill3DRect(mytank.shot.x, mytank.shot.y, 2, 2, false);
        // }
        // 将子弹集合Shots遍历取出绘制
        for (int i = 0; i < mytank.shots.size(); i++) {
            Shot shot = mytank.shots.get(i);
            // 假如子弹存在并存活
            if (shot != null && shot.isLive == true) {
                g.draw3DRect(shot.x, shot.y, 2, 2, false);
            }
        }

        // 画出敌人坦克
        for (int i = 0; i < eT.size(); i++) {
            // 从vector中取出坦克
            EnemyTank et = eT.get(i);
            // 画出 敌方坦克所有子弹
            if (et.isLive) {
                drawTank(et.getX(), et.getY(), g, et.getDirection(), 1);
                for (int j = 0; j < et.shots.size(); j++) {
                    // 取出子弹
                    Shot shot = et.shots.get(j);
                    // 画子弹
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        // 子弹不存活时移除
                        et.shots.remove(shot);
                    }
                }
            }

        }
    }

    public void hitTank(Shot s, EnemyTank enemyTank) {
        // 判断s 击中坦克
        switch (enemyTank.getDirection()) {
            case 0:
            case 2:// 向下
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40 && s.y > enemyTank.getY()
                        && s.y < enemyTank.getY() + 60) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    eT.remove(enemyTank);
                    Recorder.addallETankNum();
                }
                break;
            case 1:// 向右
            case 3:// 向左
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60 && s.y > enemyTank.getY()
                        && s.y < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    eT.remove(enemyTank);
                    Recorder.addallETankNum();
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            mytank.setDirection(0);
            // 修改坦克的坐标
            mytank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            mytank.setDirection(1);
            // 修改坦克的坐标
            mytank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            mytank.setDirection(2);
            mytank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            mytank.setDirection(3);
            mytank.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            // 判断我方坦克子弹是否销毁，一次只发射一颗
            // if (mytank.shot == null || !mytank.shot.isLive) {
            // mytank.shotEnemy();
            // }
            // 发射多颗子弹
            mytank.shotEnemy();
        }
        // 让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /*
     * x 坦克左上角x坐标
     * y 坦克左上角y坐标
     * g 画笔
     * direction 方向
     * type 坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
            default:
                break;
        }
        // 根据坦克方向来绘制
        switch (direction) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);// 坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);// 坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 坦克矩形盖子
                g.fillOval(x + 10, y + 20, 20, 20);// 坦克圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);// 炮管向上
                break;
            case 1:// 向右
                g.fill3DRect(x - 10, y + 10, 60, 10, false);// 坦克左边轮子
                g.fill3DRect(x - 10, y + 40, 60, 10, false);// 坦克右边轮子
                g.fill3DRect(x, y + 20, 40, 20, false);// 坦克矩形盖子
                g.fillOval(x + 10, y + 20, 20, 20);// 坦克圆形盖子
                g.drawLine(x + 20, y + 30, x + 50, y + 30);// 炮管向右
                break;
            case 2:// 向下
                g.fill3DRect(x, y, 10, 60, false);// 坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);// 坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 坦克矩形盖子
                g.fillOval(x + 10, y + 20, 20, 20);// 坦克圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);// 炮管向下
                break;
            case 3:// 向左
                g.fill3DRect(x - 10, y + 10, 60, 10, false);// 坦克左边轮子
                g.fill3DRect(x - 10, y + 40, 60, 10, false);// 坦克右边轮子
                g.fill3DRect(x, y + 20, 40, 20, false);// 坦克矩形盖子
                g.fillOval(x + 10, y + 20, 20, 20);// 坦克圆形盖子
                g.drawLine(x + 20, y + 30, x - 10, y + 30);// 炮管向左
                break;

            default:
                break;
        }

    }

    @Override
    public void run() {// 每隔100ms，重绘区域，刷新绘图区域

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            // // 判断一颗是否击中了坦克
            // if (mytank.shot != null && mytank.shot.isLive) {
            // // 遍历敌人坦克
            // for (int i = 0; i < eT.size(); i++) {
            // EnemyTank enemyTank = eT.get(i);
            // hitTank(mytank.shot, enemyTank);
            // }
            // }

            // 判断vector集合子弹是否击中了坦克 shots
            for (int j = 0; j < mytank.shots.size(); j++) {
                Shot shot = mytank.shots.get(j);
                if (shot != null && shot.isLive) {
                    // 遍历敌人坦克
                    for (int i = 0; i < eT.size(); i++) {
                        EnemyTank enemyTank = eT.get(i);
                        hitTank(shot, enemyTank);
                    }

                }
            }
            this.repaint();
        }

    }
}
