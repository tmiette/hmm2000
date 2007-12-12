package fr.umlv.hmm2000.astar;

import java.util.List;

/**
 * Interface of a the result object of astar algorithm.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public interface AStarResult<V> {
  /**
   * Returns the cost of the path.
   * 
   * @return the cost of the path.
   */
  public double getPathWeight();

  /**
   * Returns a list of successive vertices in the path.
   * 
   * @return a list of successive vertices in the path.
   */
  public List<V> getPath();

  /**
   * Returns the length of the path.
   * 
   * @return the length of the path.
   */
  public int length();
}
