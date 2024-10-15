import Pokemon.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokemonGame {

    public static void displayPokemon(List<Pokemon> listOfPokemon) {
        for (int i = 0; i < listOfPokemon.size(); i++) {
            System.out.println((i + 1) + ". " + listOfPokemon.get(i).getName());
        }
    }

    public static void listYourPokemonMenu(List<Pokemon> listOfPokemon) {
        Scanner scanner = new Scanner(System.in);
        boolean back = false;
        int choice;
        while (!back) {
            displayPokemon(listOfPokemon);
            System.out.println((listOfPokemon.size() + 1) + ". back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();


            if (choice > 0 && choice <= listOfPokemon.size()) {
                displayPokemonDetails(listOfPokemon, choice);
                System.out.println("1. Change name\n2. back");
                choice = scanner.nextInt();
                if (choice == 2) {
                    back = true;
                    continue;
                }
                System.out.print("Enter new name for " + listOfPokemon.get(choice).getName() + ": ");
                String newName = scanner.next();
                listOfPokemon.get(choice).changeName(newName);
            }
            back = true;
        }
        scanner.close();
    }

    public static void pokemonGameMenu() {
        Scanner scanner = new Scanner(System.in);

        // Initialize Pokémon and Bag
        List<Pokemon> listOfPokemon = initializePokemons();
        Bag bag = new Bag(3, 5);  // Start with 3 Burn Heal and 5 HP Heal

        System.out.println("\nWelcome to the Pokémon Game!");
        boolean exit = false;
        while (!exit) {
            System.out.println("1. List your Pokémon");
            System.out.println("2. Change Pokémon name");
            System.out.println("3. Hibernate Pokémon");
            System.out.println("4. Battle Pokémon");
            System.out.println("5. View your BAG (use items)");
            System.out.println("6. Run from battle");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listYourPokemonMenu(listOfPokemon);
                    break;
                case 2:
                    changePokemonName(listOfPokemon, scanner);
                    break;
                case 3:
                    hibernatePokemon(listOfPokemon, scanner);
                    break;
                case 4:
                    battlePokemon(listOfPokemon, scanner, bag);
                    break;
                case 5:
                    useBagItems(listOfPokemon, scanner, bag);
                    break;
                case 6:
                    System.out.println("You ran from the battle!");
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting the game...");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        pokemonGameMenu();
    }

    public static List<Pokemon> initializePokemons() {
        List<Attack> pikachuAttacks = new ArrayList<>();
        pikachuAttacks.add(new Attack("Thunderbolt", 10));
        pikachuAttacks.add(new Attack("Quick Attack", 10));
        pikachuAttacks.add(new Attack("Iron Tail", 15));
        pikachuAttacks.add(new Attack("Electro Ball", 5));

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
        Pokemon pikachu = new Pokemon("Pikachu", "Electric", 10, 100, pikachuAttacks);
    //    System.out.println(asciiArtPikachu);

        
        
        List<Attack> charmanderAttacks = new ArrayList<>();
        charmanderAttacks.add(new Attack("Ember", 5));
        charmanderAttacks.add(new Attack("Metal Claw", 10));
        charmanderAttacks.add(new Attack("Tail Whip", 10));
        charmanderAttacks.add(new Attack("Rage", 10));

        Pokemon charmander = new Pokemon("Charmander", "Fire", 8, 90, charmanderAttacks);

        List<Attack> squirtleAttacks = new ArrayList<>();
        squirtleAttacks.add(new Attack("Water Gun", 10));
        squirtleAttacks.add(new Attack("Bubble", 10));
        squirtleAttacks.add(new Attack("Tackle", 5));
        squirtleAttacks.add(new Attack("Withdraw", 15));
        Pokemon squirtle = new Pokemon("Squirtle", "Water", 8, 85, squirtleAttacks);

        List<Attack> bulbasaurAttacks = new ArrayList<>();
        bulbasaurAttacks.add(new Attack("Vine Whip", 10));
        bulbasaurAttacks.add(new Attack("Tackle", 5));
        bulbasaurAttacks.add(new Attack("Growl", 15));
        bulbasaurAttacks.add(new Attack("Razor Leaf", 10));
        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass", 9, 88, bulbasaurAttacks);




        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pikachu);
        pokemons.add(charmander);
        pokemons.add(squirtle);
        pokemons.add(bulbasaur);

        return pokemons;
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

    public static void hibernatePokemon(List<Pokemon> pokemons, Scanner scanner) {
        System.out.println("Choose a Pokémon to hibernate:");
        for (int i = 0; i < pokemons.size(); i++) {
            System.out.println((i + 1) + ". " + pokemons.get(i).getName());
        }
        int choice = scanner.nextInt();
        pokemons.get(choice - 1).hibernate();
    }

    public static void battlePokemon(List<Pokemon> pokemons, Scanner scanner, Bag bag) {
        System.out.println("Choose your Pokémon to battle with:");
        for (int i = 0; i < pokemons.size(); i++) {
            System.out.println((i + 1) + ". " + pokemons.get(i).getName());
        }
    
        int userChoice = scanner.nextInt();
        Pokemon userPokemon = pokemons.get(userChoice - 1);
        System.out.println(userPokemon.getAsciiArt()); 
    
        // Ensure the opponent is different from the chosen Pokémon
        Pokemon opponent;
        do {
            int opponentIndex = (int) (Math.random() * pokemons.size());
            opponent = pokemons.get(opponentIndex);
        } while (opponent == userPokemon);  // Keep selecting until it's different
    
        System.out.println("You are battling against " + opponent.getName() + "!");
    
        // Implement battle logic (use Bag during battle if needed)
        boolean battleOver = false;
        while (!battleOver) {
            System.out.println(userPokemon.getName() + "'s HP: " + userPokemon.getHp());
            System.out.println(opponent.getName() + "'s HP: " + opponent.getHp());
    
            // User attacks
            System.out.println("Choose an attack:");
            List<Attack> userAttacks = userPokemon.getAttacks();
            for (int i = 0; i < userAttacks.size(); i++) {
                System.out.println((i + 1) + ". " + userAttacks.get(i).getName() + " (" + userAttacks.get(i).getRemainingUses() + " uses left)");
            }
    
            int attackChoice = scanner.nextInt();
            Attack selectedAttack = userAttacks.get(attackChoice - 1);
            selectedAttack.useAttack();
            System.out.println(userPokemon.getName() + " used " + selectedAttack.getName() + "!");
    
            // Apply attack effect on opponent (adjust damage as needed)
            opponent.takesDamage(10);  // Example damage
            if (opponent.getHp() <= 0) {
                System.out.println(opponent.getName() + " has fainted! You win the battle!");
                battleOver = true;
                break;
            }
    
            // Opponent attacks
            System.out.println(opponent.getName() + " attacks you!");
            userPokemon.takesDamage(10);  // Example damage
            if (userPokemon.getHp() <= 0) {
                System.out.println(userPokemon.getName() + " has fainted! You lose the battle!");
                battleOver = true;
            }
        }
    }
    
    

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
        switch (itemChoice) {
            case 1:
                bag.useBurnHeal(selectedPokemon);
                break;
            case 2:
                bag.useHpHeal(selectedPokemon);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
