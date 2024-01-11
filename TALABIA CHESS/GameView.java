import javax.swing.*;//imports 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GameView extends JFrame { // zaf and thash doing this, hello

    private GameController controller;

    public GameView() {
        super("Talabia Chess");
        JPanel panel = new JPanel();

        // default size for board
        setSize(800, 800);
        
        setLayout(new BorderLayout());
        

        // make the window resizeable
        setResizable(true);

        addGameBoard(); // adds the gameboard into GUI

        addControlPanel();// adds the Control Into GUI
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);// make frame visible
    }

    // add components
    public void addGameBoard() {

        JPanel boardPanel = new JPanel();
        boardPanel.add(new JLabel("Game Board Here")); // this replace with an actual game board
        add(boardPanel, BorderLayout.CENTER); // add to the center of the frame

        GridLayout grid = new GridLayout(controller.getGameModel().getX(), controller.getGameModel().getY());
        boardPanel.setLayout(grid);

        ArrayList<ArrayList<Piece>> map = controller.getGameModel().getMap();
        for (int i = 0; i < controller.getGameModel().getY(); i++) {
            for (int j = 0; j < controller.getGameModel().getX(); j++) {

                Piece piece = map.get(i).get(j);
                JButton newButton = new JButton();

            }
        } 
    }

    public void addControlPanel() {
        JPanel controlPanel = new JPanel();
        add(controlPanel);

        // the start button for the game
        JButton startButton = new JButton("Start");
        controlPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        // the save button for the game
        JButton saveButton = new JButton("Save");
        controlPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // prompts the user to input a filename
            }
        });

        // the exit button for the game
        JButton exitButton = new JButton("Exit");
        controlPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {               
                JOptionPane.showMessageDialog(exitButton, "Exiting...");
                Game.exit();
            }
        });
    }
}

