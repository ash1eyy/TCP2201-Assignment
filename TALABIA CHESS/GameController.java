import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class GameController {
    private int turn = 0;
    private String userColour = "yellow";
    private Board board = Board.createInstance();
    private GameModel model = new GameModel();
    private GameView view = new GameView();

    public GameController() {
        board.init();
    }

    public GameController(Board board, GameView view) {
        this.board = board;
        this.view = view;
    }

    public int getTurn() {
        return model.getTurn();
    }

    public String getPlayer() {
        return model.getPlayer();
    }

    public Board getBoard() {
        return model.getBoard();
    }

    public GameModel getModel() {
        return model;
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

    // public void newGame() {
    // board = Board.createInstance();
    // board.init();

    // view.updatePieces();

    // playGame(turn, userColour);
    // }

    public void changeTurns() {// method that changes turns, the method uses a modulus to determine each
                               // players turn
        // turn++;

        // if (turn > 0) {
        //     switch (turn % 2) {
        //         case 0: // yellow at the bottom of the screen
        //             userColour = "yellow";
        //             break;
        //         case 1: // blue at the bottom of the screen
        //             userColour = "blue";
        //             break;
        //     }
        // }

        // board.flipBoard();

        // if (turn % 2 == 0) {
        //     board.switchTimeAndPlus();
        // }
        model.changeTurns();
    }

    // done by ashley
    // WORKING!!!
    public void save(String filename) {
        int overwrite = 0; // 0 to not overwrite, 1 to overwrite
        // open new file with user input filename
        File myFile = new File(filename + ".txt");
        if (myFile.exists()) {
            overwrite = (JOptionPane.showConfirmDialog(view.getBoardPanel(), "Overwrite file?", "Save",
                    JOptionPane.YES_NO_OPTION));

            if (overwrite == JOptionPane.NO_OPTION) {
                return;
            }
        }

        // save data into the file
        try {
            // open the file in FileWriter
            FileWriter myWriter = new FileWriter(filename + ".txt"); // just to know what type of file it saved into

            // Hello hyukathinker

            myWriter.write(turn + "\n"); // save turn number
            myWriter.write(userColour + "\n"); // save user colour (whose turn it is)

            // saves the pieces, their colour + direction
            for (int i = 0; i < board.getY(); i++) {
                for (Piece j : board.getMap().get(i)) {
                    Piece piece = j;

                    if (piece == null)
                        myWriter.write(" \n");
                    else {
                        myWriter.write(piece.toString() + "\n" + piece.getColour() + "\n" + piece.getDir() + "\n");
                        // System.out.println(piece.toString());
                        // System.out.println(piece.getColour());
                        // System.out.println(piece.getDir());
                    }
                }
                myWriter.write("\n"); // new line for every row in the board
            }
            // close the file
            myWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view.getBoardPanel(), "File could not be saved.");
            e.printStackTrace();
        }
    }

    public void load(String filename) {
    try {
        File readFile = new File(filename + ".txt");
        Scanner fileReader = new Scanner(readFile);

        // load turn number
        if (fileReader.hasNextLine()) {
            turn = Integer.parseInt(fileReader.nextLine().trim());
        } else {
            throw new IOException("File format incorrect - Missing turn number.");
        }

        // load user colour (whose turn it is)
        if (fileReader.hasNextLine()) {
            userColour = fileReader.nextLine().trim();
        } else {
            throw new IOException("File format incorrect - Missing user color.");
        }

        for (int i = 0; i < board.getY(); i++) {
            for (int j = 0; j < board.getX(); j++) {
                String pieceSymbol = fileReader.hasNextLine() ? fileReader.nextLine().trim() : null;

                // If pieceSymbol is a space or empty, it means there is no piece at this position.
                if (pieceSymbol == null || pieceSymbol.isEmpty() || pieceSymbol.equals(" ")) {
                    // Handle the absence of a piece here, perhaps set to null or continue
                    continue;
                } else {
                    // We have a valid piece symbol, proceed to read color and direction.
                    String pieceColour = fileReader.hasNextLine() ? fileReader.nextLine().trim() : null;
                    String pieceDir = fileReader.hasNextLine() ? fileReader.nextLine().trim() : null;

                    if (pieceColour == null || pieceDir == null) {
                        throw new IOException("File format incorrect - Missing piece color or direction.");
                    }

                    // Logic to recreate the piece on the board
                    // Example:
                    // Piece piece = new Piece(pieceSymbol, pieceColour, pieceDir);
                    // board.setPiece(i, j, piece);
                }
            }
        }

        fileReader.close();
    } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(view.getBoardPanel(), "File not found.");
        e.printStackTrace();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(view.getBoardPanel(), e.getMessage());
        e.printStackTrace();
    }
}


    // done by ashley >_<
    // NEED TO FIX
    // public void load(String filename) {
    // try {
    // File readFile = new File(filename + ".txt");
    // Scanner fileReader = new Scanner(readFile);

    // // load turn number
    // turn = Integer.valueOf(fileReader.nextLine());

    // // load user colour (whose turn it is)
    // userColour = fileReader.nextLine();

    // for (int i = 0; i < board.getY(); i++) {
    // for (int j = 0; j < board.getX(); j++) {
    // String pieceSymbol = fileReader.nextLine();
    // String pieceColour = fileReader.nextLine();
    // String pieceDir = fileReader.nextLine();

    // // board.setPiece(i, j, piece.getPiece());
    // // board.getPiece(i, j).setColour(pieceColour);
    // // board.getPiece(i, j).setDir(pieceDir);

    // if(!pieceSymbol){ //to make sure that the line is not empty before read

    // }
    // }
    // }
    // System.out.println("Succesfully Loaded Game File!!!!!!");

    // fileReader.close();
    // } catch (FileNotFoundException e) {
    // JOptionPane.showMessageDialog(view.getBoardPanel(), "File not found.");
    // // System.out.println("\nFile does not exist.");
    // e.printStackTrace();
    // }
    // }

    // public void playGame(int turn, String userColour) {
    // while (true) {
    // System.out.println(turn + ", " + userColour);
    // if (turn > 0) {
    // switch (turn % 2) {
    // case 0: // yellow at the bottom of the screen
    // userColour = "yellow";
    // break;
    // case 1: // blue at the bottom of the screen
    // userColour = "blue";
    // break;
    // }
    // }

    // view.updatePieces();

    // //if (console(board, turn, userColour)) {
    // // only executes following code if valid move was performed
    // System.out.println(board.getMap());
    // turn++;

    // // flip board every turn
    // board.flipBoard();

    // // change time <-> plus every 2 turns
    // if (turn % 2 == 0) {
    // board.switchTimeAndPlus();
    // }
    // //}
    // }
    // }

    // by ashley :>
    // public static boolean console(Board board, int turn, String userColour) {
    // // commands:
    // // 1. piece - enter x & y coordinates to select piece,
    // // then another set of x & y coordinates to move it
    // // 2. save
    // // 3. load
    // // 4. quit
    // System.out.print("\n> ");

    // Scanner input = new Scanner(System.in);

    // String filename = "";

    // switch (input.nextLine()) {
    // case "piece":
    // System.out.print("Enter x coordinate: ");
    // int pieceX = input.nextInt();
    // System.out.print("Enter y coordinate: ");
    // int pieceY = input.nextInt();

    // Piece pieceToMove = board.getPiece(pieceX, pieceY);
    // System.out.println(pieceToMove + ", " + pieceToMove.getColour());

    // System.out.print("\nEnter x coordinate of destination: ");
    // int newX = input.nextInt();
    // System.out.print("Enter y coordinate of destination: ");
    // int newY = input.nextInt();

    // if (pieceToMove.move(board, newX, newY, userColour)) {
    // pieceToMove.setX(newX);
    // pieceToMove.setY(newY);

    // return true;
    // }

    // return false;

    // case "save":
    // System.out.print("Enter filename to save to: ");
    // filename = input.nextLine();
    // save(filename);
    // break;

    // case "load":
    // System.out.print("Enter filename to load from: ");
    // filename = input.nextLine();
    // load(filename);

    // case "quit":
    // exit();
    // }

    // return false;
    // }

    // Exit the game. <-- Javier Austin ^.^
    public void exit() {
        System.out.println("Exiting Game...Goodbye!");
        System.exit(0);
    }

    public String helpCommands() { // help commands. rule of each piece
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
