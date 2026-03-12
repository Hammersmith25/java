public class Position{
    private int row;
    private int column;

    public Position(int r,int c){
        
        if (r < 1 || r > 8) {
            throw new IllegalArgumentException("Incorrect rank!");
        }
        if (c < 1 || c > 8){
            throw new IllegalArgumentException("Incorrect file!");
        }
        this.row = r;
        this.column = c;
    }

    public int getRow(){
        return this.row;
    }
    public char getCharColumn(){
        int convert = 64 + this.column;
        return (char) convert;
    }
    public int getColumn(){
        return this.column;
    }
    public void setRow(int r){
        if (r < 1 || r > 8) {
            throw new IllegalArgumentException("Incorrect rank!");
        }
        this.row = r;
    }
    public void setColumn(int c){
        if (c < 1 || c > 8) {
            throw new IllegalArgumentException("Incorrect File!");
        }
        this.column = c;
    }
}