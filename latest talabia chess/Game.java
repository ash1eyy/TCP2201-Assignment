import javax.swing.*;

/* // Design Pattern: Facade. Front-facing interface masking more complex code. */

public class Game { 
    public static void main(String[] args) {
        GameController controller = new GameController();
        // Board board = Board.createInstance();
        // board.init();

        GameView view = new GameView(controller);
        controller.setGameView(view);

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.pack();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        // do game things
    }
}