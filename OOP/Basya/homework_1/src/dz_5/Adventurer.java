package dz_5;

public class Adventurer {
    protected String name;
    protected int health;
    protected int mana;
    public Adventurer(String name, int health, int mana) {
        this.name = name;
        this.health = health;
        this.mana = mana;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        System.out.println("Имя авантюриста было изменено с " + this.name + " на " + newName);
        this.name = newName;
    }

    public int getHealth() {
        return this.health;
    }

    public void getHealth(int newHealth) {
        System.out.println("Здоровье авантюриста было изменено с " + this.health + " на " + newHealth);
        this.health = newHealth;
    }

    public int getMana() {
        return this.mana;
    }

    public void getMana(int newMana) {
        System.out.println("Здоровье авантюриста было изменено с " + this.mana + " на " + newMana);
        this.mana = newMana;
    }

    public void attack() {
        System.out.println("Авантюрист еще на знает чем ему бить врагов");
    }
}
