package irrgarten;

import java.util.ArrayList;

public class FuzzyPlayer extends Player{

    //Constructor de copia, utiliza el constructor de copia de PLayer, que a su vez utiliza el constructor de copia de LabyrinthCHaracter
    public FuzzyPlayer(Player other) {super(other);}

    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions movimientoComoUnPlayer = super.move(direction,validMoves);
        Directions movimientoComoUnFuzzyPlayer = Dice.nextStep(movimientoComoUnPlayer,validMoves,getIntelligence());
        return movimientoComoUnFuzzyPlayer;
    }



    @Override
    public float attack(){
        return Dice.intensity(getStrength()) + sumWeapons();
    }

    @Override
    protected float defensiveEnergy(){
        return Dice.intensity(getIntelligence())+sumShields();
    }
    @Override

    public String toString(){ return "FuzzyPlayer :" + super.toString();}
}
