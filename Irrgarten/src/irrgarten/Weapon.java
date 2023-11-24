package irrgarten;

/**
 *
 * @author flo & gabi
 */
public class Weapon extends CombatElement{
    //"Esta clase representa las armas que utiliza el jugador en los ataques durante los combates."
    
    
    /*
    ATRIBUTOS DE LA CLASE WEAPON
    */

    
    /*
    CONSTRUCTOR
    */
    public Weapon(float power, int uses){
        super(power,uses);

    }
    
    public float attack(){
        return produceEffect();
    }
    @Override
    public String toString(){
        
        return "W" + super.toString();
    }
    

    
    
    
}
