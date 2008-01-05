package fr.umlv.hmm2000.astar.util;

/**
 * Class with only static methods to calculate some mathematic distances.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public class Distances {

  /**
   * Calculates the Manhattan distance between two points.
   * 
   * @param x1
   *            x coordinate of the start point.
   * @param y1
   *            y coordinate of the start point.
   * @param x2
   *            x coordinate of the end point.
   * @param y2
   *            y coordinate of the start point.
   * @return the Manhattan distance between the two points.
   */
  public static double manhattanDistance(double x1, double y1, double x2,
      double y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  }

  /**
   * Calculates the Euclidean distance between two points.
   * 
   * @param x1
   *            x coordinate of the start point.
   * @param y1
   *            y coordinate of the start point.
   * @param x2
   *            x coordinate of the end point.
   * @param y2
   *            y coordinate of the start point.
   * @return the Euclidean distance between the two points.
   */
  public static double euclideanDistance(double x1, double y1, double x2,
      double y2) {
    return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
  }
}
