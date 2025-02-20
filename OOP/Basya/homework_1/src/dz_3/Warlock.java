package dz_3;

public class Warlock {
    private String name;
    public Warlock(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        System.out.println("Имя волшебника было изменено с " + this.name + " на " + newName);
        this.name = newName;
    }

    public void castSpell(MagicSpell spell) {
        System.out.println("Волшебник, " + this.name + ", использует заклинание: " + spell);
        spell.cast();
    }
}
