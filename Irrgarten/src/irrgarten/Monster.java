package irrgarten;

/**
 *
 * @author flo & gabi
 */
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
		this.health = INITIAL_HEALTH;
		this.intelligence = intelligence;
		this.strength = strength;
	}

	public boolean dead() {
		//Si la salud del monstruo es menor o igual que 0, es que esta muerto
		return health <= 0.0f;
	}

	public float attack() {
		return Dice.intensity(strength);
	}

	//Proxima practica
	public boolean defend(float receivedAttack) {
		boolean isDead = dead();
		if (!isDead){
			float defensiveEnergy = Dice.intensity(intelligence);

			if ( defensiveEnergy < receivedAttack){
				gotWounded();
				isDead = dead();
			}
		}
		return isDead;
	}

	public void setPos(int row, int col) {
		this.col = col;
		this.row = row;
	}

	public String toString() {
		return "name: " + name + ", intelligence: " + intelligence + ", strength: " + strength +
				", health: " + health + ", row: " + row + ", col: " + col + "\n";
	}

	private void gotWounded() {
		health--;
	}
}