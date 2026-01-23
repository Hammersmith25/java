import java.util.Scanner;
public class Calculator{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        System.out.println("Area of a square with the side of " + a + " is : " + a*a);
        System.out.println("Perimeter of a square with the side of " + a + " is : " + 4 * a);
        System.out.println("Diagonal of a square with the side of " + a + " is : " + Math.sqrt(2 * (a*a)));

    }
}