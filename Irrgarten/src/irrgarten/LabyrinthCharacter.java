package irrgarten;

public abstract class LabyrinthCharacter {
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row = 0;
    private int col = 0;

    public LabyrinthCharacter(String name, float intelligence, float strength, float health){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
    }

    public LabyrinthCharacter(LabyrinthCharacter other){
        this.name = other.name;
        this.intelligence = other.intelligence;
        this.strength = other.strength;
        this.health = other.health;
    }

    public boolean dead(){
        return health <= 0.0f;
    }

    public int getRow() { return row;}

    public void setRow(int row) { this.row = row;}

    public int getCol() { return col;}

    public void setCol(int col) { this.col = col;}

    protected float getIntelligence() { return intelligence; }

    public void setIntelligence(float intelligence){this.intelligence = intelligence;}

    protected float getStrength() { return strength;}

    public void setStrength(float strength){this.strength = strength;}

    protected float getHealth() { return health;}

    protected void setHealth(float health) { this.health = health;}

    public void setName(String name) { this.name = name;}

    public String getName() { return name;}

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public abstract String toString();

    protected void gotWounded(){
        health--;
    };

    public abstract float attack();

    public abstract boolean defend(float attack);
}