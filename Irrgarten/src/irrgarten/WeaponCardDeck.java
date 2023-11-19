package irrgarten;
import java.util.ArrayList;
import java.util.Collections;

public class WeaponCardDeck extends CardDeck<Weapon> {

    @Override // Lo usamos para que el compilador nos avise en caso de error
    public void addCards(ArrayList<Weapon> cards) {
        for (Weapon card : cards) {
            addCard(card);
        }
    }

    @Override
    protected void addCard(Weapon card) {
        getCardDeck().add(card);
    }


}