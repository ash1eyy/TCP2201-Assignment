import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Queue;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GameView extends JFrame { // by Javier Austin and Ashley ^.^
    private static final int dimX = 7;
    private static final int dimY = 6;
    private static GameController controller = new GameController();
    private JPanel boardPanel = new JPanel(new GridLayout(dimY, dimX)); //turned into a variable for easy access
    private PieceButton[][] buttons = new PieceButton[dimY][dimX]; //so that every button has an x and y coordinate

    public GameView() {
        super("Talabia Chess");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600)); //prefered size

        //adds button to the board panel
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                PieceButton button = new PieceButton() {
                    {
                        setPreferredSize(new Dimension(50, 50));
                        setOpaque(true);
                        setBorderPainted(true);
                    }
                };
                button.addActionListener(new PieceListener(buttons));
                buttons[i][j] = button;
                buttons[i][j].setEnabled(false); //all piece buttons disabled before game starts
                boardPanel.add(button);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = buttonPanel();
        add(buttonPanel, BorderLayout.WEST);
    }

    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(100,400));

        JButton startButton = new JButton("Start");
        JButton saveButton = new JButton("Save");
        saveButton.setEnabled(false); //save button disabled before game starts
        JButton loadButton = new JButton("Load");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //enable piece buttons
                for (int i = 0; i < dimY; i++) {
                    for (int j = 0; j < dimX; j++) {
                        buttons[i][j].setEnabled(true);
                    }
                }
                //enable save button
                saveButton.setEnabled(true);
                controller.getBoard().init();
                updatePieces();
                //GameController.newGame();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = JOptionPane.showInputDialog(null, "Enter save file name:", "Save", JOptionPane.QUESTION_MESSAGE);
                GameController.save(filename);
            }
        });
        buttonPanel.add(saveButton);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = JOptionPane.showInputDialog(null, "Enter save file name:", "Load", JOptionPane.QUESTION_MESSAGE);
                GameController.load(filename);
            }
            
        });
        buttonPanel.add(loadButton);

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String helpMessage = GameController.helpCommands();

                // Check if the helpMessage is not null before showing the dialog
                if (helpMessage != null)
                    JOptionPane.showMessageDialog(buttonPanel, helpMessage);
                else
                    JOptionPane.showMessageDialog(buttonPanel, "No help available.");
            }
        });
        buttonPanel.add(helpButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.exit();
            }
        });
        buttonPanel.add(exitButton);

        return buttonPanel;
    }

    public JPanel getBoardPanel() {
        return this.boardPanel;
    }

    //add the pieces in the board in its respective cell
    //call this function every time a piece is moved!!!!
    
    public void updatePieces() {
        Queue<Piece> pieces = new LinkedList<Piece>();
        Component[] components = boardPanel.getComponents();//gets all components as component object

        for (int i = 0; i < controller.getBoard().getY(); i++) {
            for (Piece j : controller.getBoard().getMap().get(i)) {
                if (j != null)
                    pieces.add(j);
                else
                    pieces.add(null);
            }
        }

        for (Component component : components) {
            PieceButton button = (PieceButton) component;

            if (pieces.peek() != null) {
                Piece chosenPiece = pieces.peek();
                button.setPiece(chosenPiece);
                button.setIcon(new ImageIcon("Images/" + chosenPiece.getColour() + chosenPiece.getPieceName() + ".png"));
                pieces.poll();
            }
            else {
                button.setPiece(null);
                button.setIcon(null);
                pieces.poll();
            }
        }
    }
}
