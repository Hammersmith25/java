import java.util.Date;
public class Employee extends Person implements Comparable<Employee>{
    private double salary;
    private Date hireDate;
    private String insuranceNumber;

    public Employee(String name, double salary,Date hireDate,String insuranceNumber){
        super(name);
        this.salary = salary;
        this.hireDate = hireDate;
        this.insuranceNumber = insuranceNumber;
    }
    public double getSalary(){
        return this.salary;
    } 
    public Date getHireDate(){
        return this.hireDate;
    }
    public String getInsuranceNumber(){
        return this.insuranceNumber;
    }

    public void setSalary(double s){
        this.salary = s;
    }
    public void setHireDate(Date d){
        this.hireDate = d;
    }
    public void setInsuranceNumber(String s){
        this.insuranceNumber = s;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + ", Salary: " + salary + ", Hire Date: " + hireDate + ", InsuranceNumber: " + insuranceNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        Employee other = (Employee) obj;
        return this.insuranceNumber.equals(other.insuranceNumber);
    }

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }

    public Employee cloneEmployee() {
        return new Employee(this.getName(), this.salary, this.hireDate, this.insuranceNumber);
    }

}