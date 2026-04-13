public abstract class Vehicle {
    // shared fields
    String brand;
    int speed;
    int fuel;

    // shared method (all vehicles do this the same way)
    void refuel(int amount) {
        fuel += amount;
    }

    
    abstract void move(); // abstract method - every vehicle moves differently
}

class Car extends Vehicle {
    public void move() {
        System.out.println("Car drives");
    }
}

class Motorboat extends Vehicle {
    public void move() {
        System.out.println("Motorboat sails");
    }
}