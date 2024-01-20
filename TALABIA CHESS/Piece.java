import java.util.*;

public abstract class Piece{ 
    //data field
    private int pieceX;
    private int pieceY;
    private String pieceColour;
    private String direction;

    Piece() {}

    Piece(int pieceX, int pieceY, String pieceColour, String direction) {  //zaf did this
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.pieceColour = pieceColour;
        this.direction = direction;
    }

    public int getX() { //getters setters
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

    public abstract String getPieceName();

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

    public abstract ArrayList<ArrayList<Integer>> getValidMoves(Board board);


    // this is a capture method that declares targetPiece variable from the pieces on the board
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
