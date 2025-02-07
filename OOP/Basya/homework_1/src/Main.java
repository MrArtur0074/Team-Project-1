
import test_dz_1.MusicPlayer;
import test_dz_1.VideoPlayer;
import test_dz_2.Circle;
import test_dz_2.Rectangle;
import test_dz_3.Doctor;
import test_dz_3.Patient;
import test_dz_4.Department;
import test_dz_4.Employee;
import test_dz_5.Car;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MusicPlayer spotify = new MusicPlayer();
        VideoPlayer youtube = new VideoPlayer();
        spotify.play();
        spotify.pause();
        youtube.play();
        youtube.pause();
        Rectangle rectangle = new Rectangle(10, 5);
        rectangle.calculateArea();
        rectangle.calculatePerimeter();
        Circle circle = new Circle(10);
        circle.calculateArea();
        circle.calculatePerimeter();
        Patient patient = new Patient("Эдиль");
        Doctor doctor = new Doctor("эрлан");
        System.out.println(patient.health);
        doctor.treat(patient);
        System.out.println(patient.health);
        Employee employee1 = new Employee("Алтын", "Бэкенд");
        Employee employee2 = new Employee("Баяс", "Фронтенд");
        Department department = new Department("group1");
        department.printEmployees();
        department.addEmployee(employee1);
        department.addEmployee(employee2);
        department.printEmployees();
        Car car = new Car("BMW", 140, "Hybrid");
        car.start();
        }
    }