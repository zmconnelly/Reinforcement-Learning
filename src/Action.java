
//The Action class stores a pair of values which constitute an acceleration
public class Action {

    public static final Action[] VALID_ACTIONS;     //An array of all possible acceleration, can be used to iterate over potential values

    static {
        VALID_ACTIONS = new Action[9];
        VALID_ACTIONS[0] = (new Action(-1, -1));
        VALID_ACTIONS[1] = (new Action(-1, 0));
        VALID_ACTIONS[2] = (new Action(-1, 1));

        VALID_ACTIONS[3] = (new Action(0, -1));
        VALID_ACTIONS[4] = (new Action(0, 0));
        VALID_ACTIONS[5] = (new Action(0, 1));

        VALID_ACTIONS[6] = (new Action(1, -1));
        VALID_ACTIONS[7] = (new Action(1, 0));
        VALID_ACTIONS[8] = (new Action(1, 1));
    }

    private final int x;
    private final int y;

    public Action(int action) {
        x = ((int) (action / 3)) - 1;
        y = (action % 3) - 1;
    }

    public Action(int Ax, int Ay) {

        assert (Ax >= -1 && Ax <= 1) : "Invalid X acceleration.";
        assert (Ay >= -1 && Ay <= 1) : "Invalid Y acceleration.";

        this.x = Ax;
        this.y = Ay;
    }

    public int[] getAction() {
        return new int[]{x, y};
    }

    //this is the sloppiest thing ever and i resent the fact were using an index to store our acclerations when they are inherently a pair of values
    public int toInt() {

        int val = 0;
        if (x == 0) {
            val += 3;
        }
        if (x == 1) {
            val += 6;
        }
        if (y == 0) {
            val += 1;
        }
        if (y == 1) {
            val += 2;
        }
        return val;
    }
}