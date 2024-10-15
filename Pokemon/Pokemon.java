package Pokemon;

import java.util.List;


public class Pokemon {
    protected String name;
    protected String type;
    protected int level;
    protected int hp;
    protected List<Attack> attacks;
    protected String asciiArt;

    public Pokemon(String type) {
        this.name = String.valueOf(getClass());
//        this.type = type;
        this.level = 10;
        this.hp = 100;
//        this.attacks = attacks;
        // this.asciiArt = asciiArt;
    }

    public void displayDetails() {
        System.out.println("Pokemon: " + getClass());
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Level: " + level);
        System.out.println("HP: " + hp);
        System.out.println("Attacks: ");
        for (Attack attack : attacks) {
            System.out.println("\t" + attack.getName());
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
        this.hp -= damageByAmount;
        System.out.println(name + " took " + damageByAmount + " damage. Remaining HP: " + hp);
    }

    public void heal(int healByAmount) {
        this.hp += healByAmount;
        if (this.)
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }
}