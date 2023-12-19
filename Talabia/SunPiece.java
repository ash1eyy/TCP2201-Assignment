public class SunPiece extends Piece {
    SunPiece() {
        super();
    }

    SunPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    @Override
    public boolean isValidMove(Board board, int newX, int newY) {
        switch (getDir()) {
            case "Up":
                if (newX == this.getX() - 1) {
                    return true;
                }

            case "Down":
                if (newX == this.getX() + 1) {
                    return true;
                }

            case "Left":
                if (newY == this.getY() - 1) {
                    return true;
                }

            case "Right":
                if (newY == this.getY() + 1) {
                    return true;
                }

            case "DiagUpLeft":

            case "DiagDownLeft":

            case "DiagUpRight":

            case "DiagDownRight":
        }
        return false;
    }

    @Override
    public String toString() {
        return "S";
    }
}
