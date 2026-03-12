abstract class Animal {
    protected String name;
    protected int age;
    protected String type;

    public Animal(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public abstract String getSound();

    @Override
    public String toString() {
        return name + " (" + type + ", " + age + " years)";
    }
}

class Cat extends Animal {
    public Cat(String name, int age) { 
        super(name, age,"Cat"); 
    }
    @Override
    public String getSound() { 
        return "Meow"; 
    }
}

class Dog extends Animal {
    public Dog(String name, int age) { 
        super(name, age,"Dog"); 
    }
    @Override
    public String getSound() { 
        return "Woof-woof"; 
    }
}

class Fish extends Animal {
    public Fish(String name, int age) { 
        super(name, age,"Fish"); 
    }
    @Override
    public String getSound() { 
        return "Bul-bul-bul"; 
    }
}

class Bird extends Animal {
    public Bird(String name, int age) { 
        super(name, age,"Bird"); 
    }
    @Override
    public String getSound() { 
        return "Tu-tu-tu"; 
    }
}


