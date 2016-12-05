
public class Simulator {

    private Agent agent;
    private ICollisionHandler collisionHandler;
    private char[][] track;
    private int numMoves;

    public Simulator() {

    }

    public Simulator(Agent agent, ICollisionHandler collisionHandler, char[][] track) {

        this.agent = agent;
        this.collisionHandler = collisionHandler;
        this.track = track;
        this.numMoves = 0;
    }

    public void run() {

        int[] policy = agent.learner.createPolicy();

        while (true) {

            //take action according to policy
            int stateID = agent.stateInfo.stateID;
            takeAction(stateID, policy[stateID]);
        }
    }

    public State takeAction(int stateID, int action) {

        this.numMoves++;
        switch (action) {                               //convert action into accelerations
            case 0:
                accelerate(-1, -1);
                break;
            case 1:
                accelerate(-1, 0);
                break;
            case 2:
                accelerate(-1, 1);
                break;
            case 3:
                accelerate(0, -1);
                break;
            case 4:
                accelerate(0, 0);
                break;
            case 5:
                accelerate(0, 1);
                break;
            case 6:
                accelerate(1, -1);
                break;
            case 7:
                accelerate(1, 0);
                break;
            case 8:
                accelerate(1, 1);
                break;
            default:
                System.out.println("Unreacable line: simulator.run()");
                System.exit(action);
        }
        traverse();
        printTrack();
        return agent.state;
    }

    //Updates the agent's current state by applying an acceleration
    private void accelerate(int Ax, int Ay) {

        assert (Ax >= -1 && Ax <= 1);
        assert (Ay >= -1 && Ay <= 1);

        //update velocities with 80% success rate
        if (Math.random() > 0.2) {
            this.agent.state = new State(agent.state.position, (agent.state.Vx + Ax) % 6, (agent.state.Vy + Ay) % 6);
        }
    }

    //Moves the agent according to their current velocity and detects wall collisions using
    //a super cover implementaion of Bresenham's line algorithm
    private void traverse() {

        int x = agent.state.x;
        int y = agent.state.y;
        int vx = agent.state.Vx;
        int vy = agent.state.Vy;
        int x2 = x + vx;
        int y2 = y + vy;
        int count = 1 + vx + vy;
        int xInc = (x2 > x) ? 1 : -1;
        int yInc = (y2 > y) ? 1 : -1;
        int error = vx - vy;

        vx *= 2;
        vy *= 2;

        for (; count > 0; --count) {

            if (track[y][x] == '#') {
                agent.state = collisionHandler.handleCollision(agent.state, new Position(x, y));
            } else if (track[y][x] == 'F') {
                endSimulation();
                return;
            }

            if (error > 0) {
                x += xInc;
                error -= vy;
            } else {
                y += yInc;
                error += vx;
            }
        }

        agent.state.position = new Position(x2, y2);
    }

    private void endSimulation() {

        System.out.println("Agent reached the finish line!");
        printTrack();
        //TODO add stats tracking and print out summary here

        System.exit(0);
    }

    private void printTrack() {

        int x = agent.state.x;
        int y = agent.state.y;

        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[0].length; j++) {
                if (x == i && y == j) {
                    System.out.print("A");
                } else {
                    System.out.print(track[i][j]);
                }
            }
            System.out.println("");
        }
    }
}
