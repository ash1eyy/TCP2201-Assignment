import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class GameController {
    private Board board = Board.createInstance();
    private GameView view = new GameView(this);
    private int turn = 0;
    private String userColour = "yellow";
    private boolean gameWin = false;
    private String winner;

    public GameController() {
        board.init();
    }

    public GameController(Board board, GameView view) {
        this.board = board;
        this.view = view;
    }

    /* 
    Getters and Setters methods to get and set the private varaiable
    */

    public int getTurn() {
        return turn;
    }

    public String getPlayer() {
        return userColour;
    }

    public boolean getGameWin() {
        return gameWin;
    }

    public String getWinner() {
        return winner;
    }

    public Board getBoard() {
        return board;
    }

    public GameView getGameView() {
        return view;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setGameView(GameView view) {
        this.view = view;
    }

    public void setGameWin(boolean gameWin) {
        this.gameWin = gameWin;
    }

    /* 
    
    Method that changes turns, the method uses a modulus to determine each players turn

    0 and 1 are the remainder of each turn % 2
    basically checking odd and even

    case 0 : will change player to yellow
    case 1 : will chnage player to blue 


    If turns are divided by 2 and return a remainder of 0 
    Time and Plus pieces will be switched

    */

    public void changeTurns() {
        turn++;
        
        if (turn > 0) {
            switch (turn % 2) {
                case 0: // yellow at the bottom of the screen
                    userColour = "yellow";
                    break;
                case 1: // blue at the bottom of the screen
                    userColour = "blue";
                    break;
            }
        }

        board.flipBoard();

        if (turn % 2 == 0) {
            board.switchTimeAndPlus();
        }
    }


    /* 

    Returns true if a move was successfully made, otherwise returns false

    */

    public boolean move(Piece piece, int newX, int newY) { 

        int oldX = piece.getX();
        int oldY = piece.getY();
        
        //iterates through all the valid moves of the given piece
        for (ArrayList<Integer> validMove : piece.getValidMoves(board, userColour)) {
            //if the new coords are one of the valid moves
            if (validMove.get(0) == newX &&
                validMove.get(1) == newY) {
                //System.out.println(board.getPiece(newX, newY).getPieceName());
                //if the piece at the destination is the sun, win the game
                //!! FIX !! only runs the turn after eating the sun :(
                if (board.getPiece(newX, newY) != null && 
                    board.getPiece(newX, newY).getPieceName().equals("Sun")) {
                        
                    //win game :)
                    gameWin = true;
                    winner = piece.getColour();
                }

                board.setPiece(newX, newY, piece);
                board.setPiece(oldX, oldY, null);
                return true;
            }
        }
        return false;
    }

    /* 
    Save feature
    Writes data into a text file
    @param filename 
    
    done by ashley
    WORKING!!!
    */
    public void save(GameView view, String filename) {

        /* 0 to not overwrite, 1 to overwrite */
        int overwrite = 0; 

        /* Open new file with user input filename */
        File file = new File(filename + ".txt");
        if (file.exists()) {
            overwrite = (JOptionPane.showConfirmDialog(view.getBoardPanel(), "Overwrite file?", "Save",
                    JOptionPane.YES_NO_OPTION));

            if (overwrite == JOptionPane.NO_OPTION) {
                return;
            }
        }

        /* Save data into the file */
        try {
            /* Open the file in FileWriter */
            FileWriter myWriter = new FileWriter(filename + ".txt"); // just to know what type of file it saved into

            /* 
            Saves turn number
            Saves user colour (whose turn it is)
            */
            myWriter.write(turn + "\n");
            myWriter.write(userColour + "\n\n");

            // saves the pieces, their colour + direction
            for (int i = 0; i < board.getY(); i++) {
                for (Piece j : board.getMap().get(i)) {
                    Piece piece = j;

                    if (piece == null)
                        myWriter.write(" \n");
                    else {
                        myWriter.write(piece.getClass().getName() + "\n" + piece.getColour() + "\n" + piece.getDir() + "\n");
                    }
                }
                myWriter.write("\n"); /* New line for every row in the board */
            }
            // close the file
            myWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view.getBoardPanel(), "File could not be saved.");
            e.printStackTrace();
        }
    }

    /* 
    Load feature
    Loads game from data that was saved in text file
    @param filename 
    
    Still being worked on
    */
    public void load (String filename) throws Exception {
        try {
            File file = new File(filename + ".txt");
            Scanner fileReader = new Scanner(file);

            turn = fileReader.nextInt();
            fileReader.nextLine();
            userColour = fileReader.nextLine();
            fileReader.nextLine();

            for (int i = 0; i < board.getY(); i++) {
                for (int j = 0; j < board.getX(); j++) {
                    String pieceName = fileReader.nextLine();
                    System.out.println(pieceName);

                    if (" ".equals(pieceName)) {
                        board.setPiece(j, i, null);
                    }
                    else {
                        String pieceColour = fileReader.nextLine();
                        String pieceDir = fileReader.nextLine();

                        Piece piece = PieceFactory.toPiece(pieceName);
                        piece.setX(j);
                        piece.setY(i);
                        piece.setColour(pieceColour);
                        piece.setDir(pieceDir);
                        
                        board.setPiece(j, i, piece);
                    }
                }
                fileReader.nextLine();
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Exit the game. <-- Javier Austin ^.^
    public void exit() {
        System.out.println("Exiting Game...Goodbye!");
        System.exit(0);
    }

    /* 
    Help commands
    How to play the game

    @return helpMessage.toString displays help messages
    */

    public String helpCommands() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("----------How to play Talabia Chess?----------\n");
        helpMessage.append("The Point Piece can only move forward by 1 or 2 steps.\n");
        helpMessage.append("The Hourglass Piece moves in a 3x2 L shape in any orientation.\n");
        helpMessage.append("The Time Piece can only move diagonally but can go any distance.\n");
        helpMessage.append("The Plus Piece can move horizontally and vertically only but can go any distance.\n");
        helpMessage.append("The Sun Piece can move only one step in any direction.\n");

        return helpMessage.toString();
    }
}