public class TimePiece extends Piece {
    TimePiece() {
        super();
    }

    TimePiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    @Override
    public boolean isValidMove(Board board, int newX, int newY) {
        if (board.getPiece(newX, newY) != null) {
            if (board.getPiece(newX, newY).getColour() == this.getColour()) //must be a diff colour
                return false;
        }

        //checks the direction that the player wants to move the piece in
        if (this.getY() != newY) { //either up/down/upleft/upright/downleft/downright
            if (newY > this.getY())// down/downleft/downright
                this.setDir("down");
            else // up/upleft/upright
                this.setDir("up");

            if (newX < this.getX())
                this.setDir(this.getDir() + "left");
            else if (newX > this.getX())
                this.setDir(this.getDir() + "right");
            else //cannot move up or down so move invalid
                return false;
        }
        else { //cannot move left or right so move invalid
            return false;
        }
        System.out.println("Direction: " + this.getDir());

        Piece pieceInFront;

        //checks all of the spaces in the direction that the piece wants to move
        //EXCEPT for the destination space
        //if a piece is detected, move is invalid
        switch (this.getDir()) {
            case "upleft":
                for (int i = 1; i < Math.abs(newX - this.getX()); i++) { //we use math abs so there is no negative numbers
                    pieceInFront = board.getPiece(this.getX() - i, this.getY() - i);
                    System.out.println(pieceInFront);

                    if (pieceInFront != null) {
                        return false;
                    }
                    continue;
                }
                break;

            case "upright":
                for (int i = 1; i < Math.abs(newX - this.getX()); i++) {
                    pieceInFront = board.getPiece(this.getX() + i, this.getY() - i);
                    System.out.println(pieceInFront);

                    if (pieceInFront != null) {
                        return false;
                    }


                    continue;
                }
                break;

            case "downleft":
                for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
                    pieceInFront = board.getPiece(this.getX() - i, this.getY() + i);
                    System.out.println(pieceInFront);

                    if (pieceInFront != null) {
                        return false;
                    }
                    continue;
                }
                break;

            case "downright":
                for (int i = 1; i < Math.abs(newY - this.getY()); i++) {
                    pieceInFront = board.getPiece(this.getX() + i, this.getY() + i);
                    System.out.println(pieceInFront);

                    if (pieceInFront != null) {
                        return false;
                    }
                    continue;
                }
                break;
        }
        return true;
    }

    @Override
    public String toString(){
        return "x";
    }

    @Override
    public String getPiece() {
        return "time";
    }
}
