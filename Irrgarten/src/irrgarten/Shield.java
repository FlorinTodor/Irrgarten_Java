/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author flo & gabi
 */
public class Shield extends CombatElement {
    
    //Esta clase representa los escudos que utiliza el jugador cuando se defiende de un ataque de un monstruo.

    public Shield(float protection, int uses){
        super(protection,uses);
    }

    public float protect(){
        return produceEffect();
    }
    @Override
    public String toString(){
        
        return "S" + super.toString();
    }
    

}
