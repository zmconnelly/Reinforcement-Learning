
//State holds a pair of x, y coordinates indicating a location and a pair of x,y velocity components
public final class State {

    public Position position;                  //Agent's (x,y) position
    public Velocity velocity;                  //Agent's velocity
    public boolean start;
    public boolean finish;

    private double utility;                    //Utility associated with being in this state

    public State() {

    }

    //Constructor for pre-constructed attributes
    public State(Position position, Velocity velocity) {
        this.position = position;
        this.velocity = velocity;
        this.utility = Math.random();
    }

    //Constructor for individual state components
    public State(int Px, int Py, int Vx, int Vy) {

        this.position = new Position(Px, Py);
        this.velocity = new Velocity(Vx, Vy);
        this.utility = Math.random();
    }

    //Set the utility of being in this state
    public void setUtility(double utility) {
        this.utility = utility;
    }

    //Return the utility of being in this state
    public double getUtility() {
        return this.utility;
    }
}
