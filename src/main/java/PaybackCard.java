class PaybackCard {

  private final int customerId;
  private final String name;
  private int bonusPoints = 0;
  private boolean premiumCustomer = false;
  private boolean validity = true;

  public PaybackCard(final int customerId, final String name) {
    this.customerId = customerId;
    this.name = name;
  }

  public int getCustomerId() {
    return this.customerId;
  }

  public String getName() {
    return this.name;
  }

  public int getBonuspoints() {
    return this.bonusPoints;
  }

  public boolean isPremiumCustomer() {
    return this.premiumCustomer;
  }

  public boolean isValid() {
    return this.validity;
  }

  public int addBonuspoints(int extraPoints) {
    if (extraPoints > 0 && validity == true) {
      this.bonusPoints = this.bonusPoints + extraPoints;
    }
    return this.bonusPoints;
  }

  public boolean setPremiumCustomer() {
    if (this.premiumCustomer == false) {
      this.premiumCustomer = true;
      return false;
    }
    return true;
  }

  public boolean merge(PaybackCard card) {
    boolean success = false;
    if (card.validity == true && this.validity == true
        && card.customerId != this.customerId) {
      this.addBonuspoints(card.bonusPoints);
      card.bonusPoints = 0;
      if (card.premiumCustomer == true) {
        this.setPremiumCustomer();
      }
      card.validity = false;
      success = true;
      return success;
    }
    return success;
  }

  public String toString() {
    String stringRepresentation;
    if (this.bonusPoints == 0) {
      stringRepresentation = String.format("%4d. %s, keine Punkte",
      this.customerId, this.name);
    } else if (this.bonusPoints == 0 && this.premiumCustomer == true) {
      stringRepresentation = String.format("%4d. %s, keine Punkte, Premiumkunde",
      this.customerId, this.name);
    } else if (this.premiumCustomer == true) {
      stringRepresentation = String.format("%4d. %s, %d Punkte, Premiumkunde",
      this.customerId, this.name, this.bonusPoints);
    } else {
      stringRepresentation = String.format("%4d. %s, %d Punkte", this.customerId,
      this.name, this.bonusPoints);
    }
    return stringRepresentation;
  }
}
