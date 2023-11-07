package irrgarten;
public class TestP2{

        public static void main(String[] args) {
            // Crear un juego con 4 jugadores


            // Configurar el laberinto (esto puede ajustarse según tus necesidades)



            System.out.println("Comienza el juego:");

            // Realizar una simulación de juego hasta que haya un ganador o hasta un número máximo de rondas
            for (int round = 1; round <= Game.getMaxRounds(); round++) {
                Game game = new Game(round);
                System.out.println("\nRonda " + round + ":");

                // Mostrar el estado actual del laberinto y de los jugadores
                System.out.println(game.getGameState().getPlayers());

                // Realizar los movimientos de los jugadores y los combates (debes implementar estos métodos en Game)
                // game.nextStep(preferredDirection);

                // Comprobar si el juego ha terminado
                if (game.finished()) {
                    System.out.println("\n¡El juego ha terminado!");
                    break;
                }
            }
        }
}


