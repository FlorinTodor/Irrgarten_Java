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
			char number = Integer.toString(i).charAt(0);
			Player newPlayer = new Player(number, Dice.randomIntelligence(),Dice.randomStrength());
			players.add(newPlayer);
		}
		currentPlayerIndex = Dice.randomPos(nplayers);
		currentPlayer = players.get(currentPlayerIndex);
		monsters = new ArrayList<Monster>(3); //NO TENEMOS INFORMACIÓN AHORA MISMO
		log ="";
		labyrinth = new Labyrinth(9,15,7,15);//ESTO HABRÁ QUE MODIFICARLO CON NUESTRO DISEÑO
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
		this.log = "";
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
		GameState game = new GameState(labyrinth.toString(),players.toString(),monsters.toString(),Character.getNumericValue(currentPlayer.getNumber()),finished(),log);
		return game;
	}

	private void configureLabyrinth() {
		// Configura el laberinto personalizado
		// Nota: Estoy asumiendo que las dimensiones son 8x15
		// Area
		labyrinth.addBlock(Orientation.HORIZONTAL, 0, 0, 15); // Fila 0
		labyrinth.addBlock(Orientation.VERTICAL,1, 0, 7); // Columna 0
		labyrinth.addBlock(Orientation.HORIZONTAL, 8, 0, 15); // Fila 7
		labyrinth.addBlock(Orientation.VERTICAL,1, 14, 6); // Columna 15

		// Bloques internos
		labyrinth.addBlock(Orientation.HORIZONTAL,2, 2, 3);
		labyrinth.addBlock(Orientation.HORIZONTAL,2, 8, 2);
		labyrinth.addBlock(Orientation.HORIZONTAL,2, 11, 2);
		labyrinth.addBlock(Orientation.HORIZONTAL,3, 9, 1);
		labyrinth.addBlock(Orientation.VERTICAL,3, 12, 2);
		labyrinth.addBlock(Orientation.VERTICAL,4, 11, 1);
		labyrinth.addBlock(Orientation.VERTICAL,4, 13, 1);
		labyrinth.addBlock(Orientation.VERTICAL,4, 5, 1);
		labyrinth.addBlock(Orientation.HORIZONTAL,4, 1, 3);
		labyrinth.addBlock(Orientation.HORIZONTAL,5, 3, 1);
		labyrinth.addBlock(Orientation.HORIZONTAL,6, 2, 2);
		labyrinth.addBlock(Orientation.HORIZONTAL,6, 5, 5);
		labyrinth.addBlock(Orientation.HORIZONTAL,6, 11, 3);
		labyrinth.addBlock(Orientation.HORIZONTAL,7, 7, 1);
		labyrinth.addBlock(Orientation.VERTICAL,2, 6, 3);
		labyrinth.addBlock(Orientation.HORIZONTAL,4, 7, 3);


		Monster ogre = new Monster("Ogre", Dice.randomIntelligence(), Dice.randomStrength());
		Monster vampire = new Monster("Vampire", Dice.randomIntelligence(), Dice.randomStrength());
		Monster demon = new Monster("Demon", Dice.randomIntelligence(), Dice.randomStrength());
		Monster dragon = new Monster("Dragon", Dice.randomIntelligence(), Dice.randomStrength());
		Monster zombie = new Monster("Zombie", Dice.randomIntelligence(), Dice.randomStrength());
		monsters.add(ogre);
		monsters.add(vampire);
		monsters.add(demon);
		monsters.add(dragon);
		monsters.add(zombie);

		labyrinth.addMonster(3, 2, ogre);
		labyrinth.addMonster(2, 10, vampire);
		labyrinth.addMonster(2,5,demon);
		labyrinth.addMonster(5,8,dragon);
		labyrinth.addMonster(7,12,zombie);

		labyrinth.setExitRow(7);
		labyrinth.setExitCol(14);
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

		Directions output = currentPlayer.move(preferredDirection,validMoves);

        return output;
	}

	private GameCharacter combat(Monster monster) {
		int rounds = 0;
		GameCharacter winner = GameCharacter.PLAYER;

		float playerAttack = currentPlayer.attack();

		boolean lose = monster.defend(playerAttack);

		while((!lose) && (rounds < MAX_ROUNDS)){
			winner = GameCharacter.MONSTER;

			float monsterAttack = monster.attack();

			lose = currentPlayer.defend(monsterAttack);


			if(!lose){
				playerAttack = currentPlayer.attack();
				winner = GameCharacter.PLAYER;
				lose = monster.defend(playerAttack);
			}

			rounds++;
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
