
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {

    private static Scanner in = new Scanner(System.in);

    private char readChar() {
        String s = in.nextLine();
        return s.charAt(0);
    }


    public Directions nextMove() {
        System.out.print("Where? ");

        Directions direction = Directions.DOWN;
        boolean gotInput = false;

        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;
                    break;
            }
        }
        return direction;
    }

    public void showGame(GameState gameState) {
        // Mostrar información del laberinto
        System.out.println("Laberinto:");
        System.out.println(gameState.getLabyrinth());

        // Mostrar información de los jugadores
        System.out.println("\nJugadores:");
        System.out.println(gameState.getPlayers());

        // Mostrar información de los monstruos
        System.out.println("\nMonstruos:");
        System.out.println(gameState.getMonsters());

        // Mostrar información del jugador actual
        System.out.println("\nTurno del Jugador: " + gameState.getCurrentPlayer());

        // Mostrar si hay un ganador
        if (gameState.getWinner()) {
            System.out.println("¡Felicidades! ¡Has ganado!");
        }

        // Mostrar el registro (log) del juego
        System.out.println("\nRegistro del Juego:");
        System.out.println(gameState.getLog());

    }

}
