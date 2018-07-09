import java.util.ArrayList;
import java.util.List;

class PaybackCards {

  private final List<PaybackCard> cards = new ArrayList<PaybackCard>();

  boolean add(final PaybackCard card) {
    for (PaybackCard c: cards) {
      if (c.getCustomerId() == card.getCustomerId()) {
        return false;
      }
    }
    cards.add(card);
    return true;
  }

  PaybackCard findByCustomerId(final int customerId) {
    for (PaybackCard c: cards) {
      if (c.getCustomerId() == customerId) {
        return c;
      }
    }
    return null;
  }

  int removeInvalidCards() {
    List<PaybackCard> invalidCards = new ArrayList<PaybackCard>();

    int size = cards.size();

    for (PaybackCard c: cards) {
      if (c.isValid() == false) {
        invalidCards.add(c);
      }
    }
    cards.removeAll(invalidCards);
    int newSize = size - cards.size();
    return newSize;
  }
}
