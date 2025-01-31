public class Mage extends Hero {
    public Mage(int attackDamage, int defendPoints) {
        super(attackDamage, defendPoints);
    };
    public void attack() {
        super.attack();
        System.out.println("Магической атакой");
    }

    public void defend() {
        super.defend();
        System.out.println("Магического барьера");
    }

}
