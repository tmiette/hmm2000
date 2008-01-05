package fr.umlv.hmm2000.salesentity;

import java.util.List;

import fr.umlv.hmm2000.resource.ResourcesCollection;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class represents a price for a sellable element.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class Price {

  private final ResourcesCollection resources;

  /**
   * Default constructor.
   */
  public Price() {
    this.resources = new ResourcesCollection();
  }

  /**
   * Add a resources with the specified value to the current price.
   * 
   * @param kind
   *            the kind of the resource.
   * @param value
   *            the value of the resource required.
   * @return the new price.
   */
  public Price addResource(Kind kind, int value) {
    this.resources.addResource(kind, value);
    return this;
  }

  /**
   * Returns the list of resources with theirs values required for the price.
   * 
   * @return the list.
   */
  public List<Pair<Kind, Integer>> getResourcesList() {
    return this.resources.notNullResourceList();
  }

  @Override
  public String toString() {
    return this.resources.toString();
  }

}
