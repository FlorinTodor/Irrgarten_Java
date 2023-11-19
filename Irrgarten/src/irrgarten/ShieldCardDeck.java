package irrgarten;
import java.util.ArrayList;
import java.util.Collections;

public class ShieldCardDeck extends CardDeck<Shield> {

    @Override // Lo usamos para que el compilador nos avise en caso de error
    public void addCards(ArrayList<Shield> cards) {
        for (Shield card : cards) {
            addCard(card);
        }
    }

    @Override
    protected void addCard(Shield card) {
        getCardDeck().add(card);
    }
}