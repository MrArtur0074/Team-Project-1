import dz_1.Character;
import dz_4.Hero;
import dz_5.Adventurer;
import dz_5.Berserker;
import dz_5.Healer;
import dz_5.Wizard;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Adventurer> adventurers = new ArrayList<>();

        adventurers.add(new Berserker("Guts", 500, 0));
        adventurers.add(new Wizard("Geyl", 100, 500));
        adventurers.add(new Healer("Mercy", 300, 300));

        battle(adventurers);

}

    public static void battle(List<Adventurer> adventurers) {
        System.out.println("===показ силы всех чемпионов===");
        for (Adventurer adventurer : adventurers) {
            adventurer.attack();
        }
    }
}