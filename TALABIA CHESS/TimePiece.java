import java.util.ArrayList;

public class TimePiece extends Piece {
    TimePiece() {
        super();
    }

    TimePiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    @Override
    public ArrayList<ArrayList<Integer>> getValidMoves(Board board) {
        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<ArrayList<Integer>>();

        int checkX, checkY;
        int[][] nextCoords = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int[] next : nextCoords) {
            checkX = this.getX() + next[0];
            checkY = this.getY() + next[1];

            while (checkX < board.getX() &&
                checkY < board.getY() &&
                checkX >= 0 && checkY >= 0) {
                
                //if there is no piece at the given coords, continue
                if (board.isEmpty(checkX, checkY)) {
                    validMoves.add(new ArrayList<Integer>());
                    validMoves.get(validMoves.size() - 1).add(checkX);
                    validMoves.get(validMoves.size() - 1).add(checkY);

                    checkX += next[0];
                    checkY += next[1];
                    continue;
                }
                else {
                    //if current piece and piece at space are diff colours, continue
                    if (this.getColour() != board.getPiece(checkX, checkY).getColour()) {
                        validMoves.add(new ArrayList<Integer>());
                        validMoves.get(validMoves.size() - 1).add(checkX);
                        validMoves.get(validMoves.size() - 1).add(checkY);
                        break;
                    }
                }
                break;
            }
        }

        return validMoves;
    }

    // @Override
    // public boolean isValidMove(Board board, int newX, int newY) {
    //     if (board.getPiece(newX, newY) != null) {
    //         if (board.getPiece(newX, newY).getColour() == this.getColour()) //must be a diff colour
    //             return false;
    //     }

    //     //checks the direction that the player wants to move the piece in
    //     if (this.getY() != newY) { //either up/down/upleft/upright/downleft/downright
    //         if (newY > this.getY())// down/downleft/downright
    //             this.setDir("down");
    //         else // up/upleft/upright
    //             this.setDir("up");

    //         if (newX < this.getX())
    //             this.setDir(this.getDir() + "left");
    //         else if (newX > this.getX())
    //             this.setDir(this.getDir() + "right");
    //         else //cannot move up or down so move invalid
    //             return false;
    //     }
    //     else { //cannot move left or right so move invalid
    //         return false;
    //     }
    //     ///System.out.println("Direction: " + this.getDir());

    //     Piece pieceInFront;

    //     //checks all of the spaces in the direction that the piece wants to move
    //     //EXCEPT for the destination space
    //     //if a piece is detected, move is invalid
        
    //     switch (this.getDir()) {
    //         case "upleft":
    //             for (int i = 1; i < Math.abs(newX - this.getX()); i++) {
    //                 pieceInFront = board.getPiece(this.getX() - i, this.getY() - i);
    //                 System.out.println(pieceInFront);

    //                 if (pieceInFront != null) {
    //                     return false;
    //                 }
    //                 continue;
    //             }
    //             break;

    //         case "upright":
    //             for (int i = 1; i < Math.abs(newX - this.getX()); i++) {
    //                 pieceInFront = board.getPiece(this.getX() + i, this.getY() - i);
    //                 //System.out.println(pieceInFront);

    //                 if (pieceInFront != null) {
    //                     return false;
    //                 }


    //                 continue;
    //             }
    //             break;

    //         case "downleft":
    //             for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
    //                 pieceInFront = board.getPiece(this.getX() - i, this.getY() + i);
    //                 //System.out.println(pieceInFront);

    //                 if (pieceInFront != null) {
    //                     return false;
    //                 }
    //                 continue;
    //             }
    //             break;

    //         case "downright":
    //             for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
    //                 pieceInFront = board.getPiece(this.getX() + i, this.getY() + i);
    //                 //System.out.println(pieceInFront);

    //                 if (pieceInFront != null) {
    //                     return false;
    //                 }
    //                 continue;
    //             }
    //             break;
    //     }
    //     return true;
    // }

    @Override
    public String getPieceName() {
        return "time";
    }

    @Override
    public String toString(){
        return "x";
    }
}
