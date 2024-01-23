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

    HourglassPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    @Override
    public ArrayList<ArrayList<Integer>> getValidMoves(Board board, String player) {
        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<ArrayList<Integer>>();

        if (getColour() != player)
            return validMoves;

        for (int newY = 0; newY < board.getY(); newY++) {
            for (int newX = 0; newX < board.getX(); newX++) {
                if (board.getPiece(newX, newY) != null) {
                    if (board.getPiece(newX, newY).getColour() == this.getColour())
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //by zafran and austin >.<
    // @Override
    // public boolean isValidMove(Board board, int newX, int newY) {
    //     if (board.getPiece(newX, newY) != null) {
    //         if (board.getPiece(newX, newY).getColour() == this.getColour())         // must be a diff colour
    //             return false;
    //     }
        
    //     if (Math.abs(newY - this.getY()) == 2 && Math.abs(newX - this.getX()) == 1) // must be within the range of 3x2
    //         return true;                                                            // Vertical 3x2
        
    //     if (Math.abs(newY - this.getY()) == 1 && Math.abs(newX - this.getX()) == 2) // must be within the range of 3x2
    //         return true;                                                            // Horizontal 3x2

    //     return false;
    // }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String getPieceName() {
        return "hourglass";
    }

    @Override
    public String toString() { //to string method, returns following piece onto the board
        return "H";
    }
}
