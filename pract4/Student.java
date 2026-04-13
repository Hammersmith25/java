public class Student extends Person implements CanHavePizza{
    public Student(String name, String address){
        super(name,address);
    }

    @Override
    void eatPizza(){
        System.out.println("Mmm great pizza");
    }
}