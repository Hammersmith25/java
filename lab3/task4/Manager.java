import java.util.Vector;
import java.util.Date;
class Manager extends Employee {
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

    public void addEmployee(Employee e) { 
        team.add(e); 
    }
    public void removeEmployee(Employee e) { 
        team.remove(e); 
    }

    @Override
    public String toString() {
        return super.toString() + ", Bonus: " + bonus + ", Team size: " + team.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Manager other = (Manager) obj;
        return this.bonus == other.bonus;
    }

    @Override
    public int compareTo(Employee other) {
        int result = super.compareTo(other);
        if (result == 0 && other instanceof Manager) {
            return Double.compare(this.bonus, ((Manager) other).bonus);
        }
        return result;
    }
    public Manager cloneManager() {
        Manager m = new Manager(this.getName(), this.getSalary(), this.getHireDate(), this.getInsuranceNumber(), this.bonus);
        for (Employee e : this.team) {
            m.addEmployee(e.cloneEmployee());
        }
        return m;
    }
}