import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Recorder {
    // 定义我方击毁坦克数量
    private static int allETankNum = 0;

    // private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;

    private static String recordFile = "D:\\myRecord.txt";
    private static Vector<Node> nodes = new Vector<>();
    private static Vector<EnemyTank> enemyTanks = null;

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    // 增加用于读取文件恢复信息的方法
    public static Vector<Node> getNodes()  {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            // int allEnemyTank = Integer.parseInt(br.readLine());
            String line = "";
            try {
                while ((line = br.readLine()) != null) {
                    String[] xyd = line.split(" ");
                    Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                    nodes.add(node);
                }
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return nodes;

    }

    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allETankNum + "\r\n");// 写入数据并换行
            for (int i = 0; i < enemyTanks.size(); i++) {

                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection();
                    bw.write(record + "\r\n");
                }

            }

        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }

    public static int getAllETankNum() {
        return allETankNum;
    }

    public static void setAllETankNum(int allETankNum) {
        Recorder.allETankNum = allETankNum;
    }

    public static void addallETankNum() {
        allETankNum++;
    }
}
