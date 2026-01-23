import java.util.Scanner;
public class Palindrom{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String a = in.next();
        int l,r;
        l = 0;
        r = a.length()-1;
        while(l<r){
            if(a.charAt(l) != a.charAt(r)){
                System.out.println("No");
                return;
            }
            l++;
            r--;
        }
        System.out.println("Yes");
    }
}