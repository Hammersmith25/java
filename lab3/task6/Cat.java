public class Cat extends Pet {
    public Cat(String name, int age) {
        super(name, age, "Cat");
    }

    @Override
    public String getSound() {
        return "Meow";
    }

    @Override
    public void train(String command) {
        System.out.println(name + " ignores command: " + command + ", but looks adorable.");
    }
}
