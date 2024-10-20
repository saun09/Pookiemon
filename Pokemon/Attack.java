package Pokemon;

public class Attack {
    private final String attackName;
    private int attackDamage;
    private String attackType;
    private int maxUses;
    private int remainingUses;

    public Attack(String name, int damage, String damageType, int uses) {
        this.attackName = name;
        this.attackDamage = damage;
        this.attackType = damageType;
        this.maxUses = uses;
    }

    public String getName() {
        return this.attackName;
    }

    public int getRemainingUses() {
        return this.remainingUses;
    }

    public int useAttack() {
        if (this.getRemainingUses() > 0) {
            this.remainingUses--;
            System.out.println(getName() + " used. Remaining uses: " + getRemainingUses());
            return 1;
        } else {
            System.out.println(getName() + " has no uses left!");
            return 0;
        }
    }
}