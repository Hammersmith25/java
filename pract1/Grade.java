import java.util.Scanner;
public class Grade{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        double score = in.nextDouble();

        if (score < 0 || score > 100) {
            System.out.println("Invalid score");
        } else if (score >= 95) {
            System.out.println("A");
        } else if (score >= 90) {
            System.out.println("A-");
        } else if (score >= 85) {
            System.out.println("B+");
        } else if (score >= 80) {
            System.out.println("B");
        } else if (score >= 75) {
            System.out.println("B-");
        } else if (score >= 70) {
            System.out.println("C+");
        } else if (score >= 65) {
            System.out.println("C");
        } else if (score >= 60) {
            System.out.println("C-");
        } else if (score >= 55) {
            System.out.println("D+");
        } else if (score >= 50) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }
    }
}