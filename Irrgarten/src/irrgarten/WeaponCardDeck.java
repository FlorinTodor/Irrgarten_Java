package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon> {

    public WeaponCardDeck(){super();}
    @Override // Lo usamos para que el compilador nos avise en caso de error
    public void addCards() {
        for (int i=0; i<Dice.weaponsReward(); ++i){
            Weapon weapon = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            addCard(weapon);
        }
    }


}