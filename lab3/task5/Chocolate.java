public class Chocolate implements Comparable<Chocolate> {
    private final double weight;
    private final String name;

    public Chocolate(String name, double weight) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Chocolate name cannot be empty.");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Chocolate weight must be positive.");
        }
        this.name = name;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Chocolate other) {
        return Double.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "Chocolate{name='" + name + "', weight=" + weight + "}";
    }
}
