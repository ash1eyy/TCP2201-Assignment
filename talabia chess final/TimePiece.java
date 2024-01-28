import java.util.ArrayList;

/* 
The time piece is supposed to act like a bishop 
it can move diagonally fora all available spaces
*/

public class TimePiece extends Piece {
    TimePiece() {
        super();
    }

    /* 
    
    Time Piece constructor that has the parameters

    @param pieceX Piece's X coordinate
    @param pieceY Piece's Y coordinate
    @param pieceColour Colour of piece (blue or yellow)
    @param direction Direction of piece (up/down)

    */

    TimePiece(int pieceX, int pieceY, String pieceColour, String direction) {
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
        int checkX, checkY;
        int[][] nextCoords = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int[] next : nextCoords) {
            checkX = this.getX() + next[0];
            checkY = this.getY() + next[1];

            while (checkX < board.getX() &&
                checkY < board.getY() &&
                checkX >= 0 && checkY >= 0 &&
                getColour().equals(player)) {
                
                //if there is no piece at the given coords, continue
                if (board.isEmpty(checkX, checkY)) {
                    addValidMove(validMoves, checkX, checkY);

                    checkX += next[0];
                    checkY += next[1];
                    continue;
                }
                else {
                    //if current piece and piece at space are diff colours, add & skip to next set of coords
                    if (!this.getColour().equals(board.getPiece(checkX, checkY).getColour())) {
                        addValidMove(validMoves, checkX, checkY);
                        break;
                    }
                }
                break;
            }
        }

        return validMoves;
    }

    /* 
    Get piece name method returns piece name
    @return "time" piece name
    
    To string method, returns following piece onto the board
    @return x symbol of piece
    */

    @Override
    public String getPieceName() {
        return "Time";
    }

    @Override
    public String toString(){
        return "x";
    }
}