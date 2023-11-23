package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield> {

    public ShieldCardDeck(){
        super();
    }
    @Override // Lo usamos para que el compilador nos avise en caso de error
    public void addCards() {
        for (int i=0; i<Dice.shieldsReward(); ++i){
            Shield shield = new Shield(Dice.shieldPower(), Dice.usesLeft());
            addCard(shield);
        }
    }

}

