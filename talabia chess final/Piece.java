import java.util.*;

public abstract class Piece {
    private int pieceX;
    private int pieceY;
    private String pieceColour;
    private String direction;

    /* Constructor*/
    Piece() {}

    /* 
    Point Piece constructor that has the parameters

    @param pieceX Piece's X coordinate
    @param pieceY Piece's Y coordinate
    @param pieceColour Colour of piece (blue or yellow)
    @param direction Direction of piece (up/down)
    */

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

    /* 
    Valid moves method that is made of 2d ArrayList also 
    
    @param board Game board with all the pieces
    @param player The current player's colour (blue/yellow)
    */
    public abstract ArrayList<ArrayList<Integer>> getValidMoves(Board board, String player);

    /* 
    The add valid moves method has the following parameters

    @param validMoves 2d ArrayLists of Integers
    @param validX valid X coordinates of a piece
    @param validY valid Y coordinates of a piece
    */
    public void addValidMove(ArrayList<ArrayList<Integer>> validMoves, int validX, int validY) {
        validMoves.add(new ArrayList<Integer>());
        validMoves.get(validMoves.size() - 1).add(validX);
        validMoves.get(validMoves.size() - 1).add(validY);
    }

    public void flipDir() {
        switch (direction) {
            case "up":
                setDir("down");
                break;
            case "down":
                setDir("up");
                break;
        }
    }


    /* 
    This is a capture method that declares targetPiece variable from the pieces on the board

    @param board Game board with all the pieces
    @param pieceX the X coordinate of a piece
    @param PieceY the Y coordinate of a piece
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