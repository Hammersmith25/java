import java.util.Objects;

abstract class Person {
    protected String name;
    protected int age;
    protected Animal pet;
    protected Animal tempPet; 

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean assignPet(Animal pet) {
        if (canHavePet(pet)) {
            this.pet = pet;
            return true;
        } else {
            System.out.println(this.name + " cannot have a " + pet.getClass().getSimpleName());
            return false;
        }
    }

    public Animal removePet() {
        Animal removed = this.pet;
        this.pet = null;
        return removed;
    }

    public boolean hasPet() {
        return pet != null;
    }

    public abstract String getOccupation();

    protected boolean canHavePet(Animal pet) {
        return true;
    }

    public void leavePetWith(Person caretaker) {
        if (pet == null) {
            System.out.println(name + " has no pet to leave.");
            return;
        }
        if (!caretaker.canHavePet(pet)) {
            System.out.println(caretaker.name + " cannot take care of this pet.");
            return;
        }
        caretaker.tempPet = this.pet;
        this.pet = null;
        System.out.println(name + " left pet " + caretaker.tempPet.getName() + " with " + caretaker.name);
    }

    public void retrievePetFrom(Person caretaker) {
        if (caretaker.tempPet == null) {
            System.out.println(caretaker.name + " has no temporary pet for " + name);
            return;
        }
        this.pet = caretaker.tempPet;
        caretaker.tempPet = null;
        System.out.println(name + " retrieved pet " + this.pet.getName() + " from " + caretaker.name);
    }

    @Override
    public String toString() {
        String petInfo = (pet != null) ? pet.toString() : "No pet";
        String tempInfo = (tempPet != null) ? tempPet.toString() : "";
        return name + " (" + getOccupation() + "), " + "Age: " + age + ", Pet: " + petInfo + 
               (tempPet != null ? ", Temporarily caring for: " + tempPet.getName() : "");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return this.name.equals(p.name) && this.age == p.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}