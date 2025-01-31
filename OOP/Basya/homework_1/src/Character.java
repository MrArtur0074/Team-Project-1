public class Character {
    private String name;
    private int health;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        System.out.println("Имя персонажа было изменено с " + this.name + " на " + newName);
        this.name = newName;
    }

    public int getHealth() {
        return this.health;
    }

    public void getHealth(int newHealth) {
        System.out.println("Здоровье персонажа было изменено с " + this.health + " на " + newHealth);
        this.health = newHealth;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("Персонаж получил урон равный: " + damage);
    }
}
