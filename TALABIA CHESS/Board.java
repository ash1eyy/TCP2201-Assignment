import java.util.ArrayList;
import java.util.Collections;



//The board is made out of 2 Dimensional Arrayslists

//and it uses a single instance only 

//Design Pattern: Singleton. Because only one board is used throughout the game.





public class Board { 
    private static Board singleInstance = null;
    private final int dimX = 7;
    private final int dimY = 6;
    private ArrayList<ArrayList<Piece>> map;

    private Board() {}

    public int getX() {
        return dimX;
    }

    public int getY() {
        return dimY;
    }

    public ArrayList<ArrayList<Piece>> getMap() { // 2d array of the board
        return map;
    }

    //by ashley ^_^
    //initializes the board
    public void init() {
        map = new ArrayList<ArrayList<Piece>>(dimY);
        
        for (int i = 0; i < dimY; i++) {
            map.add(new ArrayList<Piece>());
        }

        //adds pieces into row 0
        map.get(0).add(new PlusPiece());
        map.get(0).add(new HourglassPiece());
        map.get(0).add(new TimePiece());
        map.get(0).add(new SunPiece());
        map.get(0).add(new TimePiece());
        map.get(0).add(new HourglassPiece());
        map.get(0).add(new PlusPiece());

        //adds all the pieces for rows 1-4
        for (int i = 0; i < dimX; i++) {
            map.get(1).add(new PointPiece());
            map.get(2).add(null);
            map.get(3).add(null);
            map.get(dimY - 2).add(new PointPiece());
        }

        //initializes variables for each piece in row 0 & 1
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < dimX; j++) {
                map.get(i).get(j).setX(j);
                map.get(i).get(j).setY(i);
                map.get(i).get(j).setColour("blue");
                map.get(i).get(j).setDir("down");
            }
        }

        //adds all the pieces for row 6
        map.get(dimY - 1).add(new PlusPiece());
        map.get(dimY - 1).add(new HourglassPiece());
        map.get(dimY - 1).add(new TimePiece());
        map.get(dimY - 1).add(new SunPiece());
        map.get(dimY - 1).add(new TimePiece());
        map.get(dimY - 1).add(new HourglassPiece());
        map.get(dimY - 1).add(new PlusPiece());

        //initializes variables for each piece in row 5 & 6
        for (int i = dimY - 2; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                map.get(i).get(j).setX(j);
                map.get(i).get(j).setY(i);
                map.get(i).get(j).setColour("yellow");
                map.get(i).get(j).setDir("up");
            }
        }
    }

//A single instance of the board

    public static synchronized Board createInstance() { 
        if (singleInstance == null) 
            singleInstance = new Board();
        
        return singleInstance;
    }



//This is the board made out of the 2d Arraylists on display
//The main board we will be playing on using the console



    public void display() {
        //TEMPORARY CONSOLE DISPLAY. NEED TO REPLACE
        System.out.println("+-+-+-+-+-+-+-+");
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                Piece pieceChar = map.get(i).get(j);
                if (pieceChar == null) {
                    System.out.print("|" + " ");
                }
                else
                    System.out.print("|" + pieceChar.toString());
            }
            System.out.println("|\n+-+-+-+-+-+-+-+");
        }
    }



//This method will flip the board 
//It reiterates through the array with a loop 
//The method will check if the object at the coordinates are null 
//If not null the objects will be set with i and j values 


    //flip the board
    public void flipBoard() {
        Collections.reverse(map);

        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                Piece objectAtCoords = map.get(i).get(j);

                if (objectAtCoords != null) {
                    objectAtCoords.setX(j);
                    objectAtCoords.setY(i);

                    if (objectAtCoords.getDir() == "up")
                        objectAtCoords.setDir("down");
                    else
                        objectAtCoords.setDir("up");
                }
            }
        }
    }

//Method to get piece 

    public Piece getPiece(int x, int y) {
		return map.get(y).get(x);
    }

//Method that sets piece
// 
//paramerters used integer x, integer y, Piece piece
//
    public void setPiece(int x, int y, Piece piece) {
        map.get(y).set(x, piece);
    }
    //after board initializes
    //set piece at their new coords after being moved


//Method if there is no piece at the coordinate
    public boolean isEmpty(int x, int y) { 
		if (map.get(y).get(x) == null)
            return true;
        else
            return false;
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns true if a move was successfully made, otherwise returns false
    // public boolean move(Piece piece, int newX, int newY) { //the move method

/*         int oldX = piece.getX();
        int oldY = piece.getY();
        
        //iterates through all the valid moves of the given piece
        for (ArrayList<Integer> validMove : piece.getValidMoves(this)) {
            //if the new coords are one of the valid moves
            if (validMove.get(0) == newX &&
                validMove.get(1) == newY) {
                //if the piece at the destination is the sun, win the game
                // if (getPiece(newX, newY) != null && 
                //     getPiece(newX, newY).getPieceName() == "sun") {
                        
                //     //win game :)
                    
                // }

                setPiece(newX, newY, piece);
                setPiece(oldX, oldY, null);

                return true;
            }
        }
        return false;
    } */
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



//Method that switches time and plus 

    public void switchTimeAndPlus() { 
        String pieceColour = ""; 

        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {

                //Gets the coordinates of the following piece       

                Piece objectAtCoords = map.get(i).get(j);

                if (objectAtCoords != null) { //If not null switch case occurs
                    switch (objectAtCoords.getPieceName()) { 
                        case "time":
                            pieceColour = objectAtCoords.getColour();
                            setPiece(j, i, new PlusPiece()); //sets the time piece into a plus piece
                            getPiece(j, i).setX(j);
                            getPiece(j, i).setY(i);
                            map.get(i).get(j).setColour(pieceColour); //sets colour of piece from the colour it was before
                            break;

                        case "plus":
                            pieceColour = objectAtCoords.getColour();
                            setPiece(j, i, new TimePiece()); //sets the plus piece into a time piece
                            getPiece(j, i).setX(j);
                            getPiece(j, i).setY(i);
                            map.get(i).get(j).setColour(pieceColour);
                            break;

                        default:
                            break;
                    }
                }
            }
        }
    }
}
