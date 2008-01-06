package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.resource.ResourcesCollection;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;

/**
 * This class represents a player in the game.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 * 
 */
public class Player {

  public static final Player AI = new Player(-1);

  private final int id;

  private final ResourcesCollection resources;

  /**
   * Constructor of the player.
   * 
   * @param id
   *            unique id of the player.
   */
  public Player(int id) {

    this.id = id;
    this.resources = new ResourcesCollection();
  }

  /**
   * Returns the player id.
   * 
   * @return the player id.
   */
  public int getId() {

    return this.id;
  }

  /**
   * Add a resource to the player resources collection.
   * 
   * @param kind
   *            the kind of resource.
   * @param value
   *            the value to add.
   */
  public void addResource(Kind kind, int value) {

    this.resources.addResource(kind, value);
  }

  /**
   * Returns the resources collection of the player.
   * 
   * @return the resources collection of the player.
   */
  public ResourcesCollection getResources() {

    return this.resources;
  }

  /**
   * Returns if the player can spend a price.
   * 
   * @param price
   *            the price.
   * @return if the player can spend a price.
   */
  public boolean spend(Price price) {

    return this.resources.removeAllResources(price.getResourcesList());
  }

  @Override
  public boolean equals(Object o) {

    if (o == null) {
      return false;
    }
    if (!(o instanceof Player)) {
      return false;
    }
    Player p = (Player) o;
    return this.id == p.id;
  }

  @Override
  public String toString() {
    return "Player : " + this.id;
  }
}
