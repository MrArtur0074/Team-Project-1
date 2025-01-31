public class Weapon {
    private int damage;
    public Weapon(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int newDamage) {
        System.out.println("урон оружия был изменено с " + this.damage + " на " + newDamage);
        this.damage = newDamage;
    }

    public void attack() {
        System.out.println("Оружие несется в замах и наносит урон: " + this.damage);
    }
}
