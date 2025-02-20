package dz_5;

public class Healer extends Adventurer {

    public Healer(String name, int health, int mana) {
        super(name, health, mana);
    }

    @Override
    public void attack() {
        System.out.println("Авантюрист, " + this.name + ", лечит объект");
        this.mana -= 15;
    }

}
