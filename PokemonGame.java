import Pokemon.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokemonGame {

    public static void displayPokemon(List<Pokemon> listOfPokemon) {
        for (int i = 0; i < listOfPokemon.size(); i++) {
            System.out.println((i + 1) + ". " + listOfPokemon.get(i).getName());
        }
    }

    public static void listYourPokemonMenu(List<Pokemon> listOfPokemon, Scanner scanner) {
        boolean back = false;
        int choice;
        while (!back) {
            displayPokemon(listOfPokemon);
            System.out.println((listOfPokemon.size() + 1) + ". back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            if (choice > 0 && choice <= listOfPokemon.size()) {
                displayPokemonDetails(listOfPokemon, choice - 1);
                System.out.println("1. Change name\n2. back");
                int newChoice = scanner.nextInt();
                if (newChoice == 1) {
                    System.out.print("Enter new name for " + listOfPokemon.get(choice - 1).getName() + ": ");
                    String newName = scanner.next();
                    listOfPokemon.get(choice - 1).changeName(newName);
                }
                continue;
            }
            back = true;
        }
    }

    public static void fightPokemonMenu(List<Pokemon> listOfPokemon, Scanner scanner) {
        displayPokemon(listOfPokemon);
        System.out.println("Player 1:-");
        System.out.print("Choose your pokemon: ");
        int player1Choice = scanner.nextInt();
        Pokemon player1Pokemon = listOfPokemon.get(player1Choice - 1);

        System.out.println("Player 2:-");
        System.out.print("Choose your pokemon: ");
        int player2Choice = scanner.nextInt();
        Pokemon player2Pokemon = listOfPokemon.get(player2Choice - 1);

        player1Pokemon.resetAttackUses();
        player2Pokemon.resetAttackUses();

        boolean player1Run = false, player2Run = false;

        boolean exit = false;

        while (exit != true) {
            System.out.println("Player 1:-");
            player1Pokemon.displayBattleDetails();
            System.out.println();
            System.out.println("Player 2:-");
            player2Pokemon.displayBattleDetails();

            System.out.println("Player 1's turn!");
            System.out.println("1. Attack");
            System.out.println("2. Use item");
            System.out.println("3. Run");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter attack: ");
                    int attackChoice = scanner.nextInt();
                    if (attackChoice <= 0 || attackChoice > player1Pokemon.getAttacks().size()) {
                        System.out.println("Invalid Input...");
                        continue;
                    }
                    if (player1Pokemon.getAttacks().get(attackChoice - 1).useAttack()) {
                        player2Pokemon.takesDamage((player1Pokemon.getAttacks().get(attackChoice - 1)));
                        if (player2Pokemon.isDefeated()) {
                            System.out.println(player2Pokemon.getName() + " is defeated! " + player1Pokemon.getName() + " wins!");
                            exit = true;
                            continue;
                        }
                    }
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Player 1 chose to run away.");
                    System.out.println(player2Pokemon.getName() + " wins!");
                    exit = true;
                    continue;
                default:
                    System.out.println("Invalid Input...");
            }

            System.out.println("Player 1:-");
            player1Pokemon.displayBattleDetails();
            System.out.println();
            System.out.println("Player 2:-");
            player2Pokemon.displayBattleDetails();

            System.out.println("Player 2's turn!");
            System.out.println("1. Attack");
            System.out.println("2. Use item");
            System.out.println("3. Run");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter attack: ");
                    int attackChoice = scanner.nextInt();
                    if (attackChoice <= 0 || attackChoice > player2Pokemon.getAttacks().size()) {
                        System.out.println("Invalid Input...");
                        continue;
                    }
                    if (player2Pokemon.getAttacks().get(attackChoice - 1).useAttack()) {
                        player1Pokemon.takesDamage((player2Pokemon.getAttacks().get(attackChoice - 1)));
                        if (player1Pokemon.isDefeated()) {
                            System.out.println(player2Pokemon.getName() + " is defeated! " + player1Pokemon.getName() + " wins!");
                            exit = true;
                            continue;
                        }
                    }
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Player 2 chose to run away.");
                    System.out.println(player1Pokemon.getName() + " wins!");
                    exit = true;
                    continue;
                default:
                    System.out.println("Invalid Input...");
            }
        }
    }

    public static void pokemonGameMenu() {
        Scanner scanner = new Scanner(System.in);

        // Initialize Pokémon and Bag
        List<Pokemon> listOfPokemon = initializePokemon();
        Bag bag = new Bag(3, 5);  // Start with 3 Burn Heal and 5 HP Heal

        System.out.println("\nWelcome to the Pokemon Game!");
        boolean exit = false;
        while (!exit) {
            System.out.println("1. List your Pokemon");
            System.out.println("2. Fight random Pokemon");
            System.out.println("3. Exit");
//            System.out.println("2. Change Pokémon name");
//            System.out.println("3. Hibernate Pokémon");
//            System.out.println("4. Battle Pokémon");
//            System.out.println("5. View your BAG (use items)");
//            System.out.println("6. Run from battle");
//            System.out.println("7. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listYourPokemonMenu(listOfPokemon, scanner);
                    break;
                case 2:
                    fightPokemonMenu(listOfPokemon, scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the game...");
                    break;
//                case 4:
//                    battlePokemon(listOfPokemon, scanner, bag);
//                    break;
//                case 5:
//                    useBagItems(listOfPokemon, scanner, bag);
//                    break;
//                case 6:
//                    System.out.println("You ran from the battle!");
//                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        pokemonGameMenu();
    }

    public static List<Pokemon> initializePokemon() {
        List<Attack> pikachuAttacks = new ArrayList<>();
        pikachuAttacks.add(new Attack("Thunderbolt", 10, "Electric", 5));
        pikachuAttacks.add(new Attack("Quick Attack", 10, "Electric", 5));
        pikachuAttacks.add(new Attack("Iron Tail", 15, "Electric", 5));
        pikachuAttacks.add(new Attack("Electro Ball", 5, "Electric", 5));

       /* String asciiArtPikachu =""" 
        ⠸⣷⣦⠤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣤⠀⠀⠀
        ⠀⠙⣿⡄⠈⠑⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠔⠊⠉⣿⡿⠁⠀⠀⠀
        ⠀⠀⠈⠣⡀⠀⠀⠑⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⠁⠀⠀⣰⠟⠀⠀⠀⣀⣀
        ⠀⠀⠀⠀⠈⠢⣄⠀⡈⠒⠊⠉⠁⠀⠈⠉⠑⠚⠀⠀⣀⠔⢊⣠⠤⠒⠊⠉⠀⡜
        ⠀⠀⠀⠀⠀⠀⠀⡽⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠩⡔⠊⠁⠀⠀⠀⠀⠀⠀⠇
        ⠀⠀⠀⠀⠀⠀⠀⡇⢠⡤⢄⠀⠀⠀⠀⠀⡠⢤⣄⠀⡇⠀⠀⠀⠀⠀⠀⠀⢰⠀
        ⠀⠀⠀⠀⠀⠀⢀⠇⠹⠿⠟⠀⠀⠤⠀⠀⠻⠿⠟⠀⣇⠀⠀⡀⠠⠄⠒⠊⠁⠀
        ⠀⠀⠀⠀⠀⠀⢸⣿⣿⡆⠀⠰⠤⠖⠦⠴⠀⢀⣶⣿⣿⠀⠙⢄⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⢻⣿⠃⠀⠀⠀⠀⠀⠀⠀⠈⠿⡿⠛⢄⠀⠀⠱⣄⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⢸⠈⠓⠦⠀⣀⣀⣀⠀⡠⠴⠊⠹⡞⣁⠤⠒⠉⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⣠⠃⠀⠀⠀⠀⡌⠉⠉⡤⠀⠀⠀⠀⢻⠿⠆⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠰⠁⡀⠀⠀⠀⠀⢸⠀⢰⠃⠀⠀⠀⢠⠀⢣⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⢶⣗⠧⡀⢳⠀⠀⠀⠀⢸⣀⣸⠀⠀⠀⢀⡜⠀⣸⢤⣶⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠈⠻⣿⣦⣈⣧⡀⠀⠀⢸⣿⣿⠀⠀⢀⣼⡀⣨⣿⡿⠁⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠈⠻⠿⠿⠓⠄⠤⠘⠉⠙⠤⢀⠾⠿⣿⠟⠋ """;*/
    //    System.out.println(asciiArtPikachu);

        Pokemon pikachu = new Pikachu(pikachuAttacks);
        
        
        List<Attack> charmanderAttacks = new ArrayList<>();
        charmanderAttacks.add(new Attack("Ember", 5, "Fire", 5));
        charmanderAttacks.add(new Attack("Metal Claw", 10, "Fire", 5));
        charmanderAttacks.add(new Attack("Tail Whip", 10, "Fire", 5));
        charmanderAttacks.add(new Attack("Rage", 10, "Fire", 5));

        Pokemon charmander = new Charmander(charmanderAttacks);


        List<Attack> squirtleAttacks = new ArrayList<>();
        squirtleAttacks.add(new Attack("Water Gun", 10, "Water", 5));
        squirtleAttacks.add(new Attack("Bubble", 10, "Water", 5));
        squirtleAttacks.add(new Attack("Tackle", 5, "Water", 5));
        squirtleAttacks.add(new Attack("Withdraw", 15, "Water", 5));

        Pokemon squirtle = new Squirtle(squirtleAttacks);


        List<Attack> bulbasaurAttacks = new ArrayList<>();
        bulbasaurAttacks.add(new Attack("Vine Whip", 10, "Grass", 5));
        bulbasaurAttacks.add(new Attack("Tackle", 5, "Grass", 5));
        bulbasaurAttacks.add(new Attack("Growl", 15, "Grass", 5));
        bulbasaurAttacks.add(new Attack("Razor Leaf", 10, "Grass", 5));

        Pokemon bulbasaur = new Bulbasaur(bulbasaurAttacks);


        List<Pokemon> listOfPokemon = new ArrayList<>();
        listOfPokemon.add(pikachu);
        listOfPokemon.add(charmander);
        listOfPokemon.add(squirtle);
        listOfPokemon.add(bulbasaur);

        return listOfPokemon;
    }

    public static void displayPokemonDetails(List<Pokemon> listOfPokemon, int index) {
        System.out.println("Pokemon details:");
        listOfPokemon.get(index).displayDetails();
    }

    public static void changePokemonName(List<Pokemon> pokemons, Scanner scanner) {
        System.out.println("Choose a Pokémon to change its name:");
        for (int i = 0; i < pokemons.size(); i++) {
            System.out.println((i + 1) + ". " + pokemons.get(i).getName());
        }
        int choice = scanner.nextInt();
        System.out.println("Enter the new name:");
        String newName = scanner.next();
        pokemons.get(choice - 1).changeName(newName);
    }

//    public static void hibernatePokemon(List<Pokemon> pokemons, Scanner scanner) {
//        System.out.println("Choose a Pokémon to hibernate:");
//        for (int i = 0; i < pokemons.size(); i++) {
//            System.out.println((i + 1) + ". " + pokemons.get(i).getName());
//        }
//        int choice = scanner.nextInt();
//        pokemons.get(choice - 1).hibernate();
//    }

//    public static void battlePokemon(List<Pokemon> pokemons, Scanner scanner, Bag bag) {
//        System.out.println("Choose your Pokémon to battle with:");
//        for (int i = 0; i < pokemons.size(); i++) {
//            System.out.println((i + 1) + ". " + pokemons.get(i).getName());
//        }
//
//        int userChoice = scanner.nextInt();
//        Pokemon userPokemon = pokemons.get(userChoice - 1);
////        System.out.println(userPokemon.getAsciiArt());
//
//        // Ensure the opponent is different from the chosen Pokémon
//        Pokemon opponent;
//        do {
//            int opponentIndex = (int) (Math.random() * pokemons.size());
//            opponent = pokemons.get(opponentIndex);
//        } while (opponent == userPokemon);  // Keep selecting until it's different
//
//        System.out.println("You are battling against " + opponent.getName() + "!");
//
//        // Implement battle logic (use Bag during battle if needed)
//        boolean battleOver = false;
//        while (!battleOver) {
//            System.out.println(userPokemon.getName() + "'s HP: " + userPokemon.getRemainingHp());
//            System.out.println(opponent.getName() + "'s HP: " + opponent.getRemainingHp());
//
//            // User attacks
//            System.out.println("Choose an attack:");
//            List<Attack> userAttacks = userPokemon.getAttacks();
//            for (int i = 0; i < userAttacks.size(); i++) {
//                System.out.println((i + 1) + ". " + userAttacks.get(i).getName() + " (" + userAttacks.get(i).getRemainingUses() + " uses left)");
//            }
//
//            int attackChoice = scanner.nextInt();
//            Attack selectedAttack = userAttacks.get(attackChoice - 1);
//            selectedAttack.useAttack();
//            System.out.println(userPokemon.getName() + " used " + selectedAttack.getName() + "!");
//
//            // Apply attack effect on opponent (adjust damage as needed)
//            opponent.takesDamage(10);  // Example damage
//            if (opponent.getRemainingHp() <= 0) {
//                System.out.println(opponent.getName() + " has fainted! You win the battle!");
//                battleOver = true;
//                break;
//            }
//
//            // Opponent attacks
//            System.out.println(opponent.getName() + " attacks you!");
//            userPokemon.takesDamage(10);  // Example damage
//            if (userPokemon.getRemainingHp() <= 0) {
//                System.out.println(userPokemon.getName() + " has fainted! You lose the battle!");
//                battleOver = true;
//            }
//        }
//    }
//
    

    public static void useBagItems(List<Pokemon> pokemons, Scanner scanner, Bag bag) {
        System.out.println("Choose a Pokémon to heal or cure status:");
        for (int i = 0; i < pokemons.size(); i++) {
            System.out.println((i + 1) + ". " + pokemons.get(i).getName());
        }
        int choice = scanner.nextInt();
        Pokemon selectedPokemon = pokemons.get(choice - 1);

        bag.displayBag();
        System.out.println("1. Use Burn Heal");
        System.out.println("2. Use HP Heal");
        int itemChoice = scanner.nextInt();
//        switch (itemChoice) {
//            case 1:
//                bag.useBurnHeal(selectedPokemon);
//                break;
//            case 2:
//                bag.useHpHeal(selectedPokemon);
//                break;
//            default:
//                System.out.println("Invalid choice.");
//        }
    }
}
