import java.util.Scanner;
class Data{
    private double sum;
    private double max;
    private int n;

    public Data(){
        this.sum = 0;
        this.max = 0;
        this.n = 0;
    }

    public void add(float a){
        if(a > max){
            max = a;
        }
        this.sum += a;
        this.n++;
    }
    public double getAvg(){
        if(n == 0){
            return 0;
        }
        return this.sum/this.n;
    }
    public double getMax(){
        return this.max;
    }

}

class Analyzer{
    private Data data;
    public Analyzer(){
        data = new Data();
    }
    public void getAvg(){
        System.out.print("Average=");
        System.out.println(data.getAvg());
    }
    public void getMax(){
        System.out.print("Maximum=");
        System.out.println(data.getMax());
    }
    public void add(float d){
        data.add(d);
    }
}
public class Problem1 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Analyzer an = new Analyzer();
        while(true){
            System.out.print("Enter number(Q to quit):");
            String num = sc.next();
            if(num.equals("Q")){
                sc.close();
                an.getAvg();
                an.getMax();
                return;
            }
            try{
                float n = Float.parseFloat(num);
                an.add(n);
            }
            catch(Exception ex){
                System.out.println("Invalid number!");
            }

        }
    }
}