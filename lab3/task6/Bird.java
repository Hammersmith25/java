public class Bird extends Pet {
    public Bird(String name, int age) {
        super(name, age, "Bird");
    }

    @Override
    public String getSound() {
        return "Tweet";
    }

    @Override
    public void play() {
        System.out.println(name + " hops around and plays with a mirror.");
    }
}
