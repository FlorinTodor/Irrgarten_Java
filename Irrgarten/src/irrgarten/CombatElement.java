package irrgarten;

public abstract class CombatElement {

    private float effect;
    private int uses;

    public CombatElement(float effect, int uses) {
        this.effect = effect;
        this.uses = uses;
    }

    protected float produceEffect() {
        if (uses > 0){
            uses --;
            return produceEffect();
        }
        else{
            return 0.0f;
        }
    }

    public boolean discard() {
        return Dice.discardElement(uses);
    }

    public  String toString(){
       return  "[Effect: " + effect +", Uses:" + uses+ "]";
    }
}