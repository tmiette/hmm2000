package fr.umlv.hmm2000.salesentity.spell;

import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.salesentity.Sellable;

/**
 * This enum defines all the spells which be be sold.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public enum Spell implements Sellable {

  /**
   * This spell enables a unit to teleport itself.
   */
  TELEPORTATION("Teleport", new Price().addResource(Kind.GOLD, 2),
      TeleportationSpellAction.getInstance()),
  /**
   * This spell enables to destroy an obstacle.
   */
  OBSTACLE_DESTRUCTION("Obstacle destruction", new Price().addResource(
      Kind.GOLD, 5), ObstacleDestructionSpellAction.getInstance()),
  /**
   * This spell enables to add an obstacle.
   */
  OBSTACLE_CONSTRUCTION("Obstacle construction", new Price().addResource(
      Kind.GOLD, 10), ObstacleConstructionSpellAction.getInstance());

  private final String name;
  private final Price price;
  private final SpellAction action;

  /**
   * Constructor of a spell.
   * 
   * @param name
   *            the label of the spell.
   * @param price
   *            the price of the spell.
   * @param action
   *            the action performed when the spell is bought.
   */
  private Spell(String name, Price price, SpellAction action) {
    this.name = name;
    this.price = price;
    this.action = action;
  }

  @Override
  public String getLabel() {
    return this.name;
  }

  @Override
  public Price getPrice() {
    return this.price;
  }

  @Override
  public void acquire(Encounter encounter) {
    this.action.perform(encounter);
  }

}
