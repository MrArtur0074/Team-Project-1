package test_dz_3;

public class Doctor {
    public String name;
    public Doctor(String name) {
        this.name = name;
    }
    public void treat(Patient patient) {
        patient.health = "Здоров";
    }
}
