public abstract class Piece {
    protected Position a;
    public abstract boolean isLegalMove(Position b);
    public Piece(Position a){
        this.a = a;
    }
    protected boolean isSamePosition(Position b) {
        return a.getRow() == b.getRow() && a.getColumn() == b.getColumn();
    }
}
class Rook extends Piece {
    public Rook(Position a) {
        super(a);
    }
    @Override
    public boolean isLegalMove(Position b) {
        if (isSamePosition(b)) return false;
        if(a.getColumn() == b.getColumn() || a.getRow() == b.getRow()) return true;
        return false;
    }

}
class King extends Piece {
    public King(Position a) {
        super(a);
    }
    @Override
    public boolean isLegalMove(Position b){
        if (isSamePosition(b)) return false;
        int colDiff = Math.abs(b.getColumn() - a.getColumn());
        int rowDiff = Math.abs(b.getRow() - a.getRow());
        if(colDiff <= 1 && rowDiff <= 1) return true;
        return false;
    }
}

class Queen extends Piece {
    public Queen(Position a) {
        super(a);
    }
    @Override
    public boolean isLegalMove(Position b){
        if (isSamePosition(b)) return false;
        int colDiff = Math.abs(b.getColumn() - a.getColumn());
        int rowDiff = Math.abs(b.getRow() - a.getRow());
        if(colDiff == rowDiff || a.getColumn() == b.getColumn() || a.getRow() == b.getRow()) return true;
        return false;
    }
}
class Bishop extends Piece {
    public Bishop(Position a) {
        super(a);
    }
    @Override
    public boolean isLegalMove(Position b){
        if (isSamePosition(b)) return false;
        int colDiff = Math.abs(b.getColumn() - a.getColumn());
        int rowDiff = Math.abs(b.getRow() - a.getRow());
        if(colDiff == rowDiff) return true;
        return false;
    }
}
class Knight extends Piece {
    public Knight(Position a) {
        super(a);
    }
    @Override
    public boolean isLegalMove(Position b){
        if (isSamePosition(b)) return false;
        int colDiff = Math.abs(b.getColumn() - a.getColumn());
        int rowDiff = Math.abs(b.getRow() - a.getRow());
        if((colDiff == 2 && rowDiff == 1) || (rowDiff == 2 && colDiff == 1)) return true;
        return false;
    }
}
class Pawn extends Piece {
    public Pawn(Position a) {
        super(a);
    }
    @Override
    public boolean isLegalMove(Position b){
        if (isSamePosition(b)) return false;
        int colDiff = Math.abs(b.getColumn() - a.getColumn());
        int rowDiff = b.getRow() - a.getRow();
        if(colDiff == 0 && rowDiff == 1) return true;
        return false;
    }
}