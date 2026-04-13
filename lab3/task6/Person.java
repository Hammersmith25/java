import java.util.Objects;

public abstract class Person implements Comparable<Person>, Cloneable {
    protected String name;
    protected int age;
    protected Pet pet;
    protected Pet temporaryPet;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Pet getPet() {
        return pet;
    }

    public abstract String getOccupation();

    protected boolean canHavePet(Pet pet) {
        return true;
    }

    public boolean assignPet(Pet pet) {
        if (pet == null) {
            return false;
        }
        if (!canHavePet(pet)) {
            System.out.println(name + " cannot have a " + pet.getType() + ".");
            return false;
        }
        this.pet = pet;
        return true;
    }

    public Pet removePet() {
        Pet removedPet = pet;
        pet = null;
        return removedPet;
    }

    public boolean hasPet() {
        return pet != null;
    }

    public void leavePetWith(Person caretaker) {
        if (pet == null) {
            System.out.println(name + " has no pet to leave.");
            return;
        }
        if (caretaker.temporaryPet != null) {
            System.out.println(caretaker.name + " is already taking care of another pet.");
            return;
        }
        if (!caretaker.canHavePet(pet)) {
            System.out.println(caretaker.name + " cannot take care of " + pet.getName() + ".");
            return;
        }
        caretaker.temporaryPet = pet;
        pet = null;
        System.out.println(name + " left " + caretaker.temporaryPet.getName() + " with " + caretaker.name + ".");
    }

    public void retrievePetFrom(Person caretaker) {
        if (caretaker.temporaryPet == null) {
            System.out.println(caretaker.name + " has no temporary pet for " + name + ".");
            return;
        }
        pet = caretaker.temporaryPet;
        caretaker.temporaryPet = null;
        System.out.println(name + " retrieved " + pet.getName() + " from " + caretaker.name + ".");
    }

    @Override
    public int compareTo(Person other) {
        int ageComparison = Integer.compare(this.age, other.age);
        if (ageComparison != 0) {
            return ageComparison;
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public Person clone() {
        try {
            Person cloned = (Person) super.clone();
            if (this.pet != null) {
                cloned.pet = this.pet.clone();
            }
            if (this.temporaryPet != null) {
                cloned.temporaryPet = this.temporaryPet.clone();
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone should be supported for people.", e);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return age == other.age && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        String petInfo = pet == null ? "No pet" : pet.toString();
        String temporaryPetInfo = temporaryPet == null ? "No temporary pet" : temporaryPet.toString();
        return getOccupation() + "{name='" + name + "', age=" + age
                + ", pet=" + petInfo + ", temporaryPet=" + temporaryPetInfo + "}";
    }
}
