 // creates a player

public class Player extends Organism{

    private int location;
    private Weapon weapon;
    private Heal heal;

    public Player (String name, int health){
        super(name, health);
        weapon = new Weapon("Wooden Stick", 5);
        heal = new Heal("Weak Health Potion", 10);
    }


    public Weapon getWeapon() {
        return this.weapon;
    }

    // used when swapping weapons
    public void setWeapon(Weapon w){
        weapon = w;
    }

    // used when swapping heal items
    public void setHeal(Heal h) {
        heal = h;
    }

    public Heal getHeal() {
        return this.heal;
    }

    public int getLocation() {
        return location;
    }

    public boolean isPlayerAlive() {
        return this.alive;
    }

    // increments location by 1
    public void goForward() {
        this.location++;
    }

    // decreases location by 1
    public void goBackward() {
        this.location--;
    }

    // sets health based on the int passed in
    public void setHealth(int i) {
        health = i;
    }

    // this method overrides the one in the organism class, it will check if the players health has decreased to 0, if
    // so it will print the game over message and end the program.
    public void takeDamage(int damage) {
        if ((health - damage) <= 0) {
            this.setHealth(0);
            gameOver();
        } else {
            this.setHealth(this.getHealth() - damage);
            System.out.println("The alien attacked you back");
        }
    }

    // prints the game over message and ends the program
    private void gameOver() {
        System.out.println("");
        System.out.println("  /$$$$$$   /$$$$$$  /$$      /$$ /$$$$$$$$        /$$$$$$  /$$    /$$ /$$$$$$$$ /$$$$$$$ \n" +
                " /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/       /$$__  $$| $$   | $$| $$_____/| $$__  $$\n" +
                "| $$  \\__/| $$  \\ $$| $$$$  /$$$$| $$            | $$  \\ $$| $$   | $$| $$      | $$  \\ $$\n" +
                "| $$ /$$$$| $$$$$$$$| $$ $$/$$ $$| $$$$$         | $$  | $$|  $$ / $$/| $$$$$   | $$$$$$$/\n" +
                "| $$|_  $$| $$__  $$| $$  $$$| $$| $$__/         | $$  | $$ \\  $$ $$/ | $$__/   | $$__  $$\n" +
                "| $$  \\ $$| $$  | $$| $$\\  $ | $$| $$            | $$  | $$  \\  $$$/  | $$      | $$  \\ $$\n" +
                "|  $$$$$$/| $$  | $$| $$ \\/  | $$| $$$$$$$$      |  $$$$$$/   \\  $/   | $$$$$$$$| $$  | $$\n" +
                " \\______/ |__/  |__/|__/     |__/|________/       \\______/     \\_/    |________/|__/  |__/\n" +
                "                                      you died :(                                  ");
        System.exit(0);
    }
    
    public void printPlayerInformation (){
        Weapon w = this.getWeapon();
        Heal h = this.getHeal();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("NAME: "+name+" Health("+health+")    WEAPON: "+w.getName()+" Damage("+w.getDamage()+")    ITEM: "+h.getName()+" Regen("+h.getRegen()+")");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    
    }

}
