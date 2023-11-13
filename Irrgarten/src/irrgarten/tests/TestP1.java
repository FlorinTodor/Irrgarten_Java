/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package irrgarten.tests;
import irrgarten.*;
/**
 *
 * @author flo
 */
public class TestP1 {

    public static void main(String[] args) {

        // Crear instancias de la clase Weapon
        Weapon weapon1 = new Weapon(0.7f, 3);
        Weapon weapon2 = new Weapon(0.3f, 2);
        Weapon weapon3 = new Weapon(0.5f, 1);
        Weapon weapon4 = new Weapon(0.1f, 1);
       
        // Crear instancias de la clase Shield
        Shield Shield1 = new Shield(0.7f, 3);
        Shield Shield2 = new Shield(0.3f, 2);
        Shield Shield3 = new Shield(0.8f, 2);
        Shield Shield4 = new Shield(0.1f, 1);

        // Ejemplo de uso de métodos de la clase WEAPON
        System.out.println("PRUEBAS DE LA CLASE WEAPON: \n");
        System.out.println("\tCOMPROBACION DEL METODO ATTACK DE WEAPON1   : " + weapon1.attack());
        System.out.println("\tCOMPROBACION DEL METODO ATTACK DE WEAPON2   : " + weapon2.attack());
        System.out.println("\tCOMPROBACION DEL METODO DISCARD DE WEAPON3  : " + weapon3.discard());
        System.out.println("\tCOMPROBACION DEL METODO toString DE WEAPON3 : " + weapon3.toString());
        System.out.println("\tCOMPROBACION DEL METODO toString DE WEAPON4 : " + weapon4.toString());

        // Ejemplo de uso de métodos de la clase Shield
        System.out.println("\nPRUEBAS DE LA CLASE SHIELD: \n");
        System.out.println("\tCOMPROBACION DEL METODO PROTECT DE SHIELD1  : " + Shield1.protect());
        System.out.println("\tCOMPROBACION DEL METODO PROTECT DE SHIELD2  : " + Shield2.protect());
        System.out.println("\tCOMPROBACION DEL METODO DISCARD DE SHIELD3  : " + Shield3.discard());
        System.out.println("\tCOMPROBACION DEL METODO toString DE SHIELD3 : " + Shield3.toString());
        System.out.println("\tCOMPROBACION DEL METODO toString DE SHIELD4 : " + Shield4.toString());

        // Ejemplo de uso de los enumerados creados
        System.out.println("\nPRUEBAS DE LOS ENUMERADOS: \n");

        System.out.println("\tCOMPROBACION DEL ENUMERADO Directions      : " + Directions.RIGHT);
        System.out.println("\tCOMPROBACION DEL ENUMERADO GameCharacter   : " + GameCharacter.MONSTER);
        System.out.println("\tCOMPROBACION DEL ENUMERADO Orientation     : " + Orientation.HORIZONTAL);

        //Prueba de la clase GameState:
        System.out.println("\nPRUEBA DE LOS MÉTODOS DE LA CLASE GAMESTATE:\n");
        GameState game = new GameState("laberinto", "jugadores", "mounstruos", 1, true, "eventos");
        System.out.println("\tATRIBUTO LABYRINTH      : " + game.getLabyrinth());
        System.out.println("\tATRIBUTO PLAYERS        : " + game.getPlayers());
        System.out.println("\tATRIBUTO MONSTERS       : " + game.getMonsters());
        System.out.println("\tATRIBUTO CURRENTPLAYER  : " + game.getCurrentPlayer());
        System.out.println("\tATRIBUTO WINNER         : " + game.getWinner());
        System.out.println("\tATRIBUTO LOG            : " + game.getLog());
        

        // Prueba de la clase Dice
       
        System.out.println("\nPRUEBA DE LOS MÉTODOS DE LA CLASE DICE:\n");

        // Llamar a cada método de la clase Dice 100 veces
        int totalCalls = 100;
        for (int i = 1; i <= totalCalls; i++)
        {
            System.out.println("PRUEBA Nº" + i);

            System.out.println("\tCOMPROBACION DEL METODO randomPos          : " + Dice.randomPos(i));
            System.out.println("\tCOMPROBACION DEL METODO whoStarts          : " + Dice.whoStarts(i));
            System.out.println("\tCOMPROBACION DEL METODO randomIntelligence : " + Dice.randomIntelligence());
            System.out.println("\tCOMPROBACION DEL METODO randomStrength     : " + Dice.randomStrength());
            System.out.println("\tCOMPROBACION DEL METODO resurrectPlayer    : " + Dice.resurrectPlayer());
            System.out.println("\tCOMPROBACION DEL METODO weaponsReward      : " + Dice.weaponsReward());
            System.out.println("\tCOMPROBACION DEL METODO shieldsReward      : " + Dice.shieldsReward());
            System.out.println("\tCOMPROBACION DEL METODO usesLeft           : " + Dice.usesLeft());
            System.out.println("\tCOMPROBACION DEL METODO intensity          : " + Dice.intensity(i));
            System.out.println("\tCOMPROBACION DEL METODO discardElement     : " + Dice.discardElement(i));

            System.out.println("-----------------------------------------------");
        }

    }

}
