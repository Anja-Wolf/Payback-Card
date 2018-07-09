import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PaybackCardsTest {

  private PaybackCard card1;
  private PaybackCard card2;
  private PaybackCard card3;
  private PaybackCard card4;

  private PaybackCards cards;

  /** initCards. */
  @Before
  public void initCards() {
    card1 = new PaybackCard(1, "Helga Meier");
    card2 = new PaybackCard(21, "Helge Schneider");
    card3 = new PaybackCard(1, "Jesus Lizard");
    card3.addBonuspoints(100);
    card4 = new PaybackCard(4321, "Danny Rand");
    card4.addBonuspoints(123);
    card4.setPremiumCustomer();
    cards = new PaybackCards();
  }

  @Test
  public void addPaybackCards11() {
    assertTrue(cards.add(card1));
    assertFalse(cards.add(card1));
  }

  @Test
  public void addPaybackCards12() {
    assertTrue(cards.add(card1));
    assertTrue(cards.add(card2));
  }

  @Test
  public void addPaybackCards13() {
    assertTrue(cards.add(card1));
    assertFalse(cards.add(card3));
  }

  @Test
  public void findByCustomerId1() {
    assertNull(cards.findByCustomerId(1));
  }

  @Test
  public void findByCustomerId2() {
    cards.add(card1);
    PaybackCard card = cards.findByCustomerId(1);
    assertNotNull(card);
    assertEquals(card1.getCustomerId(), card.getCustomerId());
    assertEquals(card1.getName(), card.getName());
    assertEquals(card1.getBonuspoints(), card.getBonuspoints());
    assertEquals(card1.isPremiumCustomer(), card.isPremiumCustomer());
    assertEquals(card1.isValid(), card.isValid());
  }

  @Test
  public void findByCustomerId3() {
    cards.add(card4);
    PaybackCard card = cards.findByCustomerId(4321);
    assertNotNull(card);
    assertEquals(card4.getCustomerId(), card.getCustomerId());
    assertEquals(card4.getName(), card.getName());
    assertEquals(card4.getBonuspoints(), card.getBonuspoints());
    assertEquals(card4.isPremiumCustomer(), card.isPremiumCustomer());
    assertEquals(card4.isValid(), card.isValid());
  }

  @Test
  public void findByCustomerId4() {
    cards.add(card4);
    PaybackCard card = cards.findByCustomerId(4321);
    assertNotNull(card);
    card.addBonuspoints(100);
    assertEquals(card4.getBonuspoints(), card.getBonuspoints());
  }

  @Test
  public void addAndFind100() {
    for (int i = 0; i < 100; i++) {
      cards.add(new PaybackCard(i, "Name"));
    }
    for (int i = 0; i < 100; i++) {
      assertNotNull(cards.findByCustomerId(i));
    }

  }

  @Test
  public void removeNothing() {
    for (int i = 0; i < 100; i++) {
      cards.add(new PaybackCard(i, "Name"));
    }
    assertEquals(0, cards.removeInvalidCards());
  }

  @Test
  public void removeAfterMerge() {
    cards.add(card1);
    assertNotNull(cards.findByCustomerId(card1.getCustomerId()));
    cards.add(card2);
    assertNotNull(cards.findByCustomerId(card2.getCustomerId()));
    card1.merge(card2);
    assertNotNull(cards.findByCustomerId(card2.getCustomerId()));
    assertEquals(1, cards.removeInvalidCards());
    assertNotNull(cards.findByCustomerId(card1.getCustomerId()));
    assertNull(cards.findByCustomerId(card2.getCustomerId()));
    assertEquals(0, cards.removeInvalidCards());
  }

  @Test
  public void remove50() {
    for (int i = 0; i < 100; i++) {
      cards.add(new PaybackCard(i, "Name"));
    }
    for (int i = 0; i < 100; i += 2) {
      cards.findByCustomerId(i).merge(cards.findByCustomerId(i + 1));
    }
    assertEquals(50, cards.removeInvalidCards());
  }

}
