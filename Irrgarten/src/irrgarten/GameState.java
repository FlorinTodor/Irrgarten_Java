/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author flo & gabi
 */
public class GameState {
    private  String labyrinth;
    private  String players;
    private  String monsters;
    private int currentPlayer; //(representa el índice del jugador actual)
    private boolean winner;
    private  String log;

    // Constructor
    public GameState(String labyrinth, String players, String monsters, int currentPlayer, boolean winner, String log) {
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }
   
    // Consultores
    public  String getLabyrinth() {
        return labyrinth;
    }

    public  String getPlayers() {
        return players;
    }

    public  String getMonsters() {
        return monsters;
    }

    public  int getCurrentPlayer() {
        return currentPlayer;
    }

    public  boolean getWinner() {
        return winner;
    }

    public  String getLog() {
        return log;
    }
}

