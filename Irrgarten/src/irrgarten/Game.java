package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author flo & gabi
 */
public class Game {

	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player currentPlayer = new Player('0',Dice.randomIntelligence(), Dice.randomStrength());
	private Labyrinth labyrinth = new Labyrinth(10,20,5,5);
	private static final int MAX_ROUNDS = 10;
	private int currentPlayerIndex;
	private String log;

	public Game(int nplayers) {
		players = new ArrayList<Player>(nplayers);
		for(int i=0; i< nplayers; ++i){
			Player newPlayer = new Player((char) i, Dice.randomIntelligence(),Dice.randomStrength());
			players.add(newPlayer);
		}
		currentPlayerIndex = Dice.randomPos(nplayers);
		currentPlayer = players.get(currentPlayerIndex);
		monsters = new ArrayList<Monster>(5); //NO TENEMOS INFORMACIÓN AHORA MISMO
		log ="";
		labyrinth = new Labyrinth(10,20,5,5);//ESTO HABRÁ QUE MODIFICARLO CON NUESTRO DISEÑO
		configureLabyrinth();
		labyrinth.spreadPlayers(players);
	}
	public static int getMaxRounds() {
		return MAX_ROUNDS;
	}

	public boolean finished() {
		return labyrinth.haveAWinner();
	}

	public boolean nextStep(Directions preferredDirection) {
		boolean dead = currentPlayer.dead();

		if(!dead){
			Directions direction = actualDirection(preferredDirection);

			if(direction != preferredDirection){
				logPlayerNoOrders();
			}

			Monster monster = labyrinth.putPlayer(direction, currentPlayer);

			if(monster == null){
				logNoMonster();
			}
			else{
				GameCharacter winner = combat(monster);
				manageReward(winner);
			}
		}
		else{
			manageResurrection();
		}

		boolean endGame = finished();

		if(!endGame){
			nextPlayer();
		}

		return endGame;
	}

	public GameState getGameState() {
		GameState game = new GameState(labyrinth.toString(),players.toString(),monsters.toString(),currentPlayer.getNumber(),finished(),log);
		return game;
	}

	private void configureLabyrinth() {
		char[][] laberinto = {
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'M', 'X', 'M', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'X', 'M', 'X', 'X', 'X', 'X', 'M', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'X', 'X', 'M', 'E', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'M', 'X', 'X', 'X', 'M', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'X', 'X', 'M', 'X', 'X', 'M', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'X', 'M', 'X', 'X', 'X', 'X', 'M', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};

		// Configura el laberinto
		for (int row = 0; row < labyrinth.getNRows(); row++) {
			for (int col = 0; col < labyrinth.getNCols(); col++) {
				if (laberinto[row][col] == 'M') {
					// Agrega un monstruo en la posición (row, col)
					labyrinth.addMonster(row, col, new Monster("monstruo", Dice.randomIntelligence(), Dice.randomStrength()));
				} else if (laberinto[row][col] == 'E') {
					// Establece la casilla de salida en la posición (row, col)
					labyrinth.setExitRow(row);
					labyrinth.setExitCol(col);
				} else if (laberinto[row][col] == 'X') {
					// Agrega un bloque en la posición (row, col) con orientación aleatoria entre vertical y horizontal

					int orientation = Dice.randomPos(2); // 0 para horizontal, 1 para vertical
					if (orientation == 0) {
						//labyrinth.addBlock(Orientation.HORIZONTAL, row, col, 1);
					} else {
						//labyrinth.addBlock(Orientation.VERTICAL, row, col, 1);
					}
				}
			}
		}
	}

	private void nextPlayer() {
		int totalPlayers = players.size();

		// Incrementa el índice del jugador actual
		//+ 1 para asegurarnos que esté en el rango
		currentPlayerIndex = (currentPlayerIndex + 1) % totalPlayers;

		// Actualiza el jugador actual
		currentPlayer = players.get(currentPlayerIndex);
	}


	private Directions actualDirection(Directions preferredDirection) {
		int currentRow = currentPlayer.getRow();
		int currentCol = currentPlayer.getCol();

		ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow,currentCol);

        return currentPlayer.move(preferredDirection, validMoves);
	}

	private GameCharacter combat(Monster monster) {
		int rounds = 0;
		GameCharacter winner = GameCharacter.PLAYER;

		float playerAttack = currentPlayer.attack();

		boolean lose = monster.defend(playerAttack);

		while((!lose) && (rounds < MAX_ROUNDS)){
			winner = GameCharacter.MONSTER;
			rounds++;

			float monsterAttack = monster.attack();

			lose = currentPlayer.defend(monsterAttack);

			if(!lose){
				playerAttack = currentPlayer.attack();
				winner = GameCharacter.PLAYER;
				lose = monster.defend(playerAttack);
			}
		}

		logRounds(rounds, MAX_ROUNDS);

		return winner;
	}

	private void manageReward(GameCharacter winner) {
		if(winner == GameCharacter.PLAYER){
			currentPlayer.receiveReward();
			logPlayerWon();
		}
		else{
			logMonsterWon();
		}
	}

	private void manageResurrection() {
		boolean resurrect = Dice.resurrectPlayer();

		if(resurrect){
			currentPlayer.resurrect();
			logResurrected();
		}
		else{
			logPlayerSkipTurn();
		}
	}

	private void logPlayerWon() {
		log += "El jugador " + currentPlayer.getNumber() + " ha ganado el combate.\n";
	}

	private void logMonsterWon() {
		log += "El monstruo ha ganado el combate.\n";
	}

	private void logResurrected() {
		log += "El jugador " + currentPlayer.getNumber() + " ha resucitado.\n";
	}

	private void logPlayerSkipTurn() {
		log += "El jugador " + currentPlayer.getNumber() + " ha perdido el turno por estar muerto.\n";
	}

	private void logPlayerNoOrders() {
		log += "El jugador " + currentPlayer.getNumber() + " no ha seguido las instrucciones del jugador humano " +
				"(no fue posible).\n";
	}

	private void logNoMonster() {
		log += "El jugador " + currentPlayer.getNumber() + " se ha movido a una celda vacía o no le ha sido " +
				"posible moverse.\n";
	}

	private void logRounds(int rounds, int max) {
		log += "Se han producido " + rounds + " de " + max + " rondas de combate.\n";
	}
}
