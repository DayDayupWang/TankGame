import javax.swing.*;

public class MyTankGameDemo extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        MyTankGameDemo Game01 = new MyTankGameDemo();
    }

    public MyTankGameDemo() {
        mp = new MyPanel();
        // 将mp放入线程中从而实现重绘功能
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);
        this.setSize(1000, 750);
        this.addKeyListener(mp);// 增加监听事件，监听mp
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
