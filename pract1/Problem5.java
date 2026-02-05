import java.util.Scanner;
public class Problem5{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter balance: ");
        double a = in.nextDouble();
        System.out.println("Enter interest: ");
        double intrs = in.nextDouble();
        double add = (a*intrs)/100;
        a += add;
        System.out.println("Answer: " + a);
    }
}