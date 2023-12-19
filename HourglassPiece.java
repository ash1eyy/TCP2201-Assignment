public class HourglassPiece extends Piece {
    public HourglassPiece() {
        super(pieceX(), pieceY(), pieceColour, null);
    }

    private int absolute(int number) {
    return (number < 0) ? -number : number;
}

@Override
public boolean isValidMove(int newX, int newY) {
    // Calculate the change in the x and y coordinates
    int deltaX = absolute(newX - getX());
    int deltaY = absolute(newY - getY());

    // Check if the move is in an L shape (3x2)
    return (deltaX == 3 && deltaY == 2) || (deltaX == 2 && deltaY == 3);
}

    }
    

