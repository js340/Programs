//this is where the main program resides
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    private static ArrayList<Cell> cellMap;
    private static Player player;
    private boolean gameWon;

    // constructor method which creates the map and player objects
    public Program(){
        printStartingMessage();
        cellMap = createNewCellMap();
        player = createNewPlayer();
        gameWon = false;
    }

    // main code which loops as the game runs, player chooses a option and we call the corresponding method.
    public void game (){

        while (player.isPlayerAlive()) {

            printTotalMenu();
            String choice = getString("What would you like to do?");
            System.out.println("\n");

            if (choice.equals("1") || choice.equals("a") || choice.equals("A")){
                attackAlien();

            } else if (choice.equals("2") || choice.equals("p") || choice.equals("P")){
                pickUpItem();

            } else if (choice.equals("3") || choice.equals("u") || choice.equals("U")){
                useItem();

            } else if (choice.equals("4") || choice.equals("w") || choice.equals("W")){
                goForward();

            } else if (choice.equals("5") || choice.equals("s") || choice.equals("S")){
                goBackward();

            } else {
                System.out.println("Please Choose a number/letter from the options provided");
            }
        }
    }

    // this method makes alien take damage, then if aliens still alive it will deal damage to player
    private void attackAlien() {
        Organism alien = cellMap.get(player.getLocation()).getOrganism(); 
        System.out.println("You attacked "+alien.getName());

        alien.getAttacked(player);

        if (alien.getAlive()) {
            player.takeDamage(alien.getDamageAmmount());
        }

        if (checkIfAllAliensDead()) {
            gameWon();
        }
    }

    // this method checks if all the aliens are dead
    private boolean checkIfAllAliensDead() {
        //check if all aliens dead, if they are print game won
        for (Cell cell : cellMap) {
            if (cell.getOrganism().getAlive()) {
                return false;
            }
        }
        return true;
    }

    // this adds regen value to player health and makes item unusable and changes items name to indicate its unusable
    private void useItem() {
        boolean used = player.getHeal().getUsed();
        int regenAmmount = player.getHeal().getRegen();
        if (used) {
            System.out.println("This item has already been used, you can not use it again");
        } else if (player.getHealth() == 100) {
            System.out.println("You already have full health, you can not use the item");
        } else {
            if ((player.getHealth()+regenAmmount) >= 100){
                player.setHealth(100);
            } else {
                player.setHealth(player.getHealth() + regenAmmount);
            }
            player.getHeal().useItem();
            System.out.println("You have used your Heal Item and regained health");
        }
    }

    // used to swap items from inventory and the cell
    private void pickUpItem() {
        int playerLocation = player.getLocation();
        Cell currentCell = cellMap.get(playerLocation);
        Entity roomItem = currentCell.getItem();

        roomItem.swapItem(player, cellMap);

    }

    // moves player back a cell
    private void goBackward() {
        int playerLocation = player.getLocation();
        if (playerLocation == 0){
            System.out.println("You have reached the start of the Cells. You cannot go further back");
        } else {
            System.out.println("You have gone to the previous cell");
            player.goBackward();
        }
    }

    // moves player forward a cell
    private void goForward() {
        int playerLocation = player.getLocation();
        if (playerLocation == (cellMap.size()-1)){
            System.out.println("You have reached the end of the Cells. You cannot go any further");
        } else {
            System.out.println("You have gone to the next cell");
            player.goForward();
        }
    }

    //this calls methods which print the players stats row, room stats row and options
    private void printTotalMenu() {
        printPlayerInfo();
        printCellInfo();
        printOptions();
    }

    // this prints the options available to the player
    private static void printOptions() {
        System.out.println("1/A ) Attack Alien");
        System.out.println("2/P ) Pick up Item");
        System.out.println("3/U ) Use Item");
        System.out.println("4/W ) Go to next Cell");
        System.out.println("5/S ) Go to Previous Cell");
    }

    // this gets the updated player info and prints it
    public static void printPlayerInfo (){
        player.printPlayerInformation();
    }

    // prints the updated values of all the variables in the cell object, including the aliens type and weapon if applicable
    private void printCellInfo() {
        int location = player.getLocation();
        Cell cell = cellMap.get(location);
        Organism c = cell.getOrganism(); //c can be either a grey or a reptilian
        Entity e = cell.getItem(); //h can be either a weapon or a heal item

        String cLabel = c.getPrintingInfo();

        String iLabel = e.getPrintingInfo();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("CELL: "+cell.getName()+"    "+cLabel+"    "+iLabel);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // creates a new player, ensures that there is a name inputted and user hasnt left it blank
    private static Player createNewPlayer (){
        boolean emptyname = true;
        String playerName = "";
        while (emptyname) {
            playerName = getString("What is your name?");
            if (!playerName.isEmpty()){
                emptyname = false;
            }
        }
        return new Player(playerName, 100);
    }

    // returns string user input
    private static String getString (String m) {
        System.out.println(m);
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    // this method prints the start message
    private void printStartingMessage() {
        System.out.println("\n" +
            "  /$$$$$$  /$$       /$$$$$$ /$$$$$$$$ /$$   /$$                      /$$$$$$$$  /$$$$$$   /$$$$$$   /$$$$$$  /$$$$$$$  /$$$$$$$$      \n" +
            " /$$__  $$| $$      |_  $$_/| $$_____/| $$$ | $$                     | $$_____/ /$$__  $$ /$$__  $$ /$$__  $$| $$__  $$| $$_____/      \n" +
            "| $$  \\ $$| $$        | $$  | $$      | $$$$| $$                     | $$      | $$  \\__/| $$  \\__/| $$  \\ $$| $$  \\ $$| $$            \n" +
            "| $$$$$$$$| $$        | $$  | $$$$$   | $$ $$ $$                     | $$$$$   |  $$$$$$ | $$      | $$$$$$$$| $$$$$$$/| $$$$$         \n" +
            "| $$__  $$| $$        | $$  | $$__/   | $$  $$$$                     | $$__/    \\____  $$| $$      | $$__  $$| $$____/ | $$__/         \n" +
            "| $$  | $$| $$        | $$  | $$      | $$\\  $$$                     | $$       /$$  \\ $$| $$    $$| $$  | $$| $$      | $$            \n" +
            "| $$  | $$| $$$$$$$$ /$$$$$$| $$$$$$$$| $$ \\  $$                     | $$$$$$$$|  $$$$$$/|  $$$$$$/| $$  | $$| $$      | $$$$$$$$      \n" +
            "|__/  |__/|________/|______/|________/|__/  \\__/                     |________/ \\______/  \\______/ |__/  |__/|__/      |________/      \n" +

            "                                                                                                                                          \n");

        System.out.println("PLOT: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("You are trapped in a alien prison, and your objective is to kill all the aliens. Each cell contains 1 alien and 1 item, either a weapon or healing \n" +
            "item, there are 21 cells in total. You can use either the numbers or letters assigned to each option (uppercase is not needed). ");
        System.out.println("PLAYER: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("The first bar shows your health and inventory. Your inventory can hold 1 weapon and 1 healing item, you can swap your inventory item with the \n" +
            "corresponding in the cell. The second bar shows the cell you're in, the alien in the cell and the item in it. The items have a value next to them to\n" +
            "show you how much damage or health regen they do.");
        System.out.println("ALIEN: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("There are 3 types of aliens in the prison, a Grey which does the same amount of damage as their health, a Reptilian which holds any weapon and a \n" +
            "Hopkinsville which is a goblin that always holds the same gun. This means that as Greys health diminishes, so does their attack damage, however, \n" +
            "reptilians will deal as much damage as their weapon does and the Hopkinsville will always deal the same damage. You will first attack the alien, \n" +
            "then, if it survives it will attack you back. Greys will deal damage equal to their health after you've completed your attack. Hopkinsville are \n" +
            "the best at blocking your attacks, followed by the Reptilians and the greys");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LET THE GAME BEGIN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // if all aliens are dead, we print game won and end the game
    private void gameWon() {
        System.out.println(" /$$      /$$       /$$$$$$       /$$   /$$       /$$   /$$       /$$$$$$$$       /$$$$$$$        /$$\n" +
            "| $$  /$ | $$      |_  $$_/      | $$$ | $$      | $$$ | $$      | $$_____/      | $$__  $$      | $$\n" +
            "| $$ /$$$| $$        | $$        | $$$$| $$      | $$$$| $$      | $$            | $$  \\ $$      | $$\n" +
            "| $$/$$ $$ $$        | $$        | $$ $$ $$      | $$ $$ $$      | $$$$$         | $$$$$$$/      | $$\n" +
            "| $$$$_  $$$$        | $$        | $$  $$$$      | $$  $$$$      | $$__/         | $$__  $$      |__/\n" +
            "| $$$/ \\  $$$        | $$        | $$\\  $$$      | $$\\  $$$      | $$            | $$  \\ $$          \n" +
            "| $$/   \\  $$       /$$$$$$      | $$ \\  $$      | $$ \\  $$      | $$$$$$$$      | $$  | $$       /$$\n" +
            "|__/     \\__/      |______/      |__/  \\__/      |__/  \\__/      |________/      |__/  |__/      |__/\n" +
            "                                                                                                     ");
        System.out.println("You have successfully killed all the aliens and won the game. Well Done!");
        System.exit(0);
    }
    //SUBSTITUTION PRINCIPLE - Cell constructor takes in type Organism, im passing in Organism, Hopkinsville and Reptilian
    // this method is used to create the entire map of the prison, I can change the values from here
    private static ArrayList<Cell> createNewCellMap () {
        ArrayList<Cell> cellMap = new ArrayList<Cell>();
        cellMap.add(new Cell("Starting Cell",  new Organism("Qhargux", 20),                          makeWeapon(1)));
        cellMap.add(new Cell("Cell 1",         new Reptilian("Scoq'iex", 30, makeWeapon(1)),         makeWeapon(2)));
        cellMap.add(new Cell("Cell 2",         new Hopkinsville("Grin"),                             makeHeal(1)));
        cellMap.add(new Cell("Cell 3",         new Reptilian("Scrignox", 40, makeWeapon(2)),         makeWeapon(3)));
        cellMap.add(new Cell("Cell 4",         new Organism("Chestuds", 20),                         makeHeal(2)));
        cellMap.add(new Cell("Cell 5",         new Reptilian("Ulnar", 50, makeWeapon(3)),            makeWeapon(4)));
        cellMap.add(new Cell("Cell 6",         new Hopkinsville("Dhinea"),                           makeHeal(3)));
        cellMap.add(new Cell("Cell 7",         new Organism("Dacraeks", 35),                         makeWeapon(5)));
        cellMap.add(new Cell("Cell 8",         new Reptilian("Bhehra", 35, makeWeapon(5)),           makeWeapon(6)));
        cellMap.add(new Cell("Cell 9",         new Reptilian("Creeqnaiks", 40, makeWeapon(6)),       makeHeal(4)));
        cellMap.add(new Cell("Cell 10",        new Hopkinsville("Schmebuloock"),                     makeWeapon(7)));
        cellMap.add(new Cell("Cell 11",        new Organism("Vendraiks", 50),                        makeWeapon(8)));
        cellMap.add(new Cell("Cell 12",        new Organism("Khuur'els", 40),                        makeHeal(5)));
        cellMap.add(new Cell("Cell 13",        new Reptilian("Otiks", 45, makeWeapon(7)),            makeWeapon(9)));
        cellMap.add(new Cell("Cell 14",        new Hopkinsville("Nuv'ix"),                           makeWeapon(10)));
        cellMap.add(new Cell("Cell 15",        new Organism("Sain'ar", 40),                          makeHeal(6)));
        cellMap.add(new Cell("Cell 16",        new Reptilian("Phuhru", 30, makeWeapon(9)),           makeWeapon(11)));
        cellMap.add(new Cell("Cell 17",        new Organism("Philzas", 25),                          makeWeapon(12)));
        cellMap.add(new Cell("Cell 18",        new Reptilian("Thanqrins", 30, makeWeapon(10)),       makeHeal(7)));
        cellMap.add(new Cell("Cell 19",        new Organism("Kilku", 25),                            makeWeapon(13)));
        cellMap.add(new Cell("Final Cell",     new Hopkinsville("Feq'uls"),                          makeWeapon(14)));

        return cellMap;
    }

    // this method is called and returns a weapon based on the int passed into it
    private static Weapon makeWeapon(int a){
        if (a==1)
            return new Weapon("Metal Bat", 10);
        else if (a==2)
            return new Weapon("Ray Gun", 25);
        else if (a==3)
            return new Weapon("Dagger", 10);
        else if (a==4)
            return new Weapon("Dark Matter Rifle", 30);
        else if (a==5)
            return new Weapon("Photon Pistol", 20);
        else if (a==6)
            return new Weapon("Gravity Equalizer", 25);
        else if (a==7)
            return new Weapon("Gatling Rifle", 20);
        else if (a==8)
            return new Weapon("Laser Gun", 15);
        else if (a==9)
            return new Weapon("Flux Gun", 15);
        else if (a==10)
            return new Weapon("Anti-Matter Eraser", 30);
        else if (a==11)
            return new Weapon("Plasma Rifle", 25);
        else if (a==12)
            return new Weapon("Plasma Cannon", 30);
        else if (a==13)
            return new Weapon("High-Power Laser Gun", 25);
        else if (a==14)
            return new Weapon("Electron Blaster", 20);

        return null;
    }

    // this method returns a specific heal item based on the int passed in
    private static Heal makeHeal (int a){
        if (a==1)
            return new Heal("G-Fuel", 40);
        else if (a==2)
            return new Heal("Weak Health Potion", 20);
        else if (a==3)
            return new Heal("RedBull", 40);
        else if (a==4)
            return new Heal("Mega Heal Potion", 60);
        else if (a==5)
            return new Heal("Healing Elixir", 50);
        else if (a==6)
            return new Heal("Water Bottle", 20);
        else if (a==7)
            return new Heal("Chug Jug", 40);

        return null;
    }

}
