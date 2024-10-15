package Pokemon;

import java.util.List;

public class Pikachu extends Pokemon {

//    private String name;
//    private String type;
//    private int level;
//    private int hp;
//    private List<Attack> attacks;
//    private String asciiArt;

    public Pikachu() {
        super("Electric");
        Attack zap = new Attack("zap", 20);
        this.attacks = List.of(new Attack[]{zap});
    }
}