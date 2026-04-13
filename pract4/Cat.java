public class Cat extends Animal implements CanHavePizza{
    public Cat(String name){
        super(name);
    }
    @Override
    void eatPizza(){
        System.out.println("mmm");
    }
}