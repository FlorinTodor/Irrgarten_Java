package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon> {

    @Override // Lo usamos para que el compilador nos avise en caso de error
    public void addCards() {
        for (int i=0; i<Player.getMaxWeapons(); ++i){
            Weapon weapon = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            addCard(weapon);
        }
    }

    @Override
    protected void addCard(Weapon card) {
        getCardDeck().add(card);
    }

}