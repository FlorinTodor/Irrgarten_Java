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
        row = 0;
        col = 0;
    }

    public LabyrinthCharacter(LabyrinthCharacter other) {
        this(other.name, other.intelligence, other.strength, other.health);
        this.row = other.row;
        this.col = other.col;
    }

    public boolean dead(){
        return health <= 0.0f;
    }

    public int getRow() { return row;}

    public int getCol() { return col;}

    protected float getIntelligence() { return intelligence; }

    protected float getStrength() { return strength;}

    protected float getHealth() { return health;}

    protected void setHealth(float health) { this.health = health;}

    public String getName() { return name;}

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString(){
        return "Name: " + getName() + ", Intelligence: " + getIntelligence() + ", Strength: " + getStrength() +
                ", Health: " + getHealth() + ", Row: " + getRow() + ", Col: " + getCol() + "\n";
    }

    protected void gotWounded(){
        health--;
    };

    public abstract float attack();

    public abstract boolean defend(float receivedAttack);
}