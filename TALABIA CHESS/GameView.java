import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Queue;
import java.awt.event.ActionEvent;
import javax.swing.*;


//This is where the GUI code is.

// The chess Tiles are made out of buttons 
//
// Buttons of different actions are next to the chess tiles where they are easily accessible
//
//
//
//
//


public class GameView extends JFrame { // by Javier Austin and Ashley ^.^
    // private static GameView singleInstance = null;
    private final int dimX = 7;
    private final int dimY = 6;
    private PieceButton originalPiece = null; //the piece that was clicked before, if it exists
    private PieceButton newPiece = null; //the piece that was just selected
    private boolean pieceSelected = false; //
    private GameController controller;
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
                        addActionListener(new PieceListener());
                        setEnabled(false); //all piece buttons disabled before game starts
                    }
                };
                button.setX(j);
                button.setY(i); 
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = buttonPanel();
        add(buttonPanel, BorderLayout.WEST);
    }

//////////////////////////////////////////////////////////////////////
    // public static synchronized GameView createInstance() {
    //     if (singleInstance == null) 
    //         singleInstance = new GameView();
        
    //     return singleInstance;
    // }
/////////////////////////////////////////////////////////////////////


    public PieceButton getOriginalPiece() { //method to get original piece
        return this.originalPiece;
    }


    //Getters
    public PieceButton getNewPiece() { 
        return this.newPiece;
    }

    public boolean getPieceSelected() {
        return this.pieceSelected;
    }

    public PieceButton[][] getButtons() {
        return this.buttons;
    }


    //Setters
    //parameter original piece
    public void setOriginalPiece(PieceButton originalPiece) {
        this.originalPiece = originalPiece;
    }

    public void setNewPiece(PieceButton newPiece) {
        this.newPiece = newPiece;
    }

    public void setPieceSelected(boolean pieceSelected) {
        this.pieceSelected = pieceSelected;
    }

    public void setGameController(GameController controller) {
        this.controller = controller;
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
        startButton.addActionListener(new ActionListener() { //start button in GUI
            @Override
            public void actionPerformed(ActionEvent e) {
                //enable piece buttons
                changeButtonState(true);
                //enable save button
                saveButton.setEnabled(true);
                updatePieces();
                // controller.newGame();
            }
        });


        saveButton.addActionListener(new ActionListener() { //save button in GUI
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = JOptionPane.showInputDialog(null, "Enter save file name:", "Save", JOptionPane.QUESTION_MESSAGE);
                controller.save(filename);
            }
        });
        buttonPanel.add(saveButton);


        loadButton.addActionListener(new ActionListener() { //load button in GUI
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = JOptionPane.showInputDialog(null, "Enter save file name:", "Load", JOptionPane.QUESTION_MESSAGE);


                //get("filename.txt")
                controller.load(filename);
            }
        });
        buttonPanel.add(loadButton);


        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String helpMessage = controller.helpCommands();

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
                controller.exit();
            }
        });
        buttonPanel.add(exitButton);

        return buttonPanel;
    }


    //Getters
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public GameController getController() {
        return controller;
    }

    public void changeButtonState(boolean buttonState) {
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                buttons[i][j].setEnabled(buttonState);
            }
        }
    }

    //add the pieces in the board in its respective cell
    //call this function every time a piece is moved!!!!
    
    public void updatePieces() {
        Queue<Piece> pieces = new LinkedList<Piece>(); //queue data structure GG
        Component[] components = boardPanel.getComponents();//gets all components as component object

        for (int i = 0; i < controller.getBoard().getY(); i++) {
            for (Piece j : controller.getBoard().getMap().get(i)) {
                if (j != null)
                    pieces.add(j);// if not null fill with 
                else
                    pieces.add(null);
            }
        }

        for (Component component : components) { //for each loop 
            PieceButton button = (PieceButton) component;

            if (pieces.peek() != null) {
                Piece chosenPiece = pieces.peek();
                button.setPiece(chosenPiece);
                button.setIcon(new ImageIcon("Images/" + chosenPiece.getColour() + chosenPiece.getPieceName() + ".png")); //place where icon is mapped to button
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
