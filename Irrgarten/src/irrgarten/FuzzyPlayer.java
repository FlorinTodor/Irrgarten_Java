package irrgarten;

import java.util.ArrayList;

public class FuzzyPlayer extends Player{

    //Constructor
    public FuzzyPlayer(Player other) {
        super(other);
    }

    //Metodo para copiar las caracteristicas de otro objeto LabyrinthCharacter
    private void copyAttributes(LabyrinthCharacter other) {
        this.setName(other.getName());
        this.setIntelligence(other.getIntelligence());
        this.setStrength(other.getStrength());
        this.setHealth(other.getHealth());
        this.setRow(other.getRow());
        this.setCol(other.getCol());
    }

    //Metodo para copiar las caracteristicas de otro objeto Player
    private void copyAttributes(Player other){
        copyAttributes((LabyrinthCharacter) other);
        this.setWeapons(new ArrayList<>(other.getWeapons()));
        this.setShields(new ArrayList<>(other.getShields()));
        this.setConsecutiveHits(other.getConsecutiveHits());
    }

    // Método para copiar las características de otro objeto FuzzyPlayer
    private void copyAttributes(FuzzyPlayer other) {
        copyAttributes((Player) other);
    }

    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);

        if (size > 0 && !contained){
            Directions firsElement;
            firsElement = validMoves.get(0);
            return firsElement;
        }
        else{
            return direction;
        }
    }

    public float attack(){
        return (this.getStrength()+sumWeapons());
    }

    protected float defensiveEnergy(){
        return this.getIntelligence()+sumShields();
    }

    public String toString(){
        return "Name: " + this.getName() +  ", Intelligence: " + this.getIntelligence() +  ", Strength: " +
                this.getStrength() + ", Health: " + this.getHealth() + ", Row: " + this.getRow() + ", Col: " +
                this.getCol() + ", Weapons: " + this.getWeapons().toString() + ", Shields: " +
                this.getShields().toString()+"\n";
    }
}
