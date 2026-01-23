import java.util.Scanner;
public class Equation{
    public static void main(String[] args){
        double a,b,c;
        Scanner in = new Scanner(System.in);
        System.out.println("Input a: ");
        a = in.nextDouble();
        System.out.println("Input b: ");
        b = in.nextDouble();
        System.out.println("Input c: ");
        c = in.nextDouble();
        double d = b*b - 4*a*c;
        if(d < 0){
            System.out.println("error");
            return;
        }
        double x1,x2;
        x1 = (b * (-1) + Math.sqrt(d))/2*a;
        x2 = (b * (-1) - Math.sqrt(d))/2*a;

        System.out.println("("+ x1 + ";" + x2 + ")");
    }
}