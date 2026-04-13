public abstract class Pet implements Comparable<Pet>, Cloneable, Careable, Trainable {
    protected String name;
    protected int age;
    protected String type;

    public Pet(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public abstract String getSound();

    @Override
    public int compareTo(Pet other) {
        int ageComparison = Integer.compare(this.age, other.age);
        if (ageComparison != 0) {
            return ageComparison;
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public Pet clone() {
        try {
            return (Pet) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone should be supported for pets.", e);
        }
    }

    @Override
    public void feed() {
        System.out.println(name + " is eating happily.");
    }

    @Override
    public void clean() {
        System.out.println(name + "'s space has been cleaned.");
    }

    @Override
    public void play() {
        System.out.println(name + " is playing with the owner.");
    }

    @Override
    public void train(String command) {
        System.out.println(name + " is practicing command: " + command);
    }

    @Override
    public String toString() {
        return type + "{name='" + name + "', age=" + age + ", sound='" + getSound() + "'}";
    }
}
