package dz_4;

public class Warrior extends Hero {
    public Warrior(int attackDamage, int defendPoints) {
        super(attackDamage, defendPoints);
    };
    public void attack() {
        super.attack();
        System.out.println("Физической атакой");
    }

    public void defend() {
        super.defend();
        System.out.println("Физического барьера");
    }
}
