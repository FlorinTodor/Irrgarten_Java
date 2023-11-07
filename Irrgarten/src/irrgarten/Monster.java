package irrgarten;

public class Monster {

	private static final int INITIAL_HEALTH = 5;
	private String name;
	private float intelligence;
	private float strength;
	private float health;
	private int row;
	private int col;

	public Monster(String name, float intelligence, float strength) {
		this.name = name;
		this.intelligence = intelligence;
		this.strength = strength;
	}

	public boolean dead() {
		//Si la salud del monstruo es menor o igual que 0, es que esta muerto
		return health <= 0;
	}

	public float attack() {
		return Dice.intensity(strength);
	}

	//Proxima practica
	public boolean defend(float receivedAttack) {
		throw new UnsupportedOperationException();
	}

	public void setPos(int row, int col) {
		this.col = col;
		this.row = row;
	}

	public String toString() {
		return "Monster [name: " + name + ", intelligence: " + intelligence + ", strength: " + strength +
				", health: " + health + ", row: " + row + ", col: " + col + "]";
	}

	private void gotWounded() {
		health--;
	}
}