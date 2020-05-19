import java.util.ArrayList;
// this creates the healing items the player uses
public class Heal extends Entity{

    private int regen;
    private boolean used;

    public Heal (String name, int regen){
        super(name);
        this.regen = regen;
        used = false;
    }

    public String getName() {
        return this.name;
    }

    public void setNameToUsed (){ this.name = this.name + " [Used]"; }

    public int getRegen() {
        return this.regen;
    }

    public boolean getUsed (){
        return used;
    }

    public void useItem(){
        used = true;
        setNameToUsed();
    }
    
    //this methid overrides the one in the superclass, it returns the string that should be printed to display the healing item 
    @Override
    public String getPrintingInfo (){
        return "HEAL ITEM: "+name+" Regen("+regen+")";
    }
    
    //this method overrides the one in class Entity
    @Override
    public void swapItem (Player player, ArrayList<Cell> cellMap){ 
        int playerLocation = player.getLocation();
        
        Entity temp = player.getHeal();
        player.setHeal(this);
        cellMap.get(playerLocation).setItem(temp);
        
        System.out.println("You swapped your healing item with the one in the cell");

    }

}
