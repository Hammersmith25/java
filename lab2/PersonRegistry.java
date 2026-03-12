import java.util.Vector;

class PersonRegistry {
    private Vector<Person> people = new Vector<>();

    public void addPerson(Person p) {
        people.add(p);
    }

    public void removePerson(Person p) {
        people.remove(p);
    }

    public Vector<Person> getPeopleWithPets() {
        Vector<Person> result = new Vector<>();
        for (Person p : people) {
            if (p.hasPet()) result.add(p);
        }
        return result;
    }

    public Vector<Person> getPeopleWithoutPets() {
        Vector<Person> result = new Vector<>();
        for (Person p : people) {
            if (!p.hasPet()) result.add(p);
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (Person p : people) {
            result += p.toString() + "\n";
        }
        return result;
    }
}