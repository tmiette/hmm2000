package fr.umlv.hmm2000.salesentity;

import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;

/**
 * This interface represents a sellable element.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface Sellable {

  /**
   * Returns the label of the sellable element.
   * 
   * @return the label of the sellable element.
   */
  public String getLabel();

  /**
   * Returns the price of the sellable element.
   * 
   * @return the price of the sellable element.
   */
  public Price getPrice();

  /**
   * Defines the action when the sellable element is bought.
   * 
   * @param encounter
   *            the encounter event.
   */
  public void acquire(Encounter encounter);

  /**
   * Default sellable item.
   */
  public static final Sellable defaultSellable = new Sellable() {

    @Override
    public String getLabel() {
      return "Nothing";
    }

    @Override
    public Price getPrice() {
      return null;
    }

    @Override
    public void acquire(Encounter encounter) {
      throw new UnsupportedOperationException("This item cannot be bought.");
    }

  };
  
}
