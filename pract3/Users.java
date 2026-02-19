import java.util.*;
public class Users{

    private static Vector<Person> userV = new Vector<>();

    public void addUser(){
        Scanner sc = new Scanner(System.in);
        int type;
        System.out.print("Person - 0, Student- 1, Staff - 2: ");
        type = sc.nextInt();
        if(type == 0){
            System.out.print("Enter the name:");
            String name = sc.next();
            System.out.print("Enter the Address:");
            String address = sc.next();
            userV.add(new Person(name,address));
        }
        else if(type == 1){
            System.out.print("Enter the name:");
            String name = sc.next();
            System.out.print("Enter the Address:");
            String address = sc.next();
            System.out.print("Enter the program:");
            String program = sc.next();
            System.out.print("Enter the year:");
            int year = sc.nextInt();
            System.out.print("Enter the fee:");
            double fee = sc.nextDouble();
            userV.add(new Student(name,address, program, year, fee));
        }
        else if(type == 2){
            System.out.print("Enter the name:");
            String name = sc.next();
            System.out.print("Enter the Address:");
            String address = sc.next();
            System.out.print("Enter the school:");
            String school = sc.next();
            System.out.print("Enter the pay:");
            double pay = sc.nextDouble();
            userV.add(new Staff(name, address, school, pay));
        }
    }

    public void info(){
        for(Person us: userV){
            System.out.println(us.toString());
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Users test = new Users();
        String choice;
        while(true){
            System.out.print("Add - a, Info - i, Quit - q: ");
            choice = sc.next();
            if(choice.equals("a")){
                test.addUser();
            }
            else if(choice.equals("i")){
                test.info();
            }
            else if(choice.equals("q")){
                break;
            }
            else continue;
        }
        sc.close();
    }
}