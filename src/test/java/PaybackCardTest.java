import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PaybackCardTest {

  private PaybackCard card1;
  private PaybackCard card2;
  private PaybackCard card3;
  private PaybackCard card4;

  /** initCards. */
  @Before
  public void initCards() {
    card1 = new PaybackCard(1, "Helga Meier");
    card2 = new PaybackCard(21, "Helge Schneider");
    card3 = new PaybackCard(321, "Jesus Lizard");
    card3.addBonuspoints(100);
    card4 = new PaybackCard(4321, "Danny Rand");
    card4.addBonuspoints(123);
    card4.setPremiumCustomer();
  }

  @Test
  public void newPaybackCardId1() {
    assertEquals(1, card1.getCustomerId());
  }

  @Test
  public void newPaybackCardId2() {
    assertEquals(21, card2.getCustomerId());
  }

  @Test
  public void newPaybackCardName1() {
    assertEquals("Helga Meier", card1.getName());
  }

  @Test
  public void newPaybackCardName2() {
    assertEquals("Helge Schneider", card2.getName());
  }

  @Test
  public void newPaybackCardBonuspoints1() {
    assertEquals(0, card1.getBonuspoints());
  }

  @Test
  public void newPaybackCardBonuspoints2() {
    assertEquals(0, card2.getBonuspoints());
  }

  @Test
  public void newPaybackCardPremiumCustomer1() {
    assertFalse(card1.isPremiumCustomer());
  }

  @Test
  public void newPaybackCardPremiumCustomer2() {
    assertFalse(card2.isPremiumCustomer());
  }

  @Test
  public void newPaybackCardValid1() {
    assertTrue(card1.isValid());
  }

  @Test
  public void newPaybackCardValid2() {
    assertTrue(card2.isValid());
  }

  @Test
  public void addBonuspoints1() {
    for (int i = 1; i <= 100; i++) {
      final int newPoints = card1.addBonuspoints(1);
      assertEquals(i, newPoints);
    }
  }

  @Test
  public void addBonuspoints2() {
    int sum = 0;
    for (int i = 1; i <= 100; i++) {
      final int newPoints = card1.addBonuspoints(i);
      sum += i;
      assertEquals(sum, newPoints);
    }
  }

  @Test
  public void addBonuspoints3() {
    for (int i = 1; i <= 100; i++) {
      final int newPoints = card1.addBonuspoints(-i);
      assertEquals(0, newPoints);
    }
  }

  @Test
  public void setPremiumCustomer1() {
    boolean result = card1.setPremiumCustomer();
    assertFalse(result);
    assertTrue(card1.isPremiumCustomer());
    assertFalse(card2.isPremiumCustomer());
  }

  @Test
  public void setPremiumCustomer2() {
    card1.setPremiumCustomer();
    boolean result = card1.setPremiumCustomer();
    assertTrue(result);
    assertTrue(card1.isPremiumCustomer());
    assertFalse(card2.isPremiumCustomer());
  }

  @Test
  public void mergeSame() {
    boolean result = card1.merge(card1);
    assertFalse(result);
  }

  @Test
  public void mergeDifferent() {
    boolean result = card1.merge(card2);
    assertTrue(result);
  }

  @Test
  public void merge2and3() {
    final int card2id = card2.getCustomerId();
    final String card2Name = card2.getName();
    boolean result = card2.merge(card3);
    assertTrue(result);
    // card2
    assertEquals(card2id, card2.getCustomerId());
    assertEquals(card2Name, card2.getName());
    assertEquals(100, card2.getBonuspoints());
    assertFalse(card2.isPremiumCustomer());
    assertTrue(card2.isValid());
    // card3
    assertEquals(0, card3.getBonuspoints());
    assertFalse(card3.isValid());
  }

  @Test
  public void merge3and4() {
    final int card3id = card3.getCustomerId();
    final String card3Name = card3.getName();
    boolean result = card3.merge(card4);
    assertTrue(result);
    // card3
    assertEquals(card3id, card3.getCustomerId());
    assertEquals(card3Name, card3.getName());
    assertEquals(223, card3.getBonuspoints());
    assertTrue(card3.isPremiumCustomer());
    assertTrue(card3.isValid());
    // card4
    assertEquals(0, card4.getBonuspoints());
    assertFalse(card4.isValid());
  }

  @Test
  public void merge3and4AndCannotAddPointsto4() {
    final int card3id = card3.getCustomerId();
    final String card3Name = card3.getName();
    boolean result = card3.merge(card4);
    assertTrue(result);
    // card3
    assertEquals(card3id, card3.getCustomerId());
    assertEquals(card3Name, card3.getName());
    assertEquals(223, card3.getBonuspoints());
    assertTrue(card3.isPremiumCustomer());
    assertTrue(card3.isValid());
    // card4
    assertEquals(0, card4.getBonuspoints());
    assertFalse(card4.isValid());
    assertEquals(323, card3.addBonuspoints(100));
    assertEquals(0, card4.addBonuspoints(100));
  }

  @Test
  public void toStringCard1() {
    assertEquals("   1. Helga Meier, keine Punkte", card1.toString());
  }

  @Test
  public void toStringCard2() {
    assertEquals("  21. Helge Schneider, keine Punkte", card2.toString());
  }

  @Test
  public void toStringCard3() {
    assertEquals(" 321. Jesus Lizard, 100 Punkte", card3.toString());
  }

  @Test
  public void toStringCard4() {
    assertEquals("4321. Danny Rand, 123 Punkte, Premiumkunde", card4.toString());
  }

}
