package dz_5;

public class Berserker extends Adventurer {

    public Berserker(String name, int health, int mana) {
        super(name, health, mana);
    }

    @Override
    public void attack() {
        System.out.println("Авантюрист, " + this.name + ", атакует объект физической атакой");
    }

}
