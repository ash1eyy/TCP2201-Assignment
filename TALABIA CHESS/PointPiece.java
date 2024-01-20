import java.util.ArrayList;

public class PointPiece extends Piece {
    PointPiece() {
        super();
    }

    PointPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    @Override
    public ArrayList<ArrayList<Integer>> getValidMoves(Board board) {
        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<ArrayList<Integer>>();

        for (int newY = 0; newY < board.getY(); newY++) {
            for (int newX = 0; newX < board.getX(); newX++) {
                if (Math.abs(newY - this.getY()) > 2)
                    continue;

                if (newX != this.getX())
                    continue;

                if (board.getPiece(newX, newY) != null &&
                    board.getPiece(newX, newY).getColour() == this.getColour())
                    continue;

                Piece pieceInFront = null;

                for (int i = 1; i < Math.abs(newY - this.getY()) + 1; i++) {
                    if (this.getDir() == "up")
                        pieceInFront = board.getPiece(this.getX(), this.getY() - i);
                    else if (this.getDir() == "down")
                        pieceInFront = board.getPiece(this.getX(), this.getY() + i);

                    // if (pieceInFront != null)
                    //     continue;

                    validMoves.add(new ArrayList<Integer>());
                    validMoves.get(validMoves.size() - 1).add(newX);
                    validMoves.get(validMoves.size() - 1).add(newY);   
                }
            }
        }

        return validMoves;
    }

    // by ashley ^o^
    // @Override
    // public boolean isValidMove(Board board, int newX, int newY) {
    //     //System.out.println("Num of moves: " + Math.abs(newY - this.getY()));
    //     if (Math.abs(newY - this.getY()) > 2) // must be within 1-2 moves
    //         return false;

    //     if (newX != this.getX())
    //         return false;

    //     // this is to detect same colour pieces in pathway of current piece so they dont overlap
    //     if (board.getPiece(newX, newY) != null) {
    //         if (board.getPiece(newX, newY).getColour() == this.getColour()) // must be a diff colour
    //             return false;
    //     }

    //     Piece pieceInFront;

    //     //checks all of the spaces in the direction that the piece wants to move
    //     //EXCEPT for the destination space
    //     //if a piece is detected, move is invalid
    //     if (this.getDir() == "up") {
    //         for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
    //             pieceInFront = board.getPiece(this.getX(), this.getY() - i);

    //             if (pieceInFront != null)
    //                 return false;
    //         }
    //     } 
    //     else if (this.getDir() == "down") {
    //         for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
    //             pieceInFront = board.getPiece(this.getX(), this.getY() + i);

    //             if (pieceInFront != null)
    //                 return false;
    //         }
    //     }

    //     return true;
    // }

    @Override
    public String getPieceName() {
        return "point" + this.getDir();
    }

    @Override
    public String toString() {
        return "O";
    }
}
