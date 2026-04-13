public class Fish extends Pet {
    public Fish(String name, int age) {
        super(name, age, "Fish");
    }

    @Override
    public String getSound() {
        return "Blub";
    }

    @Override
    public void play() {
        System.out.println(name + " swims in circles instead of playing.");
    }

    @Override
    public void train(String command) {
        System.out.println(name + " cannot really learn command: " + command);
    }
}
