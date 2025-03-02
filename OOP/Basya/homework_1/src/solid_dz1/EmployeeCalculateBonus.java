package solid_dz1;

public class EmployeeCalculateBonus {
    private Employee employee;

    public EmployeeCalculateBonus(Employee employee) {
        this.employee = employee;
    }

    public double calculateBonus(Employee employee) {
        return employee.getSalary() * 0.1;
    }
}
