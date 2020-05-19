 // creates aliens of type Reptilian

public class Reptilian extends Organism{

    public Weapon weapon;

    public Reptilian(String name, int health, Weapon w) {
        super(name, health);
        weapon = w;
    }

    public Weapon getWeapon (){
        return weapon;
    }
    // this method overrides the range of the random variable, to change the probability of the attack getting blocked
    @Override
    public int getRandIntRange(){
        return 7;
    }
    
    // this method overrides the one in Organism if applicable 
    @Override 
    public String getPrintingInfo (){
        return "REPTILIAN: "+name+" Health("+health+")"+" Aliens Weapon: "+this.getWeapon().getName()+" Damage("+this.getWeapon().getDamage()+")";

    }
    
    // this method returns how much damage the aliens weapon does
    @Override
    public int getDamageAmmount (){
        return weapon.getDamage();
    }

}
