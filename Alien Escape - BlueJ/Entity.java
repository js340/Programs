import java.util.ArrayList; 
// this is superclass entity
public class Entity {

    protected String name;

    public Entity(String name) {
        this.name = name;
    }

    public String getName (){
        return name;
    }
    
    //this method is overridden by the weapon or heal class
    public String getPrintingInfo (){
        return "";  // leaves area blank if cell is empty
    }
    
    //this method gets overridden from either weapon or heal class
    public void swapItem (Player player, ArrayList<Cell> cellMap){ 
        throw new RuntimeException("Entity class method was not overridden by subclass");
    }

}
