package Pokemon;

import java.util.List;

public class Charmander extends Pokemon {

//    private String name;
//    private String pokemo;
//    private int level;
//    private int reminingHp;
//    private List<Attack> attacks;
//    private String asciiArt;

    public Charmander(List<Attack> charmanderAttacks) {
        super("Fire", charmanderAttacks);
        this.weaknessType.add("Water");
    }
}