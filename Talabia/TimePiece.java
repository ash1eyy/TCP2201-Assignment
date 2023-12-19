public class TimePiece extends Piece {
    TimePiece() {
        super();
    }

    TimePiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    @Override
    public boolean isValidMove(Board board, int newX, int newY) {

        switch (getDir()) {
            case "diagUpLeft":

            case "diagDownLeft":

            case "diagUpRight":

            case "diagDownRight":
        }   
        return true;
    }

    @Override
    public String toString(){
        return "x";
    }


}