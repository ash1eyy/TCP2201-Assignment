import java.util.ArrayList;

public class Board {
    //2-DIMENSIONAL ARRAYLIST FOR THE BOARD
    private int dimX = 7;
    private int dimY = 6;
    private ArrayList<ArrayList<Piece>> map;

    //METHODS IN THE BOARD CLASS
    public void init() {
        map = new ArrayList<ArrayList<Piece>>(7);
        
        for (int i = 0; i < dimY; i++) {
            map.add(new ArrayList<Piece>());
        }

        //after that need to add the pieces in
        map.get(0).add(new PlusPiece());
        map.get(0).add(new HourglassPiece());
        map.get(0).add(new TimePiece());
        map.get(0).add(new SunPiece());
        map.get(0).add(new TimePiece());
        map.get(0).add(new HourglassPiece());
        map.get(0).add(new PlusPiece());

        for (int i = 0; i < dimX; i++) {
            map.get(1).add(new PointPiece());
            map.get(2).add(null);
            map.get(3).add(null);
            map.get(dimY - 2).add(new PointPiece());
        }

        map.get(dimY - 1).add(new PlusPiece());
        map.get(dimY - 1).add(new HourglassPiece());
        map.get(dimY - 1).add(new TimePiece());
        map.get(dimY - 1).add(new SunPiece());
        map.get(dimY - 1).add(new TimePiece());
        map.get(dimY - 1).add(new HourglassPiece());
        map.get(dimY - 1).add(new PlusPiece());
    }

    public void display() {
        //TEMPORARY CONSOLE DISPLAY. NEED TO REPLACE
        System.out.println("+-+-+-+-+-+-+-+");
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                System.out.print("|" + map.get(i).get(j));
            }
            System.out.println("|\n+-+-+-+-+-+-+-+");
        }
    }

    public void updateBoard() {

    }

    public void flipBoard() {

    }

    public Piece getPiece(int x, int y) {
		return map.get(y).get(x);
    }

    public void setPiece(int x, int y, Piece piece) {
        map.get(y).set(x, piece);
    }

    public boolean isEmpty(int x, int y) {
		if (map.get(y).get(x) == null)
            return true;
        else
            return false;
    }
}
