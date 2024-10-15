class Bag {
    private int burnHeal;
    private int hpHeal;

    public Bag(int burnHeal, int hpHeal) {
        this.burnHeal = burnHeal;
        this.hpHeal = hpHeal;
    }

    public static void useBurnHeal(Pokemon pokemon) {
        if (burnHeal > 0) {
            System.out.println("Using Burn Heal on " + pokemon.getName());
            burnHeal--;
        } else {
            System.out.println("No Burn Heal left!");
        }
    }

    public static void useHpHeal(Pokemon pokemon) {
        if (hpHeal > 0) {
            System.out.println("Using HP Heal on " + pokemon.getName());
            pokemon.decreaseHp(-20); // Heal 20 HP
            hpHeal--;
        } else {
            System.out.println("No HP Heal left!");
        }
    }

    public void displayBag() {
        System.out.println("Bag contents:");
        System.out.println("Burn Heal: " + burnHeal);
        System.out.println("HP Heal: " + hpHeal);
    }
}
