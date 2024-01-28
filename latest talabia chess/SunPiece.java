import java.util.ArrayList;

public class SunPiece extends Piece {
    SunPiece() {
        super();
    }


    /* 
    
    Sun Piece constructor that has the parameters

    @param pieceX Piece's X coordinate
    @param pieceY Piece's Y coordinate
    @param pieceColour Colour of piece (blue or yellow)
    @param direction Direction of piece (up/down)

    */

    SunPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }
    
    /* 
    
    Valid moves method that is made of 2d ArrayList also 
    
    @param board Game board with all the pieces
    @param player The current player's colour (blue/yellow)
    @param newX new x coordinates
    @param newY new y coordinates
    @return validMoves

    */

    @Override
    public ArrayList<ArrayList<Integer>> getValidMoves(Board board, String player) {
        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<ArrayList<Integer>>();

        for (int newY = this.getY() - 1; newY < this.getY() + 2; newY++) {
            
            for (int newX = this.getX() - 1; newX < this.getX() + 2; newX++) {

                while (newX < board.getX() && 
                    newY < board.getY() &&
                    newX >= 0 && newY >= 0 &&
                    getColour().equals(player)) {
                        
                    if (board.getPiece(newX, newY) != null) {
                        if (board.getPiece(newX, newY).getColour().equals(this.getColour()))
                            break;
                    }
                    
                    validMoves.add(new ArrayList<Integer>());
                    validMoves.get(validMoves.size() - 1).add(newX);
                    validMoves.get(validMoves.size() - 1).add(newY);
                    break;
                }
            }
        }

        return validMoves;
    }


    /* 
    Get piece name method returns piece name
    @return "sun" piece name 
    
    To string method, returns following piece onto the board
    @return S symbol of piece 
    */

    @Override
    public String getPieceName() {
        return "Sun";
    }

    @Override
    public String toString() { 
        return "S";
    }
}