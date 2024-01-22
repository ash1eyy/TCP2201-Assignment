//PieceListener.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

//for pieces on the board only
public class PieceListener implements ActionListener {
    private PieceButton[][] buttons;
    private Color validMoveColour = new Color(100, 255, 0);

    PieceListener(PieceButton[][] buttons) {
        this.buttons = buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PieceButton clickedButton = (PieceButton) e.getSource();
        GameView view = (GameView) SwingUtilities.windowForComponent(clickedButton);

        if (view.getPieceSelected() == true) { //to update piece selected
            view.setOriginalPiece(view.getNewPiece());
        }

        view.setNewPiece(clickedButton);
        view.getNewPiece().setSelected(true);

        //if a piece has already been selected
        if (view.getPieceSelected() == true) {
            //checks all the valid moves for the original piece
            for (ArrayList<Integer> move : view.getOriginalPiece().getPiece().getValidMoves(GameController.getBoard())) {
                //if the new piece selected is in one of the valid move spaces of the original piece
                if (move.get(0) == view.getNewPiece().getX() &&
                    move.get(1) == view.getNewPiece().getY()) {
                    //if the new piece selected is the sun, win the game
                    if (view.getNewPiece().getPiece().getPieceName() == "sun") {
                        JOptionPane.showMessageDialog(view.getBoardPanel(), "Player " + GameController.getTurn() + " wins!");
                        //more win game code
                    }
                    
                    view.getNewPiece().setIcon(view.getOriginalPiece().getIcon()); //
                    view.getNewPiece().setPiece(view.getOriginalPiece().getPiece()); //

                    view.getOriginalPiece().setIcon(null);
                    view.getOriginalPiece().setPiece(null);

                    
                }
                //if not, goes to the next iteration of the loop
            }
        }

        view.setPieceSelected(true); //assign a true value

        // for (int i = 0; i < 6; i++) {
        //     for (int j = 0; j < 7; j++) {
        //         //if a piece has already been selected
        //         if (buttons[i][j].getSelected() == true) {
        //             PieceButton originalPiece = buttons[i][j];
        //         }
        //         else {
                    
        //         }
        //         // buttons[i][j].setBackground(null);
        //         // buttons[i][j].setSelected(false);
        //     }
        // }

        //only selects the button if there is a piece on it AND it is the user's first time selecting a piece
        // if (clickedButton.getSelected() == false &&
        //     clickedButton.getIcon() != null &&
        //     clickedButton.getBackground() != selectedColour) {

        //     clickedButton.setSelected(true);
        //     clickedButton.setBackground(selectedColour);
        // }
        // else if (clickedButton.getIcon() == null) {
            
        // }

        //highlight valid tiles
        if (view.getNewPiece() != null) {
            for (ArrayList<Integer> coords : view.getNewPiece().getPiece().getValidMoves(GameController.getBoard())) {
                PieceButton validMoveButton = buttons[coords.get(1)][coords.get(0)];
                validMoveButton.setBackground(validMoveColour);
            }
        }

        // for (int i = 0; i < 6; i++) {
        //     for (int j = 0; j < 7; j++) {
        //         if (clickedButton.getPiece().isValidMove(GameController.getBoard(), j, i)) {
        //             PieceButton validMoveButton = buttons[i][j];
        //             validMoveButton.setBackground(validMove);
        //         }
        //     }
        // }

        //on the next click, move the piece to the selected tile (if valid)
    }
}
