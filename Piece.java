
public abstract class Piece{
    private int pieceX;
    private int pieceY;
    private String pieceColour;

    public Piece(int pieceX, int pieceY, String pieceColour){  //zaf did this
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.pieceColour = pieceColour;
    }

    public int getX(){
        return pieceX;
    }

    public int getY(){
        return pieceY;
    }

    public String getColour(){
        return pieceColour;
    }

    public void setX(int pieceX){
        this.pieceX = pieceX;
    }

    public void setY(int pieceY){
        this.pieceY = pieceY;
    }

    public void move(int newX, int newY) { //thash did this
        if (isValidMove(newX, newY)) {
            this.pieceX = newX;
            this.pieceY = newY;
        }
    }

    public abstract boolean isValidMove(int newX, int newY);

    // this is a capture method that edeclares targetPiece variable from the pieces on the board 
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
