package irrgarten;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author flo
 */
public class Weapon {
    //"Esta clase representa las armas que utiliza el jugador en los ataques durante los combates."
    
    
    /*
    ATRIBUTOS DE LA CLASE WEAPON
    */
    private float power = 0.0f; //intensidad o fuerza del personaje
    private int uses = 0; //usos del arma
    
    /*
    CONSTRUCTOR
    */
    public Weapon(float p, int u){
        power = p;
        uses = u;
    }
    
    public float attack(){
        /*
        En el caso de que el personaje tenga usos del arma > 0, se decrementa los usos y se devuelve el valor de intensidad que tiene el personaje
        */
        if (uses > 0){ 
            uses--;
            return power;
        }
        else{
            return 0;
        }
    }
    
    public String toString(){
        
        return "W[" + power+"," + uses + "]";
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    
    
}
