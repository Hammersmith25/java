public class Employee extends Person {
    private String position;

    public Employee(String name, int age, String position) {
        super(name, age);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String getOccupation() {
        return "Employee(" + position + ")";
    }

    @Override
    protected boolean canHavePet(Pet pet) {
        return !(pet instanceof Bird);
    }
}
