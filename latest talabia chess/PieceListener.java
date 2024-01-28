import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

//for pieces on the board only
public class PieceListener implements ActionListener {
    private PieceButton[][] buttons;
    private Color validMoveColour = new Color(100, 255, 0);

    PieceListener() {}

    @Override
    public void actionPerformed(ActionEvent e) {
        PieceButton clickedButton = (PieceButton) e.getSource();
        GameView view = (GameView) SwingUtilities.windowForComponent(clickedButton); /* Window that contains the button that we clicked */
        GameController controller = view.getController();
        buttons = view.getButtons();

        //if a piece has already been selected
        if (view.getPieceSelected() == true) {
            //originalpiece = newpiece so that newpiece can be assigned to the button that was just clicked
            view.setOriginalPiece(view.getNewPiece());
        }


        view.setNewPiece(clickedButton);
        System.out.println(controller.getPlayer());
        view.getNewPiece().setSelected(controller.getPlayer());

        //DEBUG PURPOSES ONLY
        if (view.getNewPiece().getPiece() != null)
            System.out.println(view.getNewPiece().getPiece() + ", " +
            view.getNewPiece().getPieceX() + "," + view.getNewPiece().getPieceY() + ", " +
            view.getNewPiece().getPiece().getColour());

        //if a piece has already been selected, attempts to move the piece
        if (view.getPieceSelected() == true) {
            if (controller.move(view.getOriginalPiece().getPiece(), view.getNewPiece().getPieceX(), view.getNewPiece().getPieceY())) {
                controller.changeTurns();
                
                if (view.getOriginalPiece().getPiece().getClass().getSimpleName().equals("PointPiece") &&
                    (view.getNewPiece().getPieceY() == 0 || view.getNewPiece().getPieceY() == 6))
                    view.getOriginalPiece().getPiece().flipDir();
            }

            view.setPieceSelected(false);

            if (controller.getGameWin()) {
                view.changeButtonState(false);
                JOptionPane.showMessageDialog(view.getBoardPanel(), "Player " + controller.getWinner() + " wins!");
            }
        }
        //if this is the first time selecting a piece, change pieceSelected value to true
        else {
            view.setPieceSelected(true);
        }

        //highlight valid tiles
        if (view.getNewPiece() != null && view.getPieceSelected() && view.getNewPiece().getSelected()) {
            for (ArrayList<Integer> coords : view.getNewPiece().getPiece().getValidMoves(controller.getBoard(), controller.getPlayer())) {
                PieceButton validMoveButton = buttons[coords.get(1)][coords.get(0)];
                validMoveButton.setBackground(validMoveColour);
            }
        }
        else {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    buttons[i][j].setBackground(null);
                    buttons[i][j].setSelected(false);
                }
            }
        }

        view.updatePieces();
        view.updateDisplayPlayerTurn(controller);
    }
}
