import view.BrickGame;

public class TestGame {
    public static void main(String[] args) {
        BrickGame game = new BrickGame(1000, 800);

        game.setVisible(true);
        game.run();
    }
}
