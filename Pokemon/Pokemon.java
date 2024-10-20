package Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    protected String name;
    protected String pokemonType;
    protected List<String> weaknessType;
    protected int level;
    protected int maxHp;
    protected int remainingHp;
    protected List<Attack> listOfAttacks;

    public Pokemon(String pokemonType, List<Attack> listOfAttacks) {
        if (pokemonType == null || pokemonType.isEmpty()) {
            throw new IllegalArgumentException("Pokemon type cannot be null or empty");
        }
        if (listOfAttacks == null || listOfAttacks.isEmpty()) {
            throw new IllegalArgumentException("Attack list cannot be null or empty");
        }

        this.name = getClass().getSimpleName();
        this.pokemonType = pokemonType;
        this.level = 10;  // You might want to allow this to be passed as a parameter
        this.maxHp = 50 + (5 * level);
        this.remainingHp = this.maxHp;
        this.listOfAttacks = listOfAttacks;
        this.weaknessType = new ArrayList<>();
    }

    public void displayDetails() {
        System.out.println("Pokemon: " + this.getName());
        System.out.println("Name: " + name);
        System.out.println("Type: " + pokemonType);
        System.out.println("Weakness: " + weaknessType);
        System.out.println("Level: " + level);
        System.out.println("Max HP: " + maxHp);
        System.out.println("Remaining HP: " + remainingHp);
        System.out.println("Attacks:");
        for (int i = 0; i < listOfAttacks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + listOfAttacks.get(i).getName());
        }
    }

    public void changeName(String newName) {
        if (newName == null || newName.isEmpty()) {
            throw new IllegalArgumentException("New name cannot be null or empty");
        }
        this.name = newName;
        System.out.println("Pokemon's new name is: " + newName);
    }

    public void takesDamage(Attack attack) {
        if (attack == null) {
            throw new IllegalArgumentException("Attack cannot be null");
        }

        int takeDamage;
        if (this.weaknessType.contains(attack.getAttackType())) {
            takeDamage = attack.getAttackDamage() * 2;
        } else {
            takeDamage = attack.getAttackDamage();
        }

        if (this.remainingHp <= takeDamage) {
            this.remainingHp = 0;
        } else {
            this.remainingHp -= takeDamage;
        }
        System.out.println(name + " took " + takeDamage + " damage. HP: " + remainingHp + " / " + maxHp);
    }

    public void heal(int healByAmount) {
        if (healByAmount < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }

        this.remainingHp += healByAmount;
        if (this.remainingHp > this.maxHp) {
            this.remainingHp = maxHp;
        }
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getRemainingHp() {
        return remainingHp;
    }

    public List<Attack> getAttacks() {
        return listOfAttacks;
    }

    public void displayBattleDetails() {
        System.out.println("Name: " + this.getName());
        System.out.println("Level: " + this.getLevel());
        System.out.println("HP: " + this.getRemainingHp() + " / " + this.maxHp);
        System.out.println("Attacks:-");
        for (int i = 0; i < getAttacks().size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + getAttacks().get(i).getName() + " (" + getAttacks().get(i).getRemainingUses() + " uses left)");
        }
    }

    public void resetAttackUses() {
        for (Attack attack : this.listOfAttacks) {
            attack.resetUses();
        }
    }

    public boolean isDefeated() {
        return this.remainingHp == 0;
    }
}
