import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GameView extends JFrame { // by Javier Austin and Ashley ^.^
    private static int column = 7;
    private static int row = 6;
    private static GameController controller = new GameController();
    private JPanel boardPanel = new JPanel(new GridLayout(row, column)); //turned into a variable for easy access

    public GameView() {
        super("Talabia Chess");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 400)); //prefferd size

        //adds button to the arraylist and to the board panel
        for (int i = 0; i < row * column; i++) {
            JButton button = new JButton() {
                {
                    setPreferredSize(new Dimension(50, 50));
                    setOpaque(true);
                    setBorderPainted(true);
                }
            };
            boardPanel.add(button);
        }
        updatePieces(boardPanel);
        add(boardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = buttonPanel();
        add(buttonPanel, BorderLayout.WEST);
    }

    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(100,400));

        JButton startButton = new JButton("Start");
        buttonPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.newGame();
            }
        });

        JButton saveButton = new JButton("Save");
        buttonPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GameController.save(getWarningString(), ABORT, getName());
            }
        });

        JButton loadButton = new JButton("Load");
        buttonPanel.add(loadButton);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GameController.load(null, getWarningString(), ABORT, getName());
            }
            
        });

        JButton helpButton = new JButton("Help");
        buttonPanel.add(helpButton);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String helpMessage = GameController.helpCommands();

                // Check if the helpMessage is not null before showing the dialog
                if (helpMessage != null) {
                    JOptionPane.showMessageDialog(buttonPanel, helpMessage);
                } else {
                    JOptionPane.showMessageDialog(buttonPanel, "No help available.");
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.exit();
            }
        });

        return buttonPanel;
    }

    //add the pieces in the board in its respective cell
    //call this function every time a piece is moved!!!!
    
    public void updatePieces(JPanel boardPanel) {
        Queue<String> pieceNames = new LinkedList<String>();
        Component[] components = boardPanel.getComponents();//gets all components as component object

        for (int i = 0; i < controller.getBoard().getY(); i++) {
            for (Piece j : controller.getBoard().getMap().get(i)) {
                if (j != null)
                    pieceNames.add(j.getColour() + j.getPiece());
                else
                    pieceNames.add("empty");
            } 
        } //this for loop is to access the pieces in the board in the model (ok keep this)
        
        for (Component component : components) {
            JButton button = (JButton) component; //changing it into a JButton object (polymorphism)

            if (pieceNames.peek() != "empty")//when not empty 
                button.setIcon(new ImageIcon("Images/" + pieceNames.poll() + ".png")); //set button to corresponding image
            else {
                button.setIcon(null);
                pieceNames.poll();
            }
        }
    }
}
