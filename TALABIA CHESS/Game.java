import javax.swing.*;

public class Game { // Design Pattern: Facade. Front-facing interface masking more complex code.
    public static void main(String[] args) {
        GameController controller = new GameController();

        GameView view = new GameView();
        controller.setGameView(view);

        JFrame f = new GameView();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        // do game things
    }
}
