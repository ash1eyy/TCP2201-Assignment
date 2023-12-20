public class SunPiece extends Piece {
    SunPiece() {
        super();
    }

    SunPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    @Override
    public boolean isValidMove(Board board, int newX, int newY) {
        if (Math.abs(newY - this.getY()) > 1 ||
            Math.abs(newX - this.getX()) > 1) //must be within 1 move
            return false;

        if (board.getPiece(newX, newY) != null) {
            if (board.getPiece(newX, newY).getColour() == this.getColour()) //must be a diff colour
                return false;
        }

        //checks the direction that the player wants to move the piece in (IMAGINE TICTACTOE GRID)
        if (this.getY() != newY) { //either up/down/upleft/upright/downleft/downright
            if (newY > this.getY())// down/downleft/downright
                this.setDir("down");
            else if (newY < this.getY()) // up/upleft/upright
                this.setDir("up");

            if (newX < this.getX())
                this.setDir(this.getDir() + "left");
            else if (newX > this.getX())
                this.setDir(this.getDir() + "right");
        }
        else { //either left or right
            if (newX > this.getX()) //right
                this.setDir("right");
            else if (newX < this.getX()) //left
                this.setDir("left");
        }

        System.out.println("Direction: " + this.getDir());
        return true;
    }

    @Override
    public String toString() {
        return "S";
    }
}
