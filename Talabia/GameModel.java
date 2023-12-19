import java.util.*;

public class GameModel {
    public static void main(String[] args) {
        Board board = new Board();
        board.init();

        while(true) {
            board.display();
            console(board);
        }
    }

    public static void console(Board board) {
        //commands:
        //1. piece - enter x & y coordinates to select piece,
        //   then another set of x & y coordinates to move it
        //2. save
        //3. load
        //4. quit
        System.out.print("\n> ");

        Scanner input = new Scanner(System.in);
        
        switch (input.nextLine()) {
            case "piece":
                System.out.print("Enter x coordinate: ");
                int pieceX = input.nextInt();
                System.out.print("Enter y coordinate: ");
                int pieceY = input.nextInt();

                Piece pieceToMove = board.getPiece(pieceX, pieceY);

                System.out.print("\nEnter x coordinate of destination: ");
                int newX = input.nextInt();
                System.out.print("Enter y coordinate of destination: ");
                int newY = input.nextInt();
                
                pieceToMove.move(board, newX, newY);
                pieceToMove.setX(newX);
                pieceToMove.setY(newY);

                break;
            
            case "save":
                
        }
    }
}
