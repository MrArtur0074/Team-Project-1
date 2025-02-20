package dz_5;

public class Wizard extends Adventurer {

    public Wizard(String name, int health, int mana) {
        super(name, health, mana);
    }

    @Override
    public void attack() {
        System.out.println("Авантюрист, " + this.name + ", атакует объект магической атакой");
        this.mana -= 10;
    }

}
