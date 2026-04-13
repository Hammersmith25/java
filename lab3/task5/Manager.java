import java.util.Date;
import java.util.Vector;

public class Manager extends Employee {
    private double bonus;
    private Vector<Employee> team;

    public Manager(String name, double salary, Date hireDate, String insuranceNumber, double bonus) {
        super(name, salary, hireDate, insuranceNumber);
        this.bonus = bonus;
        this.team = new Vector<>();
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Vector<Employee> getTeam() {
        return team;
    }

    public void addEmployee(Employee employee) {
        team.add(employee);
    }

    @Override
    public int compareTo(Employee other) {
        int salaryComparison = super.compareTo(other);
        if (salaryComparison == 0 && other instanceof Manager) {
            return Double.compare(this.bonus, ((Manager) other).bonus);
        }
        return salaryComparison;
    }

    @Override
    public String toString() {
        return "Manager{name='" + getName() + "', salary=" + getSalary()
                + ", bonus=" + bonus + ", teamSize=" + team.size() + "}";
    }
}
