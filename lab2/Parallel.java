public class Parallel extends Circuit {

    private Circuit c1;
    private Circuit c2;
    private double potentialDifference;

    public Parallel(Circuit c1, Circuit c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public double getResistance() {
        return 1 / (1 / c1.getResistance() + 1 / c2.getResistance());
    }

    public double getPotentialDiff() {
        return potentialDifference;
    }

    public void applyPotentialDiff(double V) {

        potentialDifference = V;

        c1.applyPotentialDiff(V);
        c2.applyPotentialDiff(V);
    }
}