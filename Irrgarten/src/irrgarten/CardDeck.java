package irrgarten;
import java.util.ArrayList;
import java.util.Collections;

public abstract class CardDeck<T> {

        private ArrayList<T> cardDeck = new ArrayList<T>();

        public CardDeck() {
            cardDeck = new ArrayList<T>();
        }

        protected abstract void addCards();

        protected void addCard(T card){
                this.cardDeck.add(card);
        }

        public  T nextCard(){
                if (cardDeck.isEmpty()) {
                        addCards();
                        Collections.shuffle(cardDeck);
                }
                T objeto = cardDeck.get(0);
                cardDeck.remove(0);
                return objeto;
        }

        protected ArrayList<T> getCardDeck() {
                return cardDeck;
        }
}