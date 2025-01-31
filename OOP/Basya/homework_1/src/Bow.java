public class Bow extends Weapon{
    public Bow(int damage) {
        super(damage);
    }

    @Override
    public void attack() {
        System.out.println("Удар деревянной частью лука сбивает противника с ног и наносит урон: " + (this.damage));
    }

    public void shootArrow() {
        System.out.println("Летящие стрелы пронзают врагов и наносят урон: " + (this.damage + 2));
    }
}
