public class Student extends Person{
    private String program;
    private int year;
    private double fee;

    public Student(String name, String address, String program, int year, double fee){
        super(name, address);
        this.program = program;
        this.year = year;
        this.fee = fee;
    }

    public String getProgram(){
        return this.program;
    }
    public int getYear(){
        return this.year;
    }
    public double getFee(){
        return this.fee;
    }

    public void setProgram(String program){
        this.program = program;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setFee(double fee){
        this.fee = fee;
    }

    @Override
    public String toString(){
        return String.format("Student[%s, program = %s, year = %d, fee = %f]", super.toString(), program,  year, fee);
    }

    public static void main(String[] args){
        Student test = new Student("testName", "testAddress", "testProgram", 2, 25.4);
        System.out.println(test.toString());
    }
}