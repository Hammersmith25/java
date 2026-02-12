import java.util.*;
import pract2.Student;
class Course{
    private String name;
    private String description;
    private int credits;
    private String[] prerequisites;

    public Course(String nameOfCourse , String description , int credits, String[] prerequisites){
        this.name = nameOfCourse;
        this.description = description;
        this.credits = credits;
        this.prerequisites = prerequisites == null ? new String[0] : prerequisites;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Course{name='" + name + "', description='" + description + "', credits=" + credits
                + ", prerequisites=" + Arrays.toString(prerequisites) + "}";
    }
}
class GradeBookTest{
    private GradeBook gb;

    public GradeBookTest(GradeBook gradeBook){
        this.gb = gradeBook;
    }

    public void testGB(){
        gb.displayGradeReport();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Course cs = new Course(
                "CS101 Object-oriented Programming and Design",
                "Intro course",
                3,
                new String[0]
        );
        GradeBook gb = new GradeBook(cs);
        gb.displayMessage();
        gb.readGrades(sc);

        GradeBookTest gbt = new GradeBookTest(gb);
        gbt.testGB();
        sc.close();
    }
}
class GradeBook{
    private Course course;
    private Student[] students = new Student[10];
    private int[] grades = new int[10];
    private double avg;
    private Student lStud;
    private Student hStud;
    private int max = 0, min = 100;
    private int[] distribution = new int[11];
    private int size = students.length;

    public GradeBook(){
        this.course = new Course("Untitled", "", 0, new String[0]);
    }

    public GradeBook(Course cs){
        this.course = cs;
    }

    public GradeBook(Course cs, int studentCount){
        this.course = cs;
        this.students = new Student[studentCount];
        this.grades = new int[studentCount];
        this.size = studentCount;
    }

    public void readGrades(Scanner sc){
        System.out.println("Please, input grades for students:");
        System.out.println();
        int sum = 0;
        for (int i = 0; i < size; i++) {
            char letter = (char) ('A' + i);
            int grade;
            while (true) {
                System.out.print("Student " + letter + ": ");
                grade = sc.nextInt();
                if (grade >= 0 && grade <= 100) {
                    break;
                }
                System.out.println("Grade must be in range 0 - 100.");
            }

            students[i] = new Student("Student " + letter, 1);
            grades[i] = grade;
            sum += grade;

            if (i == 0 || grade < min) {
                min = grade;
                lStud = students[i];
            }
            if (i == 0 || grade > max) {
                max = grade;
                hStud = students[i];
            }
            distribution[grade / 10]++;
        }
        avg = (double) sum / size;
        System.out.println();
    }

    public void displayMessage(){
        System.out.println("Welcome to the grade book for " + course.getName() + "!");
        System.out.println();
    }

    public void displayGradeReport(){
        determineClassAverage();
        outputBarChart();
    }
    public void determineClassAverage(){
        System.out.printf("Class average is %.2f. Lowest grade is %d (%s, id: %d).%n",
                avg, min, lStud.getName(), lStud.getId());
        System.out.printf("Highest grade is %d (%s, id:%d).%n", max, hStud.getName(), hStud.getId());
        System.out.println();
    }
    public void outputBarChart(){
        System.out.println("Grades distribution:");
        int st =0, end = 9;
        for(int i = 0;i<11;i++){
            if(i == 10){
                System.out.print("100: ");
            }
            else{
                System.out.printf("%02d" , st);
                System.out.print("-");
                System.out.printf("%02d: " , end);
            }
            for(int j = 0;j<distribution[i];j++){
                System.out.print("*");
            }
            System.out.println();
            st+=10;
            end+=10;
        }
    }
    @Override
    public String toString() {
        return "GradeBook{course=" + course + ", students=" + students.length + "}";
    }
}
public class Problem4 {
    public static void main(String args[]){
        GradeBookTest.main(args);
    }
}
