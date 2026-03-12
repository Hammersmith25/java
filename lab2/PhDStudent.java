class PhDStudent extends Student {
    private String research;

    public PhDStudent(String name, int age, String major, String research) {
        super(name, age, major);
        this.research = research;
    }

    @Override
    public String getOccupation() {
        return "PhD Student in " + research;
    }

    @Override
    protected boolean canHavePet(Animal pet) {
        return !(pet instanceof Dog);
    }
}