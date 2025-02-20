package test_dz_5;

public class Car {
    public String model;
    public Engine engine; // Композиция: Car содержит Engine и управляет его созданием

    public Car(String model, int horsePower, String type) {
        this.model = model;
        this.engine = new Engine(horsePower, type); // Двигатель создается внутри машины
    }

    public void start() {
        System.out.println(model + " запускается с двигателем " + engine.type +
                " мощностью " + engine.horsePower + " л.с.");
    }
}
