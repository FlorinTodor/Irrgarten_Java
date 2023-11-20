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
	@Override
	public float attack() {
		return Dice.intensity(getStrength());
	}

	@Override
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
}