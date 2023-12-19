import java.lang.*;

public class PlusPiece extends Piece {
    PlusPiece() {
        super();
    }

    PlusPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    //by ashley :D
    @Override
    public boolean isValidMove(Board board, int newX, int newY) {
        //checks the direction that the player wants to move the piece in
        if (this.getX() == newX) { //either up or down
            if (newY > this.getY()) // down
                this.setDir("down");
            else //up
                this.setDir("up");
        }
        else { //either left or right
            if (newX > this.getX()) //right
                this.setDir("right");
            else //left
                this.setDir("left");
        }

        Piece pieceInFront;

        //checks all of the spaces in the direction that the piece wants to move
        //if a piece is detected, move is invalid
        switch (this.getDir()) {
            case "left":
                for (int i = 1; i < Math.abs(newX - this.getX()) + 1; i++) {
                    pieceInFront = board.getPiece(this.getX() - i, this.getY());

                    if (pieceInFront != null) {
                        return false;
                    }
                    continue;
                }
                break;

            case "right":
                for (int i = 1; i < Math.abs(newX - this.getX()) + 1; i++) {
                    pieceInFront = board.getPiece(this.getX() + i, this.getY());

                    if (pieceInFront != null) {
                        return false;
                    }
                    continue;
                }
                break;

            case "up":
                for (int i = 1; i < Math.abs(newY - this.getY()) + 1; i++) {
                    pieceInFront = board.getPiece(this.getX(), this.getY() - i);

                    if (pieceInFront != null) {
                        return false;
                    }
                    continue;
                }
                break;

            case "down":
                for (int i = 1; i < Math.abs(newY - this.getY()) + 1; i++) {
                    pieceInFront = board.getPiece(this.getX(), this.getY() + i);

                    if (pieceInFront != null) {
                        return false;
                    }
                    continue;
                }
                break;
        }
        
        //if the piece at the plus piece's destination does not share the same colour,
        //it is a valid move because it can capture the piece, otherwise it is invalid
        if (board.getPiece(newX, newY) != null) {
            if (board.getPiece(newX, newY).getColour() != this.getColour())
                return true;
            else
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "+";
    }
}
