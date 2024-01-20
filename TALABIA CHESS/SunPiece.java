import java.util.ArrayList;

public class SunPiece extends Piece {
    SunPiece() {
        super();
    }

    SunPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    /*
     * @Override
     * public boolean isValidMove(Board board, int newX, int newY) {
     * if (Math.abs(newY - this.getY()) > 1 ||
     * Math.abs(newX - this.getX()) > 1) //must be within 1 move
     * return false;
     * 
     * if (board.getPiece(newX, newY) != null) {
     * if (board.getPiece(newX, newY).getColour() == this.getColour()) //must be a
     * diff colour
     * return false;
     * }
     * 
     * //checks the direction that the player wants to move the piece in (IMAGINE
     * TICTACTOE GRID)
     * if (this.getY() != newY) { //either up/down/upleft/upright/downleft/downright
     * if (newY > this.getY())// down/downleft/downright
     * this.setDir("down");
     * else if (newY < this.getY()) // up/upleft/upright
     * this.setDir("up");
     * 
     * if (newX < this.getX())
     * this.setDir(this.getDir() + "left");
     * else if (newX > this.getX())
     * this.setDir(this.getDir() + "right");
     * }
     * else { //either left or right
     * if (newX > this.getX()) //right
     * this.setDir("right");
     * else if (newX < this.getX()) //left
     * this.setDir("left");
     * }
     * 
     * System.out.println("Direction: " + this.getDir());
     * return true;
     * }
     */

    @Override
    public ArrayList<ArrayList<Integer>> getValidMoves(Board board) {
        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<ArrayList<Integer>>();

        for (int newY = this.getY() - 1; newY < this.getY() + 2; newY++) {
            
            for (int newX = this.getX() - 1; newX < this.getX() + 2; newX++) {

                while (newX < board.getX() && 
                    newY < board.getY() &&
                    newX >= 0 && newY >= 0) {
                        
                    if (board.getPiece(newX, newY) != null) {
                        if (board.getPiece(newX, newY).getColour() == this.getColour())
                            break;
                    }
                    
                    validMoves.add(new ArrayList<Integer>());
                    validMoves.get(validMoves.size() - 1).add(newX);
                    validMoves.get(validMoves.size() - 1).add(newY);
                    break;
                }
            }
        }

        return validMoves;

        // for (int newY = 0; newY < board.getY(); newY++) {
        //     for (int newX = 0; newX < board.getX(); newX++) {

        //         if (newX != this.getX()) {
        //             continue;
        //         }

        //         if (Math.abs(newY - this.getY()) > 1 || Math.abs(newX - this.getX()) > 1) {
        //             continue;
        //         }

        //         if (board.getPiece(newX, newY) != null &&
        //                 board.getPiece(newX, newY).getColour() == this.getColour()) {
        //             continue;
        //         }

        //         Piece pieceInFront = null;

        //         for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
        //             for(int j = 1 ; i <  ; j++){

                        
        //             }

        //             if(this.getY() + 1 != null){
        //                 continue;
        //             }

        //             if(this.getY() - 1 != null){
        //                 continue;
        //             }

        //             if(this.getX() + 1 != null){
        //                 continue;
        //             }

        //             if(this.getX() - 1 != null){
        //                 continue;
        //             }

        //             if (pieceInFront != null)
        //                 continue;

        //         }

        //     }
        // }
    }

    @Override
    public String getPieceName() {
        return "sun";
    }

    @Override
    public String toString() {
        return "S";
    }
}
