public class Sword extends Weapon{
    public Sword(int damage) {
        super(damage);
    }

    @Override
    public void attack() {
        System.out.println("Меч разрезает плоть и наносит урон: " + (this.damage + 5));
    }
}
