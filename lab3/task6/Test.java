import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Employee john = new Employee("John", 30, "Engineer");
        PhDStudent alice = new PhDStudent("Alice", 26, "Computer Science", "AI");

        Pet dog = new Dog("Rex", 4);
        Pet cat = new Cat("Murka", 5);
        Pet fish = new Fish("Bubbles", 1);
        Pet bird = new Bird("Rio", 2);

        System.out.println("=== Assign pets ===");
        john.assignPet(dog);
        alice.assignPet(cat);
        System.out.println(john);
        System.out.println(alice);

        System.out.println();
        System.out.println("=== Custom interfaces in action ===");
        dog.feed();
        dog.clean();
        dog.play();
        dog.train("sit");
        cat.train("stay");
        fish.play();

        System.out.println();
        System.out.println("=== Existing interfaces in action ===");
        Person[] people = {john, alice};
        Arrays.sort(people);
        System.out.println("People sorted by age then name:");
        for (Person person : people) {
            System.out.println(person);
        }

        Pet[] pets = {dog, cat, fish, bird};
        Arrays.sort(pets);
        System.out.println("Pets sorted by age then name:");
        for (Pet pet : pets) {
            System.out.println(pet);
        }

        System.out.println();
        System.out.println("=== Leave and retrieve pet ===");
        john.leavePetWith(alice);
        System.out.println(john);
        System.out.println(alice);
        john.retrievePetFrom(alice);
        System.out.println(john);
        System.out.println(alice);

        System.out.println();
        System.out.println("=== Clone ===");
        Person clonedJohn = john.clone();
        System.out.println("Original: " + john);
        System.out.println("Clone:    " + clonedJohn);

        System.out.println();
        System.out.println("=== Restrictions ===");
        System.out.println("Alice tries to adopt fish:");
        alice.assignPet(fish);
        System.out.println("John tries to adopt bird:");
        john.assignPet(bird);
    }
}
