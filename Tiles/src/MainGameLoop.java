public class MainGameLoop {
    private Board board;
    private Score score;
    private InputState inputState;
    private Display display;
    public MainGameLoop() {
        board = new Board();
        score = new Score();
        inputState = new InputState();
        display = new Display();
    }

    public void startGame() {
    }
    public static void main(String[] args) {
        MainGameLoop game = new MainGameLoop();
        game.startGame();
    }
}
