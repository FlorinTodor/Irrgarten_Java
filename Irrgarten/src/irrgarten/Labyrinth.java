package irrgarten;

import java.util.*;

/**
 *
 * @author flo & gabi
 */
public class Labyrinth {

	private Monster[][] monsters;
	private char [][] labyrinth;
	private Player [][] players;
	private static final char BLOCK_CHAR = 'X';
	private static final char EMPTY_CHAR = '-';
	private static final char MONSTER_CHAR = 'M';
	private static final char COMBAT_CHAR = 'C';
	private static final char EXIT_CHAR = 'E';
	private static final int ROW = 0;
	private static final int COL = 1;
	private int nRows;
	private int nCols;
	private int exitRow;
	private int exitCol;

	public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
			this.nRows = nRows;
			this.nCols = nCols;
			this.exitCol = exitCol;
			this.exitRow = exitRow;
			labyrinth = new char[nRows][nCols];
			monsters = new Monster[nRows][nCols];
			players = new Player[nRows][nCols];

			// INICIALIZAMOS TODAS LAS CASILLAS
			for(int i=0; i< nRows; ++i){
				for(int j=0; j< nCols; ++j){
					labyrinth[i][j]= EMPTY_CHAR;
					monsters[i][j]= null;
					players[i][j]= null;
				}
			}
	}

	// Getter y Setter para la variable nRows
	public int getNRows() {
		return nRows;
	}

	public void setNRows(int nRows) {
		this.nRows = nRows;
	}

	// Getter y Setter para la variable nCols
	public int getNCols() {
		return nCols;
	}

	public void setNCols(int nCols) {
		this.nCols = nCols;
	}

	// Getter y Setter para la variable exitRow
	public int getExitRow() {
		return exitRow;
	}

	public void setExitRow(int exitRow) {
		this.exitRow = exitRow;
	}

	// Getter y Setter para la variable exitCol
	public int getExitCol() {
		return exitCol;
	}

	public void setExitCol(int exitCol) {
		this.exitCol = exitCol;
	}

	public void spreadPlayers(ArrayList<Player> players) {
		for(int i=0; i<players.size(); i++){
			Player p = players.get(i);

			int[] pos = randomEmptyPos();

			putPlayer2D(-1,-1, pos[ROW], pos[COL], p);
		}
	}

	public boolean haveAWinner() {
		//Comprueba si hay algún jugador en esa posición, si es null significa que no hay jugador
		return players[exitRow][exitCol] != null;
	}
	/*
	Vamos a mostrar todos los elementos que nos encontramos dentro de un laberinto
	Debemos de tener en cuenta que en una misma casilla no podemos tener objetos diferentes.
	 */
	public String toString() {
		String laberinto = "";
		for (int i=0; i< nRows; ++i){
			for (int j=0; j< nCols; ++j){
				if(i == exitRow && j == exitCol){
					laberinto += EXIT_CHAR + " ";
				}
				else{
					laberinto += labyrinth[i][j] + " ";
				}
			}
			laberinto += "\n";
		}
		return laberinto;
	}

	public void addMonster(int row, int col, Monster monster) {
		// Verifica si la posición está dentro del laberinto y está vacía
		if (posOK(row, col) && emptyPos(row, col)) {

			// Anota la presencia del monstruo en el laberinto
			labyrinth[row][col] = MONSTER_CHAR;

			// Guarda la referencia del monstruo en el atributo adecuado
			monsters[row][col] = monster;

			// Indica al monstruo cuál es su posición actual
			monster.setPos(row, col);
		}
	}

	public Monster putPlayer(Directions direction, Player player) {
		int oldRow = player.getRow();
		int oldCol = player.getCol();

		int[] newPos = dir2Pos(oldRow, oldCol, direction);

		Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);

		return monster;
	}

	public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
		int incRow;
		int incCol;
		if(orientation == Orientation.VERTICAL){
			incRow = 1;
			incCol = 0;
		}
		else{
			incRow = 0;
			incCol = 1;
		}

		int row = startRow;
		int col = startCol;

		while( (posOK(row,col)) && (emptyPos(row,col)) && (length > 0)){
			labyrinth[row][col] = BLOCK_CHAR;

			length -= 1;
			row += incRow;
			col += incCol;
		}
	}

	public ArrayList<Directions> validMoves(int row, int col) {
		ArrayList<Directions> output = new ArrayList<Directions>();

		if(canStepOn(row+1,col)){
			output.add(Directions.DOWN);
		}

		if(canStepOn(row-1,col)){
			output.add(Directions.UP);
		}

		if(canStepOn(row, col+1)){
			output.add(Directions.RIGHT);
		}

		if(canStepOn(row,col-1)){
			output.add(Directions.LEFT);
		}

		return output;
	}

	private boolean posOK(int row, int col) {
		//DEVUELVE TRUE SI SE CUMPLE
		return row >= 0 && row < nRows && col >= 0 && col < nCols;

	}

	private boolean emptyPos(int row, int col) {
		//Primero comprobamos si es una posición dentro del laberinto
			if (posOK(row, col) ) {
				//SI NO HAY NADA EN ESA POSICIÓN ESTARÁ VACIO
				return labyrinth[row][col] == EMPTY_CHAR && players[row][col] == null && monsters[row][col] == null;
			}
			//Si no se considera ni posición del laberinto return false
			return false;

	}

	private boolean monsterPos(int row, int col) {

		if (posOK(row, col)) {
			//COMPROBAMOS QUE ÚNICAMENTE SE ENCUENTRA UN MONSTER
			return monsters[row][col] != null && players[row][col] == null;
		}
		return false;

	}

	private boolean exitPos(int row, int col) {
		return row == exitRow && col == exitCol;
	}

	private boolean combatPos(int row, int col) {
		if (posOK(row, col)) {
			return monsters[row][col] != null && players[row][col] != null && labyrinth[row][col] == COMBAT_CHAR;
		}
		return false;
	}

	private boolean canStepOn(int row, int col) {
		//COMPROBAMOS QUE PRIMERO SEA UNA POSICIÓN VACIA Y QUE AL MENOS SEA UNO DE LAS TRES COSAS
		return posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col));
	}

	/*
	Este método es llamado cuando un jugador abandona una casilla y se encarga de dejar la casilla que se abandona en el
	estado correcto.
	 */
	private void updateOldPos(int row, int col) {

		if (posOK(row, col)) {
			if (combatPos(row,col)) {
				// Si el estado de la casilla era de combate, cambia a estado de monstruo
				labyrinth[row][col] = MONSTER_CHAR;
			} else {
				// En otro caso, cambia a estado de casilla vacía
				labyrinth[row][col] = EMPTY_CHAR;
			}
		}
	}
	/*
	No es necesario realizar comprobaciones relativas a no generar posiciones fuera del
	laberinto.
	 */
	private int[] dir2Pos(int row, int col, Directions direction) {
		int newRow = row;
		int newCol = col;

		// POR PRIMERA VEZ EN LA VIDA UTILIZO UN SWITCH
		switch (direction) {
			case UP:
				newRow--;
				break;
			case DOWN:
				newRow++;
				break;
			case LEFT:
				newCol--;
				break;
			case RIGHT:
				newCol++;
				break;
		}
		//Creamos un array de int que contenga el valor de nueva columna y el valor de nueva fila
		int [] newPosition = {newRow, newCol};

		return newPosition;
	}

	private int[] randomEmptyPos() {
		int[] position = new int[2];
		int maxIntentos = nRows * nCols; // Límite de intentos para evitar un bucle infinito, este tamaño recorre el laberinto

		for (int i = 0; i < maxIntentos; i++) {
			int randomRow = Dice.randomPos(nRows); // Genera una fila aleatoria
			int randomCol = Dice.randomPos(nCols); // Genera una columna aleatoria

			//Comprobamos que sea una posición vacía
			if (emptyPos(randomRow, randomCol) && randomRow != exitRow && randomCol != exitCol) {
				position[0] = randomRow;
				position[1] = randomCol;
				return position; // Devuelve la posición vacía
			}
		}

		// Si no se encuentra una posición vacía después de un número máximo de intentos, retorna null
		return null;
	}

	private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
		Monster output = null;
		if(canStepOn(row,col)){
			if(posOK(oldRow,oldCol)){
				Player p = players[oldRow][oldCol];

				if(p == player){
					updateOldPos(oldRow,oldCol);
					players[oldRow][oldCol] = null;
				}
			}

			boolean monsterPos = monsterPos(row,col);

			if(monsterPos){
				labyrinth[row][col] = COMBAT_CHAR;
				output = monsters[row][col];
			}
			else{
				char number = player.getNumber();
				labyrinth[row][col] = number;
			}

			players[row][col] = player;
			player.setPos(row,col);
		}

		return output;
	}
}
