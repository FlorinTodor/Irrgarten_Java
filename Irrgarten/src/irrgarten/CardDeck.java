package irrgarten;
import java.util.ArrayList;
import java.util.Collections;

public abstract class CardDeck<T> {

        private ArrayList<T> cardDeck;

        public CardDeck() {
            cardDeck = new ArrayList<>();
        }

        protected abstract void addCards(ArrayList<T> cards);

        protected abstract void addCard(T card);

        public  T nextCard(){
                if (cardDeck.isEmpty()) {
                        addCards(cardDeck);
                        Collections.shuffle(cardDeck);
                }
                return cardDeck.remove(0);
        }

        protected ArrayList<T> getCardDeck() {
                return cardDeck;
        }
}

