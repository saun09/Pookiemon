package Pokemon;

import java.util.List;

public class Bulbasaur extends Pokemon {

//    private String name;
//    private String pokemo;
//    private int level;
//    private int reminingHp;
//    private List<Attack> attacks;
//    private String asciiArt;

    public Bulbasaur(List<Attack> bulbasaurAttacks) {
        super("Grass", bulbasaurAttacks);
        this.weaknessType.add("Fire");
    }
}