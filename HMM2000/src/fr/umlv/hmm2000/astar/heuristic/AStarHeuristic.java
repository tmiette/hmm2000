package fr.umlv.hmm2000.astar.heuristic;

/**
 * Interface of a heuristic which can be used with astar algorithm.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public interface AStarHeuristic<V> {
  /**
   * Returns the value of the heuristic for the start vertex to reach the end
   * vertex.
   * 
   * @param vertex
   *            the vertex.
   * @param end
   *            the vertex to reach.
   * @return the hauristic value.
   */
  public double heuristicValue(V vertex, V end);
}
