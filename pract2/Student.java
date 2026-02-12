package pract2;
public class Student{
    private static int nextId = 0;
    private String name;
    private final int id;
    private int year;

    public Student(String name, int year){
        this.name = name;
        this.id = nextId;
        nextId++;
        if(year < 0){
            throw new IllegalArgumentException("Year must be positive!");
        }
        this.year = year;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public int getId(){
        return id;
    }
    public int getYear(){
        return year;
    }
    public void incrYear(){
        this.year++;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', year=" + year + "}";
    }


    public static void main(String args[]){
        Student test = new Student("test1",2001);
        System.out.println(test.getId());
        System.out.println(test.getName());
        System.out.println(test.getYear());
        test.incrYear();
        System.out.println(test.getYear());
        Student test2 = new Student("test2",2002);
        System.out.println(test2.getId());
        
    }
}
