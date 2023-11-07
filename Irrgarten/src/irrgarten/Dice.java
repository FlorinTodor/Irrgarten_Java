    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.Random;

/**
 *
 * @author flo
 */
public class Dice {
    /*
    ARIBUTOS
    */
    private static final int MAX_USES = 5; //(número máximo de usos de armas y escudos)
    private static final float MAX_INTELLIGENCE = 10.0f ; //(valor máximo para la inteligencia de jugadores y monstruos)
    private static final float MAX_STRENGTH = 10.0f ; //(valor máximo para la fuerza de jugadores y monstruos)
    private static final double RESURRECT_PROB = 0.3; // (probabilidad de que un jugador sea resucitado en cada turno)
    private static final int WEAPONS_REWARD = 2; // (numero máximo de armas recibidas al ganar un combate)
    private static final int SHIELDS_REWARD = 3; // (numero máximo de escudos recibidos al ganar un combate)
    private static final int HEALTH_REWARD = 5; // (numero máximo de unidades de salud recibidas al ganar un combate)
    private static final int MAX_ATTACK = 3; // (máxima potencia de las armas)
    private static final int MAX_SHIELD = 2; // (máxima potencia de los escudos)
    private static Random generator = new Random();
    
    /*
    METODOS
    */
  

    // Método para generar un número de fila o columna aleatoria entre 0 y max
    //usamos el +1 debido a que se genera aleatoriamente desde 0 hasta n-1
    public static int randomPos(int max) {
        // Genera un número aleatorio entre 0 (inclusive) y max (inclusive)
        return generator.nextInt(max);
    }

    // Método para determinar qué jugador comenzará la partida
    //usamos el +1 debido a que se genera aleatoriamente desde 0 hasta n-1
    public static int whoStarts(int nplayers) {
        return generator.nextInt(nplayers);
    }

    // Método para generar un valor aleatorio de inteligencia
    //usamos el * porque nextFloat() devuelve entre 0.0 y 1.0
    public static float randomIntelligence() {
        return generator.nextFloat() * MAX_INTELLIGENCE;
    }

    // Método para generar un valor aleatorio de fuerza
    //usamos el * porque nextFloat() devuelve entre 0.0 y 1.0
    public static float randomStrength() {
        return generator.nextFloat() * MAX_STRENGTH;
    }
    
    // Método para determinar si un jugador muerto debe ser resucitado
    //usamos el * porque nextFloat() devuelve entre 0.0 y 1.0
    public static boolean resurrectPlayer(){
         return generator.nextFloat() <= RESURRECT_PROB;
    }
    
    // Método para indicar la cantidad de armas que recibirá el jugador por ganar el combate
    //usamos el +1 debido a que se genera aleatoriamente desde 0 hasta n-1
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD);
    }
    
    // Método para indicar la cantidad de escudos que recibirá el jugador por ganar el combate
    //usamos el +1 debido a que se genera aleatoriamente desde 0 hasta n-1
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD);
    }
    
    // Método para indicar la cantidad de unidades de salud que recibirá el jugador por ganar el combate
    //usamos el +1 debido a que se genera aleatoriamente desde 0 hasta n-1
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD);
    }
    
    // Método para generar un valor aleatorio en el intervalo [0, MAX_ATTACK[
    public static float weaponPower(){
        return generator.nextFloat()* MAX_ATTACK;
    }
    
    // Método para generar un valor aleatorio en el intervalo [0, MAX_SHIELD[
    public static float shieldPower(){
        return generator.nextFloat() * MAX_SHIELD;
    }
    
    // Método para obtener el número de usos para un arma o escudo
    //usamos el +1 debido a que se genera aleatoriamente desde 0 hasta n-1
    public static int usesLeft(){
        return generator.nextInt(MAX_USES);
    }
    
    // Método para calcular la cantidad de competencia aplicada
    //usamos el * porque nextFloat() devuelve entre 0.0 y 1.0
    public static float intensity(float competence){
        return generator.nextFloat() * competence   ;
    }
    // Método para determinar si un elemento debe ser descartado
    //usamos el * porque nextFloat() devuelve entre 0.0 y 1.0
    public static boolean discardElement(int usesLeft){
        
        if (usesLeft == 0){
            return true;
        }
        else if(usesLeft == MAX_USES){
            return false;
        }
        else{
            float probabilidad = (float) usesLeft / MAX_USES;
            return generator.nextFloat() > probabilidad;
        }
        
    }
    
    
    
    }
