package Pokemon;

public class Attack {
    private final String attackName;
    private int attackDamage;
    private String attackEffect;
    private int remainingUses;

    public Attack(String name, int uses) {
        this.attackName = name;
        this.remainingUses = uses;
    }

    public String getName() {
        return this.attackName;
    }

    public int getRemainingUses() {
        return this.remainingUses;
    }

    public void useAttack() {
        if (this.getRemainingUses() > 0) {
            this.remainingUses--;
            System.out.println(this.attackName + " used. Remaining uses: " + this.remainingUses);
        } else {
            System.out.println(this.attackName + " has no uses left!");
        }
    }
}