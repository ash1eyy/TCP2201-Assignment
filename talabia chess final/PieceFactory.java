import java.util.HashMap;

public class PieceFactory {
    private static HashMap<String, Piece> pieces = new HashMap<String, Piece>();

    public static Piece toPiece(String pieceName) {
        pieces.put("PlusPiece", new PlusPiece());
        pieces.put("HourglassPiece", new HourglassPiece());
        pieces.put("TimePiece", new TimePiece());
        pieces.put("SunPiece", new SunPiece());
        pieces.put("PointPiece", new PointPiece());

        Piece piece = pieces.get(pieceName);
        return piece;
    }
}
