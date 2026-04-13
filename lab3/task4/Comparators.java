import java.util.Comparator;

class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee a, Employee b) {
        return a.getName().compareTo(b.getName());
    }
}

class HireDateComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee a, Employee b) {
        return a.getHireDate().compareTo(b.getHireDate());
    }
}