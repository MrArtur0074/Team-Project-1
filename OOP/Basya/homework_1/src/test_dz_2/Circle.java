package test_dz_2;

public class Circle extends Shape{
    public int radius;
    public Circle(int radius) {
        this.radius = radius;
    }
    @Override
    public void calculateArea() {
        super.calculateArea();
        System.out.println("Площадь круга равна: " + (3.14 * this.radius * this.radius));
    }

    @Override
    public void calculatePerimeter() {
        super.calculatePerimeter();
        System.out.println("Периметр круга равен: " + (3.14 * this.radius * 2));
    }
}
