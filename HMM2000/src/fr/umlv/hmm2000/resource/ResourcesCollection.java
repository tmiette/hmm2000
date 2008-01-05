package fr.umlv.hmm2000.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class represents a collection of resources.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class ResourcesCollection {

  private final HashMap<Resource.Kind, Integer> resources;

  /**
   * Default constructor.
   */
  public ResourcesCollection() {
    // Creates a pair for each kind of resource initialized to 0
    this.resources = new HashMap<Kind, Integer>();
    for (Kind kind : Kind.values()) {
      this.resources.put(kind, 0);
    }
  }

  /**
   * Add a resource with the specified value to this collection.
   * 
   * @param kind
   *            the kind of the resource.
   * @param value
   *            the value of the resource.
   */
  public void addResource(Kind kind, int value) {
    int currentValue = this.resources.get(kind);
    currentValue += value;
    this.resources.put(kind, currentValue);
  }

  /**
   * Returns if the collection contains enough resource value for the kind of
   * resource specified.
   * 
   * @param kind
   *            the kind of the resource.
   * @param value
   *            the value required.
   * @return if the collection contains enough resource.
   */
  public boolean hasEnoughResource(Kind kind, int value) {
    int currentValue = this.resources.get(kind);
    if (currentValue >= value) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes the resource value to the collection for the specified kind of the
   * collection has enough resources.
   * 
   * @param kind
   *            the kind of the resource.
   * @param value
   *            the value required.
   * @return if the remove operation success.
   */
  public boolean removeResource(Kind kind, int value) {
    if (this.hasEnoughResource(kind, value)) {
      this.resources.put(kind, this.resources.get(kind) - value);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes a list of resources values to the collection for the specified
   * kinds of the collection has enough resources.
   * 
   * @param list
   *            the list of resources.
   * @return if the remove operation success.
   */
  public boolean removeAllResources(List<Pair<Kind, Integer>> list) {
    boolean removable = true;
    for (Pair<Kind, Integer> pair : list) {
      if (!hasEnoughResource(pair.getFirstElement(), pair.getSecondElement())) {
        removable = false;
      }
    }

    if (removable) {
      for (Pair<Kind, Integer> pair : list) {
        this.resources.put(pair.getFirstElement(), this.resources.get(pair
            .getFirstElement())
            - pair.getSecondElement());
      }
    }
    return removable;
  }

  /**
   * Converts the collection to a list of pair associating the resource kind and
   * the resource value.
   * 
   * @return the list.
   */
  public List<Pair<Kind, Integer>> asList() {
    ArrayList<Pair<Kind, Integer>> l = new ArrayList<Pair<Kind, Integer>>();
    for (Kind kind : Kind.values()) {
      l.add(new Pair<Kind, Integer>(kind, this.resources.get(kind)));
    }
    return l;
  }

  /**
   * Converts the collection to a list of pair associating the resource kind and
   * the resource value only if the resource value is positive.
   * 
   * @return the list.
   */
  public List<Pair<Kind, Integer>> notNullResourceList() {
    ArrayList<Pair<Kind, Integer>> l = new ArrayList<Pair<Kind, Integer>>();
    for (Kind kind : Kind.values()) {
      int value = this.resources.get(kind);
      if (value > 0) {
        l.add(new Pair<Kind, Integer>(kind, value));
      }
    }
    return l;
  }

  @Override
  public String toString() {
    return this.resources.toString();
  }

}
