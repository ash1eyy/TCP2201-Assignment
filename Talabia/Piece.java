public abstract class Piece{
    private int pieceX;
    private int pieceY;
    private String pieceColour;
    private String direction;

    Piece() {}

    Piece(int pieceX, int pieceY, String pieceColour, String direction) {  //zaf did this
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.pieceColour = pieceColour;
        this.direction = direction;
    }

    public int getX() {
        return pieceX;
    }

    public int getY() {
        return pieceY;
    }

    public String getColour() {
        return pieceColour;
    }

    public String getDir() {
        return direction;
    }

    public void setX(int pieceX) {
        this.pieceX = pieceX;
    }

    public void setY(int pieceY) {
        this.pieceY = pieceY;
    }

    public void setColour(String colour) {
        this.pieceColour = colour;
    }

    public void setDir(String direction) {
        this.direction = direction;
    }

    //by ashley :-)
    public boolean move(Board board, int newX, int newY) {
        if (isValidMove(board, newX, newY)) {
            board.setPiece(getX(), getY(), null);
            board.setPiece(newX, newY, this);
            System.out.println("\nSuccessfully moved piece.\n");
            return true;
        }
        else {
            System.out.println("\nNot a valid move.\n");
            return false;
        }
    };

    public abstract boolean isValidMove(Board board, int newX, int newY);

    // this is a capture method that declares targetPiece variable from the pieces on the board
    public void capture(Board board, int pieceX, int pieceY) { //zaf did this 
        Piece targetPiece = board.getPiece(pieceX, pieceY);   
        
        if (this.pieceColour != targetPiece.getColour()) { //can capture
            board.setPiece(pieceX, pieceY, this); //"this" is the current piece that you are holding
            board.setPiece(this.getX(), this.getY(), null);
            System.out.println("You captured the piece!");
        }
        else { //cannot capture
            //display error
            System.out.println("You cannot capture your own piece"); //to replace with throw exception
        }
    }
}
