import java.util.Arrays;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Chocolate[] chocolates = {
                new Chocolate("Twix", 50.0),
                new Chocolate("Snickers", 57.5),
                new Chocolate("Bounty", 40.0),
                new Chocolate("Mars", 51.0)
        };

        Time[] times = {
                new Time(14, 25, 34),
                new Time(7, 5, 1),
                new Time(23, 59, 59),
                new Time(12, 0, 0)
        };

        Employee[] employees = {
                new Employee("Alice", 50000, new Date(120, 1, 1), "INS001"),
                new Employee("Bob", 70000, new Date(118, 5, 15), "INS002"),
                new Manager("Diana", 90000, new Date(115, 2, 20), "INS003", 5000),
                new Manager("Evan", 90000, new Date(116, 7, 30), "INS004", 8000)
        };

        System.out.println("Chocolates before sorting:");
        printArray(chocolates);
        Sort.bubbleSort(chocolates);
        System.out.println("Chocolates after bubble sort:");
        printArray(chocolates);

        System.out.println();
        System.out.println("Times before sorting:");
        printArray(times);
        Sort.mergeSort(times);
        System.out.println("Times after merge sort:");
        printArray(times);

        System.out.println();
        System.out.println("Employees before sorting:");
        printArray(employees);
        Sort.mergeSort(employees);
        System.out.println("Employees after merge sort:");
        printArray(employees);
    }

    private static <E> void printArray(E[] array) {
        System.out.println(Arrays.toString(array));
    }
}
