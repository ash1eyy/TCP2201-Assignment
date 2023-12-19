public class PointPiece extends Piece {

    PointPiece() {
        super();
    }

    PointPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    //by ashley ^o^
    @Override
    public boolean isValidMove(Board board, int newX, int newY) {
        if (Math.abs(newY - this.getY()) > 2) //must be within 1-2 moves
            return false;

        if (board.getPiece(newX, newY) != null) {
            if (board.getPiece(newX, newY).getColour() == this.getColour()) //must be a diff colour
                return false;
        }

        Piece pieceInFront;

        if (this.getDir() == "up") {
            for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
                pieceInFront = board.getPiece(this.getX(), this.getY() - i);

                if (pieceInFront != null)
                    return false;

                continue;
            }
        }
        else if (this.getDir() == "down") {

            for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
                pieceInFront = board.getPiece(this.getX(), this.getY() + i);

                if (pieceInFront != null)
                    return false;

                continue;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "O";
    }
}