package irrgarten;

/**
 *
 * @author flo & gabi
 */
public class Monster extends LabyrinthCharacter {

	private static final int INITIAL_HEALTH = 5;

	public Monster(String name, float intelligence, float strength) {
		super(name, intelligence, strength, INITIAL_HEALTH);
	}

	public float attack() {
		return Dice.intensity(getStrength());
	}

	//Proxima practica
	public boolean defend(float receivedAttack) {
		boolean isDead = dead();
		if (!isDead) {
			float defensiveEnergy = Dice.intensity(getIntelligence());

			if (defensiveEnergy < receivedAttack) {
				gotWounded();
				isDead = dead();
			}
		}
		return isDead;
	}

	public String toString() {
		return "Name: " + name + ", Intelligence: " + intelligence + ", Strength: " + strength +
				", Health: " + health + ", Row: " + row + ", Col: " + col + "\n";
	}
}