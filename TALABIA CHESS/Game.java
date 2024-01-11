import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Game { //Design Pattern: Facade. Front-facing interface masking more complex code.
    static HashMap<String, Piece> pieces = new HashMap<String, Piece>();
    
    static int turn = 1;
    static String userColour = "yellow";
    public static void main(String[] args) {
        Board board = Board.createInstance();
        board.init();
        GameView view = new GameView();

        GameController controller = new GameController(board, view);

        //do game things

        piecesInit();

        while (true) {
            if (turn > 1) {
                switch (turn % 2) {
                    case 0: //yellow at the bottom of the screen
                        userColour = "blue";
                        break;
                    case 1: //blue at the bottom of the screen
                        userColour = "yellow";
                        break;
                }
            }

            //TEMPORARY CODE for console display
            System.out.println("Turn: " + turn);
            board.display();
            if (console(board, userColour)) {
                //only executes following code if valid move was performed
                turn++;
                
                //flip board every turn
                board.flipBoard();
                
                //change time <-> plus every 2 turns
                if (turn % 2 == 0) {
                    board.switchTimeAndPlus();
                }
            };
        }
    }

    //by ashley :>
    public static boolean console(Board board, String userColour) {
        //commands:
        //1. piece - enter x & y coordinates to select piece,
        //   then another set of x & y coordinates to move it
        //2. save
        //3. load
        //4. quit
        System.out.print("\n> ");

        Scanner input = new Scanner(System.in);
        
        switch (input.nextLine()) {
            case "piece":
                System.out.print("Enter x coordinate: ");
                int pieceX = input.nextInt();
                System.out.print("Enter y coordinate: ");
                int pieceY = input.nextInt();

                Piece pieceToMove = board.getPiece(pieceX, pieceY);
                System.out.println(pieceToMove + ", " + pieceToMove.getColour());

                System.out.print("\nEnter x coordinate of destination: ");
                int newX = input.nextInt();
                System.out.print("Enter y coordinate of destination: ");
                int newY = input.nextInt();
                
                if (pieceToMove.move(board, newX, newY, userColour)) {
                    pieceToMove.setX(newX);
                    pieceToMove.setY(newY);

                    return true;
                }

                return false;
            
            case "save":
            case "load":
            case "quit":
                exit();
        }

        return false;
    }

    //initializes a hashmap that assigns all pieces to its corresponding text symbol
    public static void piecesInit() {
        pieces.put("+", new PlusPiece());
        pieces.put("O", new PointPiece());
        pieces.put("S", new SunPiece());
        pieces.put("x", new TimePiece());
        pieces.put("H", new HourglassPiece());
        pieces.put(" ", null);
    }

    public void save(Board board, String filename) {
        boolean fileExists = false;
        //open new file with user input filename
        try {
            File myFile = new File(filename + ".txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } 
            else {
                fileExists = true;
                throw new IOException("File already exists.");
            }

            //CHANGE to remove all console output and input
            // if (fileExists) {
            //     System.out.println("Overwrite the file? [Y/N]");
            //     String overwriteConfirm = input.next();
            //     input.nextLine();
            //     overwriteConfirm = overwriteConfirm.toUpperCase();

            //     if (overwriteConfirm.equals("N"))
            //         break;
            //     else if (!overwriteConfirm.equals("Y")) {
            //         System.out.println("Not a valid input.");
            //         break;
            //     }
            // }
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //save data into the file
        try {
            //open the file in FileWriter
            FileWriter myWriter = new FileWriter(filename + ".txt"); //just to know what type of file it saved into

            //Hello hyukathinker 
            
            myWriter.write(turn + "\n");    //save turn number
            myWriter.write(userColour + "\n"); //save user colour (whose turn it is)
            
            //saves the pieces, their colour + direction
            for (int i = 0; i < board.getY(); i++) {
                for (int j = 0; i < board.getX(); j++) {
                    Piece piece = board.getMap().get(i).get(j);

                    if (piece == null)
                        myWriter.write(" ");
                    else {
                        myWriter.write(piece.toString() + "\n" + piece.getColour() + "\n" + piece.getDir());
                    }
                }
                myWriter.write("\n"); //new line for every row in the board
            }
            //close the file
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //done by ashley >_<
    public void load(Board board, String filename) {
        try {
            File readFile = new File(filename + ".txt");
            Scanner fileReader = new Scanner(readFile);

            //load turn number
            turn = Integer.valueOf(fileReader.nextLine());

            //load user colour (whose turn it is)
            userColour = fileReader.nextLine();

            for (int i = 0; i < board.getY(); i++) {
                for (int j = 0; j < board.getX(); j++) {
                    String pieceSymbol = fileReader.nextLine();
                    String pieceColour = fileReader.nextLine();
                    String pieceDir = fileReader.nextLine();

                    board.setPiece(i, j, pieces.get(pieceSymbol));
                    board.getPiece(i, j).setColour(pieceColour);
                    board.getPiece(i, j).setDir(pieceDir);
                }
            }
            
            fileReader.close();
            } 
        catch (FileNotFoundException e) {
            //System.out.println("\nFile does not exist.");
            e.printStackTrace();
        }
    }

    //Exit the game. <-- Javier Austin ^.^
    public static void exit() {
        System.out.println("Exiting Game...Goodbye!");
        System.exit(0);
    }

    public static void helpCommands() { // zafran
        System.out.println("----------How to play Talabia Chess?----------");
        System.out.println("The Point Piece can only move forward by 1 or 2 steps.");
        System.out.println("The Hourglass Piece moves in a 3x2 L shape in any orientation.");
        System.out.println("The Time Piece can only move diagonally but can go any distance.");
        System.out.println("The Plus Piece can move horizontally and vertically only but can go any distance.");
        System.out.println("The Sun Piece can move only one step in any direction.");
    }
}