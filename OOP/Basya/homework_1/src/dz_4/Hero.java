package dz_4;

public class Hero {
    protected int attackDamage;
    protected int defendPoints;
    public Hero(int attackDamage, int defendPoints) {
        this.attackDamage = attackDamage;
        this.defendPoints = defendPoints;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public void setAttackDamage(int newAttackDamage) {
        System.out.println("Урон героя был изменено с " + this.attackDamage + " на " + newAttackDamage);
        this.attackDamage = newAttackDamage;
    }

    public int getDefendPoints() {
        return this.defendPoints;
    }

    public void setDefendPoints(int newDefendPoints) {
        System.out.println("Очки защиты героя были изменено с " + this.defendPoints + " на " + newDefendPoints);
        this.defendPoints = newDefendPoints;
    }

    public void attack() {
        System.out.println("Герой наносит урон: " + this.attackDamage);
    }

    public void defend() {
        System.out.println("Герой имеет барьер: " + this.defendPoints);
    }
}
