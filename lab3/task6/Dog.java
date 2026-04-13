public class Dog extends Pet {
    public Dog(String name, int age) {
        super(name, age, "Dog");
    }

    @Override
    public String getSound() {
        return "Woof";
    }

    @Override
    public void train(String command) {
        System.out.println(name + " follows command: " + command);
    }
}
