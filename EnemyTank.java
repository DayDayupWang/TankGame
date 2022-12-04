import java.util.Vector;

public class EnemyTank extends Tank {

    // private int direction = 2;// 0123上右下左
    Vector<Shot> shots = new Vector<Shot>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);

    }

}
