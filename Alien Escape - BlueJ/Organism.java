 // this is the superclass for all the actors in the game
import java.util.Random;

public class Organism {

    protected String name;
    protected int health;
    protected boolean alive;

    public Organism (String name, int health){
        this.name = name;
        this.health = health;
        this.alive = true;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean getAlive(){
        return alive;
    }
    
    // this method returns how much damage a alien should do, greys are created from type Organism thus they deal the same ammount of damage as their health
    public int getDamageAmmount (){
        return health;
    }

    public void getAttacked(Player player) {
        int damdam = player.getWeapon().getDamage();

        if (health == 0) {
            System.out.println("The Alien in this room is already dead");
            return;
        }
        int blocked = getRandomNumber(1, this.getRandIntRange());
        if (blocked == 1) {
            System.out.println(name+" blocked your attack!");
            return;
        }
        this.takeDamage(damdam);
    }
    
    // this method gets overridden, it changes the range of values to change the probability that the attack is blocked
    public int getRandIntRange(){
        return 5;
    }

    // This method is overridden by the Player class, because when a alien dies it doesn't end the game, unlike when the
    // player dies. Aliens also need to change their name to have [dead] in it.
    // This method deals the damage amount passed into it to the object it was called upon.
    public void takeDamage(int damage) {
        if ((this.health - damage) <= 0) {
            health = 0;
            alive = false;
            System.out.println("You killed "+name);
            this.setNameToDead();
        } else {
            this.health = this.health - damage;
        }
    }

    // adds [dead] to the end of the aliens name
    private void setNameToDead() {
        this.name = name + " [Dead]";
    }

    // takes in 2 inputs and creates a random number between those numbers, including the numbers that were passed in
    public static int getRandomNumber (int x, int y) {
        if (y <= x)
            throw new IllegalArgumentException("Minimum number must be smaller than Maximum number");
        Random random = new Random();
        return random.nextInt((y - x) + 1) + x;
    }
    
    // this method gets overridden if object is of type subclass
    public String getPrintingInfo (){
        return "GREY: "+name+" Health("+health+")";
    }

}
