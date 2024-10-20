package Pokemon;

import java.util.List;


public class Pokemon {
    protected String name;
    protected String pokemonType;
    protected String weaknessType;
    protected int level;
    protected int maxHp;
    protected int remainingHp;
    protected List<Attack> listOfAttacks;
//    protected String asciiArt;

    public Pokemon(String pokemonType, List<Attack> listOfAttacks) {
        this.name = getClass().getSimpleName();
        this.pokemonType = pokemonType;
        this.level = 10;
        this.maxHp = 50 + (5 * level);
        this.remainingHp = this.maxHp;
        this.listOfAttacks = listOfAttacks;
        // this.asciiArt = asciiArt;
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
//        System.out.println(asciiArt);
    }

    public void changeName(String newName) {
        this.name = newName;
        System.out.println("Pokemon's new name is: " + newName);
    }

//    public void hibernate() {
//        System.out.println(name + " is hibernating.");
//    }

//    public String getAsciiArt() {
//        return asciiArt;  // Getter for ASCII art
//    }

    public void takesDamage(int damageByAmount) {
        this.remainingHp -= damageByAmount;
        System.out.println(name + " took " + damageByAmount + " damage. Remaining HP: " + remainingHp);
    }

    public void heal(int healByAmount) {
        this.remainingHp += healByAmount;
        if (this.remainingHp > this.maxHp) {
            this.remainingHp = maxHp;
        }
    }

    public String getName() {
        return name;
    }

    public int getRemainingHp() {
        return remainingHp;
    }

    public List<Attack> getAttacks() {
        return listOfAttacks;
    }
}