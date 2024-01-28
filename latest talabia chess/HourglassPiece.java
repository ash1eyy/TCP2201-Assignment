import java.util.ArrayList;


/* 
 * This is the hourglass piece
 * 
 * The hourglass piece works like the Knight in the actual chess game
 * 
 * This is the only piece that can skip over other pieces duer to its movement
 * 
 */



public class HourglassPiece extends Piece {
    HourglassPiece() {
        super();
    }


    /* 
    
    Hourglass Piece constructor that has the parameters

    @param pieceX Piece's X coordinate
    @param pieceY Piece's Y coordinate
    @param pieceColour Colour of piece (blue or yellow)
    @param direction Direction of piece (up/down)

    */

    HourglassPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    /* 
    Get valid moves method 
    
    @param board Game board with all the pieces
    @param player The current player's colour (blue/yellow)
    @return validMoves valid moves of the following piece 

    */

    @Override
    public ArrayList<ArrayList<Integer>> getValidMoves(Board board, String player) {
        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<ArrayList<Integer>>();

        if (!getColour().equals(player))
            return validMoves;

        for (int newY = 0; newY < board.getY(); newY++) {
            for (int newX = 0; newX < board.getX(); newX++) {
                if (board.getPiece(newX, newY) != null) {
                    if (board.getPiece(newX, newY).getColour().equals(this.getColour()))
                        continue;
                }

                if (Math.abs(newY - this.getY()) == 2 && Math.abs(newX - this.getX()) == 1) {
                    addValidMove(validMoves, newX,newY);
                }

                if (Math.abs(newY - this.getY()) == 1 && Math.abs(newX - this.getX()) == 2) { // must be within the range of 3x2
                    addValidMove(validMoves, newX,newY);                   // Horizontal 3x2
                }
        
                continue;
            }
        }
        return validMoves;
    }

    /* 
    Get piece name method returns piece name
    @return "hourglass" piece name 
    
    To string method, returns following piece onto the board
    @return H symbol of piece 
    */

    @Override
    public String getPieceName() {
        return "Hourglass";
    }
    

    @Override
    public String toString() { 
        return "H";
    }
}