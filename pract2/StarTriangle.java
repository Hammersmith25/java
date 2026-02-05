public class StarTriangle{
    private int width;

    public StarTriangle(int n){
        this.width = n;
    }

    public String toString(){
        String str = "";
        for(int i = 0; i <= width; i++){
            str += "[*]".repeat(i) + "\n";
        }
        return str;
    }
    public void generate(){
        System.out.println(this.toString());
    } 
    public static void main(String args[]){
        StarTriangle test = new StarTriangle(8);
        test.generate();
    }
}
