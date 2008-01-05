package fr.umlv.hmm2000.astar;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.astar.heuristic.AStarHeuristic;
import fr.umlv.hmm2000.astar.priorityqueue.PrioritizedElement;
import fr.umlv.hmm2000.astar.priorityqueue.PriorityQueue;
import fr.umlv.hmm2000.map.graph.Graph;

/**
 * Class to perform astar algorithm.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public class AStar {

  /**
   * This method search the shortest path in a graph to join a start vertex and
   * a end vertex. This method perform astar algorithm. The vertices of generic
   * type V must override equals() and hashCode() methods to be used in this
   * algorithm.
   * 
   * @param graph
   *            the graph.
   * @param start
   *            the start vertex.
   * @param end
   *            the end vertex.
   * @return the shortest path, or null if the end vertex is unreachable.
   */
  public static <V> AStarResult<V> aStarAlgorithm(Graph<V> graph,
      AStarHeuristic<V> h, V start, V end) {

    // List of all astar vertices created during the algorithm
    ArrayList<AStarVertex<V>> vertices = new ArrayList<AStarVertex<V>>();

    // Get the start vertex
    AStarVertex<V> vStart = new AStarVertex<V>(h.heuristicValue(start, end),
        start);
    // Add it in the list of astar vertices
    vertices.add(vStart);

    // Priority queue to represent the open set.
    PriorityQueue open = new PriorityQueue();
    // List to represent closed set.
    ArrayList<AStarVertex<V>> closed = new ArrayList<AStarVertex<V>>();

    // set the cost of start vertex to 0
    vStart.setActualCost(0);

    // Put start vertex in the open set
    open.insert(vStart);

    // while the open set contains vertices
    while (!open.isEmpty()) {

      // get the vertex with minimum cost in the open set
      @SuppressWarnings("unchecked")
      // open set contains just AStarVertex, cast is safe
      AStarVertex<V> currentVertex = (AStarVertex<V>) open.extract();

      // get its generic value
      V currentVertexValue = currentVertex.getValue();

      // if the min vertex is the same as the end vertex, returns the path
      if (currentVertexValue.equals(end)) {
        return new AStarPath<V>(currentVertex);
      }

      // put the min vertex in the closed set
      closed.add(currentVertex);

      // for each successor of the min vertex
      int numberOfSuccessors = graph.numberOfSuccessors(currentVertexValue);
      for (int i = 0; i < numberOfSuccessors; i++) {

        // get the next successor
        V successorValue = graph.getSuccessor(currentVertexValue, i);

        // if the successor vertex is already associated to an astar
        // vertex, get the reference to this astar vertex
        AStarVertex<V> successor = AStarVertex.getAStarVertex(vertices,
            successorValue);

        if (successor == null) {
          // else create the new astar vertex
          successor = new AStarVertex<V>(h.heuristicValue(successorValue, end),
              successorValue);
          // add it in the list of astar vertices
          vertices.add(successor);
        }

        // calculate the new actual cost of the successor
        double gprime = currentVertex.getActualCost()
            + graph.edgeValue(currentVertexValue, successorValue);

        // calculate the new total cost of the successor (actual cost +
        // estimated cost)
        double fprime = gprime + successor.getEstimatedCost();

        // if the successor is already in the open or closed set and its
        // new total cost is higher than its old total cost, jump to the
        // next successor
        if (!((open.contains(successor) || closed.contains(successor)) && (fprime >= successor
            .getTotalCost()))) {

          // set the predecessor of the successor in the shortest path
          successor.setPredecessor(currentVertex);

          // set its new actual cost
          successor.setActualCost(gprime);

          // if the open set contains the successor
          if (open.contains(successor)) {
            // change the value of the successor in the open set
            // (update the heap)
            open.changeValue(successor, successor.getTotalCost());
          } else {
            // if the closed set contains the successor
            if (closed.contains(successor)) {
              // remove it of the set
              closed.remove(successor);
            }
            // put the successor in the open set
            open.insert(successor);
          }
        }
      }
    }

    // cannot find a path to join the start vertex to the end vertex
    return null;
  }

  /**
   * Inner-class representing a vertex which can be used in astar algorithm.
   * 
   * @author Tom MIETTE
   * @author Sébastien MOURET
   * @version 1.0
   */
  static class AStarVertex<V> implements PrioritizedElement {

    /*
     * Predecessor vertex in a AStarPath
     */
    private AStarVertex<V> predecessor;

    /*
     * Total cost to reach the end in a AStarPath
     */
    private double totalCost;

    /*
     * Actual cost to reach the end in a AStarPath
     */
    private double actualCost;

    /*
     * Estimated cost to reach the end in a AStarPath (higher than actual)
     */
    private final double estimatedCost;

    /*
     * Generic encapsulated value associated to this astar vertex
     */
    private final V value;

    /**
     * Constructor of a new astar vertex initialized with default values.
     * 
     * @param estimatedCost
     *            estimated cost to reach end vertex.
     * @param value
     *            generic value encapsulated in this astar vertex.
     */
    public AStarVertex(double estimatedCost, V value) {
      this.actualCost = 0;
      this.estimatedCost = estimatedCost;
      this.totalCost = this.estimatedCost;
      this.predecessor = null;
      this.value = value;
    }

    /**
     * Get the total cost (actual cost + estimated cost) to join the end vertex.
     * 
     * @return the total cost (actual cost + estimated cost) to join the end
     *         vertex.
     */
    public double getTotalCost() {
      return this.totalCost;
    }

    /**
     * Get the actual cost to join the end vertex.
     * 
     * @return the actual cost to join the end vertex.
     */
    public double getActualCost() {
      return this.actualCost;
    }

    /**
     * Set the actual cost to join the end vertex.
     * 
     * @param actualCost
     *            the new actual cost.
     */
    public void setActualCost(double actualCost) {
      this.actualCost = actualCost;
      // update the total cost
      this.totalCost = this.estimatedCost + this.actualCost;
    }

    /**
     * Get the estimated cost to join the end vertex.
     * 
     * @return the estimated cost to join the end vertex.
     */
    public double getEstimatedCost() {
      return this.estimatedCost;
    }

    /**
     * Get the predecessor of the current vertex, or null if there is not.
     * 
     * @return the predecessor of the current vertex, or null if there is not.
     */
    public AStarVertex<V> getPredecessor() {
      return predecessor;
    }

    /**
     * Set the predecessor of the current vertex.
     * 
     * @param predecessor
     *            the predecessor of the current vertex.
     */
    public void setPredecessor(AStarVertex<V> predecessor) {
      this.predecessor = predecessor;
    }

    /**
     * Get the priority value of the vertex.
     * 
     * @return the priority value of the vertex.
     */
    public double getPriorityValue() {
      return this.totalCost;
    }

    /**
     * Get the generic value encapsulated of the vertex.
     * 
     * @return the generic value encapsulated of the vertex.
     */
    public V getValue() {
      return this.value;
    }

    /**
     * Set the priority value of the vertex.
     * 
     * @param priorityValue
     *            the priority value of the vertex.
     */
    public void setPriorityValue(double priorityValue) {
      this.totalCost = priorityValue;
    }

    /**
     * Returns a hash code for this astar vertex.
     * 
     * @return a hash code for the astar vertex.
     */
    @Override
    public int hashCode() {
      return (int) (this.estimatedCost);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     */
    @Override
    public boolean equals(Object o) {
      if (o == null) {
        return false;
      }
      if (!(o instanceof AStarVertex)) {
        return false;
      }

      @SuppressWarnings("unchecked")
      AStarVertex<V> av = (AStarVertex<V>) o;

      return (this.totalCost == av.totalCost
          && this.predecessor == av.predecessor && this.value.equals(av.value));
    }

    /**
     * Returns a string representation of this astar vertex.
     * 
     * @return a string representation of this astar vertex.
     */
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("AStarVertex[actualCost=");
      sb.append(this.actualCost);
      sb.append(", estimatedCost=");
      sb.append(this.estimatedCost);
      sb.append(", totalCost=");
      sb.append(this.totalCost);
      sb.append("]");
      return sb.toString();
    }

    /**
     * Static method to search in a list the astar vertex which encapsulate the
     * generic value of type V. If there is no such vertex, this methode returns
     * null;
     * 
     * @param vertices
     *            the list of astar vertices.
     * @param value
     *            the generic value to search.
     * @return the astar vertex which encapsulate the generic value, or null.
     */
    public static <V> AStarVertex<V> getAStarVertex(
        List<AStarVertex<V>> vertices, V value) {
      for (AStarVertex<V> vertex : vertices) {
        if (vertex.getValue().equals(value)) {
          return vertex;
        }
      }
      return null;
    }
  }

}
