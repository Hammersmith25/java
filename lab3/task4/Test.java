import java.util.Vector;
import java.util.Date;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        Employee e1 = new Employee("A",  50000, new Date(2020, 1, 1),  "INS001");
        Employee e2 = new Employee("B",  70000, new Date(2018, 5, 15), "INS002");
        Employee e3 = new Employee("C", 50000, new Date(2022, 3, 10), "INS003");

        Manager m1 = new Manager("D",   90000, new Date(2015, 2, 20), "INS004", 5000);
        Manager m2 = new Manager("E", 90000, new Date(2016, 7, 30), "INS005", 8000);

        m1.addEmployee(e1);
        m1.addEmployee(e2);
        m2.addEmployee(e3);

        Vector<Employee> list = new Vector<>();
        list.add(e1); list.add(e2); list.add(e3); list.add(m1); list.add(m2);

        System.out.println("=== toString ===");
        System.out.println(e1);
        System.out.println(m1);

        System.out.println("\n=== equals ===");
        System.out.println("e1 equals e1: " + e1.equals(e1));
        System.out.println("e1 equals e2: " + e1.equals(e2));
        System.out.println("m1 equals m2: " + m1.equals(m2));

        System.out.println("\n=== compareTo (salary) ===");
        System.out.println("e1 vs e2: " + e1.compareTo(e2));
        System.out.println("e1 vs e3: " + e1.compareTo(e3));
        System.out.println("m1 vs m2 (bonus): " + m1.compareTo(m2));

        System.out.println("\n=== Sorted by salary ===");
        Collections.sort(list);
        list.forEach(System.out::println);

        System.out.println("\n=== Sorted by name ===");
        list.sort(new NameComparator());
        list.forEach(System.out::println);

        System.out.println("\n=== Sorted by hire date ===");
        list.sort(new HireDateComparator());
        list.forEach(System.out::println);

        System.out.println("\n=== Clone ===");
        Employee clonedE = e1.cloneEmployee();
        clonedE.setName("Clone of A");
        System.out.println("Original: " + e1);
        System.out.println("Cloned:   " + clonedE);

        Manager clonedM = m1.cloneManager();
        clonedM.setBonus(99999);
        System.out.println("Original manager bonus: " + m1.getBonus());
        System.out.println("Cloned manager bonus:   " + clonedM.getBonus());
    }
}