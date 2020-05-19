import java.util.ArrayList;
// this creates the weapons
public class Weapon extends Entity{

    private int damage;

    public Weapon (String name, int damage){
        super(name);
        this.damage = damage;
    }

    public String getName() { return this.name; }

    public int getDamage() { return this.damage; }

    //this method overrides the one in the subclass, it returns the string which desplays the weapons name and damage
    @Override
    public String getPrintingInfo (){
        return "WEAPON ITEM: "+name+" Damage("+damage+")";
    }
    
    //this method overrides the one in class Entity
    @Override
    public void swapItem (Player player, ArrayList<Cell> cellMap){ 
        int playerLocation = player.getLocation();
        
        Entity temp = player.getWeapon();
        player.setWeapon(this);
        cellMap.get(playerLocation).setItem(temp);
        
        System.out.println("You swapped your weapon with the one in the cell");
        
    }
    
}
