package test_dz_4;
import java.util.List;
import java.util.ArrayList;

public class Department {
    public String name;
    public List<Employee> employeeList;
    public Department(String name) {
        this.name = name;
        this.employeeList = new ArrayList<>();
    }
    public void addEmployee(Employee e) {
        employeeList.add(e);
    }

    public void printEmployees() {
        for (Employee e : employeeList) {
            System.out.println(e.name);
        }
    }
}
