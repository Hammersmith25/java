import java.util.Scanner;
public class Dino extends Animal{
    private String color;
    public Dino(String color){
        super();
        this.color = color;
    }
    public Dino(String name,String color){
        super(name);
        this.color = color;
    }

    public void sound(){
        super.sound();
        System.out.println("Raaaarr!");
    }
    public void sound(String color){
        System.out.println(color + " " + name + ": Raaaarr!");
    }
    public void setColor(String color){
        this.color = color;
    }
    @Override
    public String toString(){
        return super.toString()+ "\n" + "And my color is " + this.color;
    }

    public static void main(String[] args){
        String name;
        String color;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name: ");
        name = sc.next();
        System.out.println("Enter the color: ");
        color = sc.next();
        Dino dino1 = new Dino(name,color);
        dino1.sound();
        dino1.sound(color);
        System.out.println(dino1.toString());
        sc.close();
    }
}