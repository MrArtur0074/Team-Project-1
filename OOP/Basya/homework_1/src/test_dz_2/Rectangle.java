package test_dz_2;

public class Rectangle extends Shape{
    public int width;
    public int height;
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public void calculateArea() {
        super.calculateArea();
        System.out.println("Площадь прямоугольника равна: " + (this.height * this.width));
    }

    @Override
    public void calculatePerimeter() {
        super.calculatePerimeter();
        System.out.println("Периметр прямоугольника равен: " + ((this.width + this.height) * 2));
    }
}
