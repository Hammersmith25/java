import java.util.Date;

public class Employee extends Person implements Comparable<Employee> {
    private double salary;
    private Date hireDate;
    private String insuranceNumber;

    public Employee(String name, double salary, Date hireDate, String insuranceNumber) {
        super(name);
        this.salary = salary;
        this.hireDate = hireDate;
        this.insuranceNumber = insuranceNumber;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }

    @Override
    public String toString() {
        return "Employee{name='" + getName() + "', salary=" + salary
                + ", hireDate=" + hireDate + ", insuranceNumber='" + insuranceNumber + "'}";
    }
}
