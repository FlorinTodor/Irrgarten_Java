package irrgarten;

abstract class CombatElement {

    private float effect;
    private int uses;

    public CombatElement(float effect, int uses) {
        this.effect = effect;
        this.uses = uses;
    }

    protected float produceEffect() {
        return this.effect;
    }

    public int getUses(){
        return uses;
    }
    public void setUses(int uses){
        this.uses = uses;
    }

    public boolean discard() {
        return Dice.discardElement(uses);
    }

    public abstract String toString();
}