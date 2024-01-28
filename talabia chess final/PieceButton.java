import javax.swing.*;

import java.awt.*;

public class PieceButton extends JButton {
    private Piece piece = null;
    private int pieceX, pieceY;
    private boolean selected = false;
    private Color selectedColour = new Color(255,255,153);

    PieceButton() {}

    /*    
    Getters and Setters methods to get and set the private varaiables
    */

    public Piece getPiece() {
        return piece;
    }

    public int getPieceX() {
        return pieceX;
    }

    public int getPieceY() {
        return pieceY;
    }

    public boolean getSelected() {
        return selected;
    }

    /* 
    @param piece Pieces on the board
    @param x coordinates
    @param y coordinates
    */

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setX(int pieceX) {
        this.pieceX = pieceX;
    }

    public void setY(int pieceY) {
        this.pieceY = pieceY;
    }

    /* 
    When piece not null and same colour as player then it will select piece 
    If piece is null it will also still select 

    @param player The current player's colour (blue/yellow)
    */
    public void setSelected(String player) { //set selected 
        if (piece != null && piece.getColour().equals(player)) {
            this.selected = true;
            this.setBackground(selectedColour);
        }
        else if (piece == null) {
            this.selected = true;
        }
    }
}
