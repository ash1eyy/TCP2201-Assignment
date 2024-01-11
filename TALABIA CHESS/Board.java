import java.util.ArrayList;
import java.util.Collections;

public class Board { //Design Pattern: Singleton. Because only one board is used throughout the game.
    private static Board singleInstance = null;
    //2-DIMENSIONAL ARRAYLIST FOR THE BOARD
    private int dimX = 7;
    private int dimY = 6;
    private ArrayList<ArrayList<Piece>> map;

    private Board() {}

    public int getX() {
        return dimX;
    }

    public int getY() {
        return dimY;
    }

    public ArrayList<ArrayList<Piece>> getMap() {
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

    public static synchronized Board createInstance() {
        if (singleInstance == null) 
            singleInstance = new Board();
        
        return singleInstance;
    }

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

    //flip the board
    public void flipBoard() {
        Collections.reverse(map);

        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                Piece objectAtCoords = map.get(i).get(j);

                if (objectAtCoords != null) {
                    objectAtCoords.setX(j);
                    objectAtCoords.setY(i);
                }

                //TO DO: set colours for each piece (idk why its not correct)
            }
        }
    }

    //gets the piece 
    public Piece getPiece(int x, int y) {
		return map.get(y).get(x);
    }

    //this set piece on map
    public void setPiece(int x, int y, Piece piece) {
        map.get(y).set(x, piece);
    }
    //after board initializes
    //set piece at their new coords after being moved

    public boolean isEmpty(int x, int y) {
		if (map.get(y).get(x) == null)
            return true;
        else
            return false;
    }

    public void switchTimeAndPlus() {
        String pieceColour = "";

        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                Piece objectAtCoords = map.get(i).get(j);

                if (objectAtCoords != null) {
                    switch (objectAtCoords.getClass().getSimpleName()) {
                        case "TimePiece":
                            pieceColour = objectAtCoords.getColour();
                            this.setPiece(j, i, new PlusPiece());
                            break;

                        case "PlusPiece":
                            pieceColour = objectAtCoords.getColour();
                            this.setPiece(j, i, new TimePiece());
                            break;

                        default:
                            break;
                    }

                    objectAtCoords.setColour(pieceColour);
                }
            }
        }
    }
}