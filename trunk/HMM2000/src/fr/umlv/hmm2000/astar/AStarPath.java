package fr.umlv.hmm2000.astar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fr.umlv.hmm2000.astar.AStar.AStarVertex;

/**
 * Class to represent astar path in a graph. Astar path can be used like a basic
 * iterator. The elements are iterated from the start element to the end
 * element.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public class AStarPath<V> implements AStarResult<V> {

  /*
   * List of the vertices in the path.
   */
  private final List<V> path;

  /*
   * Cost of the path.
   */
  private final double cost;

  /**
   * Constructor of an astar path. The path is reprensented by its last vertex.
   * Each vertex get a reference to its predecessor except the first vertex of
   * the path (null reference)
   * 
   * @param end
   *            the last vertex in the astar path.
   */
  public AStarPath(AStarVertex<V> end) {

    // create a reverse list of the vertices
    ArrayList<V> invertPath = new ArrayList<V>();

    // put the last vertex in the list
    invertPath.add(end.getValue());
    AStarVertex<V> predecessor = end.getPredecessor();

    // while a predessor exists
    while (predecessor != null) {
      // put the predecessor in the list
      invertPath.add(predecessor.getValue());
      predecessor = predecessor.getPredecessor();
    }

    this.path = new ArrayList<V>();
    // create the list in the right order
    for (ListIterator<V> it = invertPath.listIterator(invertPath.size()); it
        .hasPrevious();) {
      this.path.add(it.previous());
    }

    // set the cost of the path
    this.cost = end.getActualCost();
  }

  /**
   * Returns the cost of the path.
   * 
   * @return the cost of the path.
   */
  public double getPathWeight() {
    return this.cost;
  }

  /**
   * Returns a list of successive vertices in the path.
   * 
   * @return a list of successive vertices in the path.
   */
  public List<V> getPath() {
    return this.path;
  }

  /**
   * Returns the length of the path.
   * 
   * @return the length of the path.
   */
  public int length() {
    return this.path.size() - 1;
  }

  /**
   * Returns a string representation of the path (used the toString method of
   * the generics elements).
   * 
   * @return a string representation of the path.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("AStarPath[cost=");
    sb.append(this.cost);
    sb.append(", length=");
    sb.append(this.length());
    sb.append(", path=(");
    for (Iterator<V> iterator = this.path.iterator(); iterator.hasNext();) {
      sb.append((iterator.next()));
      if (iterator.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append(")]");
    return sb.toString();
  }

}
