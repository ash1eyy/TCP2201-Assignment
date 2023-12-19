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
        Piece pieceInFront;

        if (this.getDir() == "up") {
            for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
                pieceInFront = board.getPiece(this.getX(), this.getY() - i);

                if (pieceInFront != null) {
                    return false;
                }
                continue;
            }

            if (newY == this.getY() - 1 || newY == this.getY() - 2) {
                //valid
                return true;
            }
        }
        else if (this.getDir() == "down") {
            for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
                pieceInFront = board.getPiece(this.getX(), this.getY() + i);

                if (pieceInFront != null) {
                    return false;
                }
                continue;
            }
            
            if (newY == this.getY() + 1 ||
                newY == this.getY() + 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "O";
    }
}
