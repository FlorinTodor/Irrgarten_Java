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
    public Weapon(float protection, int uses){
        super(protection,uses);

    }
    
    public float attack(){
        /*
        En el caso de que el personaje tenga usos del arma > 0, se decrementa los usos y se devuelve el valor de intensidad que tiene el personaje
        */
        if (getUses() > 0){
            setUses(getUses()-1);
            return produceEffect();
        }
        else{
            return 0.0f;
        }
    }
    
    public String toString(){
        
        return "W[Power: " + produceEffect()+", Uses:" + getUses() + "]";
    }
    

    
    
    
}
