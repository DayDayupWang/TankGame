import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class MyTankGameDemo extends JFrame {
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        MyTankGameDemo Game01 = new MyTankGameDemo();
    }

    public MyTankGameDemo() {
        System.out.println("请输入选择 1:新游戏 --- 2:继续之前的游戏");
        String key = scanner.next();
        mp = new MyPanel(key);

        // 将mp放入线程中从而实现重绘功能
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);
        this.setSize(1300, 750);
        this.addKeyListener(mp);// 增加监听事件，监听mp
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // System.out.println("监听到关闭窗口了");
                Recorder.keepRecord();
                System.exit(0);
                ;
            }
        });
    }
}
