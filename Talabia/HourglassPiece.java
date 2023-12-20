public class HourglassPiece extends Piece {
    HourglassPiece() {
        super();
    }

    HourglassPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    //by zaf and aus >.<
    @Override
    public boolean isValidMove(Board board, int newX, int newY) {
        if (board.getPiece(newX, newY) != null) {
            if (board.getPiece(newX, newY).getColour() == this.getColour())         // must be a diff colour
                return false;
        }
        
        if (Math.abs(newY - this.getY()) == 2 && Math.abs(newX - this.getX()) == 1) // must be within the range of 3x2
            return true;                                                            // Vertical 3x2
        
        if (Math.abs(newY - this.getY()) == 1 && Math.abs(newX - this.getX()) == 2) // must be within the range of 3x2
            return true;                                                            // Horizontal 3x2

        return false;
    }

    @Override
    public String toString() {
        return "H";
    }
}
