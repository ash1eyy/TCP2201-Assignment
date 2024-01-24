import java.util.*;

public abstract class Piece{ 

    /* 
    
    Data Field

    pieceX and pieceY : X and Y coordinate of piece declared here

    piece Colour : Colour of Piece

    direction: Direction of Piece
    
    
    */

    private int pieceX;
    private int pieceY;
    private String pieceColour;
    private String direction;

    /* 
    
    Constructor
    
    */

    Piece() {}

    Piece(int pieceX, int pieceY, String pieceColour, String direction) {  //zafran did this
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.pieceColour = pieceColour;
        this.direction = direction;
    }
    
    /* 
    
    Getters and Setters methods to get and set the private varaiables

    */

    public int getX() { 
        return pieceX;
    }

    public int getY() {
        return pieceY;
    }

    public String getColour() { 
        return pieceColour;
    }

    public String getDir() { 
        return direction;
    }

    public void setX(int pieceX) {
        this.pieceX = pieceX;
    }

    public void setY(int pieceY) {
        this.pieceY = pieceY;
    }

    public void setColour(String colour) {
        this.pieceColour = colour;
    }

    public void setDir(String direction) {
        this.direction = direction;
    }

    /* 
    
    This is a method that gets the name of the Piece

    */
    public abstract String getPieceName();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //by ashley :-)
    // public boolean move(Board board, int newX, int newY, String userColour) {
    //     if (isValidMove(board, newX, newY) && userColour == board.getPiece(getX(), getY()).getColour()) { //valid move method that will be used for each piece
    //         board.setPiece(getX(), getY(), null);
    //         board.setPiece(newX, newY, this);
    //         System.out.println("\nSuccessfully moved piece.\n"); //message displayed
    //         return true;
    //     }
    //     else {
    //         System.out.println("\nNot a valid move.\n"); //message displayed
    //         return false;
    //     }
    // };
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /* 
    
    Valid moves method that is made of 2d ArrayList also 

    Parameters used 
    
    board : Board 
    player : the player is A String variable
    
    */

    public abstract ArrayList<ArrayList<Integer>> getValidMoves(Board board, String player);

    /* 
    
    The add valid moves method has the following parameters

    validMoves : 2d ArrayLists of Integers 
    validX : valid X coordinate
    validY : valid Y coordinate

    */


    public void addValidMove(ArrayList<ArrayList<Integer>> validMoves, int validX, int validY) {
        validMoves.add(new ArrayList<Integer>());
        validMoves.get(validMoves.size() - 1).add(validX);
        validMoves.get(validMoves.size() - 1).add(validY);
        /* 
    
        What does this do ah

        */
    }

    /* 
    
    This is a capture method that declares targetPiece variable from the pieces on the board

    board : Board 
    pieceX : X coordinate
    PieceY : Y coordinate

    */

    public void capture(Board board, int pieceX, int pieceY) { //by zafran 
        Piece targetPiece = board.getPiece(pieceX, pieceY);   
        
        if (this.pieceColour != targetPiece.getColour()) { //can capture
            board.setPiece(pieceX, pieceY, this); //"this" is the current piece that you are holding
            board.setPiece(this.getX(), this.getY(), null);
            System.out.println("You captured the piece!");
        }
        else { //cannot capture
            //display error
            System.out.println("You cannot capture your own piece"); //to replace with throw exception
        }
    }
}
