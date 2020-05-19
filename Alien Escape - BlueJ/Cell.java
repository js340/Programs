 // this creates the cells 

public class Cell extends Entity{

    private Organism organism;
    private Entity item;

    public Cell (String name, Organism c, Entity i){
        super(name);
        organism = c;
        item = i;
    }

    public Organism getOrganism() {
        return this.organism;
    }

    public Entity getItem() { return this.item; }

    public void setItem(Entity e) { item = e; }

}
