//PieceButton.java

import javax.swing.*;

public class PieceButton extends JButton {
    private Piece piece;
    private boolean selected = false;

    PieceButton() {}

    public Piece getPiece() {
        return piece;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
