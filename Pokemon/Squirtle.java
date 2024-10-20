package Pokemon;

import java.util.List;

public class Squirtle extends Pokemon {

//    private String name;
//    private String pokemo;
//    private int level;
//    private int reminingHp;
//    private List<Attack> attacks;
//    private String asciiArt;

    public Squirtle(List<Attack> squirtleAttacks) {
        super("Water", squirtleAttacks);
        this.weaknessType.add("Grass");
        this.weaknessType.add("Electric");
    }
}