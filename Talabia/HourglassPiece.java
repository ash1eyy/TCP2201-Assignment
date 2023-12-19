public class HourglassPiece extends Piece {
    HourglassPiece() {
        super();
    }

    HourglassPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    @Override
    public boolean isValidMove(Board board, int newX, int newY) {
        // // Calculate the change in the x and y coordinates
        // int deltaX = absolute(newX - getX());
        // int deltaY = absolute(newY - getY());

        // // Check if the move is in an L shape (3x2)
        // return (deltaX == 3 && deltaY == 2) || (deltaX == 2 && deltaY == 3);
        return true;
    }

    @Override
    public String toString() {
        return "H";
    }
}  

