// creates goblins (first viewed in Hopkinsville) objects
public class Hopkinsville extends Organism {
    
    private Weapon weapon;
    
    public Hopkinsville(String name) {
        super(name, 30);
        weapon = new Weapon("Goblin Gun", 15);
    }

    public Weapon getWeapon(){
        return weapon;
    }
    // this method overrides the range of the random variable, to change the probability of the attack getting blocked
    @Override
    public int getRandIntRange(){
        return 3;
    }

    // this method overrides the one in Organism if applicable 
    @Override 
    public String getPrintingInfo (){
        return "HOPKINSVILLE: "+name+" Health("+health+")"+" Aliens Weapon: "+this.getWeapon().getName()+" Damage("+this.getWeapon().getDamage()+")";

    }

    // this method returns how much damage the aliens weapon does
    @Override
    public int getDamageAmmount (){
        return weapon.getDamage();
    }

}
