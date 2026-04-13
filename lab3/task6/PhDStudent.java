public class PhDStudent extends Person {
    private String major;
    private String researchTopic;

    public PhDStudent(String name, int age, String major, String researchTopic) {
        super(name, age);
        this.major = major;
        this.researchTopic = researchTopic;
    }

    @Override
    public String getOccupation() {
        return "PhDStudent(" + major + ", " + researchTopic + ")";
    }

    @Override
    protected boolean canHavePet(Pet pet) {
        return !(pet instanceof Fish);
    }
}
