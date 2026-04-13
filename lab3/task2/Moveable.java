public interface Moveable{
    void move();
}

interface Swimmable extends Moveable{
    void swim();
}

class Car implements Moveable{
    public void move(){
        System.out.println("Car moves")
    }
}

class Fish implements Swimmable {
    public void swim() {
        System.out.println("Fish swims");
    }
    public void move() {
        System.out.println("Fish moves");
    }
}